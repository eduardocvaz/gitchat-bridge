package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class JobEvent {
    @JsonProperty("object_kind")
    private String objectKind;
    private String ref;
    private boolean tag;
    @JsonProperty("before_sha")
    private String beforeSha;
    private String sha;
    @JsonProperty("build_id")
    private Long buildId;
    @JsonProperty("build_name")
    private String buildName;
    @JsonProperty("build_stage")
    private String buildStage;
    @JsonProperty("build_status")
    private String buildStatus;
    @JsonProperty("build_created_at")
    private String buildCreatedAt;
    @JsonProperty("build_started_at")
    private String buildStartedAt;
    @JsonProperty("build_finished_at")
    private String buildFinishedAt;
    @JsonProperty("build_duration")
    private Double buildDuration;
    @JsonProperty("build_queued_duration")
    private Double buildQueuedDuration;
    @JsonProperty("build_allow_failure")
    private boolean buildAllowFailure;
    @JsonProperty("build_failure_reason")
    private String buildFailureReason;
    @JsonProperty("retries_count")
    private int retriesCount;
    @JsonProperty("pipeline_id")
    private Long pipelineId;
    @JsonProperty("project_id")
    private Long projectId;
    @JsonProperty("project_name")
    private String projectName;
    private User user;
    private Commit commit;
    private Repository repository;
    private Project project;
    private Runner runner;
    private Object environment; // Assuming it could be any type, adjust as needed
    @JsonProperty("source_pipeline")
    private SourcePipeline sourcePipeline;
}
