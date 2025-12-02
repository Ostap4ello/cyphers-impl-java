package utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class TSACryptoanalysisUtilsTest {

    @Test
    void getNgrammAbsoluteCounts_validInput_returnsCorrectCounts() {

        String text = "abcabc";
        int nGrammLength = 2;
        Map<String, Integer> actual = TSACryptoanalysisUtils.getNgrammAbsoluteCounts(text, nGrammLength, true);
        assertEquals(2, actual.get("ab"));
        assertEquals(2, actual.get("bc"));
        assertEquals(1, actual.get("ca"));
    }

    @Test
    void getNgrammAbsoluteCounts_invalidInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> TSACryptoanalysisUtils.getNgrammAbsoluteCounts("abc", 0, false));
        assertThrows(IllegalArgumentException.class, () -> TSACryptoanalysisUtils.getNgrammAbsoluteCounts("abc", 4, false));
    }

    @Test
    void getNgrammRelativeCounts_validInput_returnsCorrectRelativeCounts() {
        String text = "abcabc";
        int nGrammLength = 2;
        boolean overlapping = true;
        Map<String, Double> expected = TSACryptoanalysisUtils.getNgrammRelativeCounts(text, nGrammLength, overlapping);
        assertEquals(0.4, expected.get("ab"));
        assertEquals(0.4, expected.get("bc"));
        assertEquals(0.2, expected.get("ca"));
    }

    @Test
    void coincidenceIndex_validInput_returnsCorrectCoincidenceIndex() {
        String text = "abcabc";
        int nGrammLength = 2;
        boolean overlapping = true;
        double expected = 0.2;
        double actual = TSACryptoanalysisUtils.coincidenceIndex(text, nGrammLength, overlapping);
        assertEquals(expected, actual, 0.001);
    }

    @Test
    void entropy_validInput_returnsCorrectEntropy() {
        // TODO:
    }

    @Test
    void getLanguageICDifferences_validInput_returnsCorrectDifferences() {
        // TODO:
    }

    @Test
    void getMostProbableLanguage_validInput_returnsCorrectLanguage() {
        // TODO:
    }
}
