package com.mycompany.tucil3_13522164;

import java.util.Queue;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class UCS implements Algorithm {
    private String startWord;
    private String endWord;
    private Long Time;
    private Queue<List<String>> path;
    private List<String> result;
    private Set<String> checkedWord;
    private int nodeChecked;

    public UCS(String startWord, String endWord) {
        this.startWord = startWord;
        this.endWord = endWord;
        this.Time = 0L;
        this.path = new LinkedList<>();
        this.result = new ArrayList<String>();
        this.checkedWord = new HashSet<String>();
        this.nodeChecked = 0;
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
        String[][] resultArray = new String[this.result.size()][this.result.get(0).length()];
        for (int i = 0; i < this.result.size(); i++) {
            resultArray[i] = this.result.get(i).split("");
        }
        return resultArray;
    }

    public Result solve() {
        // Activate Garbage Collector and Get Current Memory Usage
        System.gc();
        int memoryNow = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;

        // Initialize Start Time and Path
        Long startTimeLong = System.currentTimeMillis();
        path.add(new ArrayList<String>() { {
            add(startWord);
        } });
        checkedWord.add(startWord);

        while (!path.isEmpty()) {

            // Get First Path
            List<String> currentPath = path.poll();
            String tempWord = currentPath.get(currentPath.size() - 1);
            this.nodeChecked++;

            // Check if the word is the solution is found
            if (tempWord.equals(endWord)) {
                this.result = currentPath;
                this.Time = System.currentTimeMillis() - startTimeLong;
                int memory = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
                System.gc();
                return new Result(getResult(), nodeChecked, this.Time, memory-memoryNow);
            }

            // Add to checked word
            checkedWord.add(tempWord);

            // Get next linked word
            List<String> linkingWordList = WordChecker.wordMap.get(tempWord);

            // Skip evaluation proccess if no linked word exist
            if (linkingWordList.isEmpty()) {
                continue;
            }

            // Evaluate each linked word
            for (String word : linkingWordList) {
                if (!checkedWord.contains(word)) {
                    List<String> newPath = new ArrayList<>(currentPath);
                    newPath.add(word);
                    path.add(newPath);
                }
            }
        }

        // No Solution
        this.result.clear();
        this.Time = System.currentTimeMillis() - startTimeLong;
        return new Result(getResult(), nodeChecked, this.Time, 0);
    }
}
