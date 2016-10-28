package com.multichoice;

public class Utils {
	
	public static int getHeuristicDistance (final int xOld, final int xNew,
			final int yOld, final int yNew) {
		
		return Math.abs(xOld - xNew) + Math.abs(yOld - yNew); 
	}
	
	public static boolean isItBlocked (int x ,int y ,int grid [] []) {
		if (grid != null) {
			if (grid [x] [y] == MainClass.WATER_MOVEMENT_COST) {
				 return true;
			}
		}
		return false;
		
	}
	

}
