package com.petstoreAutomation.runners;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.*;
import com.petstoreAutomation.TestDataProviders;
import com.petstoreAutomation.Classes.PetCategory;
import com.petstoreAutomation.Classes.PetData;
import com.petstoreAutomation.Classes.Pets.August;
import com.petstoreAutomation.Classes.Pets.Whiskers;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetRunnerTest {

    
    PetCategory petCategory;

    @BeforeClass
    public void setup() {
        // Set base URI for your API
        RestAssured.baseURI = "http://localhost:8080/api/v3";
        petCategory = PetCategory.getInstance();
    }
    
    
   @Test()
   public void searchPetByID()
   {    
        petCategory = PetCategory.getInstance();
        Response response = petCategory.getPetByID("10");
        response
        .then()
        .statusCode(200)
        .body("name", equalTo("Rabbit 1"));
   }
   
   

   @Test(groups = "smoke",dataProvider = "newPet", dataProviderClass = TestDataProviders.class)
   private void createPet(PetData pet)
   {    
        Response response = petCategory.createPet(pet);
        response
        .then()
        .statusCode(200);

        response = petCategory.getPetByID(pet.ID);
        response.then()
        .body("name",equalTo(pet.name))
        .body("photoUrls[0]",equalTo(pet.photoUrls[0]));     
   }

   @Test(groups = "smoke",dataProvider = "updatePet", dataProviderClass = TestDataProviders.class,dependsOnMethods = "createPet")
   private void updatePetByID(PetData pet, String petID)
   {    
    //First, update the pet
    
        Response response = petCategory.updatePetByID(petID,pet);
        response
        .then()
        .statusCode(200);
     
    //Then, search for the pet and assert that the body is the one that was updated
        response = petCategory.getPetByID(petID);
        response.then()
        .body("name",equalTo(pet.name))
        .body("photoUrls[0]",equalTo(pet.photoUrls[0]));     
   }
   
   @Test(dataProvider = "newPet", dataProviderClass = TestDataProviders.class, dependsOnMethods = "updatePetByID")
   private void deletePet(PetData pet)
   {    
        Response response = petCategory.deletePet(pet.ID);
        response
        .then()
        .statusCode(200);
        response = petCategory.getPetByID(pet.ID);
        response.then()
        .statusCode(404);     
   }
   

   



    

    // Add more test methods as needed
}