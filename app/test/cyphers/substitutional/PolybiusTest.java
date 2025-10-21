
package cyphers.substitutional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PolybiusTest {

    @Test
    void basicEncodeDecode() {
        assertEquals("111213", Polybius.encode("abc"), "encode basic failed");
        assertEquals("abc", Polybius.decode("111213"), "decode basic failed");

        assertEquals("2122232424", Polybius.encode("fghji"), "encode basic failed");
        // Note: h->i
        assertEquals("fghii", Polybius.decode("2122232424"), "decode basic failed");

        assertEquals("535455", Polybius.encode("xyz"), "encode basic failed");
        assertEquals("xyz", Polybius.decode("535455"), "decode basic failed");
    }

    @Test
    void invalidInputs() {
        assertThrows(Exception.class,
                () -> Polybius.encode(null, 1),
                "encode should throw exception on null input");
        assertThrows(Exception.class,
                () -> Polybius.decode(null, 1),
                "decode should throw exception on null input");

        assertThrows(Exception.class,
                () -> Polybius.encode("abc!", 1),
                "encode should throw exception on invalid characters");
        assertThrows(Exception.class,
                () -> Polybius.decode("abc!", 1),
                "decode should throw exception on invalid characters");
    }
}
