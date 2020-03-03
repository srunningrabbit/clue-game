/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package tests;

import clueGame.Board;
import clueGame.BoardCell;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdjTargetTests {
	// We make the Board static because we can load it one time and
	// then do all the tests.
	private static Board board;

	@BeforeClass
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueRooms.txt");
		board.initialize();
	}

	/*
	Adjacency tests
	 */

	// Ensure that player does not move around within room
	// PINK on spreadsheet
	@Test
	public void testAdjacenciesInsideRooms() {
		Set<BoardCell> testList = board.getAdjList(0,0);	//Top left room corner test
		assertEquals(0, testList.size());
		testList = board.getAdjList(5,3);		//Doorway below test
		assertEquals(0, testList.size());

		testList = board.getAdjList(0,20);		//Top right room corner test
		assertEquals(0, testList.size());

		testList = board.getAdjList(21,0);		//Bottom left room below test
		assertEquals(0, testList.size());

		testList = board.getAdjList(16,9);		//Walkway above test
		assertEquals(0, testList.size());

		testList = board.getAdjList(21,20);		//Bottom right room corner test
		assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the walkway
	// NOTE: This test could be merged with door direction test.
	// PURPLE on spreadsheet
	@Test
	public void testAdjacencyRoomExit() {
		Set<BoardCell> testList = board.getAdjList(2,12);	//Left doorway
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(2, 11)));

		testList = board.getAdjList(12,17);					//Down doorway
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(13, 17)));

		testList = board.getAdjList(20,17);					//Left doorway with 2 adj doorways
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(20, 16)));

		testList = board.getAdjList(18,13);					//Up doorway
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(17, 13)));

		testList = board.getAdjList(16,3);					//Right doorway with walkway above
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(16, 4)));
	}

	// Test adjacency at entrance to rooms
	// CYAN on spreadsheet
	@Test
	public void testAdjacencyDoorways() {
		Set<BoardCell> testList = board.getAdjList(7, 3);	//Doorway above
		assertTrue(testList.contains(board.getCellAt(7, 2)));
		assertTrue(testList.contains(board.getCellAt(7, 4)));
		assertTrue(testList.contains(board.getCellAt(6, 3)));
		assertTrue(testList.contains(board.getCellAt(8, 3)));
		assertEquals(4, testList.size());

		testList = board.getAdjList(1, 11);					//Doorway on the right
		assertTrue(testList.contains(board.getCellAt(1, 12)));
		assertTrue(testList.contains(board.getCellAt(1, 10)));
		assertTrue(testList.contains(board.getCellAt(0, 11)));
		assertTrue(testList.contains(board.getCellAt(2, 11)));
		assertEquals(4, testList.size());

		testList = board.getAdjList(7, 17);					//Doorway below
		assertTrue(testList.contains(board.getCellAt(7, 16)));
		assertTrue(testList.contains(board.getCellAt(7, 18)));
		assertTrue(testList.contains(board.getCellAt(6, 17)));
		assertTrue(testList.contains(board.getCellAt(8, 17)));
		assertEquals(4, testList.size());

		testList = board.getAdjList(21, 16);				//Doorway right, on edge of room
		assertTrue(testList.contains(board.getCellAt(21, 15)));
		assertTrue(testList.contains(board.getCellAt(21, 17)));
		assertTrue(testList.contains(board.getCellAt(20, 16)));
		assertEquals(3, testList.size());

		testList = board.getAdjList(17, 12);				//Doorway below, room to the right
		assertTrue(testList.contains(board.getCellAt(17, 13)));
		assertTrue(testList.contains(board.getCellAt(16, 12)));
		assertTrue(testList.contains(board.getCellAt(18, 12)));
		assertEquals(3, testList.size());

		testList = board.getAdjList(16, 4);					//Doorway on the left
		assertTrue(testList.contains(board.getCellAt(16, 3)));
		assertTrue(testList.contains(board.getCellAt(16, 5)));
		assertTrue(testList.contains(board.getCellAt(15, 4)));
		assertTrue(testList.contains(board.getCellAt(17, 4)));
		assertEquals(4, testList.size());
	}

	// Test a variety of walkway scenarios
	// GREEN on spreadsheet
	@Test
	public void testAdjacencyWalkways() {
		Set<BoardCell> testList = board.getAdjList(0, 7);	//Test top cell with rooms on either side
		assertTrue(testList.contains(board.getCellAt(1, 7)));
		assertEquals(1, testList.size());

		testList = board.getAdjList(6, 11);					//Test below room cell
		assertTrue(testList.contains(board.getCellAt(6, 10)));
		assertTrue(testList.contains(board.getCellAt(6, 12)));
		assertTrue(testList.contains(board.getCellAt(7, 11)));
		assertEquals(3, testList.size());

		testList = board.getAdjList(13, 17);					//Test below room doorway
		assertTrue(testList.contains(board.getCellAt(13, 16)));
		assertTrue(testList.contains(board.getCellAt(13, 18)));
		assertTrue(testList.contains(board.getCellAt(12, 17)));
		assertTrue(testList.contains(board.getCellAt(14, 17)));
		assertEquals(4, testList.size());

		testList = board.getAdjList(14, 12);					//Test middle of walkway
		assertTrue(testList.contains(board.getCellAt(14, 11)));
		assertTrue(testList.contains(board.getCellAt(14, 13)));
		assertTrue(testList.contains(board.getCellAt(13, 12)));
		assertTrue(testList.contains(board.getCellAt(15, 12)));
		assertEquals(4, testList.size());

		testList = board.getAdjList(13, 9);					//Test below closet
		assertTrue(testList.contains(board.getCellAt(13, 8)));
		assertTrue(testList.contains(board.getCellAt(13, 10)));
		assertTrue(testList.contains(board.getCellAt(14, 9)));
		assertEquals(3, testList.size());

		testList = board.getAdjList(21, 3);					//Test room cell on the left, at bottom edge
		assertTrue(testList.contains(board.getCellAt(21, 4)));
		assertTrue(testList.contains(board.getCellAt(20, 3)));
		assertEquals(2, testList.size());

		testList = board.getAdjList(9, 6);					//Test middle of walkway
		assertTrue(testList.contains(board.getCellAt(9, 5)));
		assertTrue(testList.contains(board.getCellAt(9, 7)));
		assertTrue(testList.contains(board.getCellAt(8, 6)));
		assertTrue(testList.contains(board.getCellAt(10, 6)));
		assertEquals(4, testList.size());
	}

	/*
	Target tests
	 */

	// Tests of just walkways, 1 step
	// RED on spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(21, 5, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(20, 5)));
		assertTrue(targets.contains(board.getCellAt(21, 4)));

		board.calcTargets(10, 12, 1);
		targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(9, 12)));
		assertTrue(targets.contains(board.getCellAt(10, 13)));
		assertTrue(targets.contains(board.getCellAt(11, 12)));
	}

	// Tests of just walkways, 2 steps
	// RED on spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(0, 17, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(2, 17)));
		assertTrue(targets.contains(board.getCellAt(1, 16)));

		board.calcTargets(9, 4, 2);
		targets = board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 5)));
		assertTrue(targets.contains(board.getCellAt(9, 6)));
		assertTrue(targets.contains(board.getCellAt(8, 5)));
		assertTrue(targets.contains(board.getCellAt(7, 4)));
		assertTrue(targets.contains(board.getCellAt(8, 3)));


	}

	// Tests of just walkways, 4 steps
	// RED on spreadsheet
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(0, 10, 4);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCellAt(1, 12)));
		assertTrue(targets.contains(board.getCellAt(2, 12)));
		assertTrue(targets.contains(board.getCellAt(1, 9)));
		assertTrue(targets.contains(board.getCellAt(3, 11)));
		assertTrue(targets.contains(board.getCellAt(2, 10)));
		assertTrue(targets.contains(board.getCellAt(4, 10)));
		assertTrue(targets.contains(board.getCellAt(1, 11)));

		board.calcTargets(14, 0, 4);
		targets = board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCellAt(15, 1)));
		assertTrue(targets.contains(board.getCellAt(16, 0)));
		assertTrue(targets.contains(board.getCellAt(15, 3)));
		assertTrue(targets.contains(board.getCellAt(14, 2)));
		assertTrue(targets.contains(board.getCellAt(14, 4)));
	}

	// Tests of just walkways plus one door, 6 steps
	// RED on spreadsheet
	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(7, 20, 6);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCellAt(8, 17)));
		assertTrue(targets.contains(board.getCellAt(5, 18)));
		assertTrue(targets.contains(board.getCellAt(7, 14)));
		assertTrue(targets.contains(board.getCellAt(6, 15)));
		assertTrue(targets.contains(board.getCellAt(5, 16)));
		assertTrue(targets.contains(board.getCellAt(6, 17)));
		assertTrue(targets.contains(board.getCellAt(4, 17)));
		assertTrue(targets.contains(board.getCellAt(7, 16)));
		assertTrue(targets.contains(board.getCellAt(6, 19)));

	}

	// Test getting into a room
	// RED on spreadsheet
	@Test
	public void testTargetsIntoRoom() {
		board.calcTargets(17, 4, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCellAt(16, 3))); //Doorway
		assertTrue(targets.contains(board.getCellAt(15, 4)));
		assertTrue(targets.contains(board.getCellAt(16, 5)));
		assertTrue(targets.contains(board.getCellAt(17, 6)));
		assertTrue(targets.contains(board.getCellAt(18, 5)));
		assertTrue(targets.contains(board.getCellAt(19, 4)));
		assertTrue(targets.contains(board.getCellAt(18, 3)));
	}

	// Test getting into room, doesn't require all steps
	// RED on spreadsheet
	@Test
	public void testTargetsIntoRoomShortcut() {
		board.calcTargets(10, 14, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 15))); //Doorway
		assertTrue(targets.contains(board.getCellAt(12, 14)));
		assertTrue(targets.contains(board.getCellAt(11, 13)));
		assertTrue(targets.contains(board.getCellAt(10, 12)));
		assertTrue(targets.contains(board.getCellAt(9, 13)));
		assertTrue(targets.contains(board.getCellAt(8, 14)));
	}

	// Test getting out of a room
	// RED on spreadsheet
	@Test
	public void testRoomExit() {
		board.calcTargets(6, 3, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(7, 3)));

		board.calcTargets(6, 3, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(8, 3)));
		assertTrue(targets.contains(board.getCellAt(7, 2)));
		assertTrue(targets.contains(board.getCellAt(7, 4)));
	}
}

// GTAdrian