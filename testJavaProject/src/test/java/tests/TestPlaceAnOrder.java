package tests;

import base.TestBase;
import utils.OrderDetails;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TestPlaceAnOrder extends TestBase {
    public static String today = "";

    @Test(priority = 2)
    public void testPlaceAnOrder() {

        OrderDetails orderData = new OrderDetails();
        orderData.orderDetails(159, 1, 1, "placed");

        LocalDateTime todayDate = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        today = todayDate.format(myFormat);

        String request ="{\n" +
                "  \"id\": "+orderData.getId()+",\n" +
                "  \"petId\": "+orderData.getPetId()+",\n" +
                "  \"quantity\": "+orderData.getQuantity()+",\n" +
                "  \"shipDate\": \""+today+"T00:00:00.123Z\",\n" +
                "  \"status\": \""+orderData.getStatus()+"\",\n" +
                "  \"complete\": true\n" +
                "}";

        Response response = sendPostRequest("/store/order", request);
        Assert.assertEquals(response.getStatusCode(), 200);

        int id = response.jsonPath().getInt("id");

        Assert.assertEquals(response.jsonPath().getInt("id"), orderData.getId());
        Assert.assertEquals(response.jsonPath().getInt("petId"), orderData.getPetId());
        Assert.assertEquals(response.jsonPath().getInt("quantity"), orderData.getPetId());
        Assert.assertEquals(response.jsonPath().getString("status"), orderData.getStatus());
        Assert.assertEquals(response.jsonPath().getString("complete"), "true");

    }

}
