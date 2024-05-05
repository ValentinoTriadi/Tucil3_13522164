package com.mycompany.tucil3_13522164;

import java.util.List;
import java.util.ArrayList;

public class Greedy implements Algorithm {
    private String startWord;
    private String endWord;
    private List<String> result;
    private int nodeChecked;
    private Long Time;

    public Greedy(String startWord, String endWord) {
        this.startWord = startWord;
        this.endWord = endWord;
        this.result = new ArrayList<String>();
        this.nodeChecked = 0;
        this.Time = 0L;
    }

    public Result solve() {
        // Activate Garbage Collector and Get Current Memory Usage
        System.gc();
        int memoryNow = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;

        // Initialize Start time
        String tempWord = startWord;
        Long startTimeLong = System.currentTimeMillis();

        while (!tempWord.equals(endWord)) {

            // Add to Result
            this.result.add(tempWord);

            // Get next linked word
            List<String> linkingWordList = WordChecker.wordMap.get(tempWord);

            // add node checked
            this.nodeChecked++;
            
            // No Solution
            if (linkingWordList.isEmpty()) {
                System.out.println("No Solution");
                this.Time = System.currentTimeMillis() - startTimeLong;
                return new Result(new String[0][0], this.nodeChecked, this.Time, 0);
            }

            // Get Most Similiar Children with EndWord but not in result
            tempWord = Utils.getFirstMostSimiliar(linkingWordList, endWord, result);

            if (tempWord.equals("")) {
                System.out.println("No Solution");
                this.Time = System.currentTimeMillis() - startTimeLong;
                return new Result(new String[0][0], this.nodeChecked, this.Time, 0);
            }
        }
        
        // Solution Found
        this.result.add(endWord);
        this.nodeChecked++;
        this.Time = System.currentTimeMillis() - startTimeLong;
        int memory = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
        System.gc();
        return new Result(getResult(), this.nodeChecked, this.Time, memory - memoryNow);
    }

    public void printResult() {
        for (String temp : this.result) {
            System.out.println(temp);
        }
    }

    public String[][] getResult() {
        if (this.result.isEmpty()) {
            return new String[0][0];
        }
        int wordLength = this.result.get(0).length();
        String[][] result = new String[this.result.size()][wordLength];
        for (String temp : this.result) {
            for (int i = 0; i < wordLength; i++) {
                result[this.result.indexOf(temp)][i] = String.valueOf(temp.charAt(i));
            }
        }
        return result;
    }
}
