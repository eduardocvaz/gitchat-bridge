package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Build {
    private int id;
    private String stage;
    private String name;
    private String status;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("started_at")
    private String startedAt;
    @JsonProperty("finished_at")
    private String finishedAt;
    private Double duration; // Use Double to handle null values
    @JsonProperty("queued_duration")
    private Double queuedDuration;
    @JsonProperty("failure_reason")
    private String failureReason;
    private String when;
    private boolean manual;
    @JsonProperty("allow_failure")
    private boolean allowFailure;
    private User user;
    private Runner runner;


}
