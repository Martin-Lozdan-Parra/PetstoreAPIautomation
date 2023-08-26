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
import com.petstoreAutomation.Classes.Users.deleteUser;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserRunnerTest {

    //TODO some test are currently failing because of 500 status on POST and PUT methods for user. Consult a developer

    
    UserCategory userCategory;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/v3";
        userCategory = UserCategory.getInstance();
    }
    
   
   

   @Test(dataProvider = "newUser", dataProviderClass = TestDataProviders.class)
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

   @Test(groups = "smoke",dataProvider = "existentUser", dataProviderClass = TestDataProviders.class)
   private void createExistentUser(UserData userData)
   {    
        Response response = userCategory.createUser(userData);
        response
        .then()
        .statusCode(403);
            
   }

   //@Test(groups = "smoke",dataProvider = "updateUser", dataProviderClass = TestDataProviders.class, dependsOnMethods = "createUser")
   private void updateUserByUserName(UserData userData, String UserName) 
   {     
   
        Response response = userCategory.updateUserByUserName(UserName, userData);
        response
        .then()
        .statusCode(200);
     
   
        response = userCategory.getUserByUserName(UserName);
        response.then()
            .body("firstName",equalTo(userData.firstName))
            .body("lastName",equalTo(userData.lastName))
            .body("mail",equalTo(userData.email))
            .body("password",equalTo(userData.password))
            .body("phone",equalTo(userData.phone))
            .body("userStatus",equalTo(userData.userStatus));  
   }
   

   @Test(dataProvider = "unexistentUser", dataProviderClass = TestDataProviders.class)
   private void wrongLogin(UserData userData)
   {    
        Response response = userCategory.login(userData);
        response
        .then()
        .statusCode(400);   
   }

   @Test(dataProvider = "existentUser", dataProviderClass = TestDataProviders.class)
   private void validLogin(UserData userData)
   {    
        Response response = userCategory.login(userData);
        response
        .then()
        .statusCode(200);   
   }

   @Test(dataProvider = "newUser", dataProviderClass = TestDataProviders.class, dependsOnMethods = "createUser")
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

   @Test(dataProvider = "newUser", dataProviderClass = TestDataProviders.class, dependsOnMethods = "deleteUser")
   private void deleteUnexistentUser(UserData userData)
   {    
        Response response = userCategory.deleteUser(userData.username);
        response
        .then()
        .statusCode(404);
            
   }
}