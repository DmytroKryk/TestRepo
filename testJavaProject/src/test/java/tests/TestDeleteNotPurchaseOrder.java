package tests;

import base.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDeleteNotPurchaseOrder extends TestBase {

    @Test(priority = 7)
    public void testDeleteNotPurchasedOrderById() {
        int id = -1;
        Response response = sendDeleteRequest("/store/order/"+id);
        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.jsonPath().get("message"), "Order Not Found");

    }

}
