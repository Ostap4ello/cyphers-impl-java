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
    void basicEncypherDecyphere() {
        int[] key;

        key = generateKeyFromString("abc");

        // key: abcabcbbbx
        assertEquals("acebcdyzax", VinegereAutoKeyPT.encrypt("abcbbbxyza", key), "encrypt basic failed");
        assertEquals("abcbbbxyza", VinegereAutoKeyPT.decrypt("acebcdyzax", key), "decrypt basic failed");

    }

    @Test
    void invalidInputs() {
        int[] validKey = new int[] {1, 2, 3};
        assertThrows(Exception.class,
                () -> VinegereAutoKeyPT.encrypt(null, validKey),
                "encrypt should throw exception on null input");
        assertThrows(Exception.class,
                () -> VinegereAutoKeyPT.decrypt(null, validKey),
                "decrypt should throw exception on null input");

        assertThrows(Exception.class,
                () -> VinegereAutoKeyPT.encrypt("abc!", validKey),
                "encrypt should throw exception on invalid characters");
        assertThrows(Exception.class,
                () -> VinegereAutoKeyPT.decrypt("abc!", validKey),
                "decrypt should throw exception on invalid characters");

        assertThrows(Exception.class, () -> VinegereAutoKeyPT.encrypt("abc"), "encrypt should return null on insufficient args");
        assertThrows(Exception.class, () -> VinegereAutoKeyPT.decrypt("abc"), "decrypt should return null on insufficient args");

        assertThrows(Exception.class, () -> VinegereAutoKeyPT.encrypt("abc", "offset"),
                "encrypt should return null on wrong arg types");
        assertThrows(Exception.class, () -> VinegereAutoKeyPT.decrypt("abc", "step"),
                "decrypt should return null on wrong arg types");
    }
}
