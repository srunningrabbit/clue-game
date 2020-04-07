/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package tests;

import static org.junit.Assert.*;
import java.util.Map;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class InitTests {
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 21;

	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueRooms.txt","data/PlayerLegend.txt", "data/WeaponLegend.txt");
		board.initialize();
	}
	
	// Test rooms
	@Test
	public void testRoomLegend() {
		Map<Character, String> testLegend = board.getLegend();
		// Test size
		assertEquals(LEGEND_SIZE, testLegend.size());

		assertEquals("Food Court", testLegend.get('F'));
		assertEquals("Bathshop", testLegend.get('B'));
		assertEquals("Playland", testLegend.get('P'));
		assertEquals("Rugs and Flooring", testLegend.get('R'));
		assertEquals("Cookshop and Tableware", testLegend.get('C'));
		assertEquals("Home Decoration", testLegend.get('H'));
	}

	// Board size tests
	@Test
	public void testBoardLayout() {
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}

	// Door direction tests
	@Test
	public void testDoorDirections() {
		BoardCell cell = board.getCellAt(1, 9);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.RIGHT, cell.getDoorDirection());
		cell = board.getCellAt(5, 18);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.LEFT, cell.getDoorDirection());
		cell = board.getCellAt(18, 12);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.UP, cell.getDoorDirection());
		cell = board.getCellAt(6, 4);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.DOWN, cell.getDoorDirection());
		
		cell = board.getCellAt(16, 10); 	//room
		assertFalse(cell.isDoorway());
		cell = board.getCellAt(9, 5);		//walkway
		assertFalse(cell.isDoorway());
	}
	
	// Test there's right number of doors
	@Test
	public void testDoorNumber() {
		int numDoors = 0;
		for (int row = 0; row < board.getNumRows(); row++)
			for (int col = 0; col < board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(16, numDoors);
	}
	
	// Test room/other characters
	@Test
	public void testRoomLetters() {
		assertEquals('H', board.getCellAt(1, 1).getInitial());
		assertEquals('R', board.getCellAt(11, 2).getInitial());
		assertEquals('T', board.getCellAt(17, 3).getInitial());
		assertEquals('B', board.getCellAt(21, 7).getInitial());
		assertEquals('P', board.getCellAt(11, 18).getInitial());

		assertEquals('F', board.getCellAt(21, 20).getInitial());	// bottom right corner
		assertEquals('L', board.getCellAt(0, 18).getInitial());		// top left corner of room
		
		assertEquals('W', board.getCellAt(16, 13).getInitial());	// walkway
		assertEquals('X', board.getCellAt(11, 8).getInitial());		// closet
	}
}
