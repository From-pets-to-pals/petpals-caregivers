package com.petpals.caregivers.application.entrypoints;

import io.quarkus.test.junit.QuarkusTest;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ErrorNotFoundResourceTest {
    @Test
    void test404EndPoint()  {
        var body = Jsoup.parse(given().get("/something").getBody().prettyPrint());
        Assertions.assertEquals("Not Found", Objects.requireNonNull(body.select("h1").first()).text());
        Assertions.assertEquals("The resource doesn't exist.", Objects.requireNonNull(body.select("p").first()).text());
        given()
        .when().get("/something")
        .then()
        .statusCode(200);
    }
}
