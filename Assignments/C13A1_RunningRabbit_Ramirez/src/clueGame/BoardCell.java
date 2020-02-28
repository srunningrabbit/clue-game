/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
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
    
    public boolean isWalkway() {                    // If cell is a walkway
    	return false;
    }
    
    public boolean isRoom() {                       // If cell is part of a room
    	return false;
    }
    
    public boolean isDoorway() {                    // If cell is a doorway
    	return false;
    }

	public DoorDirection getDoorDirection() {       // Returns door direction
		return null;
	}

	public char getInitial() {                      // Returns corresponding char
		return ' ';
	}
}
