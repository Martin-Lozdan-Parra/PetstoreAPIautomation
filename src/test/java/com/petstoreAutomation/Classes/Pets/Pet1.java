package com.petstoreAutomation.Classes.Pets;


public class Pet1 extends PetData {

  public Pet1() {

    ID = "1";
    name = "Cat 1";
    category = "{\"id\": 2,\n\"name\": \"Cats\"}";
    String[] photos = {"url1", "url2"};
    photoUrls = photos;
    status = "available";
  }
}
