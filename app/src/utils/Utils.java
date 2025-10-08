package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import interfaces.TelegraphAlphabet;

public class Utils implements TelegraphAlphabet {

    public static char itocTSA(int i) {
        return (char) ('a' + i);
    }

    public static int ctoiTSA(char c) {
        return (int) (c - 'a');

    }

    // 1.7 (util)
    public static String convertToTSA(String s, boolean allowSpaces) {
        s.toLowerCase();
        if (allowSpaces)
            s.replaceAll("[^a-z] ", "");
        else
            s.replaceAll("[^a-z]", "");
        return s;
    }

    public static String convertToTSA(String s) {
        return convertToTSA(s, true);
    }

    // 1.8 (util)
    public static File chooseFile(String path, String title) {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setCurrentDirectory(new File(path));
        chooser.setDialogTitle(title);

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images",
                "jpg", "gif");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }
        return chooser.getSelectedFile();
    }

    // 1.9 (util)
    public static boolean saveTextToFile(File file, String text) {
        if (file == null) {
            return false;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred.");
            return false;
        }
    }

    public static String readTextFromFile(File file) {
        if (file == null) {
            return null;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder("");
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
        return null;
    }

    // 1.8
    public static boolean serializeObjectToFile(File file, Object obj) throws IOException {
        if (file == null || obj == null) {
            return false;
        }

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        fos.close();
        return true;
    }

    public static Object deserializeObjectFromFile(File file) throws IOException, ClassNotFoundException {
        if (file == null) {
            return null;
        }

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        ois.close();
        fis.close();

        return obj;
    }

    // 1.14
    private static void pushAlphabetToStack(Stack<Character> stack) {
        for (int i = 0; i < AlphabetLength; i++) {
            stack.push(itocTSA(i));
        }
    }

    public static Set<String> getAllPossibleStrings(int n) {
        Stack<Character> stack = new Stack<>();
        Set<String> resultSet = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder("");

        pushAlphabetToStack(stack);
        while (!stack.isEmpty()) {
            char c = stack.pop();
            sb.append(c);
            if (sb.length() == n) {
                resultSet.add(sb.toString());
                sb.deleteCharAt(sb.length() - 1);
            } else {
                pushAlphabetToStack(stack);
            }
        }
        return resultSet;
    }

    public static void printAllPossibleStrings(int n) {
        Set<String> resultSet = getAllPossibleStrings(n);
        for (String s : resultSet) {
            System.out.println(s);
        }
    }
}
