import java.util.LinkedHashMap;
import java.util.Map;

public class InventoryService implements InventoryOperations {
    private final Map<Integer, Product> products = new LinkedHashMap<>();

    @Override
    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product ID already exists.");
        }
        products.put(product.getId(), product);
    }

    @Override
    public Product findProduct(int productId) {
        Product product = products.get(productId);
        if (product == null) throw new IllegalArgumentException("Product not found.");
        return product;
    }

    @Override
    public void restockProduct(int productId, int quantity) {
        findProduct(productId).increaseStock(quantity);
    }

    @Override
    public void reduceStock(int productId, int quantity) {
        findProduct(productId).decreaseStock(quantity);
    }

    @Override
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        System.out.println("\n--- PRODUCT CATALOG ---");
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }
}
