package domain;

public class OrderTest {
    public static void main(String[] args) {
        Customer customer = new Customer(101, "Test", "test@example.com");
        Order order = new Order(101, customer);
        order.addItem(new Product(1, "Item A", 100.00), 2);
        order.addItem(new Product(2, "Item B", 50.00), 1);

        if (Math.abs(order.calculateTotal() - 250.00) > 0.001) {
            throw new AssertionError("Expected 250.00 but got " + order.calculateTotal());
        }
    }
}
