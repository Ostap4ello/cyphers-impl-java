
package cyphers.substitutional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PolybiusTest {

    @Test
    void basicEncypherDecyphere() {
        assertEquals("111213", Polybius.encrypt("abc"), "encrypt basic failed");
        assertEquals("abc", Polybius.decrypt("111213"), "decrypt basic failed");

        assertEquals("2122232424", Polybius.encrypt("fghji"), "encrypt basic failed");
        // Note: h->i
        assertEquals("fghii", Polybius.decrypt("2122232424"), "decrypt basic failed");

        assertEquals("535455", Polybius.encrypt("xyz"), "encrypt basic failed");
        assertEquals("xyz", Polybius.decrypt("535455"), "decrypt basic failed");
    }

    @Test
    void invalidInputs() {
        assertThrows(Exception.class,
                () -> Polybius.encrypt(null, 1),
                "encrypt should throw exception on null input");
        assertThrows(Exception.class,
                () -> Polybius.decrypt(null, 1),
                "decrypt should throw exception on null input");

        assertThrows(Exception.class,
                () -> Polybius.encrypt("abc!", 1),
                "encrypt should throw exception on invalid characters");
        assertThrows(Exception.class,
                () -> Polybius.decrypt("abc!", 1),
                "decrypt should throw exception on invalid characters");
    }
}
