package com.petstoreAutomation.Classes.Stores;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class StoreCategory {

  public static StoreCategory instance;

  public static synchronized StoreCategory getInstance() {
    if (instance == null) {
      instance = new StoreCategory();
    }
    return instance;
  }

  public Response placeOrder(OrderData orderData) {
    Response response =
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("id", orderData.ID)
            .formParam("petId", orderData.petID)
            .formParam("quantity", orderData.quantity)
            .formParam("shipDate", orderData.shipDate)
            .formParam("status", orderData.status)
            .formParam("complete", orderData.complete)
            .when()
            .post("/store/order");
    return response;
  }

  public Response getOrderByID(String orderID) {
    Response response = given().when().get("/store/order/" + orderID);
    return response;
  }

  public Response deleteOrder(String orderID) {
    Response response = given().when().delete("/store/order/" + orderID);
    return response;
  }
}
