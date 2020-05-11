package com.walmartlabs.developer.walmarttest;

import com.walmartlabs.developer.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/*
Created by SP
*/

public class WalmartAssertionsTests extends TestBase {

    // 1) Verify if the number of items is equal to 10
    @Test
    public void test001() {
        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .body("numItems", equalTo(10));

    }

    // 2) Verify Query = ipod
    @Test
    public void test002() {
        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .body("query", equalTo("ipod"));

    }

    // 3) Check Single Name in ArrayList (Apple iPod touch 7th Generation 32GB - Space Gray (New Model)
    @Test
    public void test003() {
        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .body("items.name", hasItem("Apple iPod touch 7th Generation 32GB - Space Gray (New Model)"));

    }

    // 4) Check Multiple Names in ArrayList (Apple iPod touch 32GB - Blue (Previous Model)","Apple iPod touch 16GB")
    // "Apple iPod touch 7th Generation 128GB - Gold (New Model)" was not available in database at the moment of testing.

    @Test
    public void test004() {
        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .body("items.name", hasItems("Apple iPod touch 32GB - Blue (Previous Model)","Apple iPod touch 16GB"));


    }

    // 5) Verify the entityType inside imageEntities for the first product (Checking Values inside Map using hasKey(entityType))
    @Test
    public void test005() {
        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .body("items[0].imageEntities[0]", hasKey("entityType"));

    }
    // 6) Check hash map values inside a list categoryPath=Home Page/Electronics/Portable Audio/Apple iPods/iPod Touch

    @Test
    public void test006() {
        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .body("items.categoryPath", hasItem("Home Page/Electronics/Portable Audio/Apple iPods/iPod Touch"));
    }

    // 7) Checking multiple values in the same statement
    @Test
    public void test007() {

               given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .body("numItems", equalTo(10))
                .body("items[0].imageEntities[0]", hasKey("entityType"))
                .body("items.name", hasItem("Apple iPod touch 7th Generation 32GB - Space Gray (New Model)"));

    }

    // 8) Logical Assertions
    @Test
    public void test008() {
        given()
                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .body("items.size", equalTo(10))
                .body("items.size", greaterThan(8))
                .body("items.size", lessThanOrEqualTo(12));
    }
}
