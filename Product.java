public class Product {
    private final int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        if (price < 0 || stock < 0) {
            throw new IllegalArgumentException("Price and stock cannot be negative.");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        this.price = price;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");
        stock += quantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0 || quantity > stock) {
            throw new IllegalArgumentException("Invalid quantity or insufficient stock.");
        }
        stock -= quantity;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %-20s | Rs. %.2f | Stock: %d",
                id, name, price, stock);
    }
}
