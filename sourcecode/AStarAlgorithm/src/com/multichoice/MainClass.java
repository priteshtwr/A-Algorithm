package com.multichoice;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class MainClass {
	
	
    public static PriorityQueue<Node> mOpenList;
    public static final int WATER_MOVEMENT_COST = -1;
    public static final int FLATLANDS_MOVEMENT_COST =  1;
    public static final int FOREST_MOVEMENT_COST = 2;
    public static final int MOUNTAIN_MOVEMENT_COST = 3;
    public static int [] [] grid ;
    public static final String FILE_NAME = "large_map.txt";
    
	public static void main(String[] args) {
		generateData();
	
		
}
	
	//This method is called to read the path from the txt file and store in into the file
	//Initially to check algorithm we used a static data set.
	private static void generateData()  {
	
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
			System.out.println(""+lines.size());
			System.out.println(""+lines.get(0).length());
			grid = new int [lines.size()] [lines.get(0).length()];
			int lineNumber = 0;
			for (String line : lines) {
				final int size = line.length();
				for (int i = 0 ;i < size ; i++ ) {
					System.out.println(""+line.charAt(i));
				}
				lineNumber ++;
			}
			//init();
		} catch (IOException e) {
	         e.printStackTrace();
		}
	
	}
	private static void init () {
	   mOpenList = new PriorityQueue<Node>(new NodeComparator());
	   findShortestPath () ;
	  
	}
	
	private static void findShortestPath () {
		EndCell endCell = new EndCell (4,4) ;
		StartCell startCell = new StartCell (0,0);
		Node node = new Node(0,0);
	    node.setCost(0);
	    node.setHeuristic(0);
	    HashSet<String> closedPath = new HashSet<String>();
		mOpenList.add(node);
		Node poppedNode;
		while (!mOpenList.isEmpty()) {
			poppedNode = mOpenList.poll();
			if (poppedNode == null) {
				break;
			}
			if (endCell.isItEndCell(poppedNode)) {
				generatePath(poppedNode);
				break;
			}
			if (closedPath.contains(poppedNode.getKey())) {
				continue;
			} else {
				closedPath.add(poppedNode.getKey());
			}
			
			Node t;
			
			   if(poppedNode.getxCoordinate()-1>=0){
				    if (!Utils.isItBlocked(poppedNode.getxCoordinate()-1, 
				    		poppedNode.getyCoordinate(), grid)) {
				    	t = new Node(poppedNode.getxCoordinate()-1,poppedNode.getyCoordinate());
				    	t.setCost(grid[poppedNode.getxCoordinate()-1] [poppedNode.getyCoordinate()]);
				    	t.setHeuristic(Utils.getHeuristicDistance(endCell.getxIndex(), 
				    			poppedNode.getxCoordinate()-1, endCell.getyIndex(), poppedNode.getyCoordinate()));
				    	 analyzeNode(poppedNode, t); 

				    }
	               
	                if(poppedNode.getyCoordinate()-1>=0){   
	                	if (!Utils.isItBlocked(poppedNode.getxCoordinate()-1, 
	                			poppedNode.getyCoordinate()-1, grid)) {
	                		t = new Node(poppedNode.getxCoordinate()-1,poppedNode.getyCoordinate()-1);
					    	t.setCost(grid[poppedNode.getxCoordinate()-1] [poppedNode.getyCoordinate()-1]);
					    	t.setHeuristic(Utils.getHeuristicDistance(endCell.getxIndex(), 
					    			poppedNode.getxCoordinate()-1, endCell.getyIndex(), poppedNode.getyCoordinate()-1));
					    	 analyzeNode(poppedNode, t); 

	                	}
	               
	                }

	                if(poppedNode.getyCoordinate()+1<grid[0].length){
	                	if (!Utils.isItBlocked(poppedNode.getxCoordinate()-1, 
	                			poppedNode.getyCoordinate()+1, grid)) {
	                		t = new Node(poppedNode.getxCoordinate()-1,poppedNode.getyCoordinate()+1);
					    	t.setCost(grid[poppedNode.getxCoordinate()-1] [poppedNode.getyCoordinate()+1]);
					    	t.setHeuristic(Utils.getHeuristicDistance(endCell.getxIndex(), 
					    			poppedNode.getxCoordinate()-1, endCell.getyIndex(), poppedNode.getyCoordinate()+1));
					    	 analyzeNode(poppedNode, t); 

	                	}
	               }
	            } 

	            if(poppedNode.getyCoordinate()-1>=0){
	            	if (!Utils.isItBlocked(poppedNode.getxCoordinate(), 
	            			poppedNode.getyCoordinate()-1, grid)) {
                		t = new Node(poppedNode.getxCoordinate(),poppedNode.getyCoordinate()-1);
				    	t.setCost(grid[poppedNode.getxCoordinate()] [poppedNode.getyCoordinate()-1]);
				    	t.setHeuristic(Utils.getHeuristicDistance(endCell.getxIndex(), 
				    			poppedNode.getxCoordinate(), endCell.getyIndex(), poppedNode.getyCoordinate()-1));
				    	 analyzeNode(poppedNode, t); 

                	}
	             }

	            if(poppedNode.getyCoordinate()+1<grid[0].length){
	            	if (!Utils.isItBlocked(poppedNode.getxCoordinate(), 
                			poppedNode.getyCoordinate()+1, grid)) {
                		t = new Node(poppedNode.getxCoordinate(),poppedNode.getyCoordinate()+1);
				    	t.setCost(grid[poppedNode.getxCoordinate()] [poppedNode.getyCoordinate()+1]);
				    	t.setHeuristic(Utils.getHeuristicDistance(endCell.getxIndex(), 
				    			poppedNode.getxCoordinate(), endCell.getyIndex(), poppedNode.getyCoordinate()+1));
				    	 analyzeNode(poppedNode, t); 

                	}
	               
	            }

	            if(poppedNode.getxCoordinate()+1<grid.length){
	            	if (!Utils.isItBlocked(poppedNode.getxCoordinate()+1, 
	            			poppedNode.getyCoordinate(), grid)) {
                		t = new Node(poppedNode.getxCoordinate()+1,poppedNode.getyCoordinate());
				    	t.setCost(grid[poppedNode.getxCoordinate()+1] [poppedNode.getyCoordinate()]);
				    	t.setHeuristic(Utils.getHeuristicDistance(endCell.getxIndex(), 
				    			poppedNode.getxCoordinate()+1, endCell.getyIndex(), poppedNode.getyCoordinate()));
				    	 analyzeNode(poppedNode, t); 

                	}
	               

	                if(poppedNode.getyCoordinate()-1>=0){
	                	if (!Utils.isItBlocked(poppedNode.getxCoordinate()+1, 
	                			poppedNode.getyCoordinate()-1, grid)) {
	                		t = new Node(poppedNode.getxCoordinate()+1,poppedNode.getyCoordinate()-1);
					    	t.setCost(grid[poppedNode.getxCoordinate()+1] [poppedNode.getyCoordinate()-1]);
					    	t.setHeuristic(Utils.getHeuristicDistance(endCell.getxIndex(), 
					    			poppedNode.getxCoordinate()+1, endCell.getyIndex(),poppedNode.getyCoordinate()-1));
					    	 analyzeNode(poppedNode, t); 

	                	}
	                  
	                }
	                
	               if(poppedNode.getyCoordinate()+1<grid[0].length){
	            	   if (!Utils.isItBlocked(poppedNode.getxCoordinate()+1, 
	            			   poppedNode.getyCoordinate()+1, grid)) {
	                		t = new Node(poppedNode.getxCoordinate()+1,poppedNode.getyCoordinate()+1);
					    	t.setCost(grid[poppedNode.getxCoordinate()+1] [poppedNode.getyCoordinate()+1]);
					    	t.setHeuristic(Utils.getHeuristicDistance(endCell.getxIndex(), 
					    			poppedNode.getxCoordinate()+1, endCell.getyIndex(),poppedNode.getyCoordinate()+1));
					    	 analyzeNode(poppedNode, t); 

	                	}
	                }  
	            }
		
		}
		
	}
	
     private static void generatePath (final Node node) {
		Node current = node;
		
		while (current.getParentNode() != null) {
			current = current.getParentNode();
		}
		
	}
	
	private static void analyzeNode(Node current, Node t){
       //Need to be implemented .
    }

}
