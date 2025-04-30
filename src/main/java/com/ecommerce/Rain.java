package com.ecommerce;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Rain {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        System.out.println(dateFormat.format(date));
        DateFormat data = DateFormat.getDateInstance();
        System.out.println(data.format(date));

        List<String> names = new ArrayList<>(List.of("Seth", "Chris", "James"));
        names.set(1, "Frank");
        System.out.println(names.stream().sorted().toList());
        String bull = new String("Frank James Derrick");
        String name = "Seth";

        Greetings myGreet = ()->{
            System.out.println("Good day Mr. " + name);
        };
        myGreet.greet();
        Product product = new Product("S25 Ultra", 1200, 0);
        try {
            product.decreaseQuantity(2);
        }
        catch (IllegalArgumentException exception) {
            System.out.println("ERROR, quantity of product cannot be negative");
        }
        finally {
            System.out.println("Operation completed successful");
        }

    }
}

interface Greetings {
    void greet();
}