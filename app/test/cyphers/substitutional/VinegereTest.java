package cyphers.substitutional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VinegereTest {

    private static int[] generateKeyFromString(String keyStr) {
        int[] key = new int[keyStr.length()];
        for (int i = 0; i < keyStr.length(); i++) {
            key[i] = keyStr.charAt(i) - 'a';
        }
        return key;
    }

    @Test
    void basicEncodeDecode() {
        int[] key;

        key = generateKeyFromString("abc");

        assertEquals("ace", Vinegere.encode("abc", key), "encode basic failed");
        assertEquals("abc", Vinegere.decode("ace", key), "decode basic failed");

        key = generateKeyFromString("zyx");

        assertEquals("zzzc", Vinegere.encode("abcd", key), "encode basic failed");
        assertEquals("abcd", Vinegere.decode("zzzc", key), "decode basic failed");
    }

    @Test
    void invalidInputs() {
        int[] validKey = new int[] {1, 2, 3};
        assertThrows(Exception.class,
                () -> Vinegere.encode(null, validKey),
                "encode should throw exception on null input");
        assertThrows(Exception.class,
                () -> Vinegere.decode(null, validKey),
                "decode should throw exception on null input");

        assertThrows(Exception.class,
                () -> Vinegere.encode("abc!", validKey),
                "encode should throw exception on invalid characters");
        assertThrows(Exception.class,
                () -> Vinegere.decode("abc!", validKey),
                "decode should throw exception on invalid characters");

        assertThrows(Exception.class, () -> Vinegere.encode("abc"), "encode should return null on insufficient args");
        assertThrows(Exception.class, () -> Vinegere.decode("abc"), "decode should return null on insufficient args");

        assertThrows(Exception.class, () -> Vinegere.encode("abc", "offset"),
                "encode should return null on wrong arg types");
        assertThrows(Exception.class, () -> Vinegere.decode("abc", "step"),
                "decode should return null on wrong arg types");
    }
}
