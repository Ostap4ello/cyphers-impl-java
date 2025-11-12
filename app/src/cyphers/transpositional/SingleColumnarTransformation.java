package cyphers.transpositional;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.List;

import exceptions.EncryptionAlgorithmConversionError;
import utils.Math;

// Write into table by rows, read by columns in given order
public class SingleColumnarTransformation {

    public static String encrypt(String plainText, Object... args) throws EncryptionAlgorithmConversionError {
        int[] keys;
        if (plainText == null || plainText.length() == 0) {
            throw new EncryptionAlgorithmConversionError("Plain text is null or empty");
        }
        if (args.length < 1) {
            throw new EncryptionAlgorithmConversionError("Insufficient arguments");
        }
        if (args[0] instanceof int[]) {
            keys = (int[]) args[0];
        } else if (args[0] instanceof String) {
            keys = Math.generatePermutationBellaso((String) args[0]);
        } else {
            throw new EncryptionAlgorithmConversionError("Argument is of wrong type");
        }

        int columns = Array.getLength(keys);
        int rows = (plainText.length()-1) / columns + 1;

        char[][] table = new char[rows][columns];

        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (index < plainText.length()) {
                    table[r][c] = plainText.charAt(index++);
                } else {
                    table[r][c] = '.'; // filler character
                }
            }
        }

        List<Integer> keyList = new ArrayList<>();
        for (int k : keys) {
            keyList.add(k);
        }

        StringBuilder modifiedTextBuilder = new StringBuilder("");
        for (int cIndex = 0; cIndex < columns; cIndex++) {
            int col = keyList.indexOf(cIndex);
            for (int r = 0; r < rows; r++) {
                if (table[r][col] != '.') // skip filler characters TODO: refactor
                    modifiedTextBuilder.append(table[r][col]);
            }

        }

        return modifiedTextBuilder.toString();
    }

    public static String decrypt(String cypherText, Object... args) throws EncryptionAlgorithmConversionError {
        int[] keys;
        if (cypherText == null || cypherText.length() == 0) {
            throw new EncryptionAlgorithmConversionError("Cypher text is null or empty");
        }
        if (args.length < 1) {
            throw new EncryptionAlgorithmConversionError("Insufficient arguments");
        }
        if (args[0] instanceof int[]) {
            keys = (int[]) args[0];
        } else if (args[0] instanceof String) {
            keys = Math.generatePermutationBellaso((String) args[0]);
        } else {
            throw new EncryptionAlgorithmConversionError("Argument is of wrong type");
        }

        int columns = Array.getLength(keys);
        int rows = (cypherText.length()-1) / columns + 1;

        int emptyCellsAfter = columns - ((rows * columns) - cypherText.length());

        char[][] table = new char[rows][columns];
        List<Integer> keyList = new ArrayList<>();
        for (int k : keys) {
            keyList.add(k);
        }

        int index = 0;
        for (int cIndex = 0; cIndex < columns; cIndex++) {
            int col = keyList.indexOf(cIndex);
            for (int row = 0; row < rows; row++) {
                // Skip empty cells
                if (row == rows - 1 && col >= emptyCellsAfter) {
                    table[row][col] = '.';
                } else {
                    table[row][col] = cypherText.charAt(index++);
                }
            }
        }

        StringBuilder decryptdTextBuilder = new StringBuilder("");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (table[r][c] != '.') // skip filler characters TODO: refactor
                    decryptdTextBuilder.append(table[r][c]);
            }
        }

        return decryptdTextBuilder.toString();
    }
}
