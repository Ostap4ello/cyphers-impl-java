package interfaces;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TelegraphAlphabetTest {

    @Test
    void alphabetLengthIs26() {
        assertEquals(26, TelegraphAlphabet.AlphabetLength, "Alphabet length should be 26");
    }
}
