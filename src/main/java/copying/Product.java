package copying;

import java.io.Serializable;

// RECORD: final class, private final fields, canonical constructor, accessors
// name()/price()/category(), and value-based equals/hashCode/toString -- all generated.
// Every component is immutable (String, double, Category record), so nothing
// reachable from a Product can change -> it never needs copying; share it freely.
public record Product(String name, double price, Category category) implements Serializable {

    // Withers: "changing" a Product builds a new one, sharing the immutable Category.
    public Product withName(String newName) { return new Product(newName, price, category); }
    public Product withPrice(double newPrice) { return new Product(name, newPrice, category); }
}
