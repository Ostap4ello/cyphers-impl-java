package cyphers;

import java.util.Random;

import interfaces.TelegraphAlphabet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.Math;

public class JavaExampleOperations implements TelegraphAlphabet {
    private static Random rnd = null; // random number generator
    private final char SEPARATOR = ',';

    public JavaExampleOperations() {
        rnd = new Random(System.currentTimeMillis());
    }

    public JavaExampleOperations(long customSeed) {
        rnd = new Random(customSeed);
    }

    // 1.2
    public void writeOutRandNumbersUntillPrime() {
        int n = -1;
        do {
            n = rnd.nextInt(98) + 3; // <3, 100>
            System.out.format("%d\n", n);
        } while (!Math.isPrime(n));
        System.out.println("-- reached prime number - Exiting\n");
    }

    // 1.3
    public void writeOut10RandomPrimes() {

        int i = 10;
        int n;
        while (i > 0) {
            n = rnd.nextInt(98) + 3; // <3, 100>
            if (Math.isPrime(n)) {
                System.out.format("%d\n", n);
                i--;
            }
        }
        System.out.println("-- reached 10th prime number - Exiting\n");
    }

    // 1.3
    public void writeOut26Letters() {
        int i = AlphabetLength;
        ArrayList<Character> l = new ArrayList<Character>();
        while (i-- > 0) {
            char c = 'a';
            int off = rnd.nextInt(26);
            c += off;
            l.add(c);
        }
        l.sort((x, y) -> {
            return Character.compare(x, y);
        });
        for (char c : l) {
            System.out.format("%c", c);
        }
        System.out.format("\n");
    }

    // 1.4
    public void random100NumsToStrToArray() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            int n = rnd.nextInt(101);
            sb.append(Integer.toString(n));
            sb.append(SEPARATOR);
        }
        String s = sb.toString();
        System.out.format("Generated string:\n%s\n", s);

        String[] sa = s.split(Character.toString(SEPARATOR));
        Integer[] ia = new Integer[sa.length];

        for (int i = 0; i < sa.length; i++) {
            ia[i] = Integer.parseInt(sa[i]);
        }

        for (int n : ia) {
            System.out.format("%d ", n);
        }
        System.out.format("\n -- end of separated string\n");
    }

    // 1.5
    void arrayToListAndBack() {
        String[] sa = { "Ado", "Pato", "Jozo", "Fero", "Robert" };
        List<String> sl = Arrays.asList(sa); // mutable list
        sl.toArray(sa); // back to array
    }

    // 1.6
    void createAndSplitString() {

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 100; i++) {
            int n = rnd.nextInt(101);
            if (n % 2 == 0)
                continue;

            sb.append(Integer.toString(n));
            sb.append(SEPARATOR);
        }

        String s = sb.toString();

        String[] sa = s.split(Character.toString(SEPARATOR));
        Integer[] ia = new Integer[sa.length];
        for (int i = 0; i < ia.length; i++) {
            ia[i] = Integer.parseInt(sa[i]);
        }

        for (int n : ia) {
            System.out.format("%d ", n);
        }
    }

}
