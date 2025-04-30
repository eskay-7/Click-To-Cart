package com.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> productsList;

    public Store(List<Product> productsList) {
        this.productsList = productsList;
    }

    public void decreaseProductQuantity(int index, int quantity) {
        Product product = productsList.get(index);
        product.decreaseQuantity(quantity);

        //If product's quantity becomes 0, then remove it
        if (product.getQuantity() == 0)
            removeProduct(index);
    }

    public void increaseProductQuantity(int index, int quantity) {
        productsList.get(index).increaseQuantity(quantity);
    }

    public void removeProduct(int index) {
        productsList.remove(index);
    }

    public void addProduct(Product product) {
        productsList.add(product);
    }

    public Product getProduct(int index) {
        Product product = productsList.get(index);
        Product productCopy = new Product(product);//Creating a deep copy of the product
        return productCopy;
    }

    public List<Product> getAllProducts() {
        List<Product> productsListCopy = new ArrayList<>();
        deepCopy(productsListCopy);
        return productsListCopy;
    }

    private void deepCopy(List<Product> productsListCopy) {
        for(Product product : productsList)
            productsListCopy.add(new Product(product));
    }

    public int getProductIndex(Product product) {
        int productId = product.getProductId();

        int index = 0;
        for (Product item : productsList) {
            if (productId == item.getProductId())
                return index;
            index++;
        }

        return -1;
    }

    public int getStoreSize() {
        return productsList.size();
    }
}
