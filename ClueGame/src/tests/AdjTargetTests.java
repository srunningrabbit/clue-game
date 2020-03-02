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
		board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt");
		board.initialize();
	}

	/*
	Adjacency tests
	 */

	// Ensure that player does not move around within room
	// PINK on spreadsheet
	@Test
	public void testAdjacenciesInsideRooms() {
		// TODO Adjacent cells in rooms
	}

	// Ensure that the adjacency list from a doorway is only the walkway
	// NOTE: This test could be merged with door direction test.
	// PURPLE on spreadsheet
	@Test
	public void testAdjacencyRoomExit() {
		// TODO Adjacent cells to doorways
	}

	// Test adjacency at entrance to rooms
	// CYAN on spreadsheet
	@Test
	public void testAdjacencyDoorways() {
		// TODO Adjacent cells in front of doorways
	}

	// Test a variety of walkway scenarios
	// GREEN on spreadsheet
	@Test
	public void testAdjacencyWalkways() {
		// TODO Adjacent cells to walkways
	}

	/*
	Target tests
	 */

	// Tests of just walkways, 1 step
	// RED on spreadsheet
	@Test
	public void testTargetsOneStep() {
		// TODO Targets with path length of 1
	}

	// Tests of just walkways, 2 steps
	// RED on spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		// TODO Targets with path length of 2
	}

	// Tests of just walkways, 4 steps
	// RED on spreadsheet
	@Test
	public void testTargetsFourSteps() {
		// TODO Targets with path length of 4
	}

	// Tests of just walkways plus one door, 6 steps
	// RED on spreadsheet
	@Test
	public void testTargetsSixSteps() {
		// TODO Targets with path length of 6
	}

	// Test getting into a room
	// RED on spreadsheet
	@Test
	public void testTargetsIntoRoom() {
		// TODO Targets inside rooms
	}

	// Test getting into room, doesn't require all steps
	// RED on spreadsheet
	@Test
	public void testTargetsIntoRoomShortcut() {
		// TODO Targets getting into rooms
	}

	// Test getting out of a room
	// RED on spreadsheet
	@Test
	public void testRoomExit() {
		// TODO Targets getting out of rooms
	}
}
