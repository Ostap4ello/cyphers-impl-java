package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MathTest {

    private static boolean isPermutation(int[] arr) {
        if (arr == null || arr.length == 0)
            return false;
        boolean[] seen = new boolean[arr.length];
        for (int v : arr) {
            if (v < 0 || v >= arr.length)
                return false;
            if (seen[v])
                return false;
            seen[v] = true;
        }
        for (boolean b : seen)
            if (!b)
                return false;
        return true;
    }

    @Test
    void primes() {
        assertTrue(utils.Math.isPrime(2), "2 should be prime");
        assertFalse(utils.Math.isPrime(1), "1 should not be prime");
        assertFalse(utils.Math.isPrime(9), "9 should not be prime");
        assertTrue(utils.Math.isPrime(97), "97 should be prime");
    }

    @Test
    void factorials() {
        assertEquals(1, utils.Math.factorial(0), "0! = 1");
        assertEquals(120, utils.Math.factorial(5), "5! = 120");
    }

    @Test
    void permutations() {
        int[] sorted = utils.Math.generateSortedPermutation(5);
        assertTrue(isPermutation(sorted), "generateSortedPermutation should produce a permutation");
        for (int i = 0; i < 5; i++)
            assertEquals(i, sorted[i], "sorted permutation order");

        int[] base = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
        int[] rand = utils.Math.generateRandomPermutationFromArray(base, 42L);
        assertTrue(isPermutation(rand), "random permutation should be valid");

        int[] inv = utils.Math.generateInversePermutation(rand);
        assertTrue(isPermutation(inv), "inverse permutation should be valid");
    }

    @Test
    void bellasoPermutation() {
        Map<String, String> testCases = new HashMap<>();
        testCases.put("abc", "012");
        testCases.put("cba", "210");
        testCases.put("aaccbb", "014523");

        for (Map.Entry<String, String> entry : testCases.entrySet()) {
            String key = entry.getKey();
            String expected = entry.getValue();
            int[] result = utils.Math.generatePermutationBellaso(key);
            assertEquals(expected, Arrays.toString(result).replaceAll("[\\[\\], ]", ""),
                    "Bellaso permutation for key: " + key);
        }
    }

    @Test
    void keyBenjaminFranklin() { // TODO: more tests
        String key = "abcdefghijklmnopqrstuvwxyz";
        assertEquals(Math.generateHomophonicKeyBenjaminFranklin(""),
                Math.generateHomophonicKeyBenjaminFranklin(key),
                "Testing auto-filling feature");
        assertTrue(
                Math.isHomophonicKeyValid(
                        Math.generateHomophonicKeyBenjaminFranklin(key)),
                "Generated Benjamin Franklin key should be valid");

    }

    @Test
    void isHomophonicKeyValid() {
        Map<Character, List<Integer>> key = new HashMap<>();

        assertFalse(Math.isHomophonicKeyValid(key), "Empty key should be invalid");

        for (char c = 'a'; c <= 'z'; c++) {
            key.put(c, new ArrayList<>());
        }

        assertFalse(Math.isHomophonicKeyValid(key), "Key with no mappings should be invalid");

        int index = 0;
        for (char c = 'a'; c <= 'x'; c++) {
            key.get(c).add(index++);
        }

        assertFalse(Math.isHomophonicKeyValid(key), "Key missing some letters should be invalid");

        key.get('y').add(index++);
        key.get('z').add(index++);

        assertTrue(Math.isHomophonicKeyValid(key), "Complete key should be valid");

    }
}
