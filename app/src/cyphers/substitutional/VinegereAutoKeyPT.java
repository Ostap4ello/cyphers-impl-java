// 2.10
package cyphers.substitutional;

import java.util.Arrays;

import cyphers.AbstractEncryptionAlgorithm;
import utils.Math;
import utils.Utils;
import exceptions.EncryptionAlgorithmConversionError;

public class VinegereAutoKeyPT extends AbstractEncryptionAlgorithm {

    public static String encrypt(String plainText, Object... args) throws EncryptionAlgorithmConversionError {
        int[] key;
        if (args.length < 1 ||
                !(args[0] instanceof int[])) {
            throw new EncryptionAlgorithmConversionError("Arguments are of wrong type or insufficient");
        }
        key = (int[]) args[0];
        if (Arrays.asList(key).size() <= 0) {
            throw new EncryptionAlgorithmConversionError("Invalid key");
        }
        // TODO: key verification

        int keyLength = key.length;
        StringBuilder outputBuilder = new StringBuilder("");

        for (int i = 0; i < plainText.length(); i+=keyLength) {
            String originalSegment, modifiedSegment;
            if (i + keyLength > plainText.length()) {
                originalSegment = plainText.substring(i);
            } else {
                originalSegment = plainText.substring(i, i + keyLength);
            }
            modifiedSegment = Vinegere.encrypt(originalSegment, key);
            key = Utils.strToIntArrayTSA(originalSegment);

            outputBuilder.append(modifiedSegment);
        }
        return outputBuilder.toString();
    }

    public static String decrypt(String cypherText, Object... args) throws EncryptionAlgorithmConversionError {
        int[] key;
        if (args.length < 1 ||
                !(args[0] instanceof int[])) {
            throw new EncryptionAlgorithmConversionError("Arguments are of wrong type or insufficient");
        }
        key = (int[]) args[0];
        if (Arrays.asList(key).size() <= 0) {
            throw new EncryptionAlgorithmConversionError("Invalid key");
        }
        //TODO: key verification

        int keyLength = key.length;
        StringBuilder outputBuilder = new StringBuilder("");

        for (int i = 0; i < cypherText.length(); i+=keyLength) {
            String originalSegment, modifiedSegment;
            if (i + keyLength > cypherText.length()) {
                originalSegment = cypherText.substring(i);
            } else {
                originalSegment = cypherText.substring(i, i + keyLength);
            }
            modifiedSegment = Vinegere.decrypt(originalSegment, key);
            key = Utils.strToIntArrayTSA(modifiedSegment);

            outputBuilder.append(modifiedSegment);
        }
        return outputBuilder.toString();
    }
}
