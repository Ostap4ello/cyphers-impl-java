package cyphers.transpositional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringTranspositionByPermutationTest {

    @Test
    void encodeDecodeExactMultiple() {
        int[] keys = new int[] { 2, 0, 1 };
        String enc = StringTranspositionByPermutation.encode("abcdef", keys);
        assertEquals("cabfde", enc, "encode failed");
        String dec = StringTranspositionByPermutation.decode(enc, keys);
        assertEquals("abcdef", dec, "decode failed");
    }

    @Test
    void encodeDecodeWithPadding() {
        int[] keys = new int[] { 2, 0, 1 };
        String encPad = StringTranspositionByPermutation.encode("abcde", keys);
        assertEquals("cabxde", encPad, "encode with padding failed");

        String decPad = StringTranspositionByPermutation.decode(encPad, keys);
        assertEquals("abcdex", decPad, "decode with padding behavior mismatch");
    }

    @Test
    void invalidInputs() {
        int[] keys = new int[] { 2, 0, 1 };
        assertThrows(Exception.class, () -> StringTranspositionByPermutation.encode("", keys),
                "encode empty should return null");
        assertThrows(Exception.class, () -> StringTranspositionByPermutation.decode("", keys),
                "decode empty should return null");
        assertThrows(Exception.class, () -> StringTranspositionByPermutation.encode("abc"),
                "encode missing args should return null");
        assertThrows(Exception.class, () -> StringTranspositionByPermutation.decode("abc"),
                "decode missing args should return null");
    }
}
