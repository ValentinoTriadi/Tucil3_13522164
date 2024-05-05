package com.mycompany.tucil3_13522164;

public class Result {
    private String[][] result;
    private int nodeChecked;
    private Long time;
    private int memory;

    Result(String[][] result, int nodeChecked, Long time, int memory) {
        this.result = result;
        this.nodeChecked = nodeChecked;
        this.time = time;
        this.memory = memory;
    }

    public String[][] getResult() {
        return result;
    }

    public int getNodeChecked() {
        return nodeChecked;
    }

    public String getTime() {
        return String.valueOf(time) + " ms";
    }

    public String getMemory() {
        return String.valueOf(memory) + " KB";
    }
}
