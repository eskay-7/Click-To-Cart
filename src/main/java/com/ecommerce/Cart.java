package com.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cartList;

    public Cart(List<Product> cartList) {
        this.cartList = cartList;
    }

    public void emptyCart() {
        cartList.clear();
    }

    public double getTotalCost() {
        double totalCost = 0;
        for (Product product : cartList)
            totalCost += product.getTotalCost();
        return totalCost;
    }

    public void decreaseProductQuantity(int index, int quantity) {
        Product product = cartList.get(index);
        product.decreaseQuantity(quantity);

        //If product's quantity becomes 0, then remove it
        if (product.getQuantity() == 0)
            removeProduct(index);
    }

    public void increaseProductQuantity(int index, int quantity) {
        Product product = cartList.get(index);
        product.increaseQuantity(quantity);
    }

    private void removeProduct(int index) {
        cartList.remove(index);
    }

    /**
     * This method performs a deep copy of the product, hence
     * removing any reference to the original product before it
     * adds it to the cart list
     * @param product
     **/
    public void addProduct(Product product, int quantity) {
        cartList.add(new Product(product));//activating deep copy
        cartList.getLast().setQuantity(quantity);
    }

//    public boolean isEmpty() {
//        return cartList.isEmpty();
//    }

    public List<Product> getAllProducts() {
        List<Product> cartListCopy = new ArrayList<>();
        deepCopy(cartListCopy);
        return cartListCopy;
    }

    public int getCartSize() {
        return cartList.size();
    }

    public Product getProduct(int index) {
        Product product = cartList.get(index);
        Product productCopy = new Product(product);//Creating a deep copy of the product
        return productCopy;
    }

    private void deepCopy(List<Product> cartListCopy) {
        for(Product product : cartList)
            cartListCopy.add(new Product(product));
    }


    public int getProductIndex(Product product) {
        int productId = product.getProductId();
//        cartList.indexOf(product);

        int index = 0;
        for (Product item : cartList) {
            if (productId == item.getProductId())
                return index;
            index++;
        }
        return -1;
    }
}
