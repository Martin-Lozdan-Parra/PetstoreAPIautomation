package com.petstoreAutomation.Classes.Users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.petstoreAutomation.Classes.Pets.PetData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class UserCategory 
{  

    public static UserCategory instance;

    public static synchronized UserCategory getInstance() {
        if (instance == null) {
            instance = new UserCategory();
        }
        return instance;
    }

    public Response getUserByUserName(String userName) {
        Response response = 
            given()
            .when()
            .get("/user/" + userName);
            return response;
            
    }

    public Response updateUserByUserName(String existentUserName, UserData userData){
        Response response =
        given()
        .contentType("application/x-www-form-urlencoded")
        .formParam("userName",existentUserName)
        .formParam("firstName", userData.firstName)
        .formParam("lastName", userData.lastName)
        .formParam("email", userData.email)
        .formParam("password", userData.password)
        .formParam("phone", userData.phone)
        .formParam("userStatus",userData.userStatus)
        .when()
        .put("/user/"+ existentUserName);
        return response;
    }

    public Response createUser(UserData userData){
        
        Response response =
        given()
        .contentType("application/x-www-form-urlencoded")
         .formParam("username", userData.username)
        .formParam("firstName", userData.firstName)
        .formParam("lastName", userData.lastName)
        .formParam("email", userData.email)
        .formParam("password", userData.password)
        .formParam("phone", userData.phone)
        .formParam("userStatus",userData.userStatus)
        .when()
        .post("/user");
        return response;
    }

    public Response deleteUser(String userName){
        
        Response response =
        given()
        .when()
        .delete("/user/" + userName);
        return response;
    }



    
}