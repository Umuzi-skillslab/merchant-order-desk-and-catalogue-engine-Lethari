package domain;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import service.OrderService;
import summary.ReceiptPrinter;

@SuppressWarnings("ThrowableResultIgnored")
class OrderTest {

    @Test
    void calculatesTotalForMultipleItems() {
        Customer customer = new Customer(101, "Test", "test@example.com");
        Order order = new Order(1, customer);

        order.addItem(new Product(1, "PS5", new BigDecimal("15600.00")), 1);
        order.addItem(new Product(2, "Controller", new BigDecimal("850.00")), 2);

        assertEquals(new BigDecimal("17300.00"), order.calculateTotal());
        assertEquals(2, order.getItems().size());
    }

    @Test
    void rejectsNullProductAndInvalidQuantity() {
        Customer customer = new Customer(101, "Test", "test@example.com");
        Order order = new Order(2, customer);
        Product product = new Product(1, "Keyboard", new BigDecimal("250.00"));

        Throwable firstThrown = assertThrows(IllegalArgumentException.class, () -> order.addItem(null, 1));
        Throwable secondThrown = assertThrows(IllegalArgumentException.class, () -> order.addItem(product, 0));
        Throwable thirdThrown = assertThrows(IllegalArgumentException.class, () -> order.addItem(product, -1));
        assertTrue(firstThrown instanceof IllegalArgumentException);
        assertTrue(secondThrown instanceof IllegalArgumentException);
        assertTrue(thirdThrown instanceof IllegalArgumentException);
    }

    @Test
    void aggregatesDuplicateProductsIntoOneLine() {
        Customer customer = new Customer(101, "Test", "test@example.com");
        Order order = new Order(3, customer);
        Product product = new Product(1, "Mouse", new BigDecimal("50.00"));

        order.addItem(product, 1);
        order.addItem(product, 2);

        assertEquals(1, order.getItems().size());
        assertEquals(3, order.getItems().get(0).getQuantity());
        assertEquals(new BigDecimal("150.00"), order.calculateTotal());
    }

    @Test
    void emptyOrderHasZeroTotalAndImmutableItemsView() {
        Customer customer = new Customer(101, "Test", "test@example.com");
        Order order = new Order(4, customer);

        assertEquals(new BigDecimal("0.00"), order.calculateTotal());
        assertTrue(order.getItems().isEmpty());
        Throwable thrown = assertThrows(UnsupportedOperationException.class,
                () -> order.getItems().add(new OrderItem(new Product(1, "Test", new BigDecimal("10.00")), 1)));
        assertTrue(thrown instanceof UnsupportedOperationException);
    }

    @Test
    void receiptPrinterFormatsOrderSummary() {
        OrderService orderService = new OrderService();
        ReceiptPrinter printer = new ReceiptPrinter();
        Customer customer = new Customer(101, "Lethabo", "lethabo@email.com");
        Order order = orderService.createOrder(customer);

        orderService.addProductToOrder(order, new Product(1, "PS5", new BigDecimal("15600.00")), 1);
        orderService.addProductToOrder(order, new Product(2, "Controller", new BigDecimal("850.00")), 2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            System.setOut(new PrintStream(outputStream));
            printer.print(order);
        } finally {
            System.setOut(originalOut);
        }

        String receipt = outputStream.toString();
        assertTrue(receipt.contains("PAYNEST RECEIPT"));
        assertTrue(receipt.contains("TOTAL: R17,300.00"));
        assertTrue(receipt.contains("PS5 x1"));
        assertTrue(receipt.contains("Controller x2"));
    }
}
