package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Runner {
    private int id;
    private String description;
    private boolean active;
    @JsonProperty("runner_type")
    private String runnerType;
    @JsonProperty("is_shared")
    private boolean isShared;
    private List<String> tags;

}
