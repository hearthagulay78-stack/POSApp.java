import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SimpleGUI {
    private Cart cart;
    private JTable table;
    private DefaultTableModel model;
    private JLabel totalLabel;

    public SimpleGUI() {
        cart = new Cart();

        JFrame frame = new JFrame("Farm2Market POS");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(Color.WHITE);

        // ===== TITLE =====
        JLabel title = new JLabel("Farm2Market POS", JLabel.CENTER);
        title.setFont(new Font("ATHENS", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        mainPanel.add(title, BorderLayout.NORTH);

        // ===== TABLE (CART) =====
        model = new DefaultTableModel(new String[]{"Product", "Price"}, 0);
        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Cart"));

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ===== PRODUCTS PANEL (ONE BOX WITH TITLE) =====
        JPanel productPanel = new JPanel(new BorderLayout());
        productPanel.setBackground(Color.WHITE);

        productPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Products",
                0, 0,
                new Font("SERIF", Font.BOLD, 16)
        ));

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton seedBtn = createButton("Seeds - ₱50");
        JButton fertBtn = createButton("Fertilizer - ₱100");
        JButton toolBtn = createButton("Tools - ₱200");
        JButton checkoutBtn = createButton("Checkout");

        buttonPanel.add(seedBtn);
        buttonPanel.add(fertBtn);
        buttonPanel.add(toolBtn);
        buttonPanel.add(checkoutBtn);

        productPanel.add(buttonPanel, BorderLayout.CENTER);

        mainPanel.add(productPanel, BorderLayout.WEST);

        // ===== TOTAL =====
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);

        totalLabel = new JLabel("Total: ₱0", JLabel.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        bottomPanel.add(totalLabel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // ===== ACTIONS =====
        seedBtn.addActionListener(e -> addToCart(new Product("Seeds", 50)));
        fertBtn.addActionListener(e -> addToCart(new Product("Fertilizer", 100)));
        toolBtn.addActionListener(e -> addToCart(new Product("Tools", 200)));

        checkoutBtn.addActionListener(e -> checkout());

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // ===== BUTTON STYLE =====
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(101, 67, 33));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Serif", Font.BOLD, 16));
        return btn;
    }

    private void addToCart(Product product) {
        cart.addProduct(product);
        model.addRow(new Object[]{product.getName(), "₱" + product.getPrice()});
        updateTotal();
    }

    private void updateTotal() {
        totalLabel.setText("Total: ₱" + cart.calculateTotal());
    }

    private void checkout() {
        Customer customer = new Customer("Hearth Agulay", true);
        Transaction transaction = new Transaction(cart, customer);

        double total = transaction.getFinalTotal();

        JOptionPane.showMessageDialog(null,
                "Customer: Hearth Agulay\n" +
                "Final Total: ₱" + total,
                "Receipt",
                JOptionPane.INFORMATION_MESSAGE);

        cart.clearCart();
        model.setRowCount(0);
        updateTotal();
    }
}