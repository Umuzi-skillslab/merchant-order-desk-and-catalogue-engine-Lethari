package App;

import domain.Customer;
import domain.Order;
import domain.Product;
import service.OrderService;
import summary.ReceiptPrinter;

public class PayNestApplication {
    public static void main(String[] args) {
        OrderService orderService = new OrderService(); // use service
        ReceiptPrinter printer = new ReceiptPrinter(); // use summary

        Customer customer = new Customer(101, "Lethabo", "lethabo@email.com");
        Order order = orderService.createOrder(customer); // create via service

        orderService.addProductToOrder(order, new Product(1, "PS5", 15600.00), 1);
        orderService.addProductToOrder(order, new Product(2, "Controller", 850.00), 2);

        printer.print(order); // print via summary
    }
}