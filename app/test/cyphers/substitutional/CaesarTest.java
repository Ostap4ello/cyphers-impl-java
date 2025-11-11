package cyphers.substitutional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CaesarTest {

    @Test
    void basicEncypherDecyphere() {
        // Offset test
        assertEquals("bcd", Caesar.encrypt("abc", 1, 0), "encrypt basic failed");
        assertEquals("abc", Caesar.decrypt("bcd", 1, 0), "decrypt basic failed");

        assertEquals("yza", Caesar.encrypt("abc", -2, 0), "encrypt basic failed");
        assertEquals("abc", Caesar.decrypt("yza", -2, 0), "decrypt basic failed");
    }

    @Test
    void invalidInputs() {
        assertThrows(Exception.class,
                () -> Caesar.encrypt(null, 1),
                "encrypt should throw exception on null input");
        assertThrows(Exception.class,
                () -> Caesar.decrypt(null, 1),
                "decrypt should throw exception on null input");

        assertThrows(Exception.class,
                () -> Caesar.encrypt("abc!", 1),
                "encrypt should throw exception on invalid characters");
        assertThrows(Exception.class,
                () -> Caesar.decrypt("abc!", 1),
                "decrypt should throw exception on invalid characters");
    }
}
