package com.petstoreAutomation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class APITest extends BaseTest {

    @BeforeClass
    public void setup() {
        // Set base URI for your API
        RestAssured.baseURI = "http://localhost:8080/api/v3";
    }

    @Test(groups = "smoke")
    public void TestExecution1(){
        getPetByID("11")
        .then()
        .statusCode(200)
        .body("name", equalTo("customDoggie"));
    }

    // Add more test methods as needed
}