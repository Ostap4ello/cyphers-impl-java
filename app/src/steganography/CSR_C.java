package steganography;

import java.sql.Time;
import java.util.Random;

import cyphers.AbstractEncryptionAlgorithm;
import interfaces.TelegraphAlphabet;
import utils.Utils;

public class CSR_C extends AbstractEncryptionAlgorithm {

    private static String reverseStringWords(String text) {
        text.replaceAll("\\s+", " ");
        String[] words = text.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = new StringBuilder(words[i]).reverse().toString();
        }
        StringBuilder returnStringBuilder = new StringBuilder("");
        if (words.length > 0) {
            returnStringBuilder.append(words[0]);
        }
        for (int i = 1; i < words.length; i++) {
            returnStringBuilder.append(" ");
            returnStringBuilder.append(words[i]);
        }
        return returnStringBuilder.toString();
    }

    private static char getRandomChar() {
        Random rnd = new Random(System.currentTimeMillis());
        return Utils.itocTSA(rnd.nextInt(AlphabetLength));
    }

    public static String encrypt(String plainText, Object... args) {
        boolean isEvenDay;

        if (plainText == null) {
            throw new IllegalArgumentException("Plain text cannot be null");
        }
        if (args.length < 1 || !(args[0] instanceof Boolean)) {
            throw new IllegalArgumentException("Arguments are of wrong type or insufficient");
        }
        isEvenDay = (Boolean) args[0];

        if (isEvenDay) {
            plainText = reverseStringWords(plainText);
        }

        StringBuilder cypherTextBuider = new StringBuilder("");
        int wordlen = 0;
        for (int i = 0; i < plainText.length(); i++) {
            if (wordlen == 2) {
                wordlen = 0;
                cypherTextBuider.append(' ');
            }

            if (plainText.charAt(i) == ' ') {
                if (wordlen == 1) {
                    cypherTextBuider.append(' ');
                }
                cypherTextBuider.append(getRandomChar());
                wordlen = 2;
            } else {
                cypherTextBuider.append(getRandomChar());
                cypherTextBuider.append(plainText.charAt(i));
                cypherTextBuider.append(getRandomChar());
                wordlen++;
            }
        }

        return cypherTextBuider.toString();
    }

    public static String decrypt(String cypherText, Object... args) {
        boolean isEvenDay;

        if (cypherText == null) {
            throw new IllegalArgumentException("Cypher text cannot be null");
        }
        if (args.length < 1 || !(args[0] instanceof Boolean)) {
            throw new IllegalArgumentException("Arguments are of wrong type or insufficient");
        }
        isEvenDay = (Boolean) args[0];

        StringBuilder plainTextBuilder = new StringBuilder("");

        String[] words = cypherText.split(" ");
        for (String word : words) {
            if (word.length() == 1) {
                plainTextBuilder.append(" ");
            } else if (word.length() == 3) {
                plainTextBuilder.append(word.charAt(1));
            } else if (word.length() == 6) {
                plainTextBuilder.append(word.charAt(1));
                plainTextBuilder.append(word.charAt(4));
            } else {
                throw new RuntimeException("Invalid word length in cypher text: " + word.length());
            }
        }

        String plainText = plainTextBuilder.toString();
        if (isEvenDay) {
            plainText = reverseStringWords(plainText);
        }

        return plainText;
    }
}
