package com.ecommerce;

import com.utilities.Console;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static Store store;
    //    private Cart cart;
    private Customer customer;

    public static void main(String[] args) {
        Main obj = new Main();

        store = new Store(new ArrayList<>());
        obj.initializeStore();


        obj.customer = new Customer("Seth",
                new Cart(new ArrayList<>()),
                new Orders(new ArrayList<>()));

        System.out.printf("HELLO %s, WELCOME TO OUR STORE\n", obj.customer.getName());
        System.out.println("---------------------------------");
        obj.mainMenu();

    }

    public void mainMenu() {
        while(true) {
            System.out.println("MAIN MENU");
            System.out.println("1. View Available Products");
            System.out.println("2. View Your Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Orders");
            System.out.println("5. Exit");
            int choice = Console.readNumber("Choose an option: ",1,5);

            switch (choice) {
                case 1 -> productsPage();
                case 2 -> cartPage();
                case 3 -> checkoutPage();
                case 4 -> ordersPage();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
            }
        }
    }

    private void ordersPage() {
        Orders orders = customer.getOrders();
        var orderReport = new OrderReport(orders);

        orderReport.printPreview();
        if(customer.getOrders().getOrdersSize() == 0) {
            System.out.println();
            return;
        }
        System.out.println("0. Go back to main menu");
        int index = Console.readNumber(
                "Choose an order to view more details: ",
                0,customer.getOrders().getOrdersSize());
        if(index == 0)
            return;
        index--;
        orderReport.printOrderSummary(index);
    }

    private void checkoutPage() {
        var cartReport = new CartReport(customer.getCart());

        cartReport.printProductsInCart();
        if(customer.getCartSize() == 0) {
            return;
        }

        System.out.printf("SubTotal: $%.2f\n", customer.getTotalCost());
        int choice = Console.readNumber("Enter 1) to checkout: ",0,1);
        switch (choice) {
            case 0 -> System.out.println();
            case 1 -> {

                if(!customer.checkout()) {
                    System.out.println();
                    return;
                }
                int index = customer.getOrdersSize() -1;
                int orderId = customer.getOrder(index).getOrderId();

                System.out.printf("Order#:%d created successfully.\n", orderId);
                System.out.println("Go to orders section to view your orders");
                approveOrder(customer.getOrder(customer.getOrdersSize()-1));
                System.out.println();
            }
            default -> System.out.println("Error, invalid input");
        }
    }

    public void productsPage() {
        var storeReport = new StoreReport(store);

        if(store.getStoreSize() == 0)
            return;
        storeReport.printProductsInStore();

        int productIndex = Console.readNumber(
                "Enter product number to add to cart: ",
                0,store.getStoreSize());

        if(productIndex == 0) {
            System.out.println();
            return;
        }
        productIndex--;
        Product productInStore = store.getProduct(productIndex);

        int quantityToAdjust = Console.readNumber(
                "Enter your preferred quantity: ",
                1, productInStore.getQuantity());

        addProductToCart(productInStore, quantityToAdjust);
        System.out.println("\n");
    }

    public void addProductToCart(Product productInStore, int quantityToIncrease) {
        int productIndexInCart = customer.getProductIndex(productInStore);
        boolean isAlreadyInCart = (productIndexInCart != -1);  //if index == -1 then it's not in cart already

        if(isAlreadyInCart) {
            int productQuantityInCart = customer.getProductQuantity(productIndexInCart);
            int availableQuantity = productInStore.getQuantity() - productQuantityInCart;

            if(productQuantityInCart < productInStore.getQuantity()
                    && quantityToIncrease <= availableQuantity)
            {
                customer.increaseProductQuantity(productIndexInCart, quantityToIncrease);
                System.out.printf("Quantity of %s successfully increased by %d",
                        productInStore.getName(), quantityToIncrease);
                return;
            }

            System.out.printf("Sorry, you already have %d %s in your cart and cannot add more than " +
                            "%d %s to your cart",productQuantityInCart, productInStore.getName(),
                    productInStore.getQuantity(), productInStore.getName());
            return;
        }
        customer.addProduct(productInStore,quantityToIncrease);
        System.out.printf("%s successfully added to cart", productInStore.getName());
    }

    public void cartPage() {
        var cartReport = new CartReport(customer.getCart());
        cartReport.printProductsInCart();

        if(customer.getCartSize() == 0)
            return;

        int productIndex = Console.readNumber(
                "Enter product number to adjust quantity: ",
                0,customer.getCartSize());
        if (productIndex == 0) {
            System.out.println();
            return;
        }

        productIndex--;
        int choice = Console.readNumber(
                "Enter 1)Increase Quantity OR " +
                        "2)Decrease Quantity: ",1,2);
        Product productInCart = customer.getProduct(productIndex);
        int productIndexInStore = store.getProductIndex(productInCart);
        switch (choice) {
            case 1 -> {
                int productQuantityInStore = store.getProduct(productIndexInStore).getQuantity();
                if (productInCart.getQuantity() == productQuantityInStore) {
                    System.out.println("You cannot increase the quantity for this product anymore");
                    System.out.println();
                    return;
                }
                increaseProductQuantity(productIndex, productIndexInStore);
            }

            case 2 -> decreaseProductQuantity(productIndex);
        }
    }

    private void decreaseProductQuantity(int productIndex) {
        Product product = customer.getProduct(productIndex);
        int quantity = Console.readNumber(
                "Enter quantity to decrease: ",
                1 , product.getQuantity());

        customer.decreaseProductQuantity(productIndex,quantity);
        System.out.printf("Quantity of %s successfully decreased by %d", product.getName(), quantity);
        System.out.println();
        System.out.println();

        product.setQuantity(quantity);
    }

    private void increaseProductQuantity(int productIndex, int productIndexInStore) {
        Product productInCart = customer.getProduct(productIndex);
        int productQuantityInStore = store.getProduct(productIndexInStore).getQuantity();
        int availableQuantity = productQuantityInStore - productInCart.getQuantity();

        int quantityToIncrease = Console.readNumber(
                "Enter quantity to increase: ",
                1 , availableQuantity);

        customer.increaseProductQuantity(productIndex,quantityToIncrease);
//        store.decreaseProductQuantity(productIndexInStore,quantity);
        System.out.printf("Quantity of %s successfully increased by %d", productInCart.getName(), quantityToIncrease);
        System.out.println();
        System.out.println();
    }

    public void approveOrder(Order order) {
        Timer timer = new Timer();
        TimerTask approveTask = new TimerTask() {
            @Override
            public void run() {
                order.setStatus(OrderStatus.CONFIRMED);
                System.out.println("\nOrder successfully confirmed");
            }
        };
        timer.schedule(approveTask, 10000);

    }
    public void initializeStore() {
        store.addProduct(new Product("Apple",2,7));
        store.addProduct(new Product("Sneaker",35,5));
        store.addProduct(new Product("Laptop",105,3));
        store.addProduct(new Product("Phone",70,13));
        store.addProduct(new Product("Watch",20,8));
    }
}