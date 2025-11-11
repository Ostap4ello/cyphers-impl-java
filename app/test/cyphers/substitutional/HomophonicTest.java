package cyphers.substitutional;

import org.junit.jupiter.api.Test;

import utils.Math;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class HomophonicTest {

    void checkTranslation(String plainText, String encryptedText, String decryptedText, Map<Character, List<Integer>> key) {
        assertEquals(plainText, decryptedText);

        for (int i = 0; i < plainText.length(); i++) {
            char pChar = plainText.charAt(i);
            String[] cypherTokens = encryptedText.split(" ");
            String cToken = cypherTokens[i];

            List<Integer> possibleValues = key.get(pChar);
            boolean matchFound = false;
            for (Integer val : possibleValues) {
                if (cToken.equals(val.toString())) {
                    matchFound = true;
                    break;
                }
            }

            assertTrue(matchFound, "No matching cypher token found for plainText character: " + pChar);
        }
    }


    @Test
    void encryptDecrypt1() {
        String skey = "abcdefghijklmnopqrstuvwxyzsomemorecharsandevenmorechars";
        Map<Character, List<Integer>> key = Math.generateHomophonicKeyBenjaminFranklin(skey);

        String plainText = "thisisarandomplaintextmessage";

        String encryptedText = Homophonic.encrypt(plainText, key, true);
        String decryptedText = Homophonic.decrypt(encryptedText, key, true);

        checkTranslation(plainText, encryptedText, decryptedText, key);
    }

    @Test
    void encryptDecrypt2() {
        String skey = "abcdefghijklmnopqrstuvwxyzsomemorecharsandevenmorechars";
        Map<Character, List<Integer>> key = Math.generateHomophonicKeyBenjaminFranklin(skey);

        String plainText = "thisisarandomplaintextmessage";

        String encryptedText = Homophonic.encrypt(plainText, key, false);
        String decryptedText = Homophonic.decrypt(encryptedText, key, false);

        checkTranslation(plainText, encryptedText, decryptedText, key);
    }
}
