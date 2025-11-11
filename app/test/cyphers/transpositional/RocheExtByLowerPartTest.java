package cyphers.transpositional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RocheExtByLowerPartTest {

    @Test
    void encryptDecyphereBehavior0() {
        /*
         * 2 0 1
         *
         * a b c
         * d E f
         * g H I
         */
        int[] keys = new int[] { 2, 0, 1 };
        String enc = RocheExtByLowerPart.encrypt("abcdefghi", keys);
        assertEquals("bcfadgehi", enc, "encrypt failed");

        String dec = RocheExtByLowerPart.decrypt(enc, keys);
        assertEquals("abcdefghi", dec, "decrypt failed");
    }

    void encryptDecyphereBehavior1() {
        /*
         * 2 1 0 3
         *
         * a b c d
         * e f G h
         * i J K l
         * M N O P
         */
        int[] keys = new int[] { 2, 1, 0, 3 };
        String enc = RocheExtByLowerPart.encrypt("abcdefghijklmnop", keys);
        assertEquals("cbfaeidhlgkojnmp", enc, "encrypt failed");

        String dec = RocheExtByLowerPart.decrypt(enc, keys);
        assertEquals("abcdefghijklmnop", dec, "decrypt failed");
    }

    void encryptDecyphereBehavior2() {
        /*
         * 2 1 0 3
         *
         * a b c d
         * e f G h
         * i J K l
         * M N
         */
        int[] keys = new int[] { 2, 1, 0, 3 };
        String enc = RocheExtByLowerPart.encrypt("abcdefghijklmn", keys);
        assertEquals("cbfaeidhlGKJNM", enc, "encrypt failed");

        String dec = RocheExtByLowerPart.decrypt(enc, keys);
        assertEquals("abcdefghijklmn", dec, "decrypt failed");
    }

    void encryptDecyphereBehavior3() {
        /*
         * 2 1 0 3
         *
         * a b c d
         * e f G h
         *
         *
         */
        int[] keys = new int[] { 2, 1, 0, 3 };
        String enc = RocheExtByLowerPart.encrypt("abcdefg", keys);
        assertEquals("cbfaedg", enc, "encrypt failed");

        String dec = RocheExtByLowerPart.decrypt(enc, keys);
        assertEquals("abcdefg", dec, "decrypt failed");

    }
}
