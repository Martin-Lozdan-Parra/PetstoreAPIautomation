package com.petstoreAutomation;
import org.testng.annotations.DataProvider;

import com.petstoreAutomation.Classes.Pets.August;
import com.petstoreAutomation.Classes.Pets.Rodolfo;
import com.petstoreAutomation.Classes.Pets.Whiskers;

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
}