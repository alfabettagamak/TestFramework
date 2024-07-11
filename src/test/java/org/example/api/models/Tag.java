package org.example.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tag {
    @JsonProperty("id")
    public int id;

    @JsonProperty("name")
    public String name;

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag(){}
}
