package com.petstoreAutomation;
import org.testng.annotations.DataProvider;

import com.petstoreAutomation.Classes.Pets.August;
import com.petstoreAutomation.Classes.Pets.Rodolfo;
import com.petstoreAutomation.Classes.Pets.Whiskers;
import com.petstoreAutomation.Classes.Users.Mary;
import com.petstoreAutomation.Classes.Users.User1;

public class TestDataProviders {

    @DataProvider(name = "newPet")
    public static Object[][] newPetDataProvider() {
        return new Object[][] {
            {new Whiskers()},
            {new Rodolfo()},
        };
    }

    @DataProvider(name = "updatePet")
    public static Object[][] updatePetDataProvider() {
        return new Object[][] {
            {new August(), "11"},
        };
    }

    @DataProvider(name = "newUser")
    public static Object[][] newUserDataProvider() {
        return new Object[][] {
            {new Mary()},
            
        };
    }

    @DataProvider(name = "existentUser")
    public static Object[][] existenUserDataProvider() {
        return new Object[][] {
            {new User1()},
            
        };
    }
}