package lab3.storeBilling;

public class Product {
    private final String name;
    private int quantity;
    private double price;
    private Product next;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }

    // Getters
    public void getName() {
        System.out.println("Product Name:" + name);
    }

    public void getQuantity() {
        System.out.println("Quantity: " + quantity);
    }

    public void getPrice() {
        System.out.println("Price: " + price);
    }

    public Product getNext() {
        return next;
    }

    public void showProduct() {
        System.out.println();
        getName();
        getQuantity();
        getPrice();
    }

    // Setters
    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public void setNext(Product newProduct) {
        this.next = newProduct;
    }
}