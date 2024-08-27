package org.example.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.not;

public class PlanetTest {

    String baseUrl = "https://swapi.dev/api/planets/";

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    public void getPlanetsTesting(int page) throws IOException, InterruptedException {
        HttpResponse response =  SendGetRequest(page);

        Assertions.assertEquals(200, response.statusCode());
        String path = "C:\\Users\\user\\IdeaProjects\\TestFramework2\\src\\test\\resources\\schema.json";

        JsonSchema jsonSchema = GetJsonSchema(path);
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(response.body().toString());
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
        Assertions.assertTrue(errors.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    public void getPlanetsRestAssuredTesting(int page) {
        RestAssured.given().queryParam("page", page).get(baseUrl)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
    }

    @Test
    public void mockExampleTesting() throws IOException, InterruptedException {
        MockWebServer mockWebServer = new MockWebServer();
        MockResponse mockResponse = new MockResponse().addHeader("Content-Type", "application/json")
                .setResponseCode(200).setBody("{\"phone\": \"+799999999\"}");
        mockWebServer.enqueue(mockResponse);
        HttpUrl url = mockWebServer.url("/getNumber");
        mockWebServer.start();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(
                URI.create("http://" + mockWebServer.getHostName() + ":" + mockWebServer.getPort() + "/getNumber")).build();
        var result = client.send(request, HttpResponse.BodyHandlers.ofString());

        OkHttpClient client1 = new OkHttpClient();
        Request request1 = new Request.Builder().url(url).build();
        Response response = client1.newCall(request1).execute();
        var a = 5;
    }

    @Test
    public void getPlanetsNegativeTesting() throws IOException, InterruptedException {
        int page = 60;
        HttpResponse response = SendGetRequest(page);
        Assertions.assertEquals(404, response.statusCode());
        String path = "C:\\Users\\user\\IdeaProjects\\TestFramework2\\src\\test\\resources\\schema.json";
        JsonSchema jsonSchema = GetJsonSchema(path);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response.body().toString());
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
        System.out.println(errors);
        Assertions.assertFalse(errors.isEmpty());
    }

    @Test
    public void getPlanetsRestAssuredNegativeTesting() {
        RestAssured.get(baseUrl+"/1/")
                .then()
                .statusCode(200)
                .body(not(matchesJsonSchemaInClasspath("schema.json")));
    }

    public HttpResponse SendGetRequest(int page) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseUrl + "?page=" + page)).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public JsonSchema GetJsonSchema(String path) throws IOException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema jsonSchema = factory.getSchema(Files.readString(Path.of(path)));
        return jsonSchema;
    }
}
