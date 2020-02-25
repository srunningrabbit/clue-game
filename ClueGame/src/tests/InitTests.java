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
	public static final int NUM_ROWS = 21;
	public static final int NUM_COLUMNS = 20;

	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("BoardLayout.csv", "ClueRooms.txt");	//CHANGE TO OUR CONFIG FILES	
		board.initialize();
	}
	
	//Test rooms
	@Test
	public void testRoomLegend() {
		Map<Character, String> testLegend = board.getLegend();
		//test size
		assertEquals(LEGEND_SIZE, testLegend.size());
		
		assertEquals("Food Court", testLegend.get('F'));
		assertEquals("Bathshop", testLegend.get('B'));
		assertEquals("Playland", testLegend.get('P'));
		assertEquals("Rugs and Flooring", testLegend.get('R'));
		assertEquals("Cookshop and Tableware", testLegend.get('C'));
		assertEquals("Home Decoration", testLegend.get('H'));
		
	}
	
	//Board size tests
	public void testBoardLayout() {
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}
	
	//Door direction tests
	public void testDoorDirections() {
		BoardCell cell = board.getCellAt(9, 1);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.RIGHT, cell.getDoorDirection());
		cell = board.getCellAt(18, 5);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.LEFT, cell.getDoorDirection());
		cell = board.getCellAt(13, 18);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.UP, cell.getDoorDirection());
		cell = board.getCellAt(3, 6);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.DOWN, cell.getDoorDirection());
		
		cell = board.getCellAt(10, 16); //room
		assertFalse(cell.isDoorway());
		cell = board.getCellAt(5, 9);	//walkway
		assertFalse(cell.isDoorway());
	}
	
	//Test there's right number of doors
	public void testDoorNumber() {
		int numDoors = 0;
		for (int row=0; row<board.getNumRows(); row++)
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(16, numDoors);
	}
	
	//Test room/other characters
	public void testRoomLetters() {
		assertEquals('H', board.getCellAt(1, 1).getInitial());
		assertEquals('R', board.getCellAt(2, 11).getInitial());
		assertEquals('T', board.getCellAt(3, 17).getInitial());
		assertEquals('B', board.getCellAt(7, 21).getInitial());
		assertEquals('F', board.getCellAt(20, 21).getInitial());	//bottom right corner
		assertEquals('P', board.getCellAt(18, 11).getInitial());
		assertEquals('L', board.getCellAt(18, 0).getInitial());		//top left corner of room
		
		assertEquals('W', board.getCellAt(13, 16).getInitial());	//walkway
		assertEquals('X', board.getCellAt(8, 11).getInitial());		//closet
	}

}
