package com.mycompany.tucil3_13522164;

import java.util.Comparator;

public class AStarNodeComparator implements Comparator<AStarNode> {
    
    @Override
    public int compare(AStarNode node1, AStarNode node2) {
        if (node1.getCost() < node2.getCost()) {
            return -1;
        } else if (node1.getCost() > node2.getCost()) {
            return 1;
        }
        return 0;
    }
}
