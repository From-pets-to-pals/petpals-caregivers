package com.petpals.caregivers;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ExampleResourceTest {
    @ConfigProperty(name = "api.key")
    String apiKey;@Test
    void testHelloEndpoint() {
        given().header("API-KEY", apiKey)
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello RESTEasy"));
    }

    @Test
    void testNoApiKey() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(401);
    }

}