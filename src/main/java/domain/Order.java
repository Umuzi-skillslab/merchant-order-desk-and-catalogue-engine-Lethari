package domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int id;
    private final Customer customer;
    private final List<OrderItem> items;

    // Constructor: starts with an empty cart
    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    // Add 1 item at a time
    public void addItem(Product product, int quantity) {
        // Safety check - no null or negative quantities
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        items.add(new OrderItem(product, quantity));
    }

    // Calculate total by looping
    public double calculateTotal() {
        double total = 0.0;
        for (OrderItem item : items) {
            total += item.getLineTotal();
        }
        return total;
    }

    // Getters - so other classes can read data
    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    // Updated toString to use the getters
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(id).append("\n");
        sb.append("Customer: ").append(customer.getName()).append("\n");
        sb.append("Items:\n");
        for (OrderItem item : items) {
            sb.append(" - ").append(item.getProduct().getName())
            .append(" x").append(item.getQuantity())
            .append(" = R").append(item.getLineTotal()).append("\n");
        }
        sb.append("Total: R").append(calculateTotal());
        return sb.toString();
    }
}