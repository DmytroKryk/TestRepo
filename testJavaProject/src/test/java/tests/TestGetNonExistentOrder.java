package tests;

import base.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.OrderDetails;

public class TestGetNonExistentOrder extends TestBase {

    @Test(priority = 5)
    public void testGetOrderById() {

        OrderDetails orderData = new OrderDetails();
        orderData.orderDetails(0, 1, 1, "placed");

        Response response = sendGetRequest("/store/order/"+orderData.getId()+"");
        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.jsonPath().get("type"), "error");
        Assert.assertEquals(response.jsonPath().get("message"), "Order not found");

    }

}
