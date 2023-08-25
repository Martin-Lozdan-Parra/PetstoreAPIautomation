package com.petstoreAutomation.runners;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.*;
import com.petstoreAutomation.TestDataProviders;
import com.petstoreAutomation.Classes.Pets.August;
import com.petstoreAutomation.Classes.Pets.PetCategory;
import com.petstoreAutomation.Classes.Pets.PetData;
import com.petstoreAutomation.Classes.Pets.Whiskers;
import com.petstoreAutomation.Classes.Stores.OrderData;
import com.petstoreAutomation.Classes.Stores.StoreCategory;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoreRunnerTest {

    
    StoreCategory storeCategory;

    @BeforeClass
    public void setup() {
        // Set base URI for your API
        RestAssured.baseURI = "http://localhost:8080/api/v3";
        storeCategory = StoreCategory.getInstance();
    }
    
    //TODO some test are currently failing because of 500 status on POST and PUT methods for user. Consult a developer
    
   //@Test(dataProvider = "newOrder", dataProviderClass = TestDataProviders.class)
   public void createOrder(OrderData orderData)
   {    
        storeCategory = StoreCategory.getInstance();
        Response response = storeCategory.placeOrder(orderData);
        response
        .then()
        .statusCode(200);

        response = storeCategory.getOrderByID(orderData.ID);
        response.then()
                .statusCode(200)
                .body("petId",equalTo(orderData.petID))
                .body("quantity",equalTo(orderData.quantity))
                .body("shipDate",equalTo(orderData.shipDate))
                .body("status",equalTo(orderData.status))
                .body("complete",equalTo(orderData.complete));
        
   }

   //@Test(dataProvider = "deleteOrder", dataProviderClass = TestDataProviders.class)
   public void deleteOrder(OrderData orderData)
   {    
        storeCategory = StoreCategory.getInstance();
        Response response = storeCategory.deleteOrder(orderData.ID);
        response.then()
                .statusCode(200);
        response = storeCategory.getOrderByID(orderData.ID);
        response.then()
                .statusCode(404);
        
        
   }

   @Test
   public void invalidDeleteOrder()
   {    
        storeCategory = StoreCategory.getInstance();
        Response response = storeCategory.deleteOrder("1001");
        response.then()
                .statusCode(400);
      
        
   }
   
   

   
   

   



    

    // Add more test methods as needed
}