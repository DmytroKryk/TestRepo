package tests;

import base.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.OrderDetails;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TestPlaceTheOrderAlreadyExists extends TestBase {

    final int idToOrder = 8;

    @BeforeMethod
    public void createOrder() {
        OrderDetails orderData = new OrderDetails();
        String request = orderData.requestConstructor(idToOrder, 1, 1, "placed");

        Response response = sendPostRequest("/store/order", request);
        Assert.assertEquals(response.getStatusCode(), 200);

        //Thread.sleep(2000);//need to wait until test data posted
    }

    @Test(priority = 3)
    public void testPlaceTheOrderWhichAlreadyExists() {

        OrderDetails orderData = new OrderDetails();
        String request = orderData.requestConstructor(idToOrder, 1, 1, "placed");

        Response response = sendPostRequest("/store/order", request);
        Assert.assertEquals(response.getStatusCode(), 200);

        int id = response.jsonPath().getInt("id");

        Assert.assertEquals(response.jsonPath().getInt("id"), idToOrder);
        Assert.assertEquals(response.jsonPath().getInt("petId"), orderData.getPetId());
        Assert.assertEquals(response.jsonPath().getInt("quantity"), orderData.getPetId());
        Assert.assertEquals(response.jsonPath().getString("status"), orderData.getStatus());
        Assert.assertEquals(response.jsonPath().getString("complete"), "true");

    }

}
