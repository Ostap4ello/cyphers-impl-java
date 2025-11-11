package cyphers.substitutional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AtbashTest {
    @Test
    void testConversion() {
        assertEquals("zyx", Atbash.encrypt("abc"), "Atbash encrypt failed");
        assertEquals("abc", Atbash.decrypt("zyx"), "Atbash decrypt failed");
    }
}
