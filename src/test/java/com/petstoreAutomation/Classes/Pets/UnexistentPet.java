package com.petstoreAutomation.Classes.Pets;


public class UnexistentPet extends PetData {

  public UnexistentPet() {

    ID = "900";
    name = "unexistent";
    category = "{\"id\": 2,\n\"name\": \"Cats\"}";
    String[] photos = {"url1", "url2"};
    photoUrls = photos;
    status = "unexistent";
  }
}
