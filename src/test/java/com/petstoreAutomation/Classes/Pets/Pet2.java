package com.petstoreAutomation.Classes.Pets;

import java.util.Map;

public class Pet2 extends PetData 
{

    

    public Pet2(){
        
        ID = "1";
        name = "Cat 2";
        category = "{\"id\": 2,\n\"name\": \"Cats\"}";
        String[] photos = {"url1","url2"};
        photoUrls = photos;
        status = "available";
        categoryID = 2;
        categoryName = "Cats";


    }

    
    
}
