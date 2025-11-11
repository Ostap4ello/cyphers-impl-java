package steganography;
import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FLOWTest {

    @Test
    void basicArgumentValidation() {
        assertThrows(IllegalArgumentException.class, () -> FLOW.encrypt(null, "/non/existent"), "encrypt should return null for null input");
        assertThrows(IllegalArgumentException.class, () -> FLOW.encrypt("abc"), "encrypt should return null when args missing");
        assertThrows(IllegalArgumentException.class, () -> FLOW.encrypt("abc", 123), "encrypt should return null for wrong arg type");
        assertThrows(IllegalArgumentException.class, () -> FLOW.encrypt("abc", "/non/existent/path.txt"), "encrypt should return null for invalid file path");

        assertThrows(IllegalArgumentException.class, () -> FLOW.decrypt(null), "decrypt should return null for null input");
    }

    @Test
    void encryptDecodeWithTempDictionary() {
        try {
            File tmp = new File("/home/ostap4ello/Workspace/stu/ksif/cvs/app/test/_tmp_flow_dict.tmp");
            tmp.getParentFile().mkdirs();
            java.nio.file.Files.write(tmp.toPath(),
                    ("apple\nbanana\navocado\nblueberry\ncarrot\ncherry\n").getBytes());

            String ct = FLOW.encrypt("abcabc", tmp.getAbsolutePath());
            assertNotNull(ct, "encrypt should return non-null for valid inputs");

            String pt = FLOW.decrypt(ct);
            assertNotNull(pt, "decrypt should return non-null");
        } catch (Exception e) {
            fail("FLOWTest failed due to exception: " + e);
        }
    }
}
