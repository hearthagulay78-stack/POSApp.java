import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    public void clearCart() {
        products.clear();
    }
}