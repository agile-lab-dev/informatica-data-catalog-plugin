package com.witboost.plugin.informatica.marketplace.service;

import com.witboost.plugin.informatica.common.exceptions.FailedOperation;
import com.witboost.plugin.informatica.common.exceptions.Problem;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DataAssetMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DataContractMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DeliveryTargetMapper;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.witboost.DataProduct;
import com.witboost.plugin.informatica.common.parser.Parser;
import com.witboost.plugin.informatica.common.service.DescriptorTreeService;
import com.witboost.plugin.informatica.marketplace.exceptions.MarketplacePluginValidationException;
import com.witboost.plugin.informatica.marketplace.openapi.model.ProvisioningRequest;
import com.witboost.plugin.informatica.marketplace.openapi.model.ProvisioningResultRequest;
import com.witboost.plugin.informatica.marketplace.openapi.model.ProvisioningResultResponse;
import com.witboost.plugin.informatica.marketplace.openapi.model.UpdateAclRequest;
import com.witboost.plugin.informatica.marketplace.openapi.model.ValidationError;
import com.witboost.plugin.informatica.marketplace.openapi.model.ValidationResult;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MarketplaceServiceImpl implements MarketplaceService {

    private final DescriptorTreeService descriptorTreeService;

    @Value("${informatica.publish_allowed_environment}")
    private String publishAllowedEnvironment;

    private final ValidatorService<DataProduct> dataProductValidator;

    private final ValidatorService<DataContract> dataContractValidator;

    private final DataContractMapper dataContractMapper;

    private final DeliveryTargetMapper deliveryTargetMapper;

    private final DataAssetMapper dataAssetMapper;

    private final InformaticaDataMarketplaceService informaticaDataMarketplaceService;

    private final MarketplaceCategoryValidator marketplaceCategoryValidator;

    @Autowired
    public MarketplaceServiceImpl(
            ValidatorService<DataProduct> dataProductValidator,
            ValidatorService<DataContract> dataContractValidator,
            DataContractMapper dataContractMapper,
            DeliveryTargetMapper deliveryTargetMapper,
            DataAssetMapper dataAssetMapper,
            InformaticaDataMarketplaceService dataMarketplaceService,
            MarketplaceCategoryValidator marketplaceCategoryValidator,
            DescriptorTreeService descriptorTreeService) {
        this.dataProductValidator = dataProductValidator;
        this.dataContractValidator = dataContractValidator;
        this.dataContractMapper = dataContractMapper;
        this.deliveryTargetMapper = deliveryTargetMapper;
        this.dataAssetMapper = dataAssetMapper;
        this.informaticaDataMarketplaceService = dataMarketplaceService;
        this.marketplaceCategoryValidator = marketplaceCategoryValidator;
        this.descriptorTreeService = descriptorTreeService;
    }

    public MarketplaceServiceImpl(
            ValidatorService<DataProduct> dataProductValidator,
            ValidatorService<DataContract> dataContractValidator,
            DataContractMapper dataContractMapper,
            DeliveryTargetMapper deliveryTargetMapper,
            DataAssetMapper dataAssetMapper,
            InformaticaDataMarketplaceService dataMarketplaceService,
            MarketplaceCategoryValidator marketplaceCategoryValidator) {
        this(
                dataProductValidator,
                dataContractValidator,
                dataContractMapper,
                deliveryTargetMapper,
                dataAssetMapper,
                dataMarketplaceService,
                marketplaceCategoryValidator,
                new DescriptorTreeService(
                        new com.witboost.plugin.informatica.common.config
                                .DescriptorStructureProperties()));
    }

    @Override
    public ProvisioningResultResponse insertProvisioningResults(
            ProvisioningResultRequest
                    provisioningResultRequest) { // see upsertProduct of WitBoostService class in
        // the older version of plugin
        var publishDecision =
                Parser.evaluatePublishToInformatica(
                        provisioningResultRequest.getDescriptor(), publishAllowedEnvironment);
        if (!publishDecision.shouldPublish()) {
            log.info("{}", publishDecision.reason());
            return new ProvisioningResultResponse()
                    .status("COMPLETED")
                    .result(publishDecision.reason());
        }
        descriptorTreeService.parse(provisioningResultRequest.getDescriptor());
        // Failures must propagate as exceptions so the controller returns 4xx/5xx and Witboost
        // marks the operation as failed. Returning HTTP 200 with status "FAILED" makes Witboost
        // show the publish as successful (it keys off the HTTP status, not the body field).
        // Validation errors -> MarketplacePluginValidationException (HTTP 400);
        // any other failure (e.g. upsert) -> RuntimeException (HTTP 500). See
        // MarketplacePluginExceptionHandler.
        var dataProduct = parseAndValidateDataProduct(provisioningResultRequest);
        var dataContract = mapAndValidateDataContract(dataProduct);
        // Category may be missing at provision time (validate skipped, or removed since): surface
        // it
        // as a 400 instead of a generic 500 thrown from deep inside the upsert.
        assertCategoryExists(dataContract);
        log.info(
                "Provisioning Data Product '{}' to Informatica Data Marketplace",
                dataProduct.getName());

        informaticaDataMarketplaceService.upsertProductToDataMarketplace(dataContract);

        log.info("Data Product '{}' provisioned successfully", dataProduct.getName());
        // TODO: enrich ProvisioningResultResponse with productName/productId, timestamp and the
        //  created-vs-updated distinction. See MarketplaceProxyApiServiceImpl.scala for the shape.
        return new ProvisioningResultResponse("COMPLETED");
    }

    @Override
    public String updateAcl(UpdateAclRequest updateAclRequest) {
        return "";
    }

    @Override
    public String delete(ProvisioningResultRequest provisioningRequest) {
        var publishDecision =
                Parser.evaluatePublishToInformatica(
                        provisioningRequest.getDescriptor(), publishAllowedEnvironment);
        if (!publishDecision.shouldPublish()) {
            log.info("{}", publishDecision.reason());
            return publishDecision.reason();
        }
        // Failures must propagate as exceptions (handled by MarketplacePluginExceptionHandler ->
        // 4xx/5xx)
        // so Witboost marks the unprovision as failed instead of green.
        var dataProduct = parseAndValidateDataProduct(provisioningRequest);
        var dataContract = mapAndValidateDataContract(dataProduct);
        log.info(
                "Deleting Data Product '{}' from Informatica Data Marketplace",
                dataProduct.getName());

        String dataProductName = dataContract.getBaseCharacteristics().getName();
        boolean existingProduct =
                informaticaDataMarketplaceService.isProductInDataMarketplace(dataProductName);

        if (existingProduct) {
            String deletedCollectionId =
                    informaticaDataMarketplaceService.deleteProduct(dataContract);
            log.info(
                    "Data Product '{}' with ID '{}' deleted successfully",
                    dataProduct.getName(),
                    deletedCollectionId);
        } else {
            log.info("Data Product '{}' does not exist. It will not be deleted.", dataProductName);
        }
        return "Delete successful";
    }

    @Override
    public ValidationResult validate(ProvisioningRequest provisioningRequest) {
        // Mirror the Data Catalog validate(): parse first so a malformed descriptor is reported as
        // invalid (not silently skipped), then short-circuit when publishing is not required.
        var eitherDataProduct = Parser.parseDataProduct(provisioningRequest.getDescriptor());
        if (eitherDataProduct.isLeft()) {
            return invalid(
                    eitherDataProduct.getLeft().problems().stream()
                            .map(Problem::getMessage)
                            .toList());
        }
        var publishDecision =
                Parser.evaluatePublishToInformatica(
                        provisioningRequest.getDescriptor(), publishAllowedEnvironment);
        if (!publishDecision.shouldPublish()) {
            log.info("Skipping validation. {}", publishDecision.reason());
            return new ValidationResult(true);
        }
        var dataProduct = eitherDataProduct.get();

        // Structural data-product validation first (pre-mapping), like the Data Catalog plugin and
        // the
        // Marketplace provisioning path, so an invalid descriptor is reported before we attempt to
        // map it.
        var dataProductValidation = dataProductValidator.validate(dataProduct);
        if (dataProductValidation.isPresent()) {
            return invalid(
                    dataProductValidation.get().problems().stream()
                            .map(Problem::getMessage)
                            .toList());
        }

        // Map, then run the structural data-contract validation and the Marketplace category check,
        // collecting all problems so every issue surfaces at once. Validation problems are returned
        // as ValidationResult, not thrown.
        var dataContract =
                dataContractMapper.toDataContractWithOutputPorts(
                        dataProduct, deliveryTargetMapper, dataAssetMapper);

        List<String> errors = new ArrayList<>();
        dataContractValidator
                .validate(dataContract)
                .ifPresent(
                        failedOperation ->
                                failedOperation
                                        .problems()
                                        .forEach(problem -> errors.add(problem.getMessage())));
        marketplaceCategoryValidator
                .validateCategoryExists(dataContract)
                .ifPresent(problem -> errors.add(problem.getMessage()));

        return errors.isEmpty() ? new ValidationResult(true) : invalid(errors);
    }

    private ValidationResult invalid(List<String> errors) {
        log.error("Validation failed with {} error(s): {}", errors.size(), errors);
        return new ValidationResult(false).error(new ValidationError(errors));
    }

    private DataProduct parseAndValidateDataProduct(ProvisioningResultRequest provisioningRequest) {
        log.debug("Parsing Data Product descriptor");
        var eitherDataProduct = Parser.parseDataProduct(provisioningRequest.getDescriptor());
        if (eitherDataProduct.isLeft()) {
            log.error("Descriptor parse failed: {}", eitherDataProduct.getLeft());
            throw new MarketplacePluginValidationException(
                    "Descriptor parse failed. See error details for more information",
                    eitherDataProduct.getLeft());
        }

        var dataProduct = eitherDataProduct.get();
        log.info("Parsed Data Product descriptor for '{}'", dataProduct.getName());

        log.debug("Validating Data Product descriptor");
        var validationResult = dataProductValidator.validate(dataProduct);
        if (validationResult.isPresent()) {
            log.error("Descriptor validation failed: {}", validationResult.get());
            throw new MarketplacePluginValidationException(
                    "Descriptor validation failed. See error details for more information",
                    validationResult.get());
        }

        return dataProduct;
    }

    private void assertCategoryExists(DataContract dataContract) {
        marketplaceCategoryValidator
                .validateCategoryExists(dataContract)
                .ifPresent(
                        problem -> {
                            log.error(
                                    "Marketplace category validation failed: {}",
                                    problem.getMessage());
                            throw new MarketplacePluginValidationException(
                                    "Descriptor validation failed. See error details for more information",
                                    new FailedOperation(List.of(problem)));
                        });
    }

    private DataContract mapAndValidateDataContract(DataProduct dataProduct) {
        log.info("Mapping Data Product to Data Contract");
        var dataContract =
                dataContractMapper.toDataContractWithOutputPorts(
                        dataProduct, deliveryTargetMapper, dataAssetMapper);
        dataContractValidator
                .validate(dataContract)
                .ifPresent(
                        failedOperation -> {
                            log.error("Data Contract validation failed: {}", failedOperation);
                            throw new MarketplacePluginValidationException(
                                    "Data Contract validation failed. See error details for more information",
                                    failedOperation);
                        });
        return dataContract;
    }
}
