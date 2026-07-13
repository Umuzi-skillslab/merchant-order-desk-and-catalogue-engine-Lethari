package domain;

public class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) { // 3. Constructor
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {//Getters
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getLineTotal(){
        return product.getPrice() * quantity;
    }
}