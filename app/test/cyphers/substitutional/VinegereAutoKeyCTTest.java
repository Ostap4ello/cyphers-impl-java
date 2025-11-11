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
    void basicEncypherDecyphere() {
        int[] key;

        key = generateKeyFromString("abc");

        // key: abcacebdfa
        assertEquals("acebdfabca", VinegereAutoKeyCT.encrypt("abcbbbzyxa", key), "encrypt basic failed");
        assertEquals("abcbbbzyxa", VinegereAutoKeyCT.decrypt("acebdfabca", key), "encrypt basic failed");

    }

    @Test
    void invalidInputs() {
        int[] validKey = new int[] {1, 2, 3};
        assertThrows(Exception.class,
                () -> VinegereAutoKeyCT.encrypt(null, validKey),
                "encrypt should throw exception on null input");
        assertThrows(Exception.class,
                () -> VinegereAutoKeyCT.decrypt(null, validKey),
                "decrypt should throw exception on null input");

        assertThrows(Exception.class,
                () -> VinegereAutoKeyCT.encrypt("abc!", validKey),
                "encrypt should throw exception on invalid characters");
        assertThrows(Exception.class,
                () -> VinegereAutoKeyCT.decrypt("abc!", validKey),
                "decrypt should throw exception on invalid characters");

        assertThrows(Exception.class, () -> VinegereAutoKeyCT.encrypt("abc"), "encrypt should return null on insufficient args");
        assertThrows(Exception.class, () -> VinegereAutoKeyCT.decrypt("abc"), "decrypt should return null on insufficient args");

        assertThrows(Exception.class, () -> VinegereAutoKeyCT.encrypt("abc", "offset"),
                "encrypt should return null on wrong arg types");
        assertThrows(Exception.class, () -> VinegereAutoKeyCT.decrypt("abc", "step"),
                "decrypt should return null on wrong arg types");
    }
}
