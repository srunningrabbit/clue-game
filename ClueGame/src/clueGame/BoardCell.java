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
    public char initial;
    public DoorDirection doorDirection;

    // Assign row and column to BoardCell
    public BoardCell(int column, int row, char initial, DoorDirection doorDirection) {
        this.column = column;
        this.row = row;
        this.initial = initial;
        this.doorDirection = doorDirection;
    }

    @Override
    public String toString() {
        return "Location: (" + column + ", " + row + ")";
    }
    
    public boolean isWalkway() {                    // If cell is a walkway
    	return initial == 'W';
    }
    
    public boolean isRoom() {                       // If cell is part of a room
    	return doorDirection.equals(DoorDirection.NONE);
    }
    
    public boolean isDoorway() {                    // If cell is a doorway
    	return !doorDirection.equals(DoorDirection.NONE);
    }

	public DoorDirection getDoorDirection() {       // Returns door direction
		return doorDirection;
	}

	public char getInitial() {                      // Returns corresponding char
		return initial;
	}
}
