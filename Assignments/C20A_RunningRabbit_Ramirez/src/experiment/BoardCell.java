/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package experiment;

/*
 * Individual cell object in board
 */
public class BoardCell {
    public int column;
    public int row;

    // Assign row and column to BoardCell
    public BoardCell(int column, int row) {
        this.column = column;
        this.row = row;
    }

    @Override
    public String toString() {
        return "Location: (" + column + ", " + row + ")";
    }
}
