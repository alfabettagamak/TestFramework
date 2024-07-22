package org.example.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


@Entity
public class Pet {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    public int id;

    @Column(name = "category")
    @JsonProperty("category")
    public String category;

    @Column(name = "name")
    @JsonProperty("name")
    public String name;

    @Column(name = "photo_urls")
    @JsonProperty("photoUrls")
    public String[] photoUrls;

    @Column(name = "tags")
    @JsonProperty("tags")
    public String[] tags;

    @Column(name = "status")
    @JsonProperty("status")
    public String status;

    public Pet(int id, String category, String name, String[] photoUrls, String[] tags, String status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public Pet(){}
}