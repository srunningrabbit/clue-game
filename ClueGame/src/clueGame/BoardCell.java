/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import java.awt.*;
import java.util.Scanner;

/*
BoardCell

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
    private boolean name;
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

    public void setHasName(boolean name) {
        this.name = name;
    }

    /*
    Methods
     */

    // Draws the board cell according to what it is
    public void draw(Graphics g) {
        int x = col * CELL_SIZE;
        int y = row * CELL_SIZE;
        if (Board.getInstance().getTargets().contains(this)) {
            g.setColor(Color.CYAN);
            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        } else if (room || doorway || closet) {
            g.setColor(Color.GRAY);
            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
            // Draw a rectangle to represent a door in a doorway
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
            g.setColor(Color.white);
            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
            g.setColor(Color.darkGray);
            g.drawRect(x, y, CELL_SIZE - 1, CELL_SIZE - 1);
        }

        // Draw name of room if cell contains a name
        if (name) {
            g.setColor(Color.BLACK);
            Scanner input = new Scanner(Board.getInstance().getLegend().get(initial));
            y -= 30; // Start y position further back to account for cells that will be drawn after
            while (input.hasNext()) {
                g.drawString(input.next(), x, y);
                y += 10;
            }
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