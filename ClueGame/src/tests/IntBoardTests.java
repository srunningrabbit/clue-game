package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

public class IntBoardTests {
	IntBoard board;

	@Before
	public void beforeALl() {
		board = new IntBoard();
	}

	//Test creation of adjacency lists

	//Test top left corner
	@Test
	public void testAdjecencyTL() {
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}

	//Test top right corner
	@Test
	public void testAdjecencyTR() {
		BoardCell cell = board.getCell(board.getGridLength()-1,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(board.getGridLength()-2, 0)));
		assertTrue(testList.contains(board.getCell(board.getGridLength()-1, 1)));
		assertEquals(2, testList.size());
	}

	//Test bottom left corner
	@Test
	public void testAdjecencyBL() {
		BoardCell cell = board.getCell(0,board.getGridWidth()-1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, board.getGridWidth()-2)));
		assertTrue(testList.contains(board.getCell(1, board.getGridWidth()-1)));
		assertEquals(2, testList.size());
	}	

	//Test bottom right corner
	@Test
	public void testAdjecencyBR() {
		BoardCell cell = board.getCell(board.getGridLength()-1,board.getGridWidth()-1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(board.getGridLength()-1, board.getGridWidth()-2)));
		assertTrue(testList.contains(board.getCell(board.getGridLength()-2, board.getGridWidth()-1)));
		assertEquals(2, testList.size());
	}	




	//Test target creation (6 methods)



}
