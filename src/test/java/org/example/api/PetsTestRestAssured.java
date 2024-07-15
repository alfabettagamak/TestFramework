package org.example.api;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class PetsTestRestAssured {
    public final String host = "https://petstore.swagger.io/v2/store/order/";

    @Test
    public void getPetRestAssuredPositive() {
        Integer orderId = 4;
        ValidatableResponse response = given().header("accept","application/json")
                .get(host + orderId)
                .then().statusCode(200);
        JsonPath respJson = response.extract().body().jsonPath();
        Assertions.assertEquals(orderId, respJson.get("id"));
    }

}
