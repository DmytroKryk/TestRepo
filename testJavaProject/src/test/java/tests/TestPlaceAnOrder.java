package tests;

import base.TestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import utils.OrderDetails;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPlaceAnOrder extends TestBase {

    @Test(priority = 2)
    public void testPlaceAnOrder() throws JsonProcessingException {

        OrderDetails orderData = new OrderDetails();
        String request = orderData.requestConstructor(159, 1, 1, "placed");

        Response response = sendPostRequest("/store/order", request);
        Assert.assertEquals(response.getStatusCode(), 200);

        Assert.assertEquals(response.jsonPath().getInt("id"), orderData.getId());
        Assert.assertEquals(response.jsonPath().getInt("petId"), orderData.getPetId());
        Assert.assertEquals(response.jsonPath().getInt("quantity"), orderData.getQuantity());
        Assert.assertEquals(response.jsonPath().getString("status"), orderData.getStatus());
        Assert.assertEquals(response.jsonPath().getString("complete"), "true");

    }

}
