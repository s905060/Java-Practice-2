/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

/**
 *
 * @Author by s905060 on 2013.
 */
import java.io.*;
import java.util.*;

public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws java.io.IOException {
        
        System.out.print("Please Enter input files: PATH + Filename \n");
        //  open up standard input
        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        String fileNameInput = br1.readLine();    
        
        System.out.print("Please Enter patterns files: PATH + Filename \n");
        //  open up standard input
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        String fileNamePatterns = br2.readLine();
        
        System.out.println("");
        //String fileNameInput = "input.txt";
        //String fileNamePatterns = "patterns.txt";

        try {
            //Create object of FileReader
            FileReader inputFile = new FileReader(fileNameInput);
            FileReader Patterns = new FileReader(fileNamePatterns);

            //Instantiate the BufferedReader Class
            BufferedReader bufferReaderInput = new BufferedReader(inputFile);
            BufferedReader bufferReaderPatterns = new BufferedReader(Patterns);

            List<String> inputs = new ArrayList<String>();
            //Variable to hold the one line data
            String input = null;
            while ((input = bufferReaderInput.readLine()) != null) {
                inputs.add(input);
            }

            //Close the buffer reader
            bufferReaderInput.close();
            inputs.toArray(new String[inputs.size()]);

            List<String> lines = new ArrayList<String>();
            //Variable to hold the one line data
            String patterns = null;
            while ((patterns = bufferReaderPatterns.readLine()) != null) {
                lines.add(patterns);
            }

            //Close the buffer reader
            bufferReaderPatterns.close();
            lines.toArray(new String[lines.size()]);

            Mode1(inputs, lines);
            Mode2(inputs, lines);
            Mode3(inputs, lines);

        } catch (Exception e) {
            System.out.println("Error while reading file line by line:" + e.getMessage());
        }
    }

    public static void Mode1(List<String> inputs, List<String> lines) {

        System.out.println("Mode1 outputs:");
        for (String matcher : inputs) {
            for (String line : lines) {
                if (matcher.equals(line)) {
                    System.out.println(matcher);
                }
            }
        }

        System.out.println("");
    }

    public static void Mode2(List<String> inputs, List<String> lines) {

        System.out.println("Mode2 outputs:");
        for (String matcher : inputs) {
            for (String line : lines) {
                boolean b = matcher.contains(line);
                if (b == true) {
                    System.out.println(matcher);
                }
            }
        }

        System.out.println("");
    }

    public static void Mode3(List<String> inputs, List<String> lines) {

        System.out.println("Mode3 outputs:");
        for (String matcher : inputs) {
            for (String line : lines) {
                int n = editDistance(matcher, line);
                if (n <= 1) {
                    System.out.println(matcher);
                }
            }
        }

        System.out.println("");
    }

    public static int editDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] d = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            d[0][j] = j;
        }
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    d[i][j] = min((d[i - 1][j] + 1), (d[i][j - 1] + 1), (d[i - 1][j - 1] + 1));
                }
            }
        }
        return (d[m][n]);
    }

    public static int min(int a, int b, int c) {
        return (Math.min(Math.min(a, b), c));
    }
}
