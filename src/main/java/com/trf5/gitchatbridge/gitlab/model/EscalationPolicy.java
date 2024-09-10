package com.trf5.gitchatbridge.gitlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EscalationPolicy {
    private int id;
    private String name;

    // Getters and setters (omitted for brevity)
}
