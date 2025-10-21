package cyphers.substitutional;

import exceptions.EncoderDecoderConversionError;
import interfaces.TelegraphAlphabet;
import utils.Utils;

public abstract class Polybius implements TelegraphAlphabet {
    private final static int KeyLength = 25;
    private final static int RowLength = 5;

    public static String encode(String plainText, Object... args) throws EncoderDecoderConversionError {
        if (args.length != 0) {
            throw new EncoderDecoderConversionError("Arguments are of wrong type or insufficient");
        }
        // String key;
        // if (args.length < 1 ||
        // !(args[0] instanceof String)) {
        // throw new EncoderDecoderConversionError("Arguments are of wrong type or
        // insufficient");
        // }
        // key = (String) args[0];
        // if (key.length() != AlphabetLength) {
        // throw new EncoderDecoderConversionError("Invalid key");
        // }
        // // TODO: key verification

        StringBuilder output = new StringBuilder("");

        for (Character c : plainText.toCharArray()) {
            int i = Utils.ctoiTSA(c);
            if (i > Utils.ctoiTSA('i')) i--;

            output.append(i / RowLength + 1);
            output.append(i % RowLength + 1);
        }

        return output.toString();
    }

    public static String decode(String cypherText, Object... args) throws EncoderDecoderConversionError {
        if (args.length != 0) {
            throw new EncoderDecoderConversionError("Arguments are of wrong type or insufficient");
        }
        // String key;
        // if (args.length < 1 ||
        // !(args[0] instanceof String)) {
        // throw new EncoderDecoderConversionError("Arguments are of wrong type or
        // insufficient");
        // }
        // key = (String) args[0];
        // if (key.length() != KeyLength) {
        // throw new EncoderDecoderConversionError("Invalid key");
        // }
        // // TODO: key verification

        if (cypherText.length() % 2 != 0) {
            throw new EncoderDecoderConversionError("Invalid text");
        }

        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < cypherText.length(); i += 2) {
            int rI = Integer.parseInt(cypherText.substring(i, i + 1)) - 1;
            int cI = Integer.parseInt(cypherText.substring(i + 1, i + 2)) - 1;

            int c = Utils.itocTSA(rI * RowLength + cI);
            if (c > (int)('i')) c++;
            char o = (char) c;
            output.append(o);
        }

        return output.toString();
    }
}
