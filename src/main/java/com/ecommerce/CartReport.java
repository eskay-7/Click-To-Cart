package com.ecommerce;

import java.util.List;

public class CartReport {
    private Cart cart;

    public CartReport(Cart cart) {
        this.cart = cart;
    }

    public void printProductsInCart() {
        List<Product> productList = cart.getAllProducts();
        if(cart.getCartSize() == 0) {
            System.out.println("Your cart is empty");
            System.out.println();
            return;
        }
        System.out.println();
        System.out.println("Cart Summary:");

        int count = 1;
        for (Product product : productList) {
            System.out.printf("%d. %s - Quantity: %d, Total: $%.2f\n",count,
                    product.getName(),product.getQuantity(),product.getTotalCost());
            count++;
        }
        System.out.println("0. Go back to main menu");
    }
}
