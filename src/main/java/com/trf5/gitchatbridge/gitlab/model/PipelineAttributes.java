package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PipelineAttributes {
    private int id;
    private int iid;
    private String name;
    private String ref;
    private boolean tag;
    private String sha;
    @JsonProperty("before_sha")
    private String beforeSha;
    private String source;
    private String status;
    private List<String> stages;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("finished_at")
    private String finishedAt;
    private int duration;
    private List<Variable> variables;
    private String url;

}
