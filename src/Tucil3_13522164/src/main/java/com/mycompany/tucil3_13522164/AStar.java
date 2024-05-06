package com.mycompany.tucil3_13522164;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

public class AStar implements Algorithm {
    private String startWord;
    private String endWord;
    private Long time;
    private Queue<AStarNode> path;
    private AStarNode result;
    private int nodeChecked;
    private Set<String> checkedWord;

    public AStar(String startWord, String endWord) {
        this.startWord = startWord;
        this.endWord = endWord;
        this.time = 0L;
        this.path = new PriorityQueue<AStarNode>(new AStarNodeComparator());
        this.nodeChecked = 0;
        this.checkedWord = new HashSet<String>();
    }

    public void printResult() {
        if (result == null) {
            System.out.println("No Solution");
            return;
        }
        for (String temp : result.getPath()) {
            System.out.println(temp);
        }
    }

    public String[][] getResult() {
        if (result == null) {
            return new String[0][0];
        }
        List<String> resultPath = result.getPath();
        String[][] resultArray = new String[resultPath.size()][startWord.length()];
        for (int i = 0; i < resultPath.size(); i++) {
            resultArray[i] = resultPath.get(i).split("");
        }
        return resultArray;
    }

    public Result solve() {
        // Activate Garbage Collector and Get Current Memory Usage
        System.gc();
        int memoryNow = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
        
        // Initialize Start Time and Path
        Long startTimeLong = System.currentTimeMillis();
        List<String> startPath = new ArrayList<String>(){{
            add(startWord);
        }};
        path.add(new AStarNode(startPath, getCost(startPath))); 

        while (!path.isEmpty()) {

            // Get First Path
            AStarNode currentPath = path.poll();

            // get last word in path
            String tempWord = currentPath.getLastWord();

            // add node checked
            nodeChecked++;

            // Check if the word is the solution is found
            if (tempWord.equals(endWord)) {
                this.result = currentPath;
                this.time = System.currentTimeMillis() - startTimeLong;
                int memory = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
                System.gc();
                return new Result(getResult(), nodeChecked, this.time, memory - memoryNow);
            }
            checkedWord.add(tempWord);

            // Get next linked word
            List<String> linkingWordList = WordChecker.wordMap.get(tempWord);

            // No Solution
            if (linkingWordList.isEmpty()) {
                System.out.println("No Solution");
                this.result = null;
                this.time = System.currentTimeMillis() - startTimeLong;
                return new Result(new String[0][0], nodeChecked, this.time, 0);
            }

            // Evaluate each linked word
            for (String word : linkingWordList) {
                if (!checkedWord.contains(word)) {
                    List<String> newPath = new ArrayList<>(currentPath.getPath());
                    newPath.add(word);
                    path.add(new AStarNode(newPath, getCost(newPath)));
                }
            }
        }

        // No Solution
        this.result = null;
        this.time = System.currentTimeMillis() - startTimeLong;
        return new Result(new String[0][0], nodeChecked, this.time, 0);
    }

    private int getCost(List<String> path) {
        int cost = path.size() - 1;
        return cost + Utils.getDifferentCharacter(path.get(cost), endWord);
    }
}