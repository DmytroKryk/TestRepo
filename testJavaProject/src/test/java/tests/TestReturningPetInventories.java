package tests;

import base.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestReturningPetInventories extends TestBase {

    @Test(priority = 1)
    public void testGetPetInventories() {

        Response response = sendGetRequest("/store/inventory");
        Assert.assertEquals(response.getStatusCode(), 200);

        // Sorry, I need to comment some Assertions below because it's public API that always change


        Assert.assertTrue(response.getBody().asString().contains("available"));
        Assert.assertTrue(response.getBody().asString().contains("pending"));
        Assert.assertTrue(response.getBody().asString().contains("sold"));
/*        Assert.assertTrue(response.getBody().asString().contains("soldout"));
        Assert.assertTrue(response.getBody().asString().contains("packing"));

        Assert.assertEquals(response.jsonPath().getInt("available"), 938);
        Assert.assertEquals(response.jsonPath().getInt("packing"), 1);
        Assert.assertEquals(response.jsonPath().getInt("pending"), 6);
        Assert.assertEquals(response.jsonPath().getInt("sold"), 14);
        Assert.assertEquals(response.jsonPath().getInt("soldout"), 1);
*/
    }

}
