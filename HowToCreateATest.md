# How the tests work

Let's go to the [Pet Runner](src/test/java/com/petstoreAutomation/runners/PetRunnerTest.java)

Here you will see methods like this
```java
@Test(dataProvider = "newUser", dataProviderClass = TestDataProviders.class)
1  private void deleteUser(UserData userData) {
2
3    Response response = userCategory.deleteUser(userData.username);
4    response
5        .then().statusCode(200);
6    response = userCategory.getUserByUserName(userData.username);
7    response
8        .then().statusCode(404);
  }
  ```

  The first thing a test does is get data, that's why there is a parameter (UserData userdata), however the test methods are never invoked nor given parameter the classic way, that's why there is a dataProvider inside the @Test tag, which is = "newUser". This is the name of the data provider, they are defined [here](src/test/java/com/petstoreAutomation/TestDataProviders.java)

  Let's look at:

```java
    @DataProvider(name = "Pet 1")
    public static Object[][] Pet1PetDataProvider() {
        return new Object[][] {
            {new Pet1()},
        };
    }
```
This is a data provider, what it does is to return in this case an instance of [Pet1](src/test/java/com/petstoreAutomation/Classes/Pets/Pet1.java)

And Pet1 it's just a class with the data of the Pet1, every pet has its own class, and they inherit from [PetData](src/test/java/com/petstoreAutomation/Classes/Pets/PetData.java) which purpose is to unify the data types of the pets for later passing whichever pet you want to the methods that does the requests.


So, going back to the runner, we now can see how the dataprovider gives, in this case, the object Pet1 to the parameter userData.

Let's go now to the [Pet Category](src/test/java/com/petstoreAutomation/Classes/Pets/PetCategory.java). Here are the restassured methods to make the requests, they receive an object with the data to make said request and return the response.


Finally, we come back to the first piece of code, the Pet Runner. Now we can see how the response that returns the petCategory class is used to assert in this case first that the status code is 200, which means that the user got deleted, and then it searches for the user that was just deleted, hoping to get a 404 which would mean that indeed, the user was eliminated.


