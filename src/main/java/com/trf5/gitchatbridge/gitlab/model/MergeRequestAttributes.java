package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MergeRequestAttributes {
    private int id;
    private int iid;
    public String title;
    public String url;
    @JsonProperty("target_branch")
    private String targetBranch;
    @JsonProperty("source_branch")
    private String sourceBranch;

    // ... (other fields like author_id, assignee_ids, etc.)
    private String state;
    @JsonProperty("merge_status")
    private String mergeStatus;
    private String description;
    private Commit lastCommit;
    private List<Label> labels;
    private String action;
    @JsonProperty("detailed_merge_status")
    private String detailedMergeStatus;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("last_edited_at")
    private String lastEditedAt;
}
