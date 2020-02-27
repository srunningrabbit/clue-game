/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

/*
 * Individual cell object in board
 */
public class BoardCell {
    public int row;
    public int column;
    public char initial;
    public DoorDirection doorDirection;

    // Assign row and column to BoardCell
    public BoardCell(int row, int column, char initial, DoorDirection doorDirection) {
        this.row = row;
        this.column = column;
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
