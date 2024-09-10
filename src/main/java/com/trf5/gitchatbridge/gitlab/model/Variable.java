package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Variable {
    private String key;
    private String value;

    // Constructors, getters, and setters (omitted for brevity)
}
