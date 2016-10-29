package com.multichoice;

import java.util.Comparator;

public class NodeComparator  implements Comparator<Node> {
	public static final int EQUAL = 0;
	public static final int GREATER = 1;
	public static final int LESSER = -1;


	@Override
	public int compare(Node node1, Node node2) {

		return node1.getTotalCost()<node2.getTotalCost()?LESSER:
			node1.getTotalCost()>node2.getTotalCost()?GREATER:EQUAL;
	}

}
