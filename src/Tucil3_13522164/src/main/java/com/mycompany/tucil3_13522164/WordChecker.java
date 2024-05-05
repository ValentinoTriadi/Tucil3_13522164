package com.mycompany.tucil3_13522164;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class WordChecker {
    public static Map<String, List<String>> wordMap = new HashMap<String, List<String>>();

    public static boolean isWordExist(String word) {
        return wordMap.containsKey(word);
    }

    public static void load() {
        wordMap = new HashMap<String, List<String>>();
        try {
            for (int i = 1; i <= 15; i++) {
                File file = new File("src/main/java/com/mycompany/tucil3_13522164/dict/data" + i + ".txt");
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String temp = scanner.nextLine();
                    Integer len = Integer.parseInt(scanner.nextLine());
                    List<String> list = new ArrayList<String>();
                    for (int j = 0; j < len; j++) {
                        list.add(scanner.nextLine());
                    }
                    if (!wordMap.containsKey(temp)) {
                        wordMap.put(temp, list);
                    }
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void loadLength(int lengthWord) {
        wordMap = new HashMap<String, List<String>>();
        try {
            File file = new File("src/main/java/com/mycompany/tucil3_13522164/dict/data" + lengthWord + ".txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                Integer len = Integer.parseInt(scanner.nextLine());
                List<String> list = new ArrayList<String>();
                for (int j = 0; j < len; j++) {
                    list.add(scanner.nextLine());
                }
                if (!wordMap.containsKey(temp)) {
                    wordMap.put(temp, list);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
