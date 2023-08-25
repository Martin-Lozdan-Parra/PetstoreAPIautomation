package com.petstoreAutomation.runners;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.*;
import com.petstoreAutomation.TestDataProviders;
import com.petstoreAutomation.Classes.Pets.August;
import com.petstoreAutomation.Classes.Pets.PetCategory;
import com.petstoreAutomation.Classes.Pets.PetData;
import com.petstoreAutomation.Classes.Pets.Whiskers;
import com.petstoreAutomation.Classes.Users.UserCategory;
import com.petstoreAutomation.Classes.Users.UserData;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserRunnerTest {

    
    UserCategory userCategory;

    @BeforeClass
    public void setup() {
        // Set base URI for your API
        RestAssured.baseURI = "http://localhost:8080/api/v3";
        userCategory = UserCategory.getInstance();
    }
    
    
   @Test(dataProvider = "existentUser", dataProviderClass = TestDataProviders.class)
   public void searchUserByID(UserData userData)
   {   
        Response response = userCategory.getUserByUserName(userData.username);
        response
        .then()
        .statusCode(200)
        .body("firstName", equalTo(userData.firstName));
   }
   
   

   @Test(groups = "smoke",dataProvider = "newUser", dataProviderClass = TestDataProviders.class)
   private void createUser(UserData userData)
   {    
        Response response = userCategory.createUser(userData);
        response
        .then()
        .statusCode(200);

        response = userCategory.getUserByUserName(userData.username);
        response.then()
        .body("firstName",equalTo(userData.firstName))
        .body("lastName",equalTo(userData.lastName))
        .body("email",equalTo(userData.email))
        .body("password",equalTo(userData.password))
        .body("phone",equalTo(userData.phone))
        .body("userStatus",equalTo(userData.userStatus));
            
   }

   @Test(groups = "smoke",dataProvider = "deleteUser", dataProviderClass = TestDataProviders.class,dependsOnMethods = "createUser")
   private void updateUserByUserName(UserData userData, String UserName)
   {    
    //First, update the pet
    
        Response response = userCategory.updateUserByUserName(UserName, userData);
        response
        .then()
        .statusCode(200);
     
    //Then, search for the pet and assert that the body is the one that was updated
        response = userCategory.getUserByUserName(UserName);
        response.then()
        .body("firstName",equalTo(userData.firstName))
        .body("userStatus",equalTo(userData.userStatus));     
   }
   
   @Test(dataProvider = "deleteUser", dataProviderClass = TestDataProviders.class, dependsOnMethods = "updateUserByUserName")
   private void deleteUser(UserData userData)
   {    
        Response response = userCategory.deleteUser(userData.username);
        response
        .then()
        .statusCode(200);
        response = userCategory.getUserByUserName(userData.username);
        response.then()
        .statusCode(404);     
   }
   

   



    

    // Add more test methods as needed
}