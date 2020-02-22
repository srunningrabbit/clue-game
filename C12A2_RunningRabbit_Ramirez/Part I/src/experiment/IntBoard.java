/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 * C12A2 Clue Paths, Part 1
 */

package experiment;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * Gameboard that holds BoardCells and finds adjacencies and targets
 */
public class IntBoard {
    private Map<BoardCell, Set<BoardCell>> adjacencyMatrix;
    private Set<BoardCell> visited;
    private Set<BoardCell> targets;
    private BoardCell[][] grid;

    // Create new gameboard and calculate adjacent cells
    public IntBoard() {
        adjacencyMatrix = new HashMap<BoardCell, Set<BoardCell>>();
        calcAdjacencies();
    }

    private void calcAdjacencies() {
        // TODO: Calulcate the adjacent cells for each cell
    }

    public Set<BoardCell> getAdjList(BoardCell cell) {
        // TODO: Return a list of adjacencies around a particular cell
        return null;
    }

    public void calcTargets(BoardCell startCell, int pathLength) {
        // TODO: Calculate the targets within a pathLength
    }

    public Set<BoardCell> getTargets() {
        // TODO: Return a list of targets
        return null;
    }

    public BoardCell getCell(int col, int row) {	// Returns cell (col,row)
    	BoardCell cell = grid[col][row];
    	return cell;
    }

    public int getGridLength() { 					// Return length of board (number of columns)
         return grid.length;
    }
     
     public int getGridWidth() {					// Return width of board (number of rows)
    	 return grid[0].length;
     }
}
