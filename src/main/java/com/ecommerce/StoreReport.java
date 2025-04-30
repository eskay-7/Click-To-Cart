package com.ecommerce;

import java.util.List;

public class StoreReport {
    private Store store;

    public StoreReport(Store store) {
        this.store = store;
    }

    public void printProductsInStore() {
        List<Product> productList = store.getAllProducts();
        if (productList.isEmpty()) {
            System.out.println("SORRY OUR STORE IS CURRENTLY OUT OF STOCK, KINDLY COME BACK LATER");
            System.out.println();
            return;
        }

        int count = 0;
        System.out.println();
        System.out.println("Available Products:");
        System.out.println("0. Go back to main menu");

        for (Product product : productList) {
            count++;
            System.out.printf("%d. %s - $%.2f --> %d-Available\n", count,
                    product.getName(), product.getPrice(), product.getQuantity());
        }
    }
}
