package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import java.util.HashMap;
import java.util.Map;
import static utils.PropertyReader.getBaseUrl;


public abstract class TestBase {
    protected RequestSpecification request;

    @BeforeClass
    public void setUp() {


        RestAssured.baseURI = getBaseUrl();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        request = RestAssured.given()
                .headers(headers)
                .log().all();
    }

    protected Response sendGetRequest(String endpoint) {
        return request
                .get(RestAssured.baseURI + endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response sendPostRequest(String endpoint, String payload) {
        return request.body(payload)
                .post(RestAssured.baseURI + endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response sendDeleteRequest(String endpoint) {
        return request.delete(RestAssured.baseURI + endpoint)
                .then()
                .log().all()
                .extract().response();
    }

}