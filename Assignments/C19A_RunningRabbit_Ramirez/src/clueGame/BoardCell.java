/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

/*
Individual cell object in board
 */
public class BoardCell {
    public int row;
    public int col;
    private char initial;
    private DoorDirection doorDirection;
    private boolean walkway;
    private boolean room;
    private boolean doorway;

    // Assign row and column to BoardCell
    public BoardCell(int row, int col, char initial, DoorDirection doorDirection) {
        this.row = row;
        this.col = col;
        this.initial = initial;
        this.doorDirection = doorDirection;
        walkway = initial == 'W';
        room = doorDirection.equals(DoorDirection.NONE) && initial != 'W' && initial != 'X';
        doorway = !doorDirection.equals(DoorDirection.NONE);
    }
    
    public DoorDirection getDoorDirection() {       // Returns door direction
        return doorDirection;
    }

    public char getInitial() {                      // Returns corresponding char
        return initial;
    }

    @Override
    public String toString() {
        return "Location: (" + row + ", " + col + ")";
    }

    public boolean isWalkway() {                    // If cell is a walkway
        return walkway;
    }

    public boolean isRoom() {                       // If cell is part of a room
        return room;
    }

    public boolean isDoorway() {                    // If cell is a doorway
        return doorway;
    }
}