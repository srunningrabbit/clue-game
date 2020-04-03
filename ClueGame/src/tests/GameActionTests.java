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

	/*
	Target location tests (computer)
	 */
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

	/*
	Accusation tests (board)
	 */
	@Test
	public void checkAccusations() {
		Solution testSolution = new Solution("Killer","Weapon","Room");
		board.setAnswer(testSolution);
		
		// Test solution that is correct
		assertTrue(board.checkAccusation(testSolution));
		
		// Test solution with wrong person
		testSolution = new Solution("Plebian","Weapon","Room");
		assertFalse(board.checkAccusation(testSolution));
		
		// Test solution with wrong weapon
		testSolution = new Solution("Killer","Flower","Room");
		assertFalse(board.checkAccusation(testSolution));
		
		// Test solution with wrong room
		testSolution = new Solution("Killer","Weapon","Outdoors");
		assertFalse(board.checkAccusation(testSolution));
	}

	/*
	Suggestion tests
	 */
	@Test
	public void creatingComputerSuggestion() {	// Computer
		ComputerPlayer testPlayer = new ComputerPlayer("Tester", 3, 3, Color.RED);
		testPlayer.possibleCard(new Card("Weapon 1", CardType.WEAPON));
		testPlayer.possibleCard(new Card("Person 1", CardType.PERSON));
		testPlayer.createSuggestion();
		Solution testSuggestion = testPlayer.getSuggestion();

		// Make sure room matches current location
		assertEquals("Home Decoration", testSuggestion.getRoom());

		// If only one weapon not seen, it's selected
		assertEquals("Weapon 1", testSuggestion.getWeapon());

		// If only one person not seen, it's selected (can be same test as weapon)
		assertEquals("Person 1", testSuggestion.getPerson());

		testPlayer = new ComputerPlayer("Tester", 3, 3, Color.RED);
		testPlayer.possibleCard(new Card("Weapon 1", CardType.WEAPON));
		testPlayer.possibleCard(new Card("Weapon 2", CardType.WEAPON));
		testPlayer.possibleCard(new Card("Weapon 3", CardType.WEAPON));
		testPlayer.possibleCard(new Card("Person 1", CardType.PERSON));
		testPlayer.possibleCard(new Card("Person 2", CardType.PERSON));
		testPlayer.possibleCard(new Card("Person 3", CardType.PERSON));
		testPlayer.createSuggestion();
		testSuggestion = testPlayer.getSuggestion();

		// If multiple weapons not seen, one of them is randomly selected
		assertTrue(testPlayer.getPossibleWeapons().contains(testSuggestion.getWeapon()));

		// If multiple persons not seen, one of them is randomly selected
		assertTrue(testPlayer.getPossiblePeople().contains(testSuggestion.getPerson()));
	}	
	
	@Test
	public void disproveSuggestion() { // Player
		Solution testSolution = new Solution("Killer", "Weapon", "Room");
		board.setAnswer(testSolution);

		// If player has only one matching card it should be returned
		Player testPlayer = new Player("Tester", 9, 6, Color.RED);
		Card card = testPlayer.disproveSuggestion(new Solution("Killer", "Spoon", "Closet"));
		assertEquals("Killer", card.getCardName());

		// If players has >1 matching card, returned card should be chosen randomly
		card = testPlayer.disproveSuggestion(new Solution("Killer", "Weapon", "Closet"));
		assertTrue(card.getCardName() == "Killer" || card.getCardName() == "Weapon");

		// If player has no matching cards, null is returned
		card = testPlayer.disproveSuggestion(new Solution("Man", "Spoon", "Closet"));
		assertEquals(null, card);
	}	
	
	@Test
	public void handlingSuggestion() { // Board
		// Suggestion no one can disprove returns null
		// Suggestion only accusing player can disprove returns null
		// Suggestion only human can disprove returns answer (i.e., card that disproves suggestion)
		// Suggestion only human can disprove, but human is accuser, returns null
		// Suggestion that two players can disprove, correct player (based on starting with next player in list) returns answer
		// Suggestion that human and another player can disprove, other player is next in list, ensure other player returns answer
	}
}
