package cyphers.substitutional;

import java.util.Arrays;

import cyphers.AbstractEncoderDecoder;
import utils.Utils;
import exceptions.EncoderDecoderConversionError;

public class Vinegere extends AbstractEncoderDecoder {

    // INFO: returns string, where each letter is shifted by offset (to right),
    // which increases by step after each letter
    private static String transform(String text, int[] key) throws EncoderDecoderConversionError {
        if (text == null || text.matches(AlphabetRegex) == false) {
            throw new EncoderDecoderConversionError("Text is null or contains invalid characters");
        }

        StringBuilder modifiedTextBuilder = new StringBuilder("");
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            int letterAsInteger = Utils.ctoiTSA(c);
            letterAsInteger = (letterAsInteger + key[i % key.length]) % AlphabetLength;
            if (letterAsInteger < 0) {
                letterAsInteger += AlphabetLength;
            }
            modifiedTextBuilder.append(Utils.itocTSA(letterAsInteger));
        }

        return modifiedTextBuilder.toString();
    }

    public static String encode(String plainText, Object... args) throws EncoderDecoderConversionError {
        int[] key;
        if (args.length < 1 ||
                !(args[0] instanceof int[])) {
            throw new EncoderDecoderConversionError("Arguments are of wrong type or insufficient");
        }
        key = (int[]) args[0];
        if (Arrays.asList(key).size() <= 0) {
            throw new EncoderDecoderConversionError("Invalid key");
        }
        // TODO: key verification

        return transform(plainText, key);
    }

    public static String decode(String cypherText, Object... args) throws EncoderDecoderConversionError {
        int[] key;
        if (args.length < 1 ||
                !(args[0] instanceof int[])) {
            throw new EncoderDecoderConversionError("Arguments are of wrong type or insufficient");
        }
        key = (int[]) args[0];
        if (Arrays.asList(key).size() <= 0) {
            throw new EncoderDecoderConversionError("Invalid key");
        }
        //TODO: key verification

        // Invert key for decoding
        for (int i = 0; i < key.length; i++) {
            key[i] = -key[i];
        }

        return transform(cypherText, key);
    }
}
