package com.petpals.caregivers.application.entrypoints;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.Headers;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class HealthCheckResourceTest {
    @ConfigProperty(name = "api.key")
    String apiKey;

    @Test
    void testHelloEndpoint() {
        given().header("API-KEY", apiKey)
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello RESTEasy"));
    }

    @Test
    void testHelloWithNameEndpoint() {
        final String name = "Sid";
        given().header("API-KEY", apiKey)
                .when().get(String.format("/hello/%s", name))
                .then()
                .statusCode(200)
                .body(is(String.format("Hello %s",name)));
    }

    @Test
    void testNoApiKey() {
        given()
                .headers(new Headers())
                .when().get("/hello/sid")
                .then()
                .statusCode(401);
    }
}