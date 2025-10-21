package utils;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    void itocAndCtoi() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder alphabetFromNumbersBuilder = new StringBuilder("");
        for (int i = 0; i < 26; i++) {
            char c = Utils.itocTSA(i);
            alphabetFromNumbersBuilder.append(c);
            int j = Utils.ctoiTSA(c);
            assertEquals(i, j, "itoc/ctoi mismatch at " + i);
        }
        assertEquals(alphabet, alphabetFromNumbersBuilder.toString(), "Alphabet mismatch");
    }

    @Test
    void convertToTsa() {
        assertEquals("ab c", Utils.convertToTSA("Ab C!"), "convertToTSA behavior (current impl)");
        assertEquals("abc", Utils.convertToTSA("Ab C!", false), "convertToTSA(allowSpaces=false) behavior (current impl)");
    }
    @Test
    void chooseFile() {
        // TODO:
    }
    @Test
    void saveAndReadText() {
        // TODO:
    }

    @Test
    void serializeDeserialize() {
        // TODO:
    }

    @Test
    void getAllPossibleStringsTSA() {
        Set<String> o = Utils.getAllPossibleStringsTSA(1);
        assertEquals(26, o.size(), "getAllPossibleStrings(1) size should be 26");
        assertTrue(o.contains("a") && o.contains("z"), "set should contain 'a' and 'z'");
    }

    @Test
    void getAllPossibleStrings() {
        Set<Character> letters = new LinkedHashSet<>();
        letters.add('a');
        letters.add('b');
        letters.add('c');
        Set<String> strings = new LinkedHashSet<>();
        strings.add("aa");
        strings.add("ab");
        strings.add("ac");
        strings.add("ba");
        strings.add("bb");
        strings.add("bc");
        strings.add("ca");
        strings.add("cb");
        strings.add("cc");
        Set<String> o_strings = Utils.getAllPossibleStrings(2, letters);
        assertEquals(strings.size(), o_strings.size(), "getAllPossibleStrings(2, {a,b,c}) size should be 9");
        assertEquals(strings, o_strings, "getAllPossibleStrings(1) content mismatch");
    }

}
