package cyphers.substitutional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CaesarTest {

    @Test
    void basicEncodeDecode() {
        // Offset test
        assertEquals("bcd", Caesar.encode("abc", 1, 0), "encode basic failed");
        assertEquals("abc", Caesar.decode("bcd", 1, 0), "decode basic failed");

        assertEquals("yza", Caesar.encode("abc", -2, 0), "encode basic failed");
        assertEquals("abc", Caesar.decode("yza", -2, 0), "decode basic failed");
    }

    @Test
    void invalidInputs() {
        assertThrows(Exception.class,
                () -> Caesar.encode(null, 1),
                "encode should throw exception on null input");
        assertThrows(Exception.class,
                () -> Caesar.decode(null, 1),
                "decode should throw exception on null input");

        assertThrows(Exception.class,
                () -> Caesar.encode("abc!", 1),
                "encode should throw exception on invalid characters");
        assertThrows(Exception.class,
                () -> Caesar.decode("abc!", 1),
                "decode should throw exception on invalid characters");
    }
}
