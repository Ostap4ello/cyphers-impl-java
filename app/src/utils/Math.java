package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import interfaces.TelegraphAlphabet;

public class Math implements TelegraphAlphabet {
    // 1.1
    public static boolean isPrime(int n) {
        if (n <= 1 || (n % 2 == 0 && n > 2)) {
            return false;
        }
        for (int i = 3; i < n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static int factorial(int n) {
        if (n <= 0)
            return 1;
        int r = 1;
        while (n > 1) {
            r *= n--; // bro it is totally unreadable thus cool
        }
        return r;
    }

    // 1.11
    // NOTE: permutation begins with 0
    public static int[] generateSortedPermutation(int n) {
        if (n <= 0) {
            return null;
        }

        int[] perm = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
        }
        return perm;
    }

    // 1.12
    public static int[] generateRandomPermutationFromArray(int[] array) {
        return generateRandomPermutationFromArray(array, System.currentTimeMillis());
    }

    public static int[] generateRandomPermutationFromArray(int[] array, long seed) {
        if (array == null || array.length == 0) {
            return null;
        }

        int[] perm = array.clone();
        Random rnd = new Random(seed);

        for (int i = perm.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            // swap perm[i] with perm[j]
            int temp = perm[i];
            perm[i] = perm[j];
            perm[j] = temp;
        }

        return perm;
    }

    // 1.13
    public static int[] generateInversePermutation(int[] perm) {
        if (perm == null || perm.length == 0) {
            return null;
        }

        int n = perm.length;
        int[] invPerm = new int[n];
        for (int i = 0; i < n; i++) {
            if (perm[i] < 0 || perm[i] >= n) {
                return null; // invalid permutation
            }
            invPerm[perm[i]] = i;
        }
        return invPerm;
    }

    // 2.12
    // INFO: The Bellaso cipher uses a string key to generate a permutation of
    // integers,
    // by assigning numbers for characters in alphabetical order, and if there are
    // multiple
    // same characters, they get assigned numbers in the order of their appearance
    // in the string.
    // Example: "bacca" -> [2, 0, 3, 4, 1]
    public static int[] generatePermutationBellaso(String stringKey) throws IllegalArgumentException {
        if (stringKey == null) {
            throw new IllegalArgumentException("Key is null");
        }
        int length = stringKey.length();
        if (length == 0 || stringKey.matches("^[a-z]*$") == false) {
            throw new IllegalArgumentException("Key is empty");
        }

        Set<Character> keyLetterSet = new HashSet<>();
        for (int i = 0; i < length; i++) {
            keyLetterSet.add(stringKey.charAt(i));
        }
        List<Character> keyLetterList = new ArrayList<>(keyLetterSet);
        keyLetterList.sort(Character::compareTo);

        int[] keyIntArray = new int[length];

        int number = 0;
        for (Character c : keyLetterList) {
            int index = 0;
            do {
                index = stringKey.indexOf(c, index);
                if (index == -1) {
                    break;
                } else {
                    keyIntArray[index] = number;
                    index++;
                    number++;
                }
            } while (true);
        }

        return keyIntArray;
    }

    // 2.8
    // INFO: Benjamin Franklin key generation
    // Generates a homophonic key with multiple values for each letter to balance
    // frequency
    // distribution. Good for long keys and large texts. (Generates pretty heavy
    // keys though)
    public static Map<Character, List<Integer>> generateHomophonicKeyBenjaminFranklin(String stringKey) {
        Map<Character, List<Integer>> key = new HashMap<>();
        Integer letterIndex = 0;

        for (char c : stringKey.toCharArray()) {
            key.putIfAbsent(c, new ArrayList<>());
            key.get(c).add(letterIndex);
            letterIndex++;
        }

        for (int i = 0; i < AlphabetLength; i++) {
            char c = Utils.itocTSA(i);
            if (!key.containsKey(c)) {
                key.put(c, new ArrayList<>());
                key.get(c).add(letterIndex);
                letterIndex++;
            }
        }

        return key;
    }

    public static boolean isHomophonicKeyValid(Map<Character, List<Integer>> key) {
        Set<Integer> allValues = new HashSet<Integer>();
        if (key.values().size() != AlphabetLength) {
            return false;
        }
        for (List<Integer> values : key.values()) {
            if (values == null) {
                return false;
            }
            if (values.isEmpty()) {
                return false;
            }
            if (!allValues.addAll(values)) {
                return false; // duplicate found
            }
        }
        return true;
    }

}
