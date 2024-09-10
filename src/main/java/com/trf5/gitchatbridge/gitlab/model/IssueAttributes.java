package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueAttributes {
    private int id;
    private int iid;
    private String title;
    private String url;
    private String action;
    private String description;
    @JsonProperty("assignee_ids")
    private List<Integer> assigneeIds;
    @JsonProperty("assignee_id")
    private int assigneeId;
    @JsonProperty("author_id")
    private int authorId;
    @JsonProperty("project_id")
    private int projectId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("updated_by_id")
    private Integer updatedById;
    @JsonProperty("last_edited_at")
    private String lastEditedAt;
    @JsonProperty("last_edited_by_id")
    private Integer lastEditedById;
    private String severity;
    @JsonProperty("escalation_status")
    private String escalationStatus;
    @JsonProperty("escalation_policy")
    private EscalationPolicy escalationPolicy;
}

