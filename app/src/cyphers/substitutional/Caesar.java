package cyphers.substitutional;

import cyphers.AbstractEncryptionAlgorithm;
import exceptions.EncryptionAlgorithmConversionError;
import interfaces.TelegraphAlphabet;

public class Caesar extends AbstractEncryptionAlgorithm {

    public static String encrypt(String plainText, Object... args) throws EncryptionAlgorithmConversionError {
        int offset;
        if (args.length < 1 ||
                !(args[0] instanceof Integer)) {
            throw new EncryptionAlgorithmConversionError("Arguments are of wrong type or insufficient");
        }
        offset = (Integer) args[0];

        return Trithemius.encrypt(plainText, offset, 0);
    }

    public static String decrypt(String cypherText, Object... args) throws EncryptionAlgorithmConversionError {
        int offset;
        if (args.length < 1 ||
                !(args[0] instanceof Integer)) {
            throw new EncryptionAlgorithmConversionError("Arguments are of wrong type or insufficient");
        }
        offset = (Integer) args[0];

        return Trithemius.decrypt(cypherText, offset, 0);
    }
}
