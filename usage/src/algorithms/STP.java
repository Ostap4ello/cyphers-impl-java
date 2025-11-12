package algorithms;

import cyphers.substitutional.Monoalphabetic;
import cyphers.transpositional.SingleColumnarTransformation;
import cyphers.substitutional.Vinegere;
import exceptions.EncryptionAlgorithmConversionError;
import utils.Utils;

public class STP {
    public static String encrypt(String plainText, Object... args) throws EncryptionAlgorithmConversionError {
        if (args.length < 3 ||
            !(args[0] instanceof String) ||
            !(args[1] instanceof String) ||
            !(args[2] instanceof String)) {
            throw new EncryptionAlgorithmConversionError("Invalid arguments for TT encryption. Expected: (String key1, String key2)");
        }

        String output = plainText;
        output = Monoalphabetic.encrypt(output, (String) args[0]);
        output = SingleColumnarTransformation.encrypt(output, (String) args[1]);
        output = Vinegere.encrypt(output, Utils.strToIntArrayTSA((String) args[2]));

        return output;
    }

    public static String decrypt(String cypherText, Object... args) throws EncryptionAlgorithmConversionError {
        if (args.length < 3 ||
                !(args[0] instanceof String) ||
                !(args[1] instanceof String) ||
                !(args[2] instanceof String)) {
            throw new EncryptionAlgorithmConversionError(
                    "Invalid arguments for TT encryption. Expected: (String key1, String key2)");
        }

        String output = cypherText;
        output = Vinegere.decrypt(output, Utils.strToIntArrayTSA((String) args[2]));
        output = SingleColumnarTransformation.decrypt(output, (String) args[1]);
        output = Monoalphabetic.decrypt(output, (String) args[0]);

        return output;
    }
}
