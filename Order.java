import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final int orderId;
    private final Customer customer;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.CREATED;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public OrderStatus getStatus() { return status; }
    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) total += item.getSubtotal();
        return total;
    }

    public void markPaid() { status = OrderStatus.PAID; }
    public void cancel() { status = OrderStatus.CANCELLED; }

    @Override
    public String toString() {
        return "Order #" + orderId + " | Customer: " + customer.getName() +
                " | Status: " + status + " | Total: Rs. " +
                String.format("%.2f", getTotal());
    }
}
