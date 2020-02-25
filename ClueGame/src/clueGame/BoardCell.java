/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 * C12A2 Clue Paths, Part 2
 */

package clueGame;

/*
 * Individual cell object in board
 */
public class BoardCell {
    public int column;
    public int row;
    private char initial;

    // Assign row and column to BoardCell
    public BoardCell(int column, int row) {
        this.column = column;
        this.row = row;
    }

    @Override
    public String toString() {
        return "Location: (" + column + ", " + row + ")";
    }
    
    public boolean isWalkway() {
    	return false;
    }
    
    public boolean isRoom() {
    	return false;
    }
    
    public boolean isDoorway() {
    	return false;
    }

	public DoorDirection getDoorDirection() {
		return null;
	}

	public char getInitial() {
		return ' ';
	}
	

}
