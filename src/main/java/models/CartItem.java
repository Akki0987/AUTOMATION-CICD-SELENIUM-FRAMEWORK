package models;

public class CartItem {

    private String productName;
    private int price;
    private int quantity;
    private int totalPrice;

    public CartItem(String productName, int price, int quantity, int totalPrice) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getProductName() { return productName; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getTotalPrice() { return totalPrice; }
}
