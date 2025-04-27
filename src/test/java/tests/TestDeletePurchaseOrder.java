package tests;

import base.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import utils.OrderDetails;

public class TestDeletePurchaseOrder extends TestBase {
    final int idToDelete = 8;

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void createOrder() {
        try {
            OrderDetails orderData = new OrderDetails();
            String request = orderData.requestConstructor(idToDelete, 1, 1, "placed");

            Response response = sendPostRequest("/store/order", request);
            Assert.assertEquals(response.getStatusCode(), 200);

            Thread.sleep(6000);//wait until test data posted
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 6)
    public void testDeletePurchasedOrderById() {

        Response response = sendDeleteRequest("/store/order/"+idToDelete);
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(response.jsonPath().get("message"), String.valueOf(idToDelete));

        softAssert.assertAll();
    }

}
