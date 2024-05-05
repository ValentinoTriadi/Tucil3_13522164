package com.mycompany.tucil3_13522164;

import java.util.List;

public class Utils {

    public static int getSimiliarity(String word1, String word2){
        if (word1.length() != word2.length()) {
            return 0;
        }
        int length = word1.length();
        int result = 0;
        for (int i = 0; i < length; i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                result++;
            }
        }
        return result;
    }

    public static String getFirstMostSimiliar(List<String> wordList, String word, List<String> bannedWord){
        int maxSimiliarity = -1;
        String result = "";
        for (String temp : wordList) {
            if (bannedWord.contains(temp)) {
                continue;
            }
            int similiarity = getSimiliarity(temp, word);
            if (similiarity > maxSimiliarity) {
                maxSimiliarity = similiarity;
                result = temp;
            }
            if (similiarity == word.length()) {
                return temp;
            }
        }
        return result;
    }

    public static int getDifferentCharacter(String word1, String word2){
        if (word1.length() != word2.length()) {
            return 0;
        }
        int length = word1.length();
        int result = 0;
        for (int i = 0; i < length; i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                result++;
            }
        }
        return result;
    }
}
