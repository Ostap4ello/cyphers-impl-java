package cyphers.substitutional;

import cyphers.AbstractEncryptionAlgorithm;
import utils.Utils;
import exceptions.EncryptionAlgorithmConversionError;

// INFO: encoding by substituting a corresponding letter from the reversed alphabet
public class Atbash extends AbstractEncryptionAlgorithm {

    // INFO: returns string, where each letter is shifted by offset (to right),
    // which increases by step after each letter
    private static String transform(String text) throws EncryptionAlgorithmConversionError {
        if (text == null || text.matches(AlphabetRegex) == false) {
            throw new EncryptionAlgorithmConversionError("Text is null or contains invalid characters");
        }

        StringBuilder modifiedTextBuilder = new StringBuilder("");
        for (char c : text.toCharArray()) {

            int letterAsInteger = Utils.ctoiTSA(c);
            letterAsInteger = (AlphabetLength - 1) - letterAsInteger;
            modifiedTextBuilder.append(Utils.itocTSA(letterAsInteger));
        }

        return modifiedTextBuilder.toString();
    }

    public static String decrypt(String cypherText, Object... args) throws EncryptionAlgorithmConversionError {
        if (args.length != 0) {
            throw new EncryptionAlgorithmConversionError("Function must take zero arguments");
        }

        return transform(cypherText);
    }

    public static String encrypt(String plainText, Object... args) throws EncryptionAlgorithmConversionError {
        if (args.length != 0) {
            throw new EncryptionAlgorithmConversionError("Function must take zero arguments");
        }

        return transform(plainText);
    }
}
