package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderDetails {
    private int id;
    private int petId;
    private int quantity;
    private String status;

    // set today value to provide in the request
    LocalDateTime todayDate = LocalDateTime.now();
    DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public String today = todayDate.format(myFormat);

    public String requestConstructor(int id, int petId, int quantity, String status) {
        try {
            this.id = id;
            this.petId = petId;
            this.quantity = quantity;
            this.status = status;
            /*return "{\n" +
                    "  \"id\": "+id+",\n" +
                    "  \"petId\": "+petId+",\n" +
                    "  \"quantity\": "+quantity+",\n" +
                    "  \"shipDate\": \""+today+"T00:00:00.123Z\",\n" +
                    "  \"status\": \""+status+"\",\n" +
                    "  \"complete\": true\n" +
                    "}";*/
            Map<String, Object> body = new HashMap<>();
            body.put("id", id);
            body.put("petId", petId);
            body.put("quantity", quantity);
            body.put("shipDate", today + "T00:00:00.123Z");
            body.put("status", status);
            body.put("complete", true);

            return new ObjectMapper().writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String requestConstructor(int id) {
        this.id = id;
        try {
            /*return "{\n" +
                    "  \"id\": " + id + ",\n" +
                    "  \"petId\": 1,\n" +
                    "  \"quantity\": 1,\n" +
                    "  \"shipDate\": \""+today+"T00:00:00.123Z\",\n" +
                    "  \"status\": \"placed\",\n" +
                    "  \"complete\": true\n" +
                    "}";*/

            Map<String, Object> body = new HashMap<>();
            body.put("id", id);
            body.put("petId", 1);
            body.put("quantity", 1);
            body.put("shipDate", today + "T00:00:00.123Z");
            body.put("status", "placed");
            body.put("complete", true);

            return new ObjectMapper().writeValueAsString(body);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetId() {
        return petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

}
