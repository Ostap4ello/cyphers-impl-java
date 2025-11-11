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
    void basicEncypherDecyphere() {
        int[] key;

        key = generateKeyFromString("abc");

        assertEquals("ace", Vinegere.encrypt("abc", key), "encrypt basic failed");
        assertEquals("abc", Vinegere.decrypt("ace", key), "decrypt basic failed");

        key = generateKeyFromString("zyx");

        assertEquals("zzzc", Vinegere.encrypt("abcd", key), "encrypt basic failed");
        assertEquals("abcd", Vinegere.decrypt("zzzc", key), "decrypt basic failed");
    }

    @Test
    void invalidInputs() {
        int[] validKey = new int[] {1, 2, 3};
        assertThrows(Exception.class,
                () -> Vinegere.encrypt(null, validKey),
                "encrypt should throw exception on null input");
        assertThrows(Exception.class,
                () -> Vinegere.decrypt(null, validKey),
                "decrypt should throw exception on null input");

        assertThrows(Exception.class,
                () -> Vinegere.encrypt("abc!", validKey),
                "encrypt should throw exception on invalid characters");
        assertThrows(Exception.class,
                () -> Vinegere.decrypt("abc!", validKey),
                "decrypt should throw exception on invalid characters");

        assertThrows(Exception.class, () -> Vinegere.encrypt("abc"), "encrypt should return null on insufficient args");
        assertThrows(Exception.class, () -> Vinegere.decrypt("abc"), "decrypt should return null on insufficient args");

        assertThrows(Exception.class, () -> Vinegere.encrypt("abc", "offset"),
                "encrypt should return null on wrong arg types");
        assertThrows(Exception.class, () -> Vinegere.decrypt("abc", "step"),
                "decrypt should return null on wrong arg types");
    }
}
