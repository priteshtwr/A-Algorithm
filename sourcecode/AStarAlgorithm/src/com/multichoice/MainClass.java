package com.multichoice;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
	public static final char START_OF_PATH = '@';
	public static final char WATER = '~';
	public static final char FOREST = '*';
	public static final char MOUNTAIN = '^';
	public static final char END = 'X';
	public static final char FLATLAND = '.';
	public static Node [] [] nodeGrid  ;
	public static char [] [] originalPath;
	public static final String TEST_FILE_NAME = "test/large_map.txt";
	public static final char PATH ='#';
	public static final String SOLUTION_FILE_NAME = "large_map.txt";



	/**
	 * This is the starting point of the application.
	 */
	public static void main(String[] args) {
		generateData();


	}

	/**
	 * Reading the test-file and creating 2d Arrays of data and path.
	 */
	private static void generateData()  {

		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(TEST_FILE_NAME), StandardCharsets.UTF_8);
			final int Y = lines.size();
			final int X = lines.get(0).length();
			nodeGrid  = new Node [X] [Y];
			originalPath = new char [X] [Y];
			int lineNumber = 0;
			for (String line : lines) {
				final int size = line.length();
				for (int i = 0 ;i < size ; i++ ) {
					System.out.println(""+line.charAt(i)+lineNumber+i);
					Node newNode;
					switch (line.charAt(i)) {
					case START_OF_PATH:
						newNode = new Node (lineNumber,i);
						newNode.setCost(FLATLANDS_MOVEMENT_COST);
						newNode.setHeuristic(Utils.getHeuristicDistance(X -1 , lineNumber, Y - 1, i));
						nodeGrid[lineNumber][i] = newNode;
						originalPath[lineNumber][i] = START_OF_PATH;
						break;
					case FLATLAND:
						newNode = new Node (lineNumber,i);
						newNode.setCost(FLATLANDS_MOVEMENT_COST);
						newNode.setHeuristic(Utils.getHeuristicDistance(X -1 , lineNumber, Y - 1, i));
						nodeGrid[lineNumber][i] = newNode;
						originalPath[lineNumber][i] = FLATLAND;
						break;

					case MOUNTAIN:
						newNode = new Node (lineNumber,i);
						newNode.setCost(MOUNTAIN_MOVEMENT_COST);
						newNode.setHeuristic(Utils.getHeuristicDistance(X -1 , lineNumber, Y - 1, i));
						nodeGrid[lineNumber][i] = newNode;
						originalPath[lineNumber][i] = MOUNTAIN;
						break;

					case WATER:
						newNode = new Node (lineNumber,i);
						newNode.setCost(WATER_MOVEMENT_COST);
						newNode.setHeuristic(Utils.getHeuristicDistance(X -1 , lineNumber, Y - 1, i));
						nodeGrid[lineNumber][i] = newNode;
						originalPath[lineNumber][i] = WATER;
						break;

					case END:
						newNode = new Node (lineNumber,i);
						newNode.setCost(FLATLANDS_MOVEMENT_COST);
						newNode.setHeuristic(Utils.getHeuristicDistance(X -1 , lineNumber, Y - 1, i));
						nodeGrid[lineNumber][i] = newNode;
						originalPath[lineNumber][i] = END;
						break;

					case FOREST:
						newNode = new Node (lineNumber,i);
						newNode.setCost(FOREST_MOVEMENT_COST);
						newNode.setHeuristic(Utils.getHeuristicDistance(X -1 , lineNumber, Y - 1, i));
						nodeGrid[lineNumber][i] = newNode;
						originalPath[lineNumber][i] = FOREST;
						break;


					default:
						break;

					}


				}
				lineNumber ++;
			}

			init();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Initializing the priority Queue with the custom comparator to maintain 
	 * lowest node at the top.
	 */
	private static void init () {
		mOpenList = new PriorityQueue<Node>(new NodeComparator());
		findShortestPath () ;

	}

	/**
	 * Starting the analysis of path.
	 */
	private static void findShortestPath () {
		EndCell endCell = new EndCell (nodeGrid.length - 1,nodeGrid.length - 1) ;
		HashSet<String> closedPath = new HashSet<String>();
		mOpenList.add(nodeGrid[0][0]);
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
				t = nodeGrid [poppedNode.getxCoordinate()-1] [poppedNode.getyCoordinate()] ;
				analyzeNode(poppedNode, t,closedPath); 


				if(poppedNode.getyCoordinate()-1>=0){   
					t = nodeGrid [poppedNode.getxCoordinate()-1] [poppedNode.getyCoordinate()-1] ;
					analyzeNode(poppedNode, t,closedPath); 

				}

				if(poppedNode.getyCoordinate()+1<nodeGrid[0].length){
					t = nodeGrid [poppedNode.getxCoordinate()-1] [poppedNode.getyCoordinate()+1] ;
					analyzeNode(poppedNode, t,closedPath); 
				}
			} 

			if(poppedNode.getyCoordinate()-1>=0){
				t = nodeGrid [poppedNode.getxCoordinate()] [poppedNode.getyCoordinate()-1] ;
				analyzeNode(poppedNode, t,closedPath); 
			}

			if(poppedNode.getyCoordinate()+1<nodeGrid[0].length){
				t = nodeGrid [poppedNode.getxCoordinate()] [poppedNode.getyCoordinate()+1] ;
				analyzeNode(poppedNode, t,closedPath); 

			}

			if(poppedNode.getxCoordinate()+1<nodeGrid.length){
				t = nodeGrid [poppedNode.getxCoordinate()+1] [poppedNode.getyCoordinate()] ;
				analyzeNode(poppedNode, t,closedPath); 



				if(poppedNode.getyCoordinate()-1>=0){
					t = nodeGrid [poppedNode.getxCoordinate()+1] [poppedNode.getyCoordinate()-1] ;
					analyzeNode(poppedNode, t,closedPath); 



				}

				if(poppedNode.getyCoordinate()+1<nodeGrid[0].length){
					t = nodeGrid [poppedNode.getxCoordinate()+1] [poppedNode.getyCoordinate()+1] ;
					analyzeNode(poppedNode, t,closedPath);
				} 
			}
		}

	}

	/**
	 * @param node:This is the final node from which path followed to reach there
	 * will be created.
	 */
	private static void generatePath (final Node node) {
		Node current = node;
		StringBuilder pathTraversed = new StringBuilder();
		originalPath[0][0] = PATH;
		while (current.getParentNode() != null) {
			pathTraversed.append(originalPath[current.getxCoordinate()][current.getyCoordinate()]);
			originalPath[current.getxCoordinate()][current.getyCoordinate()]= PATH;
		    current = current.getParentNode();
		}
		pathTraversed.append(START_OF_PATH);
		System.out.println("The path traversed is-->"+pathTraversed.reverse().toString());
		;
		try {
			File fout = new File(SOLUTION_FILE_NAME);
			FileOutputStream fos;
			fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for (int xIndex = 0 ;xIndex <nodeGrid.length ; xIndex ++) {
				StringBuilder line = new StringBuilder();
				for (int yIndex = 0 ; yIndex < nodeGrid.length ; yIndex++) {
					line.append(originalPath[xIndex][yIndex]);
				}
				bw.write(line.toString());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();

		}





	}

	/**
	 * @param current:The popped Node.
	 * @param t :The node in the vicinity of popped node.
	 * @param closedPath : Data Structure i.e. hash set containing all the closed paths.
	 */
	private static void analyzeNode(final Node current, final Node t, final HashSet<String> closedPath){
		if(t == null )return;
		if (t.getCost() == WATER_MOVEMENT_COST || closedPath.contains(t.getKey()) ) {
			return ;
		}
		boolean isInQueue = mOpenList.contains(t);
		if (isInQueue) {
			final int totalCost = t.getTotalCost();
			if (totalCost < current.getTotalCost()) {
				t.setParentNode(current);
			}
		} else {
			t.setParentNode(current);
			mOpenList.add(t);
		}
	}

}
