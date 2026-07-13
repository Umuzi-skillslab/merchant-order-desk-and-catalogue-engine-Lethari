package domain;

public class Product {
    private final int id;
    private final String name;
    private final double price;

//Constructors
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    //Getters to access what is passed in the Constructor
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}