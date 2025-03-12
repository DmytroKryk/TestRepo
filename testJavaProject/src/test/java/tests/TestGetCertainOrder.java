package tests;

import base.TestBase;
import org.testng.annotations.BeforeMethod;
import utils.OrderDetails;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestGetCertainOrder extends TestBase {
    final int idToGet = 8;
    public static String today = "";

    @BeforeMethod
    public void createOrder() {
        try {
            OrderDetails orderData = new OrderDetails();
            orderData.orderDetails(idToGet);

            LocalDateTime todayDate = LocalDateTime.now();
            DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            today = todayDate.format(myFormat);

            String request = "{\n" +
                    "  \"id\": " + orderData.getId() + ",\n" +
                    "  \"petId\": 1,\n" +
                    "  \"quantity\": 1,\n" +
                    "  \"shipDate\": \""+today+"T00:00:00.123Z\",\n" +
                    "  \"status\": \"placed\",\n" +
                    "  \"complete\": true\n" +
                    "}";

            Response response = sendPostRequest("/store/order", request);
            Assert.assertEquals(response.getStatusCode(), 200);

            Thread.sleep(3000);//need to wait until test data posted
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 4)
    public void testGetOrderById() {

        Response response = sendGetRequest("/store/order/"+idToGet+"");
        Assert.assertEquals(response.getStatusCode(), 200);

        Assert.assertTrue(response.getBody().asString().contains("id"));
        Assert.assertTrue(response.getBody().asString().contains("petId"));
        Assert.assertTrue(response.getBody().asString().contains("quantity"));
        Assert.assertTrue(response.getBody().asString().contains("shipDate"));
        Assert.assertTrue(response.getBody().asString().contains("status"));
        Assert.assertTrue(response.getBody().asString().contains("complete"));

    }

}
