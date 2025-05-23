# Hello here, this is my API Testing Project

    It designed to run test cases to check RestFul API - https://petstore.swagger.io/ (store block only)
    using RestAssured library.

# Prerequisites

- Java 11 or higher (to run the project and tests)
- Maven (for build management and dependency management)
- TestNG
- RestAssured (in my case added as dependency and imported in the classes)

# Project structure

    The project has a TestBase class which contains @BeforeClass (TestNG annotation) with the part of code
    is executed before each TestCase (because each class consist of one TestCase in my case, to be atomic, independent)
    and protected methods to send requests GET, POST, DELETE.

    All test classes inherit TestBase, and some of them has @BeforeMethod which is run once to create/post test data
    before test.

    Also, we have utils directory with technical classes:
    OrderDetails - to keep the information about the order;
    PropertyReader - to read the properties and set up BaseUrl.

    Remember about testng.xml - with the list of TestCases.

# Running the tests
- run testng.xml in IDE
- run in bash by command "mvn test -Denv=test" (environment might be 'test', 'dev', 'int', ect.)

# Test report generated by TestNG and located in "target/surefire-reports/Surefire suite/emailable-report.html"