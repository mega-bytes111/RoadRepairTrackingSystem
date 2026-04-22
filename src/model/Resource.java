package model;

public class Resource {

    private int id;
    private String name;
    private String category;
    private int quantity;
    private int available;

    // ✅ Constructor
    public Resource(int id, String name, String category, int quantity, int available) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.available = available;
    }

    // ✅ Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAvailable() {
        return available;
    }

    // ✅ Optional Setter (future use)
    public void setAvailable(int available) {
        this.available = available;
    }
}