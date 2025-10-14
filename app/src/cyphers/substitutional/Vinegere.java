// 2.3
package cyphers.substitutional;

import cyphers.AbstractEncoderDecoder;
import interfaces.TelegraphAlphabet;
import utils.Utils;
import exceptions.EncoderDecoderConversionError;
import exceptions.NotImplementedError;

public class Vinegere extends AbstractEncoderDecoder {

    // INFO: returns string, where each letter is shifted by offset (to right),
    // which increases by step after each letter
    private static String transform(String text, int offset, int step) throws EncoderDecoderConversionError {
        if (text == null || text.matches(AlphabetRegex) == false) {
            throw new EncoderDecoderConversionError("Text is null or contains invalid characters");
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

    public static String decode(String cypherText, Object... args) throws EncoderDecoderConversionError {
        int offset;
        int step;
        if (args.length < 2 ||
                !(args[0] instanceof Integer) ||
                !(args[1] instanceof Integer)) {
            throw new EncoderDecoderConversionError("Arguments are of wrong type or insufficient");
        }
        offset = (Integer) args[0];
        step = (Integer) args[1];

        return transform(cypherText, -offset, -step);
    }

    public static String encode(String plainText, Object... args) throws EncoderDecoderConversionError {
        int offset;
        int step;
        if (args.length < 2 ||
                !(args[0] instanceof Integer) ||
                !(args[1] instanceof Integer)) {
            throw new EncoderDecoderConversionError("Arguments are of wrong type or insufficient");
        }
        offset = (Integer) args[0];
        step = (Integer) args[1];

        return transform(plainText, offset, step);
    }
}
