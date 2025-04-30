package com.ecommerce;

public class OrderReport {
    private Orders orders;

    public OrderReport(Orders orders) {
        this.orders = orders;
    }

    public void printOrderSummary(int index) {
        Order order = orders.getSpecificOrder(index);
        System.out.println("Order Summary:");

        System.out.printf("Order#:%d\n", order.getOrderId());
        System.out.printf("Status:%s\n", order.getStatus());
        System.out.printf("Date:%s\n", order.getDateCreated());
        System.out.println();
        System.out.println("Products List:");
        int count = 1;
        for (Product product : order.getAllProducts()) {
            System.out.printf("%d. %s - Quantity: %d, Total: $%.2f\n",count,
                    product.getName(),product.getQuantity(),product.getTotalCost());
            count++;
        }

        System.out.printf("SubTotal: $%.2f\n", order.getTotalCost());
        System.out.println();
    }

    public void printPreview() {
        if(orders.getOrdersSize() == 0) {
            System.out.println("You have no orders available");
            System.out.println();
            return;
        }

        System.out.println("Your Orders");
        int count = 1;
        for(Order order: orders.getAllOrders()) {
            System.out.printf("%d.Order#:%d\tStatus:%s\tTotalCost:$%.2f\n",
                    count, order.getOrderId(),
                    order.getStatus(), order.getTotalCost());
            count++;
        }
    }
}
