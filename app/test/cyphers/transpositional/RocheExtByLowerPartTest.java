package cyphers.transpositional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RocheExtByLowerPartTest {

    @Test
    void encodeDecodeBehavior0() {
        /*
         * 2 0 1
         *
         * a b c
         * d E f
         * g H I
         */
        int[] keys = new int[] { 2, 0, 1 };
        String enc = RocheExtByLowerPart.encode("abcdefghi", keys);
        assertEquals("bcfadgehi", enc, "encode failed");

        String dec = RocheExtByLowerPart.decode(enc, keys);
        assertEquals("abcdefghi", dec, "decode failed");
    }

    void encodeDecodeBehavior1() {
        /*
         * 2 1 0 3
         *
         * a b c d
         * e f G h
         * i J K l
         * M N O P
         */
        int[] keys = new int[] { 2, 1, 0, 3 };
        String enc = RocheExtByLowerPart.encode("abcdefghijklmnop", keys);
        assertEquals("cbfaeidhlgkojnmp", enc, "encode failed");

        String dec = RocheExtByLowerPart.decode(enc, keys);
        assertEquals("abcdefghijklmnop", dec, "decode failed");
    }

    void encodeDecodeBehavior2() {
        /*
         * 2 1 0 3
         *
         * a b c d
         * e f G h
         * i J K l
         * M N
         */
        int[] keys = new int[] { 2, 1, 0, 3 };
        String enc = RocheExtByLowerPart.encode("abcdefghijklmn", keys);
        assertEquals("cbfaeidhlGKJNM", enc, "encode failed");

        String dec = RocheExtByLowerPart.decode(enc, keys);
        assertEquals("abcdefghijklmn", dec, "decode failed");
    }

    void encodeDecodeBehavior3() {
        /*
         * 2 1 0 3
         *
         * a b c d
         * e f G h
         *
         *
         */
        int[] keys = new int[] { 2, 1, 0, 3 };
        String enc = RocheExtByLowerPart.encode("abcdefg", keys);
        assertEquals("cbfaedg", enc, "encode failed");

        String dec = RocheExtByLowerPart.decode(enc, keys);
        assertEquals("abcdefg", dec, "decode failed");

    }
}
