package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SourcePipeline {
    private Project project;
    @JsonProperty("pipeline_id")
    private int pipelineId;
    @JsonProperty("job_id")
    private int jobId;
}
