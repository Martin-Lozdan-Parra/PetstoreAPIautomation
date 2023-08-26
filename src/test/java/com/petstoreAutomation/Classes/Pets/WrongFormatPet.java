package com.petstoreAutomation.Classes.Pets;


public class WrongFormatPet extends PetData {

  public WrongFormatPet() {

    ID = "Letters should not be allowed";
    name = "corrupt";
    category = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    String[] photos = {"url1", "url2"};
    photoUrls = photos;
    status = "unexistent";
  }
}
