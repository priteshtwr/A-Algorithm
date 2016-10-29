package com.multichoice;

public class Utils {

	public static int getHeuristicDistance (final int xOld, final int xNew,
			final int yOld, final int yNew) {

		return Math.abs(xOld - xNew) + Math.abs(yOld - yNew); 
	}



}
