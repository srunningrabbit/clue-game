/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

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

	// Variable used for singleton pattern
	private static Board theInstance = new Board();
	
	// Constructor is private to ensure only one can be created
	private Board() {}
	
	// This method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

	public void initialize() {

	}

	// Set both the board config file and room config file
	public void setConfigFiles(String boardLayout, String roomLegend) {
		
	}

	// Load room legend configuration
	public void loadRoomConfig() {

	}

	// Load board layout configuration
	public void loadBoardConfig() {

	}

	// Calculate adjacencies around every cell
	public void calcAdjacencies() {

	}

	// Calculate targets within length of path
	public void calcTargets(BoardCell cell, int pathLength) {

	}
	
	public Map<Character, String> getLegend() {		// Returns room legend
		return null;
	}

	public int getNumRows() {						// Returns number of rows
		return numRows;
	}

	public int getNumColumns() {					// Returns number of columns
		return numColumns;
	}

	public BoardCell getCellAt(int col, int row) {	// Returns cell at corresponding location
		return null;
	}
}
