package cyphers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JavaExampleOperationsTest {

    private static class OutCapture implements AutoCloseable {
        private final PrintStream original = System.out;
        private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        OutCapture() {
            System.setOut(new PrintStream(baos));
        }

        String get() {
            return baos.toString();
        }

        @Override
        public void close() {
            System.setOut(original);
        }
    }

    @Test
    void writesRandomNumbersUntilPrime() {
        JavaExampleOperations ops = new JavaExampleOperations(12345L);

        try (OutCapture cap = new OutCapture()) {
            ops.writeOutRandNumbersUntillPrime();
            String out = cap.get().trim();
            String[] lines = out.split("\\R");
            assertTrue(lines.length >= 2, "Should print at least one number and the termination message");
            String terminator = lines[lines.length - 1];
            assertEquals("-- reached prime number - Exiting", terminator, "Termination message mismatch");

            int lastNum = Integer.parseInt(lines[lines.length - 2].trim());
            assertTrue(utils.Math.isPrime(lastNum), "Last printed number should be prime");
        }
    }

    @Test
    void writes10RandomPrimes() {
        JavaExampleOperations ops = new JavaExampleOperations(12345L);

        try (OutCapture cap = new OutCapture()) {
            ops.writeOut10RandomPrimes();
            String out = cap.get().trim();
            String[] lines = out.split("\\R");
            assertTrue(lines.length >= 2, "Should print primes and termination message");
            assertEquals("-- reached 10th prime number - Exiting", lines[lines.length - 1],
                    "Termination message mismatch");

            int primeCount = 0;
            for (int i = 0; i < lines.length - 1; i++) {
                String s = lines[i].trim();
                if (s.isEmpty())
                    continue;
                int n = Integer.parseInt(s);
                assertTrue(utils.Math.isPrime(n), "Printed number should be prime: " + n);
                primeCount++;
            }
            assertEquals(10, primeCount, "Should print exactly 10 primes");
        }
    }

    @Test
    void writes26Letters() {
        JavaExampleOperations ops = new JavaExampleOperations(12345L);

        try (OutCapture cap = new OutCapture()) {
            ops.writeOut26Letters();
            String out = cap.get();
            assertTrue(out.matches("^[a-z]{26}\\R$"), "Should print 26 lowercase letters and newline");
        }
    }
}
