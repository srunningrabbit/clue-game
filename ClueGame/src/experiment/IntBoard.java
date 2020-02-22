package experiment;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IntBoard {
    private Map<BoardCell, Set<BoardCell>> adjacencyMatrix;
    private Set<BoardCell> visited;
    private Set<BoardCell> targets;
    private BoardCell[][] grid;

    public IntBoard() {
        adjacencyMatrix = new HashMap<BoardCell, Set<BoardCell>>();
        calcAdjacencies();
    }

    private void calcAdjacencies() { }

    public Set<BoardCell> getAdjList(BoardCell cell) {
        return null;
    }

    public void calcTargets(BoardCell startCell, int pathLength) { }

    public Set<BoardCell> getTargets() {
        return null;
    }
    
    public BoardCell getCell(int col, int row) {	//returns cell (col,row)
    	BoardCell cell = grid[col][row];
    	return cell;
    }
     public int getGridLength() { 					//return length of board (number of columns)
    	 return grid.length;
     }
     
     public int getGridWidth() {					//return width of board (number of rows)
    	 return grid[0].length;
     }
}
