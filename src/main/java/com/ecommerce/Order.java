package com.ecommerce;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private List<Product> productList;
    private OrderStatus status;
    private final double totalCost;
    private final int orderId;
    private final LocalDateTime dateCreated;

    public Order(List<Product> productList, double totalCost) {
        this.productList = productList;
        this.totalCost = totalCost;
        status = OrderStatus.PENDING;
        this.dateCreated = LocalDateTime.now();
        orderId = this.hashCode();
    }

    public int getOrderId() {
        return orderId;
    }

    public double getTotalCost() {
        return totalCost;
    }

    private void deepCopy(List<Product> productListCopy) {
        for(Product product : productList)
            productListCopy.add(new Product(product));
    }

    public List<Product> getAllProducts() {
        List<Product> productListCopy = new ArrayList<>();
        deepCopy(productListCopy);
        return productListCopy;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getDateCreated() {
        var format = DateTimeFormatter.ofPattern("dd-MM-yyyy    hh:mm:ss");
        return dateCreated.format(format);
    }
}
