package cyphers;

import exceptions.NotImplementedError;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractEncryptionAlgorithmTest {

    @Test
    void abstractMethodsReturnNull() {
        assertThrows(NotImplementedError.class,
                () -> AbstractEncryptionAlgorithm.encrypt("abc"),
                "Abstract encrypt should return null");
        assertThrows(NotImplementedError.class,
                () -> AbstractEncryptionAlgorithm.decrypt("xyz"),
                "Abstract decrypt should return null");
    }
}
