package cyphers.substitutional;

import cyphers.AbstractEncoderDecoder;
import utils.Utils;
import exceptions.EncoderDecoderConversionError;

// INFO: encoding by substituting a corresponding letter from the reversed alphabet
public class Atbash extends AbstractEncoderDecoder {

    // INFO: returns string, where each letter is shifted by offset (to right),
    // which increases by step after each letter
    private static String transform(String text) throws EncoderDecoderConversionError {
        if (text == null || text.matches(AlphabetRegex) == false) {
            throw new EncoderDecoderConversionError("Text is null or contains invalid characters");
        }

        StringBuilder modifiedTextBuilder = new StringBuilder("");
        for (char c : text.toCharArray()) {

            int letterAsInteger = Utils.ctoiTSA(c);
            letterAsInteger = (AlphabetLength - 1) - letterAsInteger;
            modifiedTextBuilder.append(Utils.itocTSA(letterAsInteger));
        }

        return modifiedTextBuilder.toString();
    }

    public static String decode(String cypherText, Object... args) throws EncoderDecoderConversionError {
        if (args.length != 0) {
            throw new EncoderDecoderConversionError("Function must take zero arguments");
        }

        return transform(cypherText);
    }

    public static String encode(String plainText, Object... args) throws EncoderDecoderConversionError {
        if (args.length != 0) {
            throw new EncoderDecoderConversionError("Function must take zero arguments");
        }

        return transform(plainText);
    }
}
