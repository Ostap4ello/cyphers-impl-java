package cyphers.transpositional;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.concurrent.CyclicBarrier;

import utils.Math;

import exceptions.EncoderDecoderConversionError;

// INFO: write to table by columns; length of each row of the table are defined by key;
// then read in key order by rows
public abstract class Roche {
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

        // TODO: validate keys length (m+1)m/2 >= plainText.length()

        int nCols = keys.length;
        int nRows = keys.length;

        // Create table and output builder
        Deque<Character> cypherTextDeque = new ArrayDeque<>();
        plainText.chars().forEach(c -> cypherTextDeque.addLast((char) c));
        Character[][] table = new Character[nRows][nCols];
        StringBuilder output = new StringBuilder();

        // Fill table
        for (int i = 0; !cypherTextDeque.isEmpty(); i++) {
            int rI = i / nCols;
            int cI = i % nCols;
            if (rI < keys[cI] + 1) {
                table[rI][cI] = cypherTextDeque.removeFirst();
            }
        }

        // Construct output
        int[] columnOrder = Math.generateInversePermutation(keys);
        for (int cI : columnOrder) {
            int maxRowIndex = Integer.min(keys[cI] + 1, nRows);
            for (int rowIndex = 0; rowIndex < maxRowIndex; rowIndex++) {
                if (table[rowIndex][cI] != null) {
                    output.append(table[rowIndex][cI]);
                }
            }
        }
        return output.toString();
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

        // TODO: validate keys length (m+1)m/2 >= plainText.length()

        int nCols = keys.length;
        int nRows = keys.length;

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

        // Construct output
        for (int i = 0; output.length() < cypherText.length(); i++) {
            if (table[i / nCols][i % nCols] != null) {
                output.append(table[i / nCols][i % nCols]);
            }
        }

        return output.toString();
    }
}
