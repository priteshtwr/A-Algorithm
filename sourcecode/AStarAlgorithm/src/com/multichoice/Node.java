package com.multichoice;

public class Node {

	private int cost;
	private int heuristic;
	private Node parentNode;
	private int xCoordinate;
	private int yCoordinate;
	private int totalCost;


	public int getxCoordinate() {
		return xCoordinate;
	}


	public int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
		totalCost = totalCost +this.cost;
	}


	public int getHeuristic() {
		return heuristic;
	}


	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
		if (this.parentNode != null) {
			totalCost = this.parentNode.getTotalCost();
		}
	}


	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
		totalCost = totalCost +this.heuristic;
	}


	public int getyCoordinate() {
		return yCoordinate;
	}


	public int getTotalCost () {
		return totalCost;
	}


	public Node (final int xCoordinate ,final int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}


	public Node getParentNode() {
		return parentNode;
	}


	public String getKey () {
		StringBuilder builder = new StringBuilder();
		builder.append(xCoordinate);
		builder.append(yCoordinate);
		return builder.toString();

	}



}
