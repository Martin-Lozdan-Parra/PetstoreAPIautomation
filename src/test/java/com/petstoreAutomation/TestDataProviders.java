package com.petstoreAutomation;
import org.testng.annotations.DataProvider;

import com.petstoreAutomation.Classes.Pets.Rodolfo;
import com.petstoreAutomation.Classes.Pets.Whiskers;

public class TestDataProviders {

    @DataProvider(name = "newPet")
    public static Object[][] photoUrlsDataProvider() {
        return new Object[][] {
            {new Whiskers()},
            {new Rodolfo()},
            
            // Add more URLs as needed
        };
    }
}