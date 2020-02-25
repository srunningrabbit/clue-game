package clueGame;

import java.util.Map;
import java.util.Set;

public class Board {
	public final static int MAX_BOARD_SIZE = 50;
	private int numRows;
	private int numColumns;
	private BoardCell[][] board;
	private Map<Character, String> legend;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;

	// variable used for singleton pattern
	private static Board theInstance = new Board();
	
	// constructor is private to ensure only one can be created
	private Board() {}
	
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

	public void initialize() {

	}
	
	public void setConfigFiles(String boardLayout, String roomLegend) {
		
	}

	public void loadRoomConfig() {

	}

	public void loadBoardConfig() {

	}

	public void calcAdjacencies() {

	}

	public void calcTargets(BoardCell cell, int pathLength) {

	}
	
	public Map<Character, String> getLegend() {
		return null;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	
	public BoardCell getCellAt(int col, int row) {
		return null;
	}
}
