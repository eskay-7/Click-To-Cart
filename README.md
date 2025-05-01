# Click-To-Cart 🛒
A simple Java-based console shopping application that simulates an online store environment.

## 📌 Overview
**Click-To-Cart** is a console-based application written in Java that allows users to simulate a shopping experience. It showcases object-oriented programming principles through classes like `Product`, `Customer`, `Cart`, and `Store`. The `Main` class serves as the interface for user interaction, delegating tasks to the other components.

This project is ideal for practicing core Java concepts, including class design, collections, control structures, and encapsulation.

## 🧱 Features
- 🛍️ **View available products**
- ➕ **Add products to cart**
- 🗑️ **Remove products from cart**
- 🧾 **View cart and total cost**
- 🛒 **Simulate checkout process**

## 🧩 Class Structure

- `Main`  
  Provides the console interface and delegates operations to other classes.

- `Product`  
  Represents items available for purchase with attributes like ID, name, and price.

- `Customer`  
  Represents a customer with relevant information.

- `Cart`  
  Handles operations like adding/removing products and calculating totals.

- `Store`  
  Holds product inventory and manages available stock.

## 🚀 How to Run
1. Clone the repository:
   ```bash
   git clone git@github.com:eskay-7/Click-To-Cart.git
   ```
2. Compile the Java files:
   ```bash
   javac *.java
   ```
3. Run the application:
   ```bash
   java Main
   ```

## 🛠️ Technologies Used
- Java (Core)
- OOP (Object-Oriented Programming)
- Console-based I/O

## 🎯 Future Improvements
- Integration with a database (e.g., MySQL)
- Unit testing using JUnit

## 🙌 Acknowledgements
This project was created as part of my learning journey as a second-year IT student, exploring software development fundamentals through practical application.

---

Feel free to fork, clone, or contribute!
