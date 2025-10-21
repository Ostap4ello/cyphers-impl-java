package cyphers.transpositional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RocheTest {

    @Test
    void encodeDecodeBehavior0() {
        /*
         * 2 0 1
         *
         * a b c
         * d e
         * f
         */
        int[] keys = new int[] { 2, 0, 1 };
        String enc = Roche.encode("abcdef", keys);
        assertEquals("bceadf", enc, "encode failed");

        String dec = Roche.decode(enc, keys);
        assertEquals("abcdef", dec, "decode failed");
    }

    void encodeDecodeBehavior1() {
        /*
         * 2 1 0 3
         *
         * a b c d
         * e f   g
         * h     i
         *       j
         */
        int[] keys = new int[] { 2, 1, 0, 3 };
        String enc = Roche.encode("abcdefghij", keys);
        assertEquals("cbfaehdgij", enc, "encode failed");

        String dec = Roche.decode(enc, keys);
        assertEquals("abcdefghij", dec, "decode failed");
    }

    void encodeDecodeBehavior2() {
        /*
         * 2 1 0 3
         *
         * a b c d
         * e f   g
         *
         *
         */
        int[] keys = new int[] { 2, 1, 0, 3 };
        String enc = Roche.encode("abcdefg", keys);
        assertEquals("cbfaedg", enc, "encode failed");

        String dec = Roche.decode(enc, keys);
        assertEquals("abcdefg", dec, "decode failed");

    }

    // @Test
    // void encodeDecodeBehavior1() {
    // int[] keys = new int[] { 2, 0, 1 };
    // String enc = SingleColumnarTransformation.encode("abcdefgh", keys);
    // // For keys [2,0,1] and text "abcdefg" => read b,e,h | c,f | a,d,g ->
    // "behcfadg"
    // assertEquals("behcfadg", enc, "encode failed");
    //
    // String dec = SingleColumnarTransformation.decode(enc, keys);
    // assertEquals("abcdefgh", dec, "decode failed");
    // }
    //
    // @Test
    // void encodeDecodeBehavior2() {
    // int[] keys = new int[] { 2, 0, 1 };
    // String enc = SingleColumnarTransformation.encode("abcdef", keys);
    // // For keys [2,0,1] and text "abcdefg" => read b,e | c,f | a,d -> "becfad"
    // assertEquals("becfad", enc, "encode failed");
    //
    // String dec = SingleColumnarTransformation.decode(enc, keys);
    // assertEquals("abcdef", dec, "decode failed");
    // }
    //
    // @Test
    // void argumentValidationEncode() {
    // int[] keys = new int[] { 2, 0, 1 };
    // assertThrows(Exception.class, () -> SingleColumnarTransformation.encode(null,
    // keys),
    // "encode null input should return null");
    // assertThrows(Exception.class, () -> SingleColumnarTransformation.encode("",
    // keys),
    // "encode empty input should return null");
    // assertThrows(Exception.class, () ->
    // SingleColumnarTransformation.encode("abc"),
    // "encode missing args should return null");
    // assertThrows(Exception.class, () ->
    // SingleColumnarTransformation.encode("abc", "bad-key-type"),
    // "encode wrong arg type should return null");
    // }
    //
    // @Test
    // void argumentValidationDecode() {
    // int[] keys = new int[] { 2, 0, 1 };
    // assertThrows(Exception.class, () -> SingleColumnarTransformation.decode(null,
    // keys),
    // "decode null input should return null");
    // assertThrows(Exception.class, () -> SingleColumnarTransformation.decode("",
    // keys),
    // "decode empty input should return null");
    // assertThrows(Exception.class, () ->
    // SingleColumnarTransformation.decode("abc"),
    // "decode missing args should return null");
    // assertThrows(Exception.class, () ->
    // SingleColumnarTransformation.decode("abc", "bad-key-type"),
    // "decode wrong arg type should return null");
    // }
}
