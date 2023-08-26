package com.petstoreAutomation.Classes.Pets;

import java.util.Map;

public class Pet3 extends PetData 
{

    

    public Pet3(){
        
        ID = "3";
        name = "Cat 3";
        category = "{\"id\": 2,\n\"name\": \"Cats\"}";
        String[] photos = {"url1","url2"};
        photoUrls = photos;
        status = "pending";
        categoryID = 2;
        categoryName = "Cats";

    }

    
    
}
