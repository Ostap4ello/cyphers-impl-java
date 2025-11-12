package algorithms;
import cyphers.transpositional.SingleColumnarTransformation;
import exceptions.EncryptionAlgorithmConversionError;

public class TT {
    public static String encrypt(String plainText, Object... args) throws EncryptionAlgorithmConversionError {
        if (args.length < 2 ||
            !(args[0] instanceof String) ||
            !(args[1] instanceof String)) {
            throw new EncryptionAlgorithmConversionError("Invalid arguments for TT encryption. Expected: (String key1, String key2)");
        }

        String output = plainText;

        output = SingleColumnarTransformation.encrypt(output, (String) args[0]);
        output = SingleColumnarTransformation.encrypt(output, (String) args[1]);
        return output;
    }

    public static String decrypt(String cypherText, Object... args) throws EncryptionAlgorithmConversionError {
        if (args.length < 2 ||
            !(args[0] instanceof String) ||
            !(args[1] instanceof String)) {
            throw new EncryptionAlgorithmConversionError("Invalid arguments for TT encryption. Expected: (String key1, String key2)");
        }

        String output = cypherText;

        output = SingleColumnarTransformation.decrypt(output, (String) args[1]);
        output = SingleColumnarTransformation.decrypt(output, (String) args[0]);
        return output;
    }
}
