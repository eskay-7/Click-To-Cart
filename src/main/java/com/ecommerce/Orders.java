package com.ecommerce;

import java.util.List;

public class Orders {
    private List<Order> orderList;

    public Orders(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public Order getSpecificOrder(int index) {
        return orderList.get(index);
    }

    public int getOrdersSize() {
        return orderList.size();
    }

//    private void deepCopy(List<Product> productListCopy) {
//        for(Product product : productList)
//            productListCopy.add(new Product(product));
//    }
    public List<Order> getAllOrders() {
//        List<Product> productListCopy = new ArrayList<>();
//        deepCopy(productListCopy);
        return orderList;
    }
}
