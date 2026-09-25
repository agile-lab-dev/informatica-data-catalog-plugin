package com.witboost.plugin.informatica.datacatalog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncCatalogSourceResponse {

    @JsonProperty("jobId")
    private String jobId;

    @JsonProperty("trackingURI")
    private String trackingURI;

    @JsonProperty("status")
    private String status;

    @JsonProperty("taskGroups")
    private List<TaskGroup> taskGroups;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TaskGroup {
        @JsonProperty("groupId")
        private String groupId;

        @JsonProperty("groupName")
        private String groupName;

        @JsonProperty("tasks")
        private List<Task> tasks;

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Task {
            @JsonProperty("taskId")
            private String taskId;

            @JsonProperty("taskName")
            private String taskName;

            @JsonProperty("status")
            private String status;
        }
    }
}
