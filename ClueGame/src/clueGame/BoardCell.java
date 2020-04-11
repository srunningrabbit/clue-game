/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import java.awt.*;

/*
Individual cell object in board
 */
public class BoardCell {
    public int row;
    public int col;
    private char initial;
    private DoorDirection doorDirection;
    private boolean walkway;
    private boolean closet;
    private boolean room;
    private boolean doorway;
    private static final int CELL_SIZE = 25;

    // Assign row and column to BoardCell
    public BoardCell(int row, int col, char initial, DoorDirection doorDirection) {
        this.row = row;
        this.col = col;
        this.initial = initial;
        this.doorDirection = doorDirection;
        walkway = initial == 'W';
        closet = initial == 'X';
        room = doorDirection.equals(DoorDirection.NONE) && initial != 'W' && initial != 'X';
        doorway = !doorDirection.equals(DoorDirection.NONE);
    }

    /*
    Getters
     */

    public DoorDirection getDoorDirection() {       // Returns door direction
        return doorDirection;
    }

    public char getInitial() {                      // Returns corresponding char
        return initial;
    }

    public static int getCellSize() {
        return CELL_SIZE;
    }

    /*
    Methods
     */

    public void draw(Graphics g) {
        int x = col * CELL_SIZE;
        int y = row * CELL_SIZE;
        if (room || doorway || closet) {
            g.setColor(Color.GRAY);
            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
            if (doorway) {
                g.setColor(Color.darkGray);
                switch (doorDirection) {
                    case UP:
                        g.fillRect(x, y, CELL_SIZE, 5);
                        break;
                    case DOWN:
                        g.fillRect(x, y + CELL_SIZE - 5, CELL_SIZE, 5);
                        break;
                    case LEFT:
                        g.fillRect(x, y, 5, CELL_SIZE);
                        break;
                    case RIGHT:
                        g.fillRect(x + CELL_SIZE - 5, y, 5, CELL_SIZE);
                        break;
                    default:
                        break;
                }
            }
        } else if (walkway) {
            g.setColor(Color.orange);
            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
            g.setColor(Color.darkGray);
            g.drawRect(x, y, CELL_SIZE - 1, CELL_SIZE - 1);
        }
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