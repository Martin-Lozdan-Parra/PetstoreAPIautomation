# Pet store API test Automation

This repository's goal is test the [swagger-petstore](https://github.com/swagger-api/swagger-petstore) API

## Requirements



Java 1.8 or older

Maven

The [swagger-petstore API](https://github.com/swagger-api/swagger-petstore) running


## How to run Tests

There are 3 [xml](UserTestSuite.xml), each one is a test suite. In order to execute all the 3 suites use 
```bash
mvn test
```
this will execute all the tests

If you want to run all the tests of a store category (Pets, Users or Store), you may use
```bash
mvn test -DsuiteXmlFile=<Name of the xml file here>
```

If you want to run only one test, you may use
```bash
mvn test -Dtest=<Name of the runner that contains the test>#<Name of the test>
example: mvn test -Dtest=StoreRunnerTest#createOrder
```
to find the test method name, you can go to the runners class and look at the methods that are below the @Test tag, those are the names you want. [More Details](HowToCreateATest.md)

## Reports

You can find reports of the tests run [here](target/surefire-reportsindex.html) 
(you may run a test first)

## How it works

It uses testNG to create a [list](testCases.md) of test cases and run them, using different commands that will be explained briefly

The architecture consists in having the 3 sections of the API (pets, store, and users) represented by a ["Category"](src/test/java/com/petstoreAutomation/Classes/Pets/PetCategory.java) class which is responsible for providing the methods to complete the requests to the API, [objects](src/test/java/com/petstoreAutomation/Classes/Pets/Pet1.java) that holds the Data of the different elements of the sections, and [Data providers](src/test/java/com/petstoreAutomation/TestDataProviders.java), which consist of a class with many methods that handle the data from the object to the tests. [More Details](HowToCreateATest.md)


With this architecture is very easy to create new tests, since the requests are already created on the Category classes, they only need data to operate, and the way to give them the data is by creating a new Object with the desired fields, then giving it to the Data Provider and by last, invoking all the methods in one of the [Test Runner](src/test/java/com/petstoreAutomation/runners/StoreRunnerTest.java), where the actual tests are.
Then there are 3 [xml](StoreTestSuite.xml) files on the base of the repository, which are the ones responsible for executing the tests.


Also, the tests are independent of the API, but dependent of themselves. This means that the tests use data they generate. The "delete" tests utilize the data that was created by the "create" tests, that way some scenarios like deleting a pet, run the test again trying to delete that same pet, but it was already done by the previous run, so it passes on the first one, but fails on the second one, are avoided. One better approach for this would involve having an endpoint like run-fixtures or reset-data where all the data is reset to the point where no test was run. That way you could run this request before each suite test, making the tests independant between each other.


# Important note

In order to run the tests is necessary to have the local API up, to do so, clone [this](https://github.com/swagger-api/swagger-petstore) repo and inside it run the command: mvn package jetty.
Now you can run the tests.