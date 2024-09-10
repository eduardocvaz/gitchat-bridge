package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PipelineEvent {
    @JsonProperty("object_kind")
    private String objectKind;

    @JsonProperty("object_attributes")
    private PipelineAttributes objectAttributes;

    @JsonProperty("merge_request")
    private MergeRequest mergeRequest;

    private User user;
    private Project project;
    private Commit commit;
    @JsonProperty("source_pipeline")
    private SourcePipeline sourcePipeline;
    private List<Build> builds;

    // Constructors, getters, and setters (omitted for brevity)
}
