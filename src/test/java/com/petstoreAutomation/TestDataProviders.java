package com.petstoreAutomation;
import org.testng.annotations.DataProvider;

import com.petstoreAutomation.Classes.Pets.*;
import com.petstoreAutomation.Classes.Stores.*;
import com.petstoreAutomation.Classes.Users.*;


public class TestDataProviders {


    //------------ PETS -----------//

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

    @DataProvider(name = "Pet 1")
    public static Object[][] Pet1PetDataProvider() {
        return new Object[][] {
            {new Pet1()},
        };
    }


    @DataProvider(name = "Pet 3")
    public static Object[][] Pet3PetDataProvider() {
        return new Object[][] {
            {new Pet3()},
        };
    }

    @DataProvider(name = "UnExistentPet")
    public static Object[][] UnExistentPetDataProvider() {
        return new Object[][] {
            {new UnexistentPet()},
        };
    }

    @DataProvider(name = "WrongFormatPet")
    public static Object[][] WrongFormatPetDataProvider() {
        return new Object[][] {
            {new WrongFormatPet()},
        };
    }

    @DataProvider(name = "DeletePet")
    public static Object[][] DeletePetDataProvider() {
        return new Object[][] {
            {new Pet2()},
        };
    }

    //------------users---------------//

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

     @DataProvider(name = "unexistentUser")
    public static Object[][] unexistenUserDataProvider() {
        return new Object[][] {
            {new unexistentUser()},
            
        };
    }

    @DataProvider(name = "updateUser")
    public static Object[][] updateUserDataProvider() {
        return new Object[][] {
            {new updatedUser(), "user1"},
            
        };
    }

    //---------------ORDERS------------//

    @DataProvider(name = "newOrder")
    public static Object[][] newOrderDataProvider() {
        return new Object[][] {
            {new newOrder()},
        };
    }

    @DataProvider(name = "deleteOrder")
    public static Object[][] deleteOrderDataProvider() {
        return new Object[][] {
            {new newOrder()},
        };
    }

    @DataProvider(name = "newInvalidOrder")
    public static Object[][] newInvalidOrderDataProvider() {
        return new Object[][] {
            {new newInvalidOrder()},
        };
    }
}