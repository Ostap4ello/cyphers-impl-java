package cyphers.substitutional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrithemiusTest {

    @Test
    void basicEncodeDecode() {
        // Offset test
        assertEquals("bcd", Trithemius.encode("abc", 1, 0), "encode basic failed");
        assertEquals("abc", Trithemius.decode("bcd", 1, 0), "decode basic failed");

        assertEquals("yza", Trithemius.encode("abc", -2, 0), "encode basic failed");
        assertEquals("abc", Trithemius.decode("yza", -2, 0), "decode basic failed");

        // Step test
        assertEquals("ace", Trithemius.encode("abc", 0, 1), "encode with step failed");
        assertEquals("abc", Trithemius.decode("ace", 0, 1), "decode with step failed");

        assertEquals("bbb", Trithemius.encode("abc", 1, -1), "encode with step failed");
        assertEquals("abc", Trithemius.decode("bbb", 1, -1), "decode with step failed");
    }

    @Test
    void invalidInputs() {
        assertThrows(Exception.class,
                () -> Trithemius.encode(null, 1, 1),
                "encode should throw exception on null input");
        assertThrows(Exception.class,
                () -> Trithemius.decode(null, 1, 1),
                "decode should throw exception on null input");

        assertThrows(Exception.class,
                () -> Trithemius.encode("abc!", 1, 1),
                "encode should throw exception on invalid characters");
        assertThrows(Exception.class,
                () -> Trithemius.decode("abc!", 1, 1),
                "decode should throw exception on invalid characters");

        assertThrows(Exception.class, () -> Trithemius.encode("abc"), "encode should return null on insufficient args");
        assertThrows(Exception.class, () -> Trithemius.encode("abc", 1),
                "encode should return null on insufficient args");
        assertThrows(Exception.class, () -> Trithemius.decode("abc"), "decode should return null on insufficient args");
        assertThrows(Exception.class, () -> Trithemius.decode("abc", 1),
                "decode should return null on insufficient args");

        assertThrows(Exception.class, () -> Trithemius.encode("abc", "offset", 1),
                "encode should return null on wrong arg types");
        assertThrows(Exception.class, () -> Trithemius.decode("abc", 1, "step"),
                "decode should return null on wrong arg types");
    }
}
