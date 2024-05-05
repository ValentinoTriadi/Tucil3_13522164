package com.mycompany.tucil3_13522164;

import java.util.ArrayList;
import java.util.List;

public class AStarNode {
    private List<String> path;
    private int cost;

    public AStarNode(List<String> path, int cost) {
        this.path = new ArrayList<>(path);
        this.cost = cost;
    }

    public List<String> getPath() {
        return path;
    }

    public String getLastWord() {
        return path.get(path.size() - 1);
    }

    public int getCost() {
        return cost;
    }
}
