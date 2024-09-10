package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class  Repository {
    private String name;
    private String url;
    private String description;
    private String homepage;

    // Constructors, getters, and setters
}
