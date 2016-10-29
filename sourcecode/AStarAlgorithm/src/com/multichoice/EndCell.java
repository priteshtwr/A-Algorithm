package com.multichoice;

public class EndCell {

	private int xIndex;
	private int yIndex;

	public EndCell (final int xIndex, final int yIndex) {
		this.xIndex = xIndex;
		this.yIndex = yIndex;
	}

	public boolean isItEndCell (Node cell) {
		if (cell != null) {
			if (cell.getxCoordinate() == xIndex 
					&& cell.getyCoordinate() == yIndex) {
				return true;
			}
		}
		return false;
	}

	public int getxIndex() {
		return xIndex;
	}

	public int getyIndex() {
		return yIndex;
	}

}
