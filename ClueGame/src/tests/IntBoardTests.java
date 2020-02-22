package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

public class IntBoardTests {
	private IntBoard board;

	@Before
	public void beforeAll() {
		board = new IntBoard();
	}

	/*
	 * Test creation of adjacency lists
	 */

	// Test top left corner
	@Test
	public void testAdjecencyTL() {
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}

	// Test top right corner
	@Test
	public void testAdjecencyTR() {
		BoardCell cell = board.getCell(board.getGridLength() - 1,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(board.getGridLength() - 2, 0)));
		assertTrue(testList.contains(board.getCell(board.getGridLength() - 1, 1)));
		assertEquals(2, testList.size());
	}

	// Test bottom left corner
	@Test
	public void testAdjecencyBL() {
		BoardCell cell = board.getCell(0,board.getGridWidth() - 1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, board.getGridWidth() - 2)));
		assertTrue(testList.contains(board.getCell(1, board.getGridWidth() - 1)));
		assertEquals(2, testList.size());
	}	

	// Test bottom right corner
	@Test
	public void testAdjecencyBR() {
		BoardCell cell = board.getCell(board.getGridLength() - 1,board.getGridWidth() - 1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(board.getGridLength() - 1, board.getGridWidth() - 2)));
		assertTrue(testList.contains(board.getCell(board.getGridLength() - 2, board.getGridWidth() - 1)));
		assertEquals(2, testList.size());
	}

    // Test left edge
    @Test
    public void testAdjecencyLE() {
        BoardCell cell = board.getCell(0, 2);
        Set<BoardCell> testList = board.getAdjList(cell);
        assertTrue(testList.contains(board.getCell(0, 1)));
        assertTrue(testList.contains(board.getCell(0, 3)));
        assertTrue(testList.contains(board.getCell(1, 2)));
        assertEquals(3, testList.size());
    }

	// Test right edge
    @Test
    public void testAdjecencyRE() {
        BoardCell cell = board.getCell(board.getGridLength() - 1, 2);
        Set<BoardCell> testList = board.getAdjList(cell);
        assertTrue(testList.contains(board.getCell(board.getGridLength() - 1, 1)));
        assertTrue(testList.contains(board.getCell(board.getGridLength() - 1, 3)));
        assertTrue(testList.contains(board.getCell(board.getGridLength() - 2, 2)));
        assertEquals(3, testList.size());
    }

    // Test a middle square, [1][1]
    @Test
    public void testAdjecency1_1() {
        BoardCell cell = board.getCell(1, 1);
        Set<BoardCell> testList = board.getAdjList(cell);
        assertTrue(testList.contains(board.getCell(1, 0)));
        assertTrue(testList.contains(board.getCell(1, 2)));
        assertTrue(testList.contains(board.getCell(0, 1)));
        assertTrue(testList.contains(board.getCell(2, 1)));
        assertEquals(4, testList.size());
    }

    // Test another middle square, [2][2]
    @Test
    public void testAdjecency2_2() {
        BoardCell cell = board.getCell(2, 2);
        Set<BoardCell> testList = board.getAdjList(cell);
        assertTrue(testList.contains(board.getCell(2, 1)));
        assertTrue(testList.contains(board.getCell(2, 3)));
        assertTrue(testList.contains(board.getCell(1, 2)));
        assertTrue(testList.contains(board.getCell(3, 2)));
        assertEquals(4, testList.size());
    }

    /*
     * Test creation of targets
     */

    // Test bottom left corner
    @Test
    public void testLength2TargetsBL() {
        BoardCell cell = board.getCell(0,board.getGridWidth() - 1);

        // Path length of 3
        board.calcTargets(cell, 3);
        Set targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(0,board.getGridWidth() - 1)));
        assertTrue(targets.contains(board.getCell(0,board.getGridWidth() - 2)));
        assertTrue(targets.contains(board.getCell(0,board.getGridWidth() - 3)));
        assertTrue(targets.contains(board.getCell(1,board.getGridWidth() - 1)));
        assertTrue(targets.contains(board.getCell(2,board.getGridWidth() - 1)));
        assertTrue(targets.contains(board.getCell(1,board.getGridWidth() - 2)));
        assertEquals(6, targets.size());

        // Path length of 4
        board.calcTargets(cell, 4);
        targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(0,board.getGridWidth() - 1)));
        assertTrue(targets.contains(board.getCell(0,board.getGridWidth() - 2)));
        assertTrue(targets.contains(board.getCell(0,board.getGridWidth() - 3)));
        assertTrue(targets.contains(board.getCell(0,board.getGridWidth() - 4)));
        assertTrue(targets.contains(board.getCell(1,board.getGridWidth() - 1)));
        assertTrue(targets.contains(board.getCell(2,board.getGridWidth() - 1)));
        assertTrue(targets.contains(board.getCell(3,board.getGridWidth() - 1)));
        assertTrue(targets.contains(board.getCell(1,board.getGridWidth() - 2)));
        assertTrue(targets.contains(board.getCell(1,board.getGridWidth() - 3)));
        assertTrue(targets.contains(board.getCell(2,board.getGridWidth() - 2)));
        assertEquals(10, targets.size());
    }

    // Test top right corner
    @Test
    public void testLength1TargetsTR() {
        BoardCell cell = board.getCell(board.getGridLength() - 1,0);

        // Path length of 3
        board.calcTargets(cell, 3);
        Set targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 1,0)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 1,1)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 1,2)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 2,0)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 3,0)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 2,1)));
        assertEquals(6, targets.size());

        // Path length of 4
        board.calcTargets(cell, 4);
        targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 1,0)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 1,0)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 1,0)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 1,0)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 1,0)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 1,0)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 1,0)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 1,0)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 1,0)));
        assertTrue(targets.contains(board.getCell(board.getGridLength() - 1,0)));
        assertEquals(10, targets.size());
    }

    // Test square [1][2]
    @Test
    public void testLength2Targets1_2() {
        BoardCell cell = board.getCell(1, 2);

        // Path length of 3
        board.calcTargets(cell, 3);
        Set targets = board.getTargets();
        assertTrue(targets.contains(board.getCell(1, 2)));
        assertTrue(targets.contains(board.getCell(1, 1)));
        assertTrue(targets.contains(board.getCell(1, 0)));
        assertTrue(targets.contains(board.getCell(1, 3)));
        assertTrue(targets.contains(board.getCell(0, 1)));
        assertTrue(targets.contains(board.getCell(0, 2)));
        assertTrue(targets.contains(board.getCell(0, 3)));
        assertTrue(targets.contains(board.getCell(2, 1)));
        assertTrue(targets.contains(board.getCell(2, 2)));
        assertTrue(targets.contains(board.getCell(2, 3)));
        assertTrue(targets.contains(board.getCell(3, 2)));
        assertEquals(11, targets.size());
    }
}
