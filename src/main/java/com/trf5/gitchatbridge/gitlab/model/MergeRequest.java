package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MergeRequest {
    private int id;
    private int iid;
    private String title;
    @JsonProperty("source_branch")
    private String sourceBranch;
    @JsonProperty("source_project_id")
    private int sourceProjectId;
    @JsonProperty("target_branch")
    private String targetBranch;
    @JsonProperty("target_project_id")
    private int targetProjectId;
    private String state;
    @JsonProperty("merge_status")
    private String mergeStatus;
    @JsonProperty("detailed_merge_status")
    private String detailedMergeStatus;
    private String url;

}