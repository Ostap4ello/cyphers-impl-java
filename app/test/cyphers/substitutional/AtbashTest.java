package cyphers.substitutional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AtbashTest {
    @Test
    void testConversion() {
        assertEquals("zyx", Atbash.encode("abc"), "Atbash encode failed");
        assertEquals("abc", Atbash.decode("zyx"), "Atbash decode failed");
    }
}
