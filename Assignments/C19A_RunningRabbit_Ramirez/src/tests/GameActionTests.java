/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package tests;

import clueGame.*;
import static org.junit.Assert.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameActionTests {
	private static Board board;

	@BeforeClass
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueRooms.txt","data/PlayerLegend.txt", "data/WeaponLegend.txt");
		board.initialize();

		// Created new test players for testing
		ArrayList<Player> newPlayers = new ArrayList<>();
		newPlayers.add(new HumanPlayer("Person 1", 3, 19, Color.RED));
		newPlayers.add(new ComputerPlayer("Person 2", 10, 1, Color.ORANGE));
		newPlayers.add(new ComputerPlayer("Person 3", 20, 1, Color.YELLOW));
		newPlayers.add(new ComputerPlayer("Person 4", 3, 3, Color.GREEN));
		newPlayers.add(new ComputerPlayer("Person 5", 20, 10, Color.BLUE));
		board.setPlayers(newPlayers);

		// Created new deck for testing
		ArrayList<Card> newDeck = new ArrayList<>();
		newDeck.add(new Card("Home Decoration", CardType.ROOM));
		newDeck.add(new Card("Room Decoration", CardType.ROOM));
		newDeck.add(new Card("Kitchen Decoration", CardType.ROOM));
		newDeck.add(new Card("Weapon 1", CardType.WEAPON));
		newDeck.add(new Card("Weapon 2", CardType.WEAPON));
		newDeck.add(new Card("Weapon 3", CardType.WEAPON));
		newDeck.add(new Card("Weapon 4", CardType.WEAPON));
		newDeck.add(new Card("Weapon 5", CardType.WEAPON));
		newDeck.add(new Card("Person 1", CardType.PERSON));
		newDeck.add(new Card("Person 2", CardType.PERSON));
		newDeck.add(new Card("Person 3", CardType.PERSON));
		newDeck.add(new Card("Person 4", CardType.PERSON));
		newDeck.add(new Card("Person 5", CardType.PERSON));
		board.setDeck(newDeck);
		board.shuffleDeck();
		board.dealCards();
	}

	/*
	Target location tests (computer)
	 */
	@Test
	public void computerLocationTest() {
		// No room in target list, random selection
		ComputerPlayer testPlayer = new ComputerPlayer("Tester", 9, 6, Color.RED);
		board.calcTargets(testPlayer.getRow(), testPlayer.getColumn(), 1);
		Set<BoardCell> testTargets = board.getTargets();
		BoardCell testLocation = testPlayer.pickLocation(board.getTargets());
		assertTrue(testTargets.contains(testLocation));

		// If room in list that was not just visited, must select it
		testPlayer = new ComputerPlayer("Tester", 7, 4, Color.RED);
		board.calcTargets(testPlayer.getRow(), testPlayer.getColumn(), 1);
		testLocation = testPlayer.pickLocation(board.getTargets());
		assertEquals(testLocation, board.getCellAt(6, 4));

		// If room just visited is in list, each target (including room) selected randomly (after picking door, leaving)
		testPlayer = new ComputerPlayer("Tester", 16, 3, Color.RED);
		board.calcTargets(testPlayer.getRow(), testPlayer.getColumn(), 2);
		testTargets = board.getTargets();
		testLocation = testPlayer.pickLocation(board.getTargets());
		assertTrue(testTargets.contains(testLocation));
	}

	/*
	Accusation tests (board)
	 */
	@Test
	public void checkAccusations() {
		Solution testSolution = new Solution("Killer","Weapon","Room");
		Solution testAccusation = new Solution("Killer","Weapon","Room");
		board.setAnswer(testSolution);

		// Test solution that is correct
		assertTrue(board.checkAccusation(testAccusation));

		// Test solution with wrong person
		testAccusation = new Solution("Plebian","Weapon","Room");
		assertFalse(board.checkAccusation(testAccusation));

		// Test solution with wrong weapon
		testAccusation = new Solution("Killer","Flower","Room");
		assertFalse(board.checkAccusation(testAccusation));

		// Test solution with wrong room
		testAccusation = new Solution("Killer","Weapon","Outdoors");
		assertFalse(board.checkAccusation(testAccusation));
	}

	/*
	Suggestion tests
	 */
	@Test
	public void creatingComputerSuggestion() {	// Computer
		// Grabbing the 4th player
		ComputerPlayer testPlayer = (ComputerPlayer) board.getPlayers().get(3);
		testPlayer.possibleCard(new Card("Weapon 1", CardType.WEAPON));
		testPlayer.possibleCard(new Card("Person 1", CardType.PERSON));
		ArrayList<Card> testSeenCards = board.getDeck();
		// Remove cards to make sure that the player sees them as possibilities
		testSeenCards.removeIf(card -> card.getCardName().equals("Weapon 1"));
		testSeenCards.removeIf(card -> card.getCardName().equals("Person 1"));
		testPlayer.setSeenCards(testSeenCards);
		testPlayer.createSuggestion();
		Solution testSuggestion = testPlayer.getSuggestion();

		// Make sure room matches current location
		assertEquals("Home Decoration", testSuggestion.getRoom());

		// If only one weapon not seen, it's selected
		assertEquals("Weapon 1", testSuggestion.getWeapon());

		// If only one person not seen, it's selected (can be same test as weapon)
		assertEquals("Person 1", testSuggestion.getPerson());

		testPlayer = new ComputerPlayer("Player 4", 3, 3, Color.RED);
		testPlayer.possibleCard(new Card("Weapon 1", CardType.WEAPON));
		testPlayer.possibleCard(new Card("Weapon 2", CardType.WEAPON));
		testPlayer.possibleCard(new Card("Weapon 3", CardType.WEAPON));
		testPlayer.possibleCard(new Card("Person 1", CardType.PERSON));
		testPlayer.possibleCard(new Card("Person 2", CardType.PERSON));
		testPlayer.possibleCard(new Card("Person 3", CardType.PERSON));
		testPlayer.createSuggestion();
		testSuggestion = testPlayer.getSuggestion();

		// If multiple weapons not seen, one of them is randomly selected
		boolean seenWeapon = false;
		for (Card card : testPlayer.getPossibleWeapons()) {
			if (card.getCardName().equals(testSuggestion.getWeapon())) {
				seenWeapon = true;
				break;
			}
		}
		assertTrue(seenWeapon);

		// If multiple persons not seen, one of them is randomly selected
		boolean seenPerson = false;
		for (Card card : testPlayer.getPossiblePeople()) {
			if (card.getCardName().equals(testSuggestion.getPerson())) {
				seenPerson = true;
				break;
			}
		}
		assertTrue(seenPerson);
	}

	@Test
	public void disproveSuggestion() { // Player
		Player testPlayer = new Player("Tester", 9, 6, Color.RED);
		ArrayList<Card> newHand = new ArrayList<>();
		newHand.add(new Card("Killer", CardType.PERSON));
		newHand.add(new Card("Weapon", CardType.WEAPON));
		newHand.add(new Card("Room", CardType.ROOM));
		testPlayer.setHand(newHand);

		// If player has only one matching card it should be returned
		Card card = testPlayer.disproveSuggestion(new Solution("Killer", "Spoon", "Closet"));
		assertEquals("Killer", card.getCardName());

		// If players has >1 matching card, returned card should be chosen randomly
		card = testPlayer.disproveSuggestion(new Solution("Killer", "Weapon", "Closet"));
		assertTrue(card.getCardName().equals("Killer") || card.getCardName().equals("Weapon"));

		// If player has no matching cards, null is returned
		card = testPlayer.disproveSuggestion(new Solution("Man", "Spoon", "Closet"));
		assertNull(card);
	}

	@Test
	public void handlingSuggestion() { // Board
		ArrayList<Player> testPlayers = board.getPlayers();
		ArrayList<Card> testDeck = board.getDeck();

		// Suggestion no one can disprove returns null
		Solution testSuggestion = new Solution("","","");
		assertNull(board.handleSuggestion(testSuggestion, testPlayers.get(1)));

		// Suggestion only accusing player can disprove returns null
		ArrayList<Card> oldHand = testPlayers.get(1).getHand();
		ArrayList<Card> newHand = new ArrayList<>();
		newHand.add(new Card("", CardType.PERSON));
		newHand.add(new Card("Weapon", CardType.WEAPON));
		newHand.add(new Card("Room", CardType.ROOM));
		testPlayers.get(1).setHand(newHand);

		assertNull(board.handleSuggestion(testSuggestion, testPlayers.get(1)));
		testPlayers.get(1).setHand(oldHand);

		// Suggestion only human can disprove returns answer (i.e., card that disproves suggestion)
		oldHand = testPlayers.get(0).getHand();
		newHand = new ArrayList<>();
		newHand.add(new Card("", CardType.PERSON));
		newHand.add(new Card("Weapon", CardType.WEAPON));
		newHand.add(new Card("Room", CardType.ROOM));
		testPlayers.get(0).setHand(newHand);

		Card testCard = board.handleSuggestion(testSuggestion, testPlayers.get(1));
		assertTrue(testSuggestion.hasCard(testCard));
		testPlayers.get(0).setHand(oldHand);

		// Suggestion only human can disprove, but human is accuser, returns null
		oldHand = testPlayers.get(0).getHand();
		newHand = new ArrayList<>();
		newHand.add(new Card("", CardType.PERSON));
		newHand.add(new Card("Weapon", CardType.WEAPON));
		newHand.add(new Card("Room", CardType.ROOM));
		testPlayers.get(0).setHand(newHand);

		assertNull(board.handleSuggestion(testSuggestion, testPlayers.get(0)));
		testPlayers.get(0).setHand(oldHand);

		// Suggestion that two players can disprove, correct player (based on starting with next player in list) returns answer
		ArrayList<Card> oldHand1 = testPlayers.get(1).getHand();
		newHand = new ArrayList<>();
		newHand.add(new Card("", CardType.PERSON));
		newHand.add(new Card("Weapon", CardType.WEAPON));
		newHand.add(new Card("Room", CardType.ROOM));
		testPlayers.get(1).setHand(newHand);

		ArrayList<Card> oldHand2 = testPlayers.get(2).getHand();
		newHand = new ArrayList<>();
		newHand.add(new Card("Killer", CardType.PERSON));
		newHand.add(new Card("", CardType.WEAPON));
		newHand.add(new Card("Room", CardType.ROOM));
		testPlayers.get(2).setHand(newHand);

		testCard = board.handleSuggestion(testSuggestion, testPlayers.get(0));
		assertTrue(testPlayers.get(1).getHand().contains(testCard));
		testPlayers.get(1).setHand(oldHand1);
		testPlayers.get(2).setHand(oldHand2);

		// Suggestion that human and another player can disprove, other player is next in list, ensure other player returns answer
		oldHand1 = testPlayers.get(0).getHand();
		newHand = new ArrayList<>();
		newHand.add(new Card("", CardType.PERSON));
		newHand.add(new Card("Weapon", CardType.WEAPON));
		newHand.add(new Card("Room", CardType.ROOM));
		testPlayers.get(0).setHand(newHand);

		oldHand2 = testPlayers.get(1).getHand();
		newHand = new ArrayList<>();
		newHand.add(new Card("Killer", CardType.PERSON));
		newHand.add(new Card("", CardType.WEAPON));
		newHand.add(new Card("Room", CardType.ROOM));
		testPlayers.get(1).setHand(newHand);

		testCard = board.handleSuggestion(testSuggestion, testPlayers.get(2));
		assertTrue(testPlayers.get(1).getHand().contains(testCard));
		testPlayers.get(0).setHand(oldHand1);
		testPlayers.get(1).setHand(oldHand2);
	}
}
