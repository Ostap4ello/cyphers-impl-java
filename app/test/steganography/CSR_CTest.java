package steganography;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CSR_CTest {

    @Test
    void invalidArguments() {
        assertThrows(IllegalArgumentException.class, () -> CSR_C.encrypt(null, true), "encrypt should return null for null input");
        assertThrows(IllegalArgumentException.class, () -> CSR_C.encrypt("abc"), "encrypt should return null when args missing");
        assertThrows(IllegalArgumentException.class, () -> CSR_C.decrypt(null, true), "decrypt should return null for null input");
        assertThrows(IllegalArgumentException.class, () -> CSR_C.decrypt("abc"), "decrypt should return null when args missing");
    }

    @Test
    void roundTripOddDay() {
        String pt = "ab cd";
        String ct = CSR_C.encrypt(pt, false);
        String rt = CSR_C.decrypt(ct, false);
        assertEquals(pt, rt, "Round-trip failed for odd day");
    }

    @Test
    void roundTripEvenDay() {
        String pt = "hello world";
        String ct = CSR_C.encrypt(pt, true);
        String rt = CSR_C.decrypt(ct, true);
        assertEquals(pt, rt, "Round-trip failed for even day");
    }
}
