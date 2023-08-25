package com.petstoreAutomation;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BaseTest {

    

    @BeforeClass
    public void setup() {
        // Set base URI for your API
        RestAssured.baseURI = "http://localhost:8080/api/v3";
    }

    

    public Response getPetByID(String petID) {
        Response response = given()
            .when()
            .get("/pet/" + petID);
            return response;
            
    }

    // Add more test methods as needed
}