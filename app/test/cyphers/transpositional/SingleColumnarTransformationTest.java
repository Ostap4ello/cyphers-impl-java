package cyphers.transpositional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SingleColumnarTransformationTest {

    @Test
    void encodeDecodeBehavior0() {
        int[] keys = new int[] { 2, 0, 1 };
        String enc = SingleColumnarTransformation.encode("abcdefg", keys);
        // For keys [2,0,1] and text "abcdefg" => read b,e | c,f | a,d,g -> "becfadg"
        assertEquals("becfadg", enc, "encode failed");

        String dec = SingleColumnarTransformation.decode(enc, keys);
        assertEquals("abcdefg", dec, "decode failed");

    }

    @Test
    void encodeDecodeBehavior1() {
        int[] keys = new int[] { 2, 0, 1 };
        String enc = SingleColumnarTransformation.encode("abcdefgh", keys);
        // For keys [2,0,1] and text "abcdefg" => read b,e,h | c,f | a,d,g -> "behcfadg"
        assertEquals("behcfadg", enc, "encode failed");

        String dec = SingleColumnarTransformation.decode(enc, keys);
        assertEquals("abcdefgh", dec, "decode failed");
    }

    @Test
    void encodeDecodeBehavior2() {
        int[] keys = new int[] { 2, 0, 1 };
        String enc = SingleColumnarTransformation.encode("abcdef", keys);
        // For keys [2,0,1] and text "abcdefg" => read b,e | c,f | a,d -> "becfad"
        assertEquals("becfad", enc, "encode failed");

        String dec = SingleColumnarTransformation.decode(enc, keys);
        assertEquals("abcdef", dec, "decode failed");
    }

    @Test
    void argumentValidationEncode() {
        int[] keys = new int[] { 2, 0, 1 };
        assertThrows(Exception.class, () -> SingleColumnarTransformation.encode(null, keys),
                "encode null input should return null");
        assertThrows(Exception.class, () -> SingleColumnarTransformation.encode("", keys),
                "encode empty input should return null");
        assertThrows(Exception.class, () -> SingleColumnarTransformation.encode("abc"),
                "encode missing args should return null");
        assertThrows(Exception.class, () -> SingleColumnarTransformation.encode("abc", "bad-key-type"),
                "encode wrong arg type should return null");
    }

    @Test
    void argumentValidationDecode() {
        int[] keys = new int[] { 2, 0, 1 };
        assertThrows(Exception.class, () -> SingleColumnarTransformation.decode(null, keys),
                "decode null input should return null");
        assertThrows(Exception.class, () -> SingleColumnarTransformation.decode("", keys),
                "decode empty input should return null");
        assertThrows(Exception.class, () -> SingleColumnarTransformation.decode("abc"),
                "decode missing args should return null");
        assertThrows(Exception.class, () -> SingleColumnarTransformation.decode("abc", "bad-key-type"),
                "decode wrong arg type should return null");
    }
}
