package com.petstoreAutomation.runners;

import static org.hamcrest.Matchers.*;

import com.petstoreAutomation.Classes.Stores.OrderData;
import com.petstoreAutomation.Classes.Stores.StoreCategory;
import com.petstoreAutomation.TestDataProviders;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StoreRunnerTest {

  StoreCategory storeCategory;

  @BeforeClass
  public void setup() {
    // Set base URI for your API
    RestAssured.baseURI = "http://localhost:8080/api/v3";
    storeCategory = StoreCategory.getInstance();
  }

  @Test(dataProvider = "newOrder", dataProviderClass = TestDataProviders.class)
  public void createOrder(OrderData orderData) {
    storeCategory = StoreCategory.getInstance();
    Response response = storeCategory.placeOrder(orderData);
    response.then().statusCode(200);

    response = storeCategory.getOrderByID(orderData.ID.toString());

    response
        .then()
        .statusCode(200)
        .body("petId", equalTo(orderData.petID))
        .body("quantity", equalTo(orderData.quantity))
        .body("shipDate", equalTo(orderData.shipDate))
        .body("status", equalTo(orderData.status))
        .body("complete", equalTo(orderData.complete));
  }

  @Test(dataProvider = "newInvalidOrder", dataProviderClass = TestDataProviders.class)
  public void createInvalidOrder(OrderData orderData) {
    storeCategory = StoreCategory.getInstance();
    Response response = storeCategory.placeOrder(orderData);

    response.then().statusCode(500);
  }

  @Test(dataProvider = "deleteOrder", dataProviderClass = TestDataProviders.class)
  public void deleteOrder(OrderData orderData) {
    storeCategory = StoreCategory.getInstance();
    Response response = storeCategory.deleteOrder(orderData.ID.toString());

    response.then().statusCode(200);
    response = storeCategory.getOrderByID(orderData.ID.toString());
    response.then().statusCode(404);
  }

  @Test
  public void invalidDeleteOrder() {
    storeCategory = StoreCategory.getInstance();
    Response response = storeCategory.deleteOrder("1001");
    
    response.then().statusCode(400);
  }

  // Add more test methods as needed
}
