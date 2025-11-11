package cyphers.substitutional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrithemiusTest {

    @Test
    void basicEncypherDecyphere() {
        // Offset test
        assertEquals("bcd", Trithemius.encrypt("abc", 1, 0), "encrypt basic failed");
        assertEquals("abc", Trithemius.decrypt("bcd", 1, 0), "decrypt basic failed");

        assertEquals("yza", Trithemius.encrypt("abc", -2, 0), "encrypt basic failed");
        assertEquals("abc", Trithemius.decrypt("yza", -2, 0), "decrypt basic failed");

        // Step test
        assertEquals("ace", Trithemius.encrypt("abc", 0, 1), "encrypt with step failed");
        assertEquals("abc", Trithemius.decrypt("ace", 0, 1), "decrypt with step failed");

        assertEquals("bbb", Trithemius.encrypt("abc", 1, -1), "encrypt with step failed");
        assertEquals("abc", Trithemius.decrypt("bbb", 1, -1), "decrypt with step failed");
    }

    @Test
    void invalidInputs() {
        assertThrows(Exception.class,
                () -> Trithemius.encrypt(null, 1, 1),
                "encrypt should throw exception on null input");
        assertThrows(Exception.class,
                () -> Trithemius.decrypt(null, 1, 1),
                "decrypt should throw exception on null input");

        assertThrows(Exception.class,
                () -> Trithemius.encrypt("abc!", 1, 1),
                "encrypt should throw exception on invalid characters");
        assertThrows(Exception.class,
                () -> Trithemius.decrypt("abc!", 1, 1),
                "decrypt should throw exception on invalid characters");

        assertThrows(Exception.class, () -> Trithemius.encrypt("abc"), "encrypt should return null on insufficient args");
        assertThrows(Exception.class, () -> Trithemius.encrypt("abc", 1),
                "encrypt should return null on insufficient args");
        assertThrows(Exception.class, () -> Trithemius.decrypt("abc"), "decrypt should return null on insufficient args");
        assertThrows(Exception.class, () -> Trithemius.decrypt("abc", 1),
                "decrypt should return null on insufficient args");

        assertThrows(Exception.class, () -> Trithemius.encrypt("abc", "offset", 1),
                "encrypt should return null on wrong arg types");
        assertThrows(Exception.class, () -> Trithemius.decrypt("abc", 1, "step"),
                "decrypt should return null on wrong arg types");
    }
}
