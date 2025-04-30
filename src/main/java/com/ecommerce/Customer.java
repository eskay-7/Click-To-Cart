package com.ecommerce;

public class Customer {
    private String name;
    private Cart cart;
    private Orders orders;
    private double balance;
    private final int customerId;


    public Customer(String name, Cart cart, Orders orders) {
        this.name = name;
        this.cart = cart;
        this.orders = orders;
        balance = 500.00;
        customerId = this.hashCode();
    }

    public int getCartSize() {
        return cart.getCartSize();
    }

    public void increaseProductQuantity(int index, int quantity) {
        cart.increaseProductQuantity(index, quantity);
    }

    public double getTotalCost() {
        return cart.getTotalCost();
    }
    public int getProductIndex(Product product) {
        return cart.getProductIndex(product);
    }

    public Product getProduct(int index) {
        return cart.getProduct(index);
    }


    public void addProduct(Product product, int quantity) {
        cart.addProduct(product, quantity);
    }
    public void decreaseProductQuantity(int index, int quantity) {
        cart.decreaseProductQuantity(index, quantity);
    }

    public String getName() {
        return name;
    }

    public Cart getCart() {
        return cart;
    }

    public boolean checkout() {
        if(balance < cart.getTotalCost()) {
            System.out.printf(
                    "Sorry, your account balance $%.2f is " +
                    "less than order amount $%.2f\n",
                    balance, cart.getTotalCost());
            return false;
        }
        Order order = new Order(cart.getAllProducts(),cart.getTotalCost());
        orders.addOrder(order);
        cart.emptyCart();
        return true;
    }

    public Orders getOrders() {
        return orders;
    }

    public int getProductQuantity(int index) {
        return cart.getProduct(index).getQuantity();
    }

    public Order getOrder(int index) {
        return orders.getSpecificOrder(index);
    }

    public int getOrdersSize() {
        return orders.getOrdersSize();
    }
}
