package cyphers.transpositional;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

import cyphers.AbstractEncryptionAlgorithm;
import utils.Math;

import exceptions.EncryptionAlgorithmConversionError;

// INFO: Write to rows, read by columns, upper part first, then lower part. Reading order,
// as well as length of the upper part of each column is defined by the key.
public abstract class RocheExtByLowerPart extends AbstractEncryptionAlgorithm {
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

        // TODO: validate keys length (m+1)m/2 >= plainText.length()

        int nCols = keys.length;
        int nRows = (plainText.length() - 1) / nCols + 1;

        // Create table and output builder
        Deque<Character> cypherTextDeque = new ArrayDeque<>();
        plainText.chars().forEach(c -> cypherTextDeque.addLast((char) c));
        Character[][] table = new Character[nRows][nCols];
        StringBuilder output = new StringBuilder();

        // Fill table
        for (int i = 0; !cypherTextDeque.isEmpty(); i++) {
            table[i / nCols][i % nCols] = cypherTextDeque.removeFirst();
        }

        // Construct output
        int[] columnOrder = Math.generateInversePermutation(keys);
        for (int cI : columnOrder) {
            int maxRowIndex = Integer.min(keys[cI] + 1, nRows);
            for (int rowIndex = 0; rowIndex < maxRowIndex; rowIndex++) {
                output.append(table[rowIndex][cI]);
            }
        }
        for (int cI : columnOrder) {
            int maxRowIndex = nRows;
            for (int rowIndex = keys[cI]+1; rowIndex < maxRowIndex; rowIndex++) {
                output.append(table[rowIndex][cI]);
            }
        }
        return output.toString();
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

        // TODO: validate keys length (m+1)m/2 >= plainText.length()

        int nCols = keys.length;
        int nRows = (cypherText.length() - 1) / nCols + 1;

        // Create table and output builder
        Deque<Character> cypherTextDeque = new ArrayDeque<>();
        cypherText.chars().forEach(c -> cypherTextDeque.addLast((char) c));
        Character[][] table = new Character[nRows][nCols];
        StringBuilder output = new StringBuilder();

        // Fill table
        int[] columnOrder = Math.generateInversePermutation(keys);
        for (int cI : columnOrder) {
            int maxRI = Integer.min(keys[cI] + 1, nRows);
            for (int rI = 0; rI < maxRI; rI++) {
                if (!cypherTextDeque.isEmpty()) {
                    table[rI][cI] = cypherTextDeque.removeFirst();
                }
            }
        }
        for (int cI : columnOrder) {
            int maxRI = nRows;
            for (int rI = keys[cI]+1; rI < maxRI; rI++) {
                if (!cypherTextDeque.isEmpty()) {
                    table[rI][cI] = cypherTextDeque.removeFirst();
                }
            }
        }

        // Construct output
        for (int i = 0; i < cypherText.length(); i++) {
            output.append(table[i / nCols][i % nCols]);
        }

        return output.toString();
    }
}
