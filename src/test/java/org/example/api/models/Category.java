package org.example.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Category {

    @JsonProperty("id")
    public int categoryId;

    @JsonProperty("name")
    public String categoryName;

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Category(){}
}
