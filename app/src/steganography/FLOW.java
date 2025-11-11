package steganography;

import java.io.File;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import cyphers.AbstractEncryptionAlgorithm;
import interfaces.TelegraphAlphabet;
import utils.Utils;

public class FLOW extends AbstractEncryptionAlgorithm {
    private static HashMap<Character, ArrayList<String>> getWordMap(String path) {
        String text;
        try {
            text = Utils.readTextFromFile(new File(path));
            if (text == null) {
                throw new IllegalArgumentException("Could not read file at path: " + path);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not read file at path: " + path);
        }
        text = Utils.convertToTSA(text, true);

        String[] textSplitted = text.split("\\(\\s|\\n\\)*");
        HashMap<Character, ArrayList<String>> wordIndex = new HashMap<>();
        for (String w : textSplitted) {
            if (w.length() == 0)
                continue;

            char c = w.charAt(0);
            ArrayList<String> wordCollection = wordIndex.get(c);
            if (wordCollection == null) {
                wordIndex.put(c, new ArrayList<>());
                wordCollection = wordIndex.get(c);
            }

            wordCollection.add(w);
        }
        return wordIndex;
    }

    public static String encrypt(String plainText, Object... args) {
        String path;
        if (plainText == null) {
            throw new IllegalArgumentException("Plain text cannot be null");
        }
        if (args.length < 1 || !(args[0] instanceof String)) {
            throw new IllegalArgumentException("Arguments are of wrong type or insufficient");
        }
        path = (String) args[0];

        HashMap<Character, ArrayList<String>> wordMap = getWordMap(path);
        if (wordMap == null) {
            throw new IllegalArgumentException("Could not create word map from file at path: " + path);
        }

        Random rnd = new Random(System.currentTimeMillis());

        plainText = Utils.convertToTSA(plainText, false);
        StringBuilder cypherTextBuilder = new StringBuilder("");
        for (Character c : plainText.toCharArray()) {
            ArrayList<String> wordCollection = wordMap.get(c);
            if (wordCollection == null || wordCollection.size() == 0) {
                continue;
            }
            String w = wordCollection.get(rnd.nextInt(wordCollection.size()));
            cypherTextBuilder.append(w);
            cypherTextBuilder.append(' ');
        }

        return cypherTextBuilder.toString().trim();
    }

    public static String decrypt(String cypherText, Object... args) {
        if (cypherText == null) {
            throw new IllegalArgumentException("Cypher text cannot be null");
        }

        String[] sa = cypherText.split("\\s*");
        StringBuilder ptsb = new StringBuilder("");
        for (String s : sa) {
            if (s.length() > 0) {
                ptsb.append(s.charAt(0));
            }
        }
        return Utils.convertToTSA(ptsb.toString(), false);
    }

}
