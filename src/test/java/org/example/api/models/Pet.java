package org.example.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pet {

    @JsonProperty("id")
    public int id;

    @JsonProperty("category")
    public Category category;

    @JsonProperty("name")
    public String name;

    @JsonProperty("photoUrls")
    public String[] photoUrls;

    @JsonProperty("tags")
    public Tag[] tags;


    @JsonProperty("status")
    public String status;

    public Pet(int id, Category category, String name, String[] photoUrls, Tag[] tags, String status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public Pet(){}
}