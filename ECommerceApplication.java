import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ECommerceApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final InventoryService inventory = new InventoryService();
    private final Map<Integer, Customer> customers = new LinkedHashMap<>();
    private final Map<Integer, Order> orders = new LinkedHashMap<>();
    private int nextCustomerId = 1;
    private int nextOrderId = 1001;

    public static void main(String[] args) {
        new ECommerceApplication().run();
    }

    private void run() {
        loadSampleData();

        while (true) {
            printMenu();
            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1 -> inventory.displayProducts();
                    case 2 -> addCustomer();
                    case 3 -> viewCustomers();
                    case 4 -> placeOrder();
                    case 5 -> viewOrders();
                    case 6 -> payForOrder();
                    case 7 -> restockProduct();
                    case 0 -> {
                        System.out.println("Thank you for using E-Commerce Application!");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n====================================");
        System.out.println("       E-COMMERCE APPLICATION");
        System.out.println("====================================");
        System.out.println("1. View Products");
        System.out.println("2. Add Customer");
        System.out.println("3. View Customers");
        System.out.println("4. Place Order");
        System.out.println("5. View Orders");
        System.out.println("6. Pay for Order");
        System.out.println("7. Restock Product");
        System.out.println("0. Exit");
    }

    private void loadSampleData() {
        inventory.addProduct(new Product(101, "Laptop", 55000, 5));
        inventory.addProduct(new Product(102, "Headphones", 2500, 10));
        inventory.addProduct(new Product(103, "Keyboard", 1800, 8));
        customers.put(nextCustomerId, new Customer(nextCustomerId, "Sruthi", "sruthi@example.com"));
        nextCustomerId++;
    }

    private void addCustomer() {
        String name = readText("Enter customer name: ");
        String email = readText("Enter email: ");
        Customer customer = new Customer(nextCustomerId++, name, email);
        customers.put(customer.getId(), customer);
        System.out.println("Customer added successfully. ID = " + customer.getId());
    }

    private void viewCustomers() {
        System.out.println("\n--- CUSTOMERS ---");
        if (customers.isEmpty()) {
            System.out.println("No customers.");
            return;
        }
        for (Customer customer : customers.values()) {
            System.out.println(customer);
        }
    }

    private void placeOrder() {
        viewCustomers();
        int customerId = readInt("Enter customer ID: ");
        Customer customer = customers.get(customerId);
        if (customer == null) throw new IllegalArgumentException("Customer not found.");

        Order order = new Order(nextOrderId++, customer);

        inventory.displayProducts();
        int productId = readInt("Enter product ID: ");
        Product product = inventory.findProduct(productId);
        int quantity = readInt("Enter quantity: ");

        if (quantity > product.getStock()) {
            throw new IllegalArgumentException("Insufficient stock.");
        }

        inventory.reduceStock(productId, quantity);
        order.addItem(product, quantity);
        orders.put(order.getOrderId(), order);

        System.out.println("Order placed successfully.");
        System.out.println(order);
    }

    private void viewOrders() {
        System.out.println("\n--- ORDERS ---");
        if (orders.isEmpty()) {
            System.out.println("No orders.");
            return;
        }

        for (Order order : orders.values()) {
            System.out.println(order);
            for (OrderItem item : order.getItems()) {
                System.out.println("   " + item);
            }
        }
    }

    private void payForOrder() {
        viewOrders();
        int orderId = readInt("Enter order ID: ");
        Order order = orders.get(orderId);

        if (order == null) throw new IllegalArgumentException("Order not found.");
        if (order.getStatus() != OrderStatus.CREATED) {
            throw new IllegalArgumentException("Order is not available for payment.");
        }

        System.out.println("1. UPI");
        System.out.println("2. Card");
        int choice = readInt("Choose payment method: ");

        PaymentGateway gateway;
        if (choice == 1) {
            gateway = new UPIPaymentGateway();
        } else if (choice == 2) {
            gateway = new CardPaymentGateway();
        } else {
            throw new IllegalArgumentException("Invalid payment method.");
        }

        System.out.println("Using " + gateway.getGatewayName() + " gateway...");
        if (gateway.pay(order.getTotal())) {
            order.markPaid();
            System.out.println("Order #" + order.getOrderId() + " is now PAID.");
        }
    }

    private void restockProduct() {
        inventory.displayProducts();
        int productId = readInt("Enter product ID: ");
        int quantity = readInt("Enter quantity to add: ");
        inventory.restockProduct(productId, quantity);
        System.out.println("Stock updated successfully.");
    }

    private int readInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}
