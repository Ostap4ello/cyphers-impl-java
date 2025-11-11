package cyphers.substitutional;

import cyphers.AbstractEncryptionAlgorithm;
import exceptions.EncryptionAlgorithmConversionError;
import interfaces.TelegraphAlphabet;
import utils.Math;
import utils.Utils;

public class Monoalphabetic extends AbstractEncryptionAlgorithm {

    public static String encrypt(String plainText, Object... args) throws EncryptionAlgorithmConversionError {
        String key;
        if (args.length < 1 ||
                !(args[0] instanceof String)) {
            throw new EncryptionAlgorithmConversionError("Arguments are of wrong type or insufficient");
        }
        key = (String) args[0];
        if (key.length() != AlphabetLength) {
            throw new EncryptionAlgorithmConversionError("Invalid key");
        }
        //TODO: key verification

        int[] keys = Math.generatePermutationBellaso(key);
        StringBuilder output = new StringBuilder("");

        for (char c : plainText.toCharArray()) {
            output.append(Utils.itocTSA(keys[Utils.ctoiTSA(c)]));
        }

        return output.toString();
    }

    public static String decrypt(String cypherText, Object... args) throws EncryptionAlgorithmConversionError {
        String key;
        if (args.length < 1 ||
                !(args[0] instanceof String)) {
            throw new EncryptionAlgorithmConversionError("Arguments are of wrong type or insufficient");
        }
        key = (String) args[0];
        if (key.length() != AlphabetLength) {
            throw new EncryptionAlgorithmConversionError("Invalid key");
        }
        //TODO: key verification

        int[] keys = Math.generateInversePermutation(Math.generatePermutationBellaso(key));
        StringBuilder output = new StringBuilder("");

        for (char c : cypherText.toCharArray()) {
            output.append(Utils.itocTSA(keys[Utils.ctoiTSA(c)]));
        }

        return output.toString();
    }
}
