package utils;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.log;
import static java.lang.Math.abs;

public class TSACryptoanalysisUtils {

    // 3.1
    public static Map<String, Double> getNgrammRelativeCounts(String text, int nGrammLength, boolean overlapping) {
        Map<String, Integer> ngrammCounts = getNgrammAbsoluteCounts(text, nGrammLength, overlapping);

        int totalNgramms = 0;
        if (overlapping) {
            totalNgramms = text.length() - nGrammLength + 1;
        } else {
            totalNgramms = text.length() / nGrammLength;
        }
        Map<String, Double> ngrammRelativeCounts = new HashMap<>();
        for (Map.Entry<String, Integer> entry : ngrammCounts.entrySet()) {
            ngrammRelativeCounts.put(entry.getKey(), entry.getValue() / (double) totalNgramms);
        }
        return ngrammRelativeCounts;
    }

    // 3.1
    public static Map<String, Integer> getNgrammAbsoluteCounts(String text, int nGrammLength, boolean overlapping) {
        if (nGrammLength <= 0) {
            throw new IllegalArgumentException("N-gramm length must be greater than 0.");
        }
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text must not be null or empty.");
        }
        int textLength = text.length();
        if (!overlapping && textLength % nGrammLength != 0) {
            throw new IllegalArgumentException(
                    "Text length must be a multiple of n-gramm length for non-overlapping n-gramms.");
        }

        Map<String, Integer> ngrammCounts = new HashMap<>();
        int step = overlapping ? 1 : nGrammLength;
        for (int i = 0; i <= text.length() - nGrammLength; i += step) {
            String ngramm = text.substring(i, i + nGrammLength);
            ngrammCounts.put(ngramm, ngrammCounts.getOrDefault(ngramm, 0) + 1);
        }
        return ngrammCounts;
    }

    // 3.2
    public static double coincidenceIndex(String text, int nGrammLength, boolean overlapping) {
        Map<String, Integer> ngrammCounts = getNgrammAbsoluteCounts(text, nGrammLength, overlapping);

        int totalNgramms = 0;
        if (overlapping) {
            totalNgramms = text.length() - nGrammLength + 1;
        } else {
            totalNgramms = text.length() / nGrammLength;
        }

        double coincidenceIndex = 0.0;
        for (Map.Entry<String, Integer> entry : ngrammCounts.entrySet()) {
            coincidenceIndex += entry.getValue() * (entry.getValue() - 1);
        }

        coincidenceIndex /= (double) (totalNgramms * (totalNgramms - 1));
        return coincidenceIndex;
    }

    // 3.3
    public static double entropy(String text, int nGrammLength, boolean overlapping) {
        Map<String, Double> ngrammRelativeCounts = getNgrammRelativeCounts(text, nGrammLength, overlapping);

        double entropy = 0.0;
        for (Map.Entry<String, Double> entry : ngrammRelativeCounts.entrySet()) {
            double p = entry.getValue();
            entropy += p * (log(p) / log(2));
        }
        entropy = -entropy;
        return entropy;
    }

    // NOTE: Note that this class is only for TSA (a-z) alphabet analysis.
    public static class LanguageICs {
        public static Map<String, Double> LanguageICMap = Map.of(
                "ENG", 0.0665,
                "GER", 0.0760,
                "SLK", 0.0603,
                "RND", 0.039 // minimal for 26 letters alphabet
        );
    }

    public static Map<String, Double> getLanguageICDifferences(String text, int nGrammLength, boolean overlapping) {
        double textIC = coincidenceIndex(text, nGrammLength, overlapping);
        Map<String, Double> differences = new HashMap<>();
        for (Map.Entry<String, Double> entry : LanguageICs.LanguageICMap.entrySet()) {
            differences.put(entry.getKey(), abs(textIC - entry.getValue()));
        }
        return differences;
    }

    public static String getMostProbableLanguage(Map<String, Double> icDifferences, double threshold) {
        String probableLanguage = "Unknown";
        double minDifference = Double.MAX_VALUE;
        for (Map.Entry<String, Double> entry : icDifferences.entrySet()) {
            if (entry.getValue() < minDifference && entry.getValue() <= threshold) {
                minDifference = entry.getValue();
                probableLanguage = entry.getKey();
            }
        }
        return probableLanguage;
    }
}
