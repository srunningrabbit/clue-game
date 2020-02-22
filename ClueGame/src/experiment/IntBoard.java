/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 * C12A2 Clue Paths, Part 1
 */

package experiment;

import java.util.HashMap;
import java.util.HashSet;
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

    // Calculate adjacencies around every cell
    private void calcAdjacencies() {
        for (BoardCell[] boardCell : grid) {
            for (BoardCell cell : boardCell) {
                int row = cell.row;
                int col = cell.column;
                Set<BoardCell> adjacentCells = new HashSet<BoardCell>();
                if (row - 1 >= 0)
                    adjacentCells.add(new BoardCell(row - 1, col));
                if (row + 1 < getGridWidth())
                    adjacentCells.add(new BoardCell(row + 1, col));
                if (col - 1 >= 0)
                    adjacentCells.add(new BoardCell(row, col - 1));
                if (col + 1 < getGridLength())
                    adjacentCells.add(new BoardCell(row, col + 1));
                adjacencyMatrix.put(cell, adjacentCells);
            }
        }
    }

    public Set<BoardCell> getAdjList(BoardCell cell) {
        return adjacencyMatrix.get(cell);
    }

    public void calcTargets(BoardCell startCell, int pathLength) {
        // TODO: Calculate the targets within a pathLength
        for (BoardCell cell : getAdjList(startCell)) {
            targets.add(cell);
            calcTargets(cell, pathLength - 1);
        }
    }

    public Set<BoardCell> getTargets() {
        // TODO: Return a list of targets
        return targets;
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
