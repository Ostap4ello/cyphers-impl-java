package cyphers.substitutional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VinegereTest {

    @Test
    void basicEncodeDecode() {
        // Offset test
        assertEquals("bcd", Vinegere.encode("abc", 1, 0), "encode basic failed");
        assertEquals("abc", Vinegere.decode("bcd", 1, 0), "decode basic failed");

        assertEquals("yza", Vinegere.encode("abc", -2, 0), "encode basic failed");
        assertEquals("abc", Vinegere.decode("yza", -2, 0), "decode basic failed");

        // Step test
        assertEquals("ace", Vinegere.encode("abc", 0, 1), "encode with step failed");
        assertEquals("abc", Vinegere.decode("ace", 0, 1), "decode with step failed");

        assertEquals("bbb", Vinegere.encode("abc", 1, -1), "encode with step failed");
        assertEquals("abc", Vinegere.decode("bbb", 1, -1), "decode with step failed");
    }

    @Test
    void invalidInputs() {
        assertThrows(Exception.class,
                () -> Vinegere.encode(null, 1, 1),
                "encode should throw exception on null input");
        assertThrows(Exception.class,
                () -> Vinegere.decode(null, 1, 1),
                "decode should throw exception on null input");

        assertThrows(Exception.class,
                () -> Vinegere.encode("abc!", 1, 1),
                "encode should throw exception on invalid characters");
        assertThrows(Exception.class,
                () -> Vinegere.decode("abc!", 1, 1),
                "decode should throw exception on invalid characters");

        assertThrows(Exception.class, () -> Vinegere.encode("abc"), "encode should return null on insufficient args");
        assertThrows(Exception.class, () -> Vinegere.encode("abc", 1),
                "encode should return null on insufficient args");
        assertThrows(Exception.class, () -> Vinegere.decode("abc"), "decode should return null on insufficient args");
        assertThrows(Exception.class, () -> Vinegere.decode("abc", 1),
                "decode should return null on insufficient args");

        assertThrows(Exception.class, () -> Vinegere.encode("abc", "offset", 1),
                "encode should return null on wrong arg types");
        assertThrows(Exception.class, () -> Vinegere.decode("abc", 1, "step"),
                "decode should return null on wrong arg types");
    }
}
