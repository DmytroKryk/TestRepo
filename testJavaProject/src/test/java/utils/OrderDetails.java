package utils;

public class OrderDetails {
    private int id;
    private int petId;
    private int quantity;
    private String status;

    public void orderDetails(int id, int petId, int quantity, String status) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.status = status;
    }

    public void orderDetails(int id) {
        this.id = id;
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
