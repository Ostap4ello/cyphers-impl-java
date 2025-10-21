package cyphers.transpositional;

import java.lang.reflect.Array;
import java.util.ArrayList;

import exceptions.EncoderDecoderConversionError;
import utils.Math;

// Write into table by rows, read by columns in given order
public class SingleColumnarTransformation {

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
            throw new EncoderDecoderConversionError("Argument is of wrong type");
        }

        keys = Math.generateInversePermutation(keys); // get order of reading columns
        int keyLength = Array.getLength(keys);
        int plainTextLength = plainText.length();
        StringBuilder modifiedTextBuilder = new StringBuilder("");

        for (int letterIndex : keys) {
            for (int baseIndex = 0; baseIndex < plainTextLength; baseIndex += keyLength) {
                int index = baseIndex + letterIndex;
                if (index < plainTextLength) {
                    modifiedTextBuilder.append(plainText.charAt(index));
                }
            }
        }
        return modifiedTextBuilder.toString();
    }

    private static String cypherTextAddMissingLetters(String cypherText, int[] keys) {
        int keyLength = Array.getLength(keys);
        int cypherTextLength = cypherText.length();

        // Fill text so it is rectangular
        // Note: cypherTextLength > 0, see checks. TODO: move check here
        int fullRowCount = cypherTextLength / keyLength;
        int columnLength = fullRowCount + 1;
        int fillerLength = keyLength - (cypherTextLength % keyLength);

        ArrayList<Integer> nonfullColumnIndexes = new ArrayList<>();
        for (int i = 0; i < fillerLength; i++) {
            nonfullColumnIndexes.add(keys[keyLength - 1 - i]);
        }
        nonfullColumnIndexes.sort(Integer::compareTo);

        StringBuilder modifiedTextBuilder = new StringBuilder(cypherText);

        for (int i = 0; i < fillerLength; i++) {
            int index = (nonfullColumnIndexes.get(i) * columnLength) + fullRowCount;
            modifiedTextBuilder.insert(index, '.');
        }

        return modifiedTextBuilder.toString();
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
            throw new EncoderDecoderConversionError("Argument is of wrong type");
        }

        cypherText = cypherTextAddMissingLetters(cypherText, keys);

        int keyLength = Array.getLength(keys);
        int columnCount = cypherText.length() / keyLength;

        StringBuilder decodedTextBuilder = new StringBuilder("");
        for (int letterIndex = 0; letterIndex < columnCount; letterIndex++) {
            for (int baseIndex : keys) {
                int index = letterIndex + keyLength * baseIndex;
                if (cypherText.charAt(index) != '.') // skip filler characters TODO: refactor
                    decodedTextBuilder.append(cypherText.charAt(index));
            }
        }
        return decodedTextBuilder.toString();
    }
}
