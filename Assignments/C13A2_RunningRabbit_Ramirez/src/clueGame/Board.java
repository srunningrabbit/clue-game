/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import org.junit.Before;

import java.io.*;
import java.util.*;

public class Board {
	public final static int MAX_BOARD_SIZE = 50;
	private int numRows;
	private int numColumns;
	private String boardConfigFile;
	private String roomConfigFile;
	private BoardCell[][] board;
	private Map<Character, String> legend;
	private Map<BoardCell, Set<BoardCell>> adjacencyMatrix;
	private Set<BoardCell> targets;

	// Variable used for singleton pattern
	private static Board theInstance = new Board();
	
	// Constructor is private to ensure only one can be created
	private Board() {}
	
	// This method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

	public void initialize() throws FileNotFoundException {
		targets = new HashSet<>();
		try {
			loadRoomConfig();
			loadBoardConfig();
		} catch (BadConfigFormatException | IOException e) {
			System.out.println(e.getMessage());
		}
		calcAdjacencies();
	}

	// Set both the board config file and room config file
	public void setConfigFiles(String boardLayout, String roomLegend) {
		boardConfigFile = boardLayout;
		roomConfigFile = roomLegend;
	}

	// Load room legend configuration
	public void loadRoomConfig() throws IOException, BadConfigFormatException {
		File roomConfig = new File(roomConfigFile);
		Scanner fileInput = new Scanner(roomConfig);
		legend = new HashMap<>();

		while (fileInput.hasNextLine()) {
			String[] legendRow = fileInput.nextLine().split(", ");
			// Check if room type is correct
			if (!legendRow[2].equals("Card") && !legendRow[2].equals("Other"))
				throw new BadConfigFormatException("Error: room type is not Card or Other");
			char initial = legendRow[0].charAt(0);
			String roomName = legendRow[1];
			legend.put(initial, roomName);
		}
	}

	// Load board layout configuration
	public void loadBoardConfig() throws IOException, BadConfigFormatException {
		File boardConfig = new File(boardConfigFile);
		Scanner fileInput = new Scanner(boardConfig);

		// Find number of board rows
		numRows = getFileLength(boardConfig);
		BoardCell[][] gameBoard = new BoardCell[numRows][];

		for (int row = 0; row < numRows; row++) {
			String[] boardRow;
			if (fileInput.hasNextLine()) {
				boardRow = fileInput.nextLine().split(",");
			} else break;

			// Find number of board columns or throw exception if columns are mismatched
			if (row > 0 && boardRow.length != numColumns)
				throw new BadConfigFormatException("Error: rows do not have same number of columns");
			numColumns = boardRow.length;
			BoardCell[] boardCellArray = new BoardCell[numColumns];

			for (int col = 0; col < boardRow.length; col++) {
				BoardCell cell;
				char initial = boardRow[col].charAt(0);
				// Check if room is valid according to legend
				if (!legend.containsKey(initial))
					throw new BadConfigFormatException("Error: room not in legend");

				if (boardRow[col].length() > 1) {
					// Add a direction if it is a door
					DoorDirection doorDirection = DoorDirection.NONE;
					switch (boardRow[col].charAt(1)) {
						case 'U':
							doorDirection = DoorDirection.UP;
							break;
						case 'D':
							doorDirection = DoorDirection.DOWN;
							break;
						case 'L':
							doorDirection = DoorDirection.LEFT;
							break;
						case 'R':
							doorDirection = DoorDirection.RIGHT;
							break;
						case 'N':
						default:
							break;
					}
					// Add cell with second character
					cell = new BoardCell(row, col, initial, doorDirection);
				} else {
					// Add cell without second character
					cell = new BoardCell(row, col, initial, DoorDirection.NONE);
				}
				boardCellArray[col] = cell;
			}
			gameBoard[row] = boardCellArray;
		}
		board = gameBoard;
	}

	// Calculate adjacencies around every cell
	public void calcAdjacencies() {
		adjacencyMatrix = new HashMap<>();
		for (BoardCell[] boardCell : board) {
			for (BoardCell cell : boardCell) {
				int col = cell.column;
				int row = cell.row;
				Set<BoardCell> adjacentCells = new HashSet<BoardCell>();
				if (row - 1 >= 0)
					adjacentCells.add(getCellAt(row - 1, col));
				if (row + 1 < getBoardLength())
					adjacentCells.add(getCellAt(row + 1, col));
				if (col - 1 >= 0)
					adjacentCells.add(getCellAt(row, col - 1));
				if (col + 1 < getBoardWidth())
					adjacentCells.add(getCellAt(row, col + 1));
				adjacencyMatrix.put(cell, adjacentCells);
			}
		}
	}

	// Calculate targets within length of path
	public void calcTargets(BoardCell startCell, int pathLength) {
		for (BoardCell cell : getAdjList(startCell)) {
			targets.add(cell);
			if (pathLength > 2)
				calcTargets(cell, pathLength - 1);
		}
	}
	
	public Map<Character, String> getLegend() {		// Returns room legend
		return legend;
	}

	public int getNumRows() {						// Returns number of rows
		return numRows;
	}

	public int getNumColumns() {					// Returns number of columns
		return numColumns;
	}

	public BoardCell getCellAt(int row, int col) {	// Returns cell at corresponding location
		BoardCell cell = board[row][col];
		return cell;
	}

	public int getBoardLength() {                    // Return length of board (number of columns)
		return board.length;
	}

	public int getBoardWidth() {                     // Return width of board (number of rows)
		return board[0].length;
	}

	public Set<BoardCell> getAdjList(BoardCell cell) {  // Returns adjacency list for particular cell
		return adjacencyMatrix.get(cell);
	}

	public int getFileLength(File file) throws FileNotFoundException {	// Returns length of file
		Scanner fileInput = new Scanner(file);
		int lines = 0;
		while (fileInput.hasNextLine()) {
			lines++;
			fileInput.nextLine();
		}
		return lines;
	}
}
