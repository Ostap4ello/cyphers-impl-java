package cyphers;

import exceptions.EncryptionAlgorithmConversionError;
import exceptions.NotImplementedError;
import interfaces.TelegraphAlphabet;

public abstract class AbstractEncryptionAlgorithm implements TelegraphAlphabet {

    public static String encrypt(String plainText, Object... args) throws EncryptionAlgorithmConversionError {
        throw new NotImplementedError("This method should be implemented by subclasses");
    }

    public static String decrypt(String cypherText, Object... args) throws EncryptionAlgorithmConversionError {
        throw new NotImplementedError("This method should be implemented by subclasses");
    }
}
