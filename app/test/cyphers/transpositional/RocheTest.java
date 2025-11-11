package cyphers.transpositional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RocheTest {

    @Test
    void encryptDecyphereBehavior0() {
        /*
         * 2 0 1
         *
         * a b c
         * d e
         * f
         */
        int[] keys = new int[] { 2, 0, 1 };
        String enc = Roche.encrypt("abcdef", keys);
        assertEquals("bceadf", enc, "encrypt failed");

        String dec = Roche.decrypt(enc, keys);
        assertEquals("abcdef", dec, "decrypt failed");
    }

    void encryptDecyphereBehavior1() {
        /*
         * 2 1 0 3
         *
         * a b c d
         * e f   g
         * h     i
         *       j
         */
        int[] keys = new int[] { 2, 1, 0, 3 };
        String enc = Roche.encrypt("abcdefghij", keys);
        assertEquals("cbfaehdgij", enc, "encrypt failed");

        String dec = Roche.decrypt(enc, keys);
        assertEquals("abcdefghij", dec, "decrypt failed");
    }

    void encryptDecyphereBehavior2() {
        /*
         * 2 1 0 3
         *
         * a b c d
         * e f   g
         *
         *
         */
        int[] keys = new int[] { 2, 1, 0, 3 };
        String enc = Roche.encrypt("abcdefg", keys);
        assertEquals("cbfaedg", enc, "encrypt failed");

        String dec = Roche.decrypt(enc, keys);
        assertEquals("abcdefg", dec, "decrypt failed");

    }

    // @Test
    // void encryptDecyphereBehavior1() {
    // int[] keys = new int[] { 2, 0, 1 };
    // String enc = SingleColumnarTransformation.encrypt("abcdefgh", keys);
    // // For keys [2,0,1] and text "abcdefg" => read b,e,h | c,f | a,d,g ->
    // "behcfadg"
    // assertEquals("behcfadg", enc, "encrypt failed");
    //
    // String dec = SingleColumnarTransformation.decrypt(enc, keys);
    // assertEquals("abcdefgh", dec, "decrypt failed");
    // }
    //
    // @Test
    // void encryptDecyphereBehavior2() {
    // int[] keys = new int[] { 2, 0, 1 };
    // String enc = SingleColumnarTransformation.encrypt("abcdef", keys);
    // // For keys [2,0,1] and text "abcdefg" => read b,e | c,f | a,d -> "becfad"
    // assertEquals("becfad", enc, "encrypt failed");
    //
    // String dec = SingleColumnarTransformation.decrypt(enc, keys);
    // assertEquals("abcdef", dec, "decrypt failed");
    // }
    //
    // @Test
    // void argumentValidationEncypher() {
    // int[] keys = new int[] { 2, 0, 1 };
    // assertThrows(Exception.class, () -> SingleColumnarTransformation.encrypt(null,
    // keys),
    // "encrypt null input should return null");
    // assertThrows(Exception.class, () -> SingleColumnarTransformation.encrypt("",
    // keys),
    // "encrypt empty input should return null");
    // assertThrows(Exception.class, () ->
    // SingleColumnarTransformation.encrypt("abc"),
    // "encrypt missing args should return null");
    // assertThrows(Exception.class, () ->
    // SingleColumnarTransformation.encrypt("abc", "bad-key-type"),
    // "encrypt wrong arg type should return null");
    // }
    //
    // @Test
    // void argumentValidationDecyphere() {
    // int[] keys = new int[] { 2, 0, 1 };
    // assertThrows(Exception.class, () -> SingleColumnarTransformation.decrypt(null,
    // keys),
    // "decrypt null input should return null");
    // assertThrows(Exception.class, () -> SingleColumnarTransformation.decrypt("",
    // keys),
    // "decrypt empty input should return null");
    // assertThrows(Exception.class, () ->
    // SingleColumnarTransformation.decrypt("abc"),
    // "decrypt missing args should return null");
    // assertThrows(Exception.class, () ->
    // SingleColumnarTransformation.decrypt("abc", "bad-key-type"),
    // "decrypt wrong arg type should return null");
    // }
}
