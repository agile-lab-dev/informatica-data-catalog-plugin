package com.witboost.plugin.informatica.datacatalog.service.client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Disabled("Integration tests - requires real Informatica API credentials and network access")
class JobApiClientTest {

    @Autowired JobApiClient jobApiClient;

    String jobId = "783ee97f-0e9f-4656-a383-e41b90f2f12f";

    @Test
    void getJobStatus() {
        System.out.println(jobApiClient.getJobStatus(jobId));
    }

    @Test
    void waitForJobCompletion() {
        jobApiClient.waitForJobCompletion(jobId);
    }
}
