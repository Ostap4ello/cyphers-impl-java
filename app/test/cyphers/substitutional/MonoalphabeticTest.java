package cyphers.substitutional;

import org.junit.jupiter.api.Test;

import exceptions.EncryptionAlgorithmConversionError;

import static org.junit.jupiter.api.Assertions.*;

public class MonoalphabeticTest {

    // e -> h, h -> l, l -> b, b -> e
    private static final String VALID_KEY = "aecdhfglijkbmnopqrstuvwxyz";
    private static final String INVALID_KEY = "abc";

    @Test
    public void testEncypher_ValidInput() throws EncryptionAlgorithmConversionError {
        String plainText = "hellobabydelta";
        String expectedCipherText = "lhbboeaeydhbta";
        String actualCipherText = Monoalphabetic.encrypt(plainText, VALID_KEY);
        assertEquals(expectedCipherText, actualCipherText);
    }

    @Test
    public void testDecyphere_ValidInput() throws EncryptionAlgorithmConversionError {
        String cipherText = "lhbboeaeydhbta"; // Must match the result of encoding 'HELLO'
        String expectedPlainText = "hellobabydelta";
        String actualPlainText = Monoalphabetic.decrypt(cipherText, VALID_KEY);
        assertEquals(expectedPlainText, actualPlainText);
    }

    @Test
    public void testEncypher_InvalidKeyLength() {
        Exception exception = assertThrows(EncryptionAlgorithmConversionError.class, () -> {
            Monoalphabetic.encrypt("hello", INVALID_KEY);
        });
        assertEquals("Invalid key", exception.getMessage());
    }

    @Test
    public void testEncypher_InvalidArgumentType() {
        Exception exception = assertThrows(EncryptionAlgorithmConversionError.class, () -> {
            Monoalphabetic.encrypt("hello", 123);
        });
        assertEquals("Arguments are of wrong type or insufficient", exception.getMessage());
    }

    @Test
    public void testDecyphere_InvalidKeyLength() {
        Exception exception = assertThrows(EncryptionAlgorithmConversionError.class, () -> {
            Monoalphabetic.decrypt("ciphertext", INVALID_KEY);
        });
        assertEquals("Invalid key", exception.getMessage());
    }

    @Test
    public void testDecyphere_InvalidArgumentType() {
        Exception exception = assertThrows(EncryptionAlgorithmConversionError.class, () -> {
            Monoalphabetic.decrypt("ciphertext", 123);
        });
        assertEquals("Arguments are of wrong type or insufficient", exception.getMessage());
    }

    @Test
    public void testEncypher_EmptyPlainText() throws EncryptionAlgorithmConversionError {
        String expectedCipherText = ""; // Assuming that encoding an empty string returns an empty string
        String actualCipherText = Monoalphabetic.encrypt("", VALID_KEY);
        assertEquals(expectedCipherText, actualCipherText);
    }

    @Test
    public void testDecyphere_EmptyCipherText() throws EncryptionAlgorithmConversionError {
        String expectedPlainText = ""; // Assuming that decoding an empty string returns an empty string
        String actualPlainText = Monoalphabetic.decrypt("", VALID_KEY);
        assertEquals(expectedPlainText, actualPlainText);
    }
}
