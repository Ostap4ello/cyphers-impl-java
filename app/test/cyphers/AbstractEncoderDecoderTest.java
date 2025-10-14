package cyphers;

import exceptions.NotImplementedError;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractEncoderDecoderTest {

    @Test
    void abstractMethodsReturnNull() {
        assertThrows(NotImplementedError.class,
                () -> AbstractEncoderDecoder.encode("abc"),
                "Abstract encode should return null");
        assertThrows(NotImplementedError.class,
                () -> AbstractEncoderDecoder.decode("xyz"),
                "Abstract decode should return null");
    }
}
