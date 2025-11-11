package cyphers.substitutional;

import cyphers.AbstractEncryptionAlgorithm;
import exceptions.EncryptionAlgorithmConversionError;
import interfaces.TelegraphAlphabet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Random;

public class Homophonic extends AbstractEncryptionAlgorithm {

    private static class KeyOperations {
        private static Map<Character, List<Integer>> key;
        private static Map<Character, Integer> rotateKeyStates;
        private static Map<Integer, Character> inverseKey;

        private static boolean isKeyValid(Object checkKey) {
            if (checkKey == null || !(checkKey instanceof Map<?, ?>)) {
                return false;
            }
            Map<?, ?> tempKey = (Map<?, ?>) checkKey;
            // Some magic stuff idk
            if (!tempKey.keySet().stream().allMatch(k -> k instanceof Character) ||
                    !tempKey.values().stream().allMatch(v -> v instanceof List<?>)) {
                return false;
            }
            for (Object v : tempKey.values()) {
                List<?> lst = (List<?>) v;
                if (!lst.stream().allMatch(i -> i instanceof Integer)) {
                    return false;
                }
            }

            Map<Character, List<Integer>> tmpKey2 = (Map<Character, List<Integer>>) tempKey; // TODO: unchecked cast
            return true;
        }

        public static void setKey(Object newKey) {

            if (isKeyValid(newKey)) {
                key = (Map<Character, List<Integer>>) newKey;
            } else {
                throw new IllegalArgumentException("Invalid key");
            }

            inverseKey = null; // reset inverse key
            rotateKeyStates = new HashMap<>(); // reset rotation states
        }

        private static void createInverseKey() {
            Map<Integer, Character> invKey = new java.util.HashMap<>();
            for (Map.Entry<Character, List<Integer>> entry : key.entrySet()) {
                Character c = entry.getKey();
                for (Integer i : entry.getValue()) {
                    invKey.put(i, c);
                }
            }
            inverseKey = invKey;
        }

        public static Integer encryptElement(char c, boolean rotate) {
            List<Integer> possibleValues = key.get(c);
            int index;
            if (rotate) { // ROTATE
                index = rotateKeyStates.getOrDefault(c, 0);
                rotateKeyStates.put(c, (index + 1) % possibleValues.size());
            } else { // RANDOM
                Random rand = new Random(System.currentTimeMillis());
                index = rand.nextInt(possibleValues.size());
            }
            return possibleValues.get(index);
        }

        public static Character decryptElement(int i) {
            if (inverseKey == null) {
                createInverseKey();
            }
            return inverseKey.get(i);
        }

    }

    public static String encrypt(String plainText, Object... args) throws EncryptionAlgorithmConversionError {
        boolean toRotate;
        if (args.length < 2 ||
                !(args[1] instanceof Boolean)) {
            throw new EncryptionAlgorithmConversionError("Arguments are of wrong type or insufficient");
        }
        toRotate = (Boolean) args[1];
        KeyOperations.setKey(args[0]);

        StringBuilder output = new StringBuilder("");
        for (char c : plainText.toCharArray()) {
            Integer encryptdValue = KeyOperations.encryptElement(c, toRotate);
            output.append(Integer.toString(encryptdValue));
            output.append(" ");
        }
        return output.toString();
    }

    public static String decrypt(String cypherText, Object... args) throws EncryptionAlgorithmConversionError {
        if (args.length < 1) {
            throw new EncryptionAlgorithmConversionError("Arguments are of wrong type or insufficient");
        }
        KeyOperations.setKey(args[0]);

        StringBuilder output = new StringBuilder("");
        String[] tokens = cypherText.split(" ");
        for (String token : tokens) {
            Integer n = Integer.parseInt(token);
            Character c = KeyOperations.decryptElement(n);
            output.append(c);
        }
        return output.toString();
    }
}
