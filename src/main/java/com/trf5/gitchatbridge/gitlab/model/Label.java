package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class  Label {
    private int id;
    private String title;
    private String color;
    @JsonProperty("project_id")
    private int projectId;
    // ... other fields

    // Constructors, getters, and setters
}
