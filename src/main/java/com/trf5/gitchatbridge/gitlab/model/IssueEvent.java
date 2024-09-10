package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueEvent {
    private String objectKind;
    private String eventType;
    private User user;
    private Project project;
    @JsonProperty("object_attributes")
    private IssueAttributes objectAttributes;
    private Repository repository;
    private List<User> assignees;
    private User assignee;
    private List<Label> labels;
    private Changes changes;

    // Constructors, getters, and setters (omitted for brevity)
}