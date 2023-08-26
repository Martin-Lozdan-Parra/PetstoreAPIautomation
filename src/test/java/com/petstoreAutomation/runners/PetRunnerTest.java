package com.petstoreAutomation.runners;

import static org.hamcrest.Matchers.*;

import com.petstoreAutomation.Classes.Pets.PetCategory;
import com.petstoreAutomation.Classes.Pets.PetData;
import com.petstoreAutomation.TestDataProviders;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetRunnerTest {

  PetCategory petCategory;

  @BeforeClass
  public void setup() {
    // Set base URI for your API
    RestAssured.baseURI = "http://localhost:8080/api/v3";
    petCategory = PetCategory.getInstance();
  }

  @Test(dataProvider = "newPet", dataProviderClass = TestDataProviders.class)
  private void createPet(PetData pet) {
    Response response = petCategory.createPet(pet);
    response.then().statusCode(200);

    response = petCategory.getPetByID(pet.ID);
    response
        .then()
        .body("name", equalTo(pet.name))
        .body("photoUrls[0]", equalTo(pet.photoUrls[0]))
        .body("status", equalTo(pet.status));

    int categoryId = response.path("category.id");
    String categoryName = response.path("category.name");

    assert categoryId == pet.categoryID;
    assert categoryName.equals(pet.categoryName);
  }

  @Test(dataProvider = "WrongFormatPet", dataProviderClass = TestDataProviders.class)
  private void createWrongFormatPet(PetData pet) {
    Response response = petCategory.createPet(pet);
    response.then().statusCode(500);
  }

  @Test(dataProvider = "Pet 1", dataProviderClass = TestDataProviders.class)
  private void createExistentPet(PetData pet) {
    Response response = petCategory.createPet(pet);
    response.then().statusCode(403);
  }

  @Test(dataProvider = "updatePet",dataProviderClass = TestDataProviders.class,dependsOnMethods = "createPet")
  private void updatePetByID(PetData pet, String petID) {
  
    Response response = petCategory.updatePetByID(petID, pet);
    response.then().statusCode(200);
    response = petCategory.getPetByID(petID);
    response
        .then()
        .body("name", equalTo(pet.name))
        .body("photoUrls[0]", equalTo(pet.photoUrls[0]))
        .body("status", equalTo(pet.status));
    int categoryId = response.path("category.id");
    String categoryName = response.path("category.name");
    assert categoryId == pet.categoryID;
    assert categoryName.equals(pet.categoryName);
  }

  @Test(dataProvider = "newPet",dataProviderClass = TestDataProviders.class,dependsOnMethods = "updatePetByID")
  private void deletePet(PetData pet) {
    Response response = petCategory.deletePet(pet.ID);
    response.then().statusCode(200);
    response = petCategory.getPetByID(pet.ID);
    response.then().statusCode(404);
  }

  @Test(dataProvider = "UnExistentPet",dataProviderClass = TestDataProviders.class,dependsOnMethods = "updatePetByID")
  private void deleteUnexistentPet(PetData pet) {
    Response response = petCategory.deletePet(pet.ID);
    response.then().statusCode(404);
  }

  @Test(dataProvider = "UnExistentPet", dataProviderClass = TestDataProviders.class)
  private void updateUnExistent(PetData pet) {
    // First, update the pet

    Response response = petCategory.updatePetByID(pet.ID, pet);
    response.then().statusCode(404);
  }

  // Add more test methods as needed
}
