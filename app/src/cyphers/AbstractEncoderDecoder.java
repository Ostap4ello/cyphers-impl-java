package cyphers;

import exceptions.EncoderDecoderConversionError;
import exceptions.NotImplementedError;
import interfaces.TelegraphAlphabet;

public abstract class AbstractEncoderDecoder implements TelegraphAlphabet {

    public static String encode(String plainText, Object... args) throws EncoderDecoderConversionError {
        throw new NotImplementedError("This method should be implemented by subclasses");
    }

    public static String decode(String cypherText, Object... args) throws EncoderDecoderConversionError {
        throw new NotImplementedError("This method should be implemented by subclasses");
    }
}
