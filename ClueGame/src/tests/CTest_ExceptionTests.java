package tests;

/*
 * This program tests that, when loading config files, exceptions 
 * are thrown appropriately.
 */

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;

public class CTest_ExceptionTests {

	// Test that an exception is thrown for a config file that does not 
	// have the same number of columns for each row
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, IOException {
		// Note that we are using a LOCAL Board variable, because each 
		// test will load different files
		Board board = Board.getInstance();
		board.setConfigFiles("data/CTest_ClueLayoutBadColumns.csv", "data/CTest_ClueLegend.txt","data/PlayerLegend.txt", "data/WeaponLegend.txt");
		// Instead of initialize, we call the two load functions directly.
		// This is necessary because initialize contains a try-catch. 
		board.loadRoomConfig();
		// This one should throw an exception
		board.loadBoardConfig();
	}
	
	// Test that an exception is thrown for a config file that specifies 
	// a room that is not in the legend. See first test for other important comments. 
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, IOException {
		Board board = Board.getInstance();
		board.setConfigFiles("data/CTest_ClueLayoutBadRoom.csv", "data/CTest_ClueLegend.txt","data/PlayerLegend.txt", "data/WeaponLegend.txt");
		board.loadRoomConfig();
		board.loadBoardConfig();
	}
	
	// Test that an exception is thrown for a config file with a room type 
	// that is not Card or Other
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoomFormat() throws BadConfigFormatException, IOException {
		Board board = Board.getInstance();
		board.setConfigFiles("data/CTest_ClueLayout.csv", "data/CTest_ClueLegendBadFormat.txt","data/PlayerLegend.txt", "data/WeaponLegend.txt");
		board.loadRoomConfig();
	}

}
