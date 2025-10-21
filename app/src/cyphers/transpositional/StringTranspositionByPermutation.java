package cyphers.transpositional;

import java.lang.reflect.Array;

import exceptions.EncoderDecoderConversionError;
import utils.Math;

public class StringTranspositionByPermutation {

    private static String applyTransformation(String text, int[] keys, boolean allowExtension)
            throws EncoderDecoderConversionError {
        int keyLength = Array.getLength(keys);
        int textLength = text.length();
        StringBuilder modifiedTextBuilder = new StringBuilder("");

        for (int baseIndex = 0; baseIndex < textLength; baseIndex += keyLength) {
            for (int letterIndex : keys) {
                int index = baseIndex + letterIndex;
                if (index >= textLength) {
                    if (!allowExtension) {
                        throw new EncoderDecoderConversionError("Text length is not a multiple of key length");
                    }
                    modifiedTextBuilder.append("x");
                } else {
                    modifiedTextBuilder.append(text.charAt(index));
                }
            }
        }
        return modifiedTextBuilder.toString();
    }

    public static String encode(String plainText, Object... args) throws EncoderDecoderConversionError {
        int[] keys;
        if (plainText == null || plainText.length() == 0) {
            throw new EncoderDecoderConversionError("Plain text is null or empty");
        }
        if (args.length < 1) {
            throw new EncoderDecoderConversionError("Insufficient arguments");
        }
        if (args[0] instanceof int[]) {
            keys = (int[]) args[0];
        } else if (args[0] instanceof String) {
            keys = Math.generatePermutationBellaso((String) args[0]);
        } else {
            return null;
        }

        return applyTransformation(plainText, keys, true);
    }

    public static String decode(String cypherText, Object... args) throws EncoderDecoderConversionError {
        int[] keys;
        if (cypherText == null || cypherText.length() == 0) {
            throw new EncoderDecoderConversionError("Cypher text is null or empty");
        }
        if (args.length < 1) {
            throw new EncoderDecoderConversionError("Insufficient arguments");
        }
        if (args[0] instanceof int[]) {
            keys = (int[]) args[0];
        } else if (args[0] instanceof String) {
            keys = Math.generatePermutationBellaso((String) args[0]);
        } else {
            return null;
        }

        keys = Math.generateInversePermutation(keys);
        return applyTransformation(cypherText, keys, false);
    }
}
