package cyphers.substitutional;

import org.junit.jupiter.api.Test;

import exceptions.EncoderDecoderConversionError;

import static org.junit.jupiter.api.Assertions.*;

public class MonoalphabeticTest {

    // e -> h, h -> l, l -> b, b -> e
    private static final String VALID_KEY = "aecdhfglijkbmnopqrstuvwxyz";
    private static final String INVALID_KEY = "abc";

    @Test
    public void testEncode_ValidInput() throws EncoderDecoderConversionError {
        String plainText = "hellobabydelta";
        String expectedCipherText = "lhbboeaeydhbta";
        String actualCipherText = Monoalphabetic.encode(plainText, VALID_KEY);
        assertEquals(expectedCipherText, actualCipherText);
    }

    @Test
    public void testDecode_ValidInput() throws EncoderDecoderConversionError {
        String cipherText = "lhbboeaeydhbta"; // Must match the result of encoding 'HELLO'
        String expectedPlainText = "hellobabydelta";
        String actualPlainText = Monoalphabetic.decode(cipherText, VALID_KEY);
        assertEquals(expectedPlainText, actualPlainText);
    }

    @Test
    public void testEncode_InvalidKeyLength() {
        Exception exception = assertThrows(EncoderDecoderConversionError.class, () -> {
            Monoalphabetic.encode("hello", INVALID_KEY);
        });
        assertEquals("Invalid key", exception.getMessage());
    }

    @Test
    public void testEncode_InvalidArgumentType() {
        Exception exception = assertThrows(EncoderDecoderConversionError.class, () -> {
            Monoalphabetic.encode("hello", 123);
        });
        assertEquals("Arguments are of wrong type or insufficient", exception.getMessage());
    }

    @Test
    public void testDecode_InvalidKeyLength() {
        Exception exception = assertThrows(EncoderDecoderConversionError.class, () -> {
            Monoalphabetic.decode("ciphertext", INVALID_KEY);
        });
        assertEquals("Invalid key", exception.getMessage());
    }

    @Test
    public void testDecode_InvalidArgumentType() {
        Exception exception = assertThrows(EncoderDecoderConversionError.class, () -> {
            Monoalphabetic.decode("ciphertext", 123);
        });
        assertEquals("Arguments are of wrong type or insufficient", exception.getMessage());
    }

    @Test
    public void testEncode_EmptyPlainText() throws EncoderDecoderConversionError {
        String expectedCipherText = ""; // Assuming that encoding an empty string returns an empty string
        String actualCipherText = Monoalphabetic.encode("", VALID_KEY);
        assertEquals(expectedCipherText, actualCipherText);
    }

    @Test
    public void testDecode_EmptyCipherText() throws EncoderDecoderConversionError {
        String expectedPlainText = ""; // Assuming that decoding an empty string returns an empty string
        String actualPlainText = Monoalphabetic.decode("", VALID_KEY);
        assertEquals(expectedPlainText, actualPlainText);
    }
}
