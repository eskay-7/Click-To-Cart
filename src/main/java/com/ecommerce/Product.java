package com.ecommerce;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private int productId;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        productId = this.hashCode();
    }

    public Product(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.productId = product.getProductId();
    }

    public double getTotalCost() {
        return price * quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuantity(int quantity) {
        if(this.quantity == 0 || quantity < 0)
            throw new IllegalArgumentException("ERROR, quantity of product cannot be negative");
        this.quantity -= quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProductId() {
        return productId;
    }

    public boolean isAvailable() {
        if(quantity == 0)
            return false;
        return true;
    }
}
