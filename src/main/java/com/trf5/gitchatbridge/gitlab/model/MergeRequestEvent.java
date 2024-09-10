package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data // Lombok annotation for generating getters, setters, etc.
@JsonIgnoreProperties(ignoreUnknown = true)
public class MergeRequestEvent {

    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_type")
    private String eventType;
    private User user;
    private Project project;
    private Repository repository;
    @JsonProperty("object_attributes")
    private MergeRequestAttributes objectAttributes;
    private List<Label> labels;
    private Changes changes;
    private List<User> assignees;
    private List<User> reviewers;

    // Constructors, getters, and setters (omitted for brevity, but you'll need them)
}
