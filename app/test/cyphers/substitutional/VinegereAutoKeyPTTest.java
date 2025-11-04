package cyphers.substitutional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VinegereAutoKeyPTTest {

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

        // key: abcabcbbbx
        assertEquals("acebcdyzax", VinegereAutoKeyPT.encode("abcbbbxyza", key), "encode basic failed");
        assertEquals("abcbbbxyza", VinegereAutoKeyPT.decode("acebcdyzax", key), "decode basic failed");

    }

    @Test
    void invalidInputs() {
        int[] validKey = new int[] {1, 2, 3};
        assertThrows(Exception.class,
                () -> VinegereAutoKeyPT.encode(null, validKey),
                "encode should throw exception on null input");
        assertThrows(Exception.class,
                () -> VinegereAutoKeyPT.decode(null, validKey),
                "decode should throw exception on null input");

        assertThrows(Exception.class,
                () -> VinegereAutoKeyPT.encode("abc!", validKey),
                "encode should throw exception on invalid characters");
        assertThrows(Exception.class,
                () -> VinegereAutoKeyPT.decode("abc!", validKey),
                "decode should throw exception on invalid characters");

        assertThrows(Exception.class, () -> VinegereAutoKeyPT.encode("abc"), "encode should return null on insufficient args");
        assertThrows(Exception.class, () -> VinegereAutoKeyPT.decode("abc"), "decode should return null on insufficient args");

        assertThrows(Exception.class, () -> VinegereAutoKeyPT.encode("abc", "offset"),
                "encode should return null on wrong arg types");
        assertThrows(Exception.class, () -> VinegereAutoKeyPT.decode("abc", "step"),
                "decode should return null on wrong arg types");
    }
}
