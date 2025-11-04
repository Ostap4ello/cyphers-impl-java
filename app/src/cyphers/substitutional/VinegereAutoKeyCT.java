// 2.11
package cyphers.substitutional;

import java.util.Arrays;

import cyphers.AbstractEncoderDecoder;
import utils.Math;
import utils.Utils;
import exceptions.EncoderDecoderConversionError;

public class VinegereAutoKeyCT extends AbstractEncoderDecoder {

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

        int keyLength = key.length;
        StringBuilder outputBuilder = new StringBuilder("");

        for (int i = 0; i < plainText.length(); i+=keyLength) {
            String originalSegment, modifiedSegment;
            if (i + keyLength > plainText.length()) {
                originalSegment = plainText.substring(i);
            } else {
                originalSegment = plainText.substring(i, i + keyLength);
            }
            modifiedSegment = Vinegere.encode(originalSegment, key);
            key = Utils.strToIntArrayTSA(modifiedSegment);

            outputBuilder.append(modifiedSegment);
        }
        return outputBuilder.toString();
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

        int keyLength = key.length;
        StringBuilder outputBuilder = new StringBuilder("");

        for (int i = 0; i < cypherText.length(); i+=keyLength) {
            String originalSegment, modifiedSegment;
            if (i + keyLength > cypherText.length()) {
                originalSegment = cypherText.substring(i);
            } else {
                originalSegment = cypherText.substring(i, i + keyLength);
            }
            modifiedSegment = Vinegere.decode(originalSegment, key);
            key = Utils.strToIntArrayTSA(originalSegment);

            outputBuilder.append(modifiedSegment);
        }
        return outputBuilder.toString();
    }
}
