public interface InventoryOperations {
    void addProduct(Product product);
    Product findProduct(int productId);
    void restockProduct(int productId, int quantity);
    void reduceStock(int productId, int quantity);
    void displayProducts();
}
