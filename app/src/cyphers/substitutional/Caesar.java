package cyphers.substitutional;

import cyphers.AbstractEncoderDecoder;
import exceptions.EncoderDecoderConversionError;
import interfaces.TelegraphAlphabet;

public class Caesar extends AbstractEncoderDecoder {

    public static String encode(String plainText, Object... args) throws EncoderDecoderConversionError {
        int offset;
        if (args.length < 1 ||
                !(args[0] instanceof Integer)) {
            throw new EncoderDecoderConversionError("Arguments are of wrong type or insufficient");
        }
        offset = (Integer) args[0];

        return Trithemius.encode(plainText, offset, 0);
    }

    public static String decode(String cypherText, Object... args) throws EncoderDecoderConversionError {
        int offset;
        if (args.length < 1 ||
                !(args[0] instanceof Integer)) {
            throw new EncoderDecoderConversionError("Arguments are of wrong type or insufficient");
        }
        offset = (Integer) args[0];

        return Trithemius.decode(cypherText, offset, 0);
    }
}
