package tests;

import base.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.OrderDetails;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestPlaceTheOrderAlreadyExists extends TestBase {

    final int idToOrder = 8;
    public String today = "";

    @BeforeMethod
    public void createOrder() {
        try {
            OrderDetails orderData = new OrderDetails();
            orderData.orderDetails(idToOrder, 1, 1, "placed");

            LocalDateTime todayDate = LocalDateTime.now();
            DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            today = todayDate.format(myFormat);


            String request = "{\n" +
                    "  \"id\": " + orderData.getId() + ",\n" +
                    "  \"petId\": " + orderData.getPetId() + ",\n" +
                    "  \"quantity\": " + orderData.getQuantity() + ",\n" +
                    "  \"shipDate\": \""+today+"T00:00:00.123Z\",\n" +
                    "  \"status\": \"" + orderData.getStatus() + "\",\n" +
                    "  \"complete\": true\n" +
                    "}";

            Response response = sendPostRequest("/store/order", request);
            Assert.assertEquals(response.getStatusCode(), 200);

            Thread.sleep(2000);//need to wait until test data posted
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 3)
    public void testPlaceTheOrderWhichAlreadyExists() {

        OrderDetails orderData = new OrderDetails();
        orderData.orderDetails(idToOrder, 1, 1, "placed");

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

        Assert.assertEquals(response.jsonPath().getInt("id"), idToOrder);
        Assert.assertEquals(response.jsonPath().getInt("petId"), orderData.getPetId());
        Assert.assertEquals(response.jsonPath().getInt("quantity"), orderData.getPetId());
        Assert.assertEquals(response.jsonPath().getString("status"), orderData.getStatus());
        Assert.assertEquals(response.jsonPath().getString("complete"), "true");

    }

}
