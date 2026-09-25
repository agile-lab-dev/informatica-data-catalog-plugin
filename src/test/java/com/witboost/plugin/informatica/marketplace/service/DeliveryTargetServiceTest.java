package com.witboost.plugin.informatica.marketplace.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.marketplace.model.DataCollection;
import com.witboost.plugin.informatica.marketplace.model.DeliveryTargetsResponse;
import com.witboost.plugin.informatica.marketplace.service.client.DataCollectionApiClient;
import com.witboost.plugin.informatica.marketplace.service.client.DeliveryTargetApiClient;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeliveryTargetServiceTest {

    @Mock private DeliveryTargetApiClient deliveryTargetApiClient;

    @Mock private DataCollectionApiClient dataCollectionApiClient;

    @InjectMocks private DeliveryTargetService deliveryTargetService;

    /**
     * Regression: Informatica omits the {@code deliveryTargets} field for collections with no
     * associated targets, so {@link DataCollection#getDeliveryTargets()} returns {@code null}.
     * {@code updateDeliveryTargets} must treat it as an empty list rather than NPE on {@code
     * .stream()}.
     */
    @Test
    void updateDeliveryTargets_collectionWithNullDeliveryTargets_doesNotThrow() {
        String collectionId = "collection-123";
        DataCollection collectionWithNullTargets = new DataCollection();
        collectionWithNullTargets.setDeliveryTargets(null);
        when(dataCollectionApiClient.getDataCollectionById(collectionId, "all"))
                .thenReturn(collectionWithNullTargets);

        DataContract dataContract = new DataContract();
        // deliveryTargets defaults to an empty ArrayList in DataContract

        assertDoesNotThrow(
                () -> deliveryTargetService.updateDeliveryTargets(collectionId, dataContract));

        verify(deliveryTargetApiClient, never()).getAllDeliveryTargets(null, null);
    }

    /**
     * An output port opting out via {@code specific.publishToInformatica: false} produces no
     * delivery target in the DataContract, so on the next provisioning the one already on the
     * Marketplace must be deleted as obsolete. Here every output port opts out, leaving the
     * contract with no delivery target.
     */
    @Test
    void updateDeliveryTargets_targetsMissingFromTheContractAreDeleted() {
        String collectionId = "collection-123";

        DataCollection.DeliveryTarget obsolete = new DataCollection.DeliveryTarget();
        obsolete.setId("dt-obsolete");
        obsolete.setName("dp_test_snowflake_read");
        DataCollection collection = new DataCollection();
        collection.setDeliveryTargets(List.of(obsolete));
        when(dataCollectionApiClient.getDataCollectionById(collectionId, "all"))
                .thenReturn(collection);

        var apiDeliveryTarget =
                new com.witboost.plugin.informatica.marketplace.model.DeliveryTarget();
        apiDeliveryTarget.setId("dt-obsolete");
        apiDeliveryTarget.setName("dp_test_snowflake_read");
        var response = new DeliveryTargetsResponse();
        response.setItems(List.of(apiDeliveryTarget));
        when(deliveryTargetApiClient.getAllDeliveryTargets(null, null)).thenReturn(response);

        DataContract dataContract = new DataContract();
        // No delivery target survives the opt-out filter

        deliveryTargetService.updateDeliveryTargets(collectionId, dataContract);

        verify(deliveryTargetApiClient).deleteDeliveryTarget("dt-obsolete");
    }
}
