package com.petstoreAutomation.Classes.Pets;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class PetCategory
{  

    public static PetCategory instance;

    public static synchronized PetCategory getInstance() {
        if (instance == null) {
            instance = new PetCategory();
        }
        return instance;
    }

    public Response getPetByID(String petID) {
        Response response = 
            given()
            .when()
            .get("/pet/" + petID);
            return response;
            
    }

    public Response updatePetByID(String existentPetID, PetData petData){
        Response response =
        given()
        .contentType("application/x-www-form-urlencoded")
        .formParam("id",existentPetID)
        .formParam("name", petData.name)
        .formParam("category", petData.category)
        .formParam("photoUrls",petData.photoUrls[0])
        .when()
        .put("/pet");
        return response;
    }

    public Response createPet(PetData petData){
        
        Response response =
        given()
        .contentType("application/x-www-form-urlencoded")
        .formParam("id",petData.ID)
        .formParam("name", petData.name)
        .formParam("category", petData.category)
        .formParam("photoUrls",(Object[])petData.photoUrls)
        .when()
        .post("/pet");
        return response;
    }

    public Response deletePet(String petID){
        
        Response response =
        given()
        .when()
        .delete("/pet/" + petID);
        return response;
    }



    
}