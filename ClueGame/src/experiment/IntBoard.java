package experiment;

import java.util.Map;
import java.util.Set;

public class IntBoard {
    Map<BoardCell, Set<BoardCell>> adjacencyMatrix;

    public IntBoard() {
        calcAdjacencies();
    }

    private void calcAdjacencies() {

    }

    public Set<BoardCell> getAdjList() {
        return null;
    }

    public void calcTargets(BoardCell startCell, int pathLength) {

    }

    public Set<BoardCell> getTargets() {
        return null;
    }
}
