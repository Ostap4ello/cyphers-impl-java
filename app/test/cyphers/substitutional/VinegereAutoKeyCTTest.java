package cyphers.substitutional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VinegereAutoKeyCTTest {

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

        // key: abcacebdfa
        assertEquals("acebdfabca", VinegereAutoKeyCT.encode("abcbbbzyxa", key), "encode basic failed");
        assertEquals("abcbbbzyxa", VinegereAutoKeyCT.decode("acebdfabca", key), "encode basic failed");

    }

    @Test
    void invalidInputs() {
        int[] validKey = new int[] {1, 2, 3};
        assertThrows(Exception.class,
                () -> VinegereAutoKeyCT.encode(null, validKey),
                "encode should throw exception on null input");
        assertThrows(Exception.class,
                () -> VinegereAutoKeyCT.decode(null, validKey),
                "decode should throw exception on null input");

        assertThrows(Exception.class,
                () -> VinegereAutoKeyCT.encode("abc!", validKey),
                "encode should throw exception on invalid characters");
        assertThrows(Exception.class,
                () -> VinegereAutoKeyCT.decode("abc!", validKey),
                "decode should throw exception on invalid characters");

        assertThrows(Exception.class, () -> VinegereAutoKeyCT.encode("abc"), "encode should return null on insufficient args");
        assertThrows(Exception.class, () -> VinegereAutoKeyCT.decode("abc"), "decode should return null on insufficient args");

        assertThrows(Exception.class, () -> VinegereAutoKeyCT.encode("abc", "offset"),
                "encode should return null on wrong arg types");
        assertThrows(Exception.class, () -> VinegereAutoKeyCT.decode("abc", "step"),
                "decode should return null on wrong arg types");
    }
}
