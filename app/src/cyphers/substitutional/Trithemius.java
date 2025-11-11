package cyphers.substitutional;

import cyphers.AbstractEncryptionAlgorithm;
import utils.Utils;
import exceptions.EncryptionAlgorithmConversionError;

public class Trithemius extends AbstractEncryptionAlgorithm {

    // INFO: returns string, where each letter is shifted by offset (to right),
    // which increases by step after each letter
    private static String transform(String text, int offset, int step) throws EncryptionAlgorithmConversionError {
        if (text == null || text.matches(AlphabetRegex) == false) {
            throw new EncryptionAlgorithmConversionError("Text is null or contains invalid characters");
        }

        StringBuilder modifiedTextBuilder = new StringBuilder("");
        for (char c : text.toCharArray()) {

            int letterAsInteger = Utils.ctoiTSA(c);
            letterAsInteger = (letterAsInteger + offset) % AlphabetLength;
            if (letterAsInteger < 0) {
                letterAsInteger += AlphabetLength;
            }
            modifiedTextBuilder.append(Utils.itocTSA(letterAsInteger));

            offset = (offset + step) % AlphabetLength;
        }

        return modifiedTextBuilder.toString();
    }

    public static String decrypt(String cypherText, Object... args) throws EncryptionAlgorithmConversionError {
        int offset;
        int step;
        if (args.length < 2 ||
                !(args[0] instanceof Integer) ||
                !(args[1] instanceof Integer)) {
            throw new EncryptionAlgorithmConversionError("Arguments are of wrong type or insufficient");
        }
        offset = (Integer) args[0];
        step = (Integer) args[1];

        return transform(cypherText, -offset, -step);
    }

    public static String encrypt(String plainText, Object... args) throws EncryptionAlgorithmConversionError {
        int offset;
        int step;
        if (args.length < 2 ||
                !(args[0] instanceof Integer) ||
                !(args[1] instanceof Integer)) {
            throw new EncryptionAlgorithmConversionError("Arguments are of wrong type or insufficient");
        }
        offset = (Integer) args[0];
        step = (Integer) args[1];

        return transform(plainText, offset, step);
    }
}
