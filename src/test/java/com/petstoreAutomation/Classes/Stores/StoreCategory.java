package com.petstoreAutomation.Classes.Stores;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.petstoreAutomation.Classes.Pets.PetData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class StoreCategory 
{  

    public static StoreCategory instance;

    public static synchronized StoreCategory getInstance() {
        if (instance == null) {
            instance = new StoreCategory();
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

    public Response updateUserByUserName(String existentUserName, StoreData userData){
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

    public Response createUser(StoreData userData){
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

    public Response login(StoreData userData){
        Response response =
        given()
        .contentType("application/x-www-form-urlencoded")
         .formParam("username", userData.username)
         .formParam("username", userData.password)
        .when()
        .get("/user/login");
        return response;
    }



    
}