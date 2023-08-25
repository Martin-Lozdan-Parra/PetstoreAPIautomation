package com.petstoreAutomation.Classes.Users;

import java.util.Map;

import com.petstoreAutomation.Classes.Pets.PetData;

public class unexistentUser extends UserData
{

    

    public unexistentUser(){

        username = "zzzzzzzzzzzzz";
        firstName = "first name 1";
        lastName = "last name 1";
        email = "email@test.com";
        password= "nonexistent";
        phone = "123-456-7890";
        userStatus = 1;   

    }

    
    
}
