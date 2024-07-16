package org.example.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.api.helpers.DataGenerator;
import org.example.api.models.Pet;
import org.example.api.models.UpdateBody;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class PetsTest {
    public final String host = "https://petstore.swagger.io/v2/";
    public final String endpoint = "pet/";
    HttpClient client;


    @BeforeEach
    public void setup() {
        client = HttpClient.newHttpClient();
    }

    @Test
//    @ParameterizedTest
//    @ValueSource(strings = {"1", "2"})
    public void getPetPositive() throws IOException, InterruptedException {
        Integer petId = 1;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(host + endpoint + petId.toString()))
                .header("accept", "application/json").build();
        HttpResponse httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(200, httpResponse.statusCode());
        JSONObject jsonResponse = new JSONObject(httpResponse.body().toString());
        Assertions.assertAll(
                () -> Assertions.assertEquals(petId, jsonResponse.get("id")),
                () -> Assertions.assertEquals("dog", jsonResponse.get("name")),
                () -> Assertions.assertEquals("sold", jsonResponse.get("status"))
        );
    }

    @Test
    public void postPetPositive() throws IOException, InterruptedException {
        //String body = "{\"name\": \"doggie\", \"photoUrls\": [\"string\"]}";
        Pet expectedPet = DataGenerator.generateTestPet();
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(expectedPet);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(host + endpoint))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("accept", "application/json")
                .header("Content-Type", "application/json").build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Pet actualPet = objectMapper.readValue(response.body().toString(), Pet.class);
        Assertions.assertEquals(expectedPet.id, actualPet.id);
    }

    @Test
    public void postPetIdPositive() throws IOException, InterruptedException {
        Pet expectedPet = DataGenerator.generateTestPet();
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(expectedPet);
        HttpRequest createRequest = HttpRequest.newBuilder().uri(URI.create(host + endpoint))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("accept", "application/json")
                .header("Content-Type", "application/json").build();
        HttpResponse createResponse = client.send(createRequest, HttpResponse.BodyHandlers.ofString());
        Pet actualPet = objectMapper.readValue(createResponse.body().toString(), Pet.class);
        String body2 = "name=2&status=2";
        HttpRequest updateRequest = (HttpRequest) HttpRequest.newBuilder().uri(URI.create(host + endpoint + expectedPet.id))
                .POST(HttpRequest.BodyPublishers.ofString(body2))
                .header("accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded").build();
        HttpResponse updateResponse = client.send(updateRequest, HttpResponse.BodyHandlers.ofString());
        UpdateBody responseBody = objectMapper.readValue(updateResponse.body().toString(), UpdateBody.class);
        Assertions.assertEquals("200", responseBody.code);
        Assertions.assertEquals("unknown", responseBody.type);
        Assertions.assertEquals(String.valueOf(expectedPet.id), responseBody.message);
    }

    @Test
    public void postPetIdNegative() throws IOException, InterruptedException {
        Pet expectedPet = DataGenerator.generateTestPet();
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(expectedPet);
        HttpRequest createRequest = HttpRequest.newBuilder().uri(URI.create(host + endpoint))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("accept", "application/json")
                .header("Content-Type", "application/json").build();
        HttpResponse createResponse = client.send(createRequest, HttpResponse.BodyHandlers.ofString());
        Pet actualPet = objectMapper.readValue(createResponse.body().toString(), Pet.class);
        String fakeid = "999999999";
        String body2 = "name=2&status=2";
        HttpRequest updateRequest = (HttpRequest) HttpRequest.newBuilder().uri(URI.create(host + endpoint + fakeid))
                .POST(HttpRequest.BodyPublishers.ofString(body2))
                .header("accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded").build();
        HttpResponse updateResponse = client.send(updateRequest, HttpResponse.BodyHandlers.ofString());
        UpdateBody responseBody = objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .readValue(updateResponse.body().toString(), UpdateBody.class);
        Assertions.assertNotEquals("200", responseBody.code);
        Assertions.assertNotEquals("String.valueOf(expectedPet.id)", responseBody.message);
    }

}


