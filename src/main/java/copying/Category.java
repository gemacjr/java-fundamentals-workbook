package copying;

import java.io.Serializable;

// RECORD: the compiler generates what we hand-wrote before -- a final class,
// private final fields, a canonical constructor, accessors name()/description(),
// plus value-based equals(), hashCode() and toString().
public record Category(String name, String description) implements Serializable {

    // "Wither": return a NEW Category instead of mutating this one.
    public Category withName(String newName) {
        return new Category(newName, description);
    }
}
