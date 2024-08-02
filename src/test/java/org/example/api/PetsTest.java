package org.example.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.api.helpers.DataGenerator;
import org.example.api.models.ApiTestBase;
import org.example.api.models.Pet;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class PetsTest extends ApiTestBase {
    public final String host = "https://petstore.swagger.io/v2/";
    public final String endpoint = "pet/";


    @Test
    public void getPetPositive() throws IOException, InterruptedException {
        Integer petId = 1;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(host + endpoint +  petId.toString()))
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

//    @Test
//    public void postPetIdPositive() throws IOException, InterruptedException {
//        String expectedPetId = createPet();
//        String body2 = "name=2&status=2";
//
//        HttpRequest updateRequest = (HttpRequest) HttpRequest.newBuilder().uri(URI.create(host + endpoint + expectedPetId))
//                .POST(HttpRequest.BodyPublishers.ofString(body2))
//                .header("accept", "application/json")
//                .header("Content-Type", "application/x-www-form-urlencoded").build();
//
//        HttpResponse updateResponse = sendApiRequest(updateRequest);
//        ObjectMapper objectMapper = new ObjectMapper();
//        UpdateBody responseBody = objectMapper.readValue(updateResponse.body().toString(), UpdateBody.class);
//
//        Assertions.assertEquals("200", responseBody.code);
//        Assertions.assertEquals("unknown", responseBody.type);
//        Assertions.assertEquals(expectedPetId, responseBody.message);
//    }

//    @Test
//    public void postPetIdNegative() throws IOException, InterruptedException {
//        String expectedPetId = createPet();
//        String fakeid = "999999999";
//        String body2 = "name=2&status=2";
//        HttpRequest updateRequest = (HttpRequest) HttpRequest.newBuilder().uri(URI.create(host + endpoint + fakeid))
//                .POST(HttpRequest.BodyPublishers.ofString(body2))
//                .header("accept", "application/json")
//                .header("Content-Type", "application/x-www-form-urlencoded").build();
//        HttpResponse updateResponse = sendApiRequest(updateRequest);
//        ObjectMapper objectMapper = new ObjectMapper();
//        UpdateBody responseBody = objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
//                .readValue(updateResponse.body().toString(), UpdateBody.class);
//        Assertions.assertNotEquals("200", responseBody.code);
//        Assertions.assertNotEquals(expectedPetId, responseBody.message);
//    }

    public String createPet() throws IOException, InterruptedException {
        Pet expectedPet = DataGenerator.generateTestPet();
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(expectedPet);
        HttpRequest createRequest = HttpRequest.newBuilder().uri(URI.create(host + endpoint))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("accept", "application/json")
                .header("Content-Type", "application/json").build();
        HttpResponse createResponse = client.send(createRequest, HttpResponse.BodyHandlers.ofString());
        //Pet actualPet = objectMapper.readValue(createResponse.body().toString(), Pet.class);

        var createdId = new JSONObject(createResponse.body().toString()).get("id");
        return createdId.toString();
    }
}
