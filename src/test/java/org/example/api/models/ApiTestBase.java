package org.example.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiTestBase {

    protected HttpClient client;

    @BeforeEach
    public void setup(){
        client = HttpClient.newHttpClient();
    }

    public HttpResponse sendApiRequest (HttpRequest request) throws IOException, InterruptedException {
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}