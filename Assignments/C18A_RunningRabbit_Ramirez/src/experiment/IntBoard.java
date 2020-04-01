/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
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
    private Set<BoardCell> targets;
    private BoardCell[][] grid;

    // Create new gameboard and calculate adjacent cells
    public IntBoard() {
        adjacencyMatrix = new HashMap<BoardCell, Set<BoardCell>>();
        targets = new HashSet<BoardCell>();
        grid = new BoardCell[][]{
                {new BoardCell(0, 0), new BoardCell(1, 0), new BoardCell(2, 0), new BoardCell(3, 0)},
                {new BoardCell(0, 1), new BoardCell(1, 1), new BoardCell(2, 1), new BoardCell(3, 1)},
                {new BoardCell(0, 2), new BoardCell(1, 2), new BoardCell(2, 2), new BoardCell(3, 2)},
                {new BoardCell(0, 3), new BoardCell(1, 3), new BoardCell(2, 3), new BoardCell(3, 3)}
        };
        calcAdjacencies();
    }

    // Calculate adjacencies around every cell
    private void calcAdjacencies() {
        for (BoardCell[] boardCell : grid) {
            for (BoardCell cell : boardCell) {
                int col = cell.column;
                int row = cell.row;
                Set<BoardCell> adjacentCells = new HashSet<BoardCell>();
                if (row - 1 >= 0)
                    adjacentCells.add(getCell(col, row - 1));
                if (row + 1 < getGridWidth())
                    adjacentCells.add(getCell(col, row + 1));
                if (col - 1 >= 0)
                    adjacentCells.add(getCell(col - 1, row));
                if (col + 1 < getGridLength())
                    adjacentCells.add(getCell(col + 1, row));
                adjacencyMatrix.put(cell, adjacentCells);
            }
        }
    }

    public Set<BoardCell> getAdjList(BoardCell cell) {  // Returns adjacency list for particular cell
        return adjacencyMatrix.get(cell);
    }

    // Calculate targets within length of path
    public void calcTargets(BoardCell startCell, int pathLength) {
        for (BoardCell cell : getAdjList(startCell)) {
            targets.add(cell);
            if (pathLength > 2)
                calcTargets(cell, pathLength - 1);
        }
    }

    public Set<BoardCell> getTargets() {            // Returns targets
        return targets;
    }

    public BoardCell getCell(int col, int row) {    // Returns cell (col,row)
    	BoardCell cell = grid[row][col];
    	return cell;
    }

    public int getGridLength() {                    // Return length of board (number of columns)
         return grid.length;
    }
     
    public int getGridWidth() {                     // Return width of board (number of rows)
    	 return grid[0].length;
     }
}
