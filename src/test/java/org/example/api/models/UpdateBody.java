package org.example.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateBody {

    @JsonProperty("code")
    public String code;

    @JsonProperty("type")
    public String type;

    @JsonProperty("message")
    public String message;

    public UpdateBody (String code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public UpdateBody() {
    }
}
