package App;

import domain.Customer;
import domain.Order;
import service.OrderService;
import service.ProductCatalog;
import summary.ReceiptPrinter;

public class PayNestApplication {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        ReceiptPrinter printer = new ReceiptPrinter();
        ProductCatalog productCatalog = new ProductCatalog();

        Customer customer = new Customer(101, "Lethabo", "lethabo@email.com");
        Order order = orderService.createOrder(customer);

        orderService.addProductToOrder(order, productCatalog.findById(1), 1);
        orderService.addProductToOrder(order, productCatalog.findById(2), 2);

        printer.print(order);
    }
}