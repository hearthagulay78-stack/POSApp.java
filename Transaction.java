public class Transaction {
    private Cart cart;
    private Customer customer;

    public Transaction(Cart cart, Customer customer) {
        this.cart = cart;
        this.customer = customer;
    }

    public double getFinalTotal() {
        double total = cart.calculateTotal();

        if (customer.isMember()) {
            total *= 0.90; // 10% discount
        }

        return total;
    }
}