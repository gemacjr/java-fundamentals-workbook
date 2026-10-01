package copying;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CopyTest {

    private Product newLaptop() {
        return new Product("Laptop", 999.0, new Category("Electronics", "Gadgets"));
    }

    @Test
    void assignmentIsNotACopy() {
        Product original = newLaptop();
        Product alias = original;          // same object, two names
        assertSame(original, alias);

        // Aliasing an immutable object is harmless: nobody can mutate it.
        // "Changing" it makes a NEW object and re-points only this variable.
        alias = alias.withPrice(1.0);

        assertNotSame(original, alias);
        assertEquals(999.0, original.price()); // original untouched
        assertEquals(1.0, alias.price());
    }

    @Test
    void withersShareTheUnchangedCategory() {
        Product original = newLaptop();
        Product cheaper = original.withPrice(1.0);   // new Product...

        assertNotSame(original, cheaper);
        assertSame(original.category(), cheaper.category()); // ...reusing the same Category
        assertEquals(999.0, original.price());

        // Nobody can mutate the shared Category. "Changing" it produces a new object:
        Category renamed = cheaper.category().withName("Toys");

        assertNotSame(renamed, cheaper.category());
        assertEquals("Electronics", original.category().name());
    }

    @Test
    void recordsCompareByValueNotIdentity() {
        Product a = newLaptop();
        Product b = newLaptop();             // built separately

        assertNotSame(a, b);                 // different objects...
        assertEquals(a, b);                  // ...but equal VALUES (record equals)
        assertEquals(a.hashCode(), b.hashCode());
        assertEquals("Category[name=Electronics, description=Gadgets]",
                a.category().toString());    // generated toString()
    }
}
