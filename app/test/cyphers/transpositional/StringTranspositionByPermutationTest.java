package cyphers.transpositional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringTranspositionByPermutationTest {

    @Test
    void encryptDecyphereExactMultiple() {
        int[] keys = new int[] { 2, 0, 1 };
        String enc = StringTranspositionByPermutation.encrypt("abcdef", keys);
        assertEquals("cabfde", enc, "encrypt failed");
        String dec = StringTranspositionByPermutation.decrypt(enc, keys);
        assertEquals("abcdef", dec, "decrypt failed");
    }

    @Test
    void encryptDecyphereWithPadding() {
        int[] keys = new int[] { 2, 0, 1 };
        String encPad = StringTranspositionByPermutation.encrypt("abcde", keys);
        assertEquals("cabxde", encPad, "encrypt with padding failed");

        String decPad = StringTranspositionByPermutation.decrypt(encPad, keys);
        assertEquals("abcdex", decPad, "decrypt with padding behavior mismatch");
    }

    @Test
    void invalidInputs() {
        int[] keys = new int[] { 2, 0, 1 };
        assertThrows(Exception.class, () -> StringTranspositionByPermutation.encrypt("", keys),
                "encrypt empty should return null");
        assertThrows(Exception.class, () -> StringTranspositionByPermutation.decrypt("", keys),
                "decrypt empty should return null");
        assertThrows(Exception.class, () -> StringTranspositionByPermutation.encrypt("abc"),
                "encrypt missing args should return null");
        assertThrows(Exception.class, () -> StringTranspositionByPermutation.decrypt("abc"),
                "decrypt missing args should return null");
    }
}
