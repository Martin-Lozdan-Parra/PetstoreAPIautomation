package com.petstoreAutomation.Classes.Users;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class UserCategory {

  public static UserCategory instance;

  public static synchronized UserCategory getInstance() {
    if (instance == null) {
      instance = new UserCategory();
    }
    return instance;
  }

  public Response getUserByUserName(String userName) {
    Response response = given().when().get("/user/" + userName);
    return response;
  }

  // TODO provide a good way to convert from json to string

  public Response updateUserByUserName(String existentUserName, UserData userData) {

    String jsonFilePath = "path/to/request.json";
    // String jsonBody = JsonObject.readfromFile(jsonFilePath);
    Response response =
        given()
            .contentType("application/json")
            // .body(jsonBody)
            .when()
            .put("/user/" + existentUserName);
    return response;
  }

  public Response createUser(UserData userData) {
    Response response =
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("id", userData.ID)
            .formParam("username", userData.username)
            .formParam("firstName", userData.firstName)
            .formParam("lastName", userData.lastName)
            .formParam("mail", userData.email)
            .formParam("password", userData.password)
            .formParam("phone", userData.phone)
            .formParam("userStatus", userData.userStatus)
            .when()
            .post("/user");
    return response;
  }

  public Response deleteUser(String userName) {
    Response response = given().when().delete("/user/" + userName);
    return response;
  }

  public Response login(UserData userData) {
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
