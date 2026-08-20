# E-Commerce Application - Enhanced OOP Console Project

## Requirements
- Java JDK 17 or later
- Any Java IDE: IntelliJ IDEA, Eclipse, or VS Code

## Run from terminal
Open terminal inside the `src` folder:

```bash
javac *.java
java ECommerceApplication
```

## Main OOP concepts demonstrated
- Encapsulation: private fields with methods
- Abstraction: InventoryOperations and PaymentGateway interfaces
- Polymorphism: PaymentGateway reference can hold UPI/Card implementations
- Composition: Order contains OrderItem objects
- Collections: Map and List
- Enum: OrderStatus
- Exception handling: validation using IllegalArgumentException
- Separation of responsibility: inventory logic is inside InventoryService

## Menu
1. View Products
2. Add Customer
3. View Customers
4. Place Order
5. View Orders
6. Pay for Order
7. Restock Product
0. Exit
