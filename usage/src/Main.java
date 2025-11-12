import algorithms.*;

public class Main {
    public static void main(String[] args) {
        String plainText = "testujemevaseimplementaciecivietespravnesifrovatadesifrovat";
        String encryptedText;
        String decryptedText;

        String key1 = "zyxwvutsrqponmlkjihgfedcba";
        String key2 = "klasicke";
        String key3 = "sifry";

        encryptedText = STP.encrypt(plainText, key1, key2, key3);
        decryptedText = STP.decrypt(encryptedText, key1, key2, key3);

        System.out.println("--- Substitutional-Transpositional-Polyalphabetic Cipher (STP) ---");
        System.out.println("Plain text:     " + plainText);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);

        System.out.println();

        key1 = "predmet";
        key2 = "klasickesifry";

        encryptedText = TT.encrypt(plainText, key1, key2);
        decryptedText = TT.decrypt(encryptedText, key1, key2);

        System.out.println("--- Double Transpositional Cipher (TT) ---");
        System.out.println("Plain text:     " + plainText);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);

    }
}
