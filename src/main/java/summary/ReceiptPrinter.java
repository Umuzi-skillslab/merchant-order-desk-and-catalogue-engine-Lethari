package summary;

import domain.Order;
import domain.OrderItem;

public class ReceiptPrinter {

    public void print(Order order) {
        System.out.println("=================================");
        System.out.println(" PAYNEST RECEIPT");
        System.out.println("=================================");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("---------------------------------");

        for (OrderItem item: order.getItems()) {
            System.out.printf("%s x%d R%.2f%n",
                item.getProduct().getName(),
                item.getQuantity(),
                item.getLineTotal());
        }

        System.out.println("---------------------------------");
        System.out.printf("TOTAL: R%.2f%n", order.calculateTotal());
        System.out.println("=================================");
    }
}