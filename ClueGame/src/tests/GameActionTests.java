package tests;

import clueGame.*;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameActionTests {
	private static Board board;

	@BeforeClass
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueRooms.txt","data/PlayerLegend.txt", "data/WeaponLegend.txt");
		board.initialize();
	}
	

	// Target Location Tests (Computer)
	@Test
	public void computerLocationTest() {
		// No room in target list, random selection
		ComputerPlayer testPlayer = new ComputerPlayer("Tester", 9, 6, Color.RED);
		board.calcTargets(testPlayer.getRow(), testPlayer.getColumn(), 1);
		BoardCell testLocation = testPlayer.pickLocation(board.getTargets());
		//TODO I dont remember how we tested random picking lol
		
		// If room in list that was not just visited, must select it
		testPlayer = new ComputerPlayer("Tester", 7, 4, Color.RED);
		board.calcTargets(testPlayer.getRow(), testPlayer.getColumn(), 1);
		testLocation = testPlayer.pickLocation(board.getTargets());
		assertEquals(testLocation, board.getCellAt(6, 4));
				
		// If room just visited is in list, each target (including room) selected randomly (after picking door, leaving)
		testPlayer = new ComputerPlayer("Tester", 16, 3, Color.RED);
		board.calcTargets(testPlayer.getRow(), testPlayer.getColumn(), 2);
		testLocation = testPlayer.pickLocation(board.getTargets());
		//TODO same random picking test + must not be room
	}

	// Accusation Tests (Board)
	@Test
	public void checkAccusations() {
		//TODO Test solution that is correct
		//TODO Test solution with wrong person
		//TODO Test solution with wrong weapon
		//TODO Test solution with wrong room
	}

	// Suggestion tests
	@Test
	public void creatingComputerSuggestion() {	//Computer	
		// Make sure room matches current location
		// If only one weapon not seen, it's selected
		// If only one person not seen, it's selected (can be same test as weapon)
		// If multiple weapons not seen, one of them is randomly selected
		// If multiple persons not seen, one of them is randomly selected
	}	
	
	@Test
	public void disproveSuggestion() { //Player
		//If player has only one matching card it should be returned
		//If players has >1 matching card, returned card should be chosen randomly
		//If player has no matching cards, null is returned
	}	
	
	@Test
	public void handlingSuggestion() { //Board
		//Suggestion no one can disprove returns null
		//Suggestion only accusing player can disprove returns null
		//Suggestion only human can disprove returns answer (i.e., card that disproves suggestion)
		//Suggestion only human can disprove, but human is accuser, returns null
		//Suggestion that two players can disprove, correct player (based on starting with next player in list) returns answer
		//Suggestion that human and another player can disprove, other player is next in list, ensure other player returns answer
	}	

}
