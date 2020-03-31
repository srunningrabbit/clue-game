/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package tests;

import clueGame.*;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.*;

public class GameSetupTests {
	private static Board board;

	@BeforeClass
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueRooms.txt","data/PlayerLegend.txt", "data/WeaponLegend.txt");
		board.initialize();
	}

	/*
	Test player config file
	 */

	@Test
	public void testPlayerTypes() {
		// Test for at least one human and one computer
		boolean foundHuman = false;
		boolean foundComp = false;
		ArrayList<Player> testPlayers = board.getPlayers();
		for(Player player: testPlayers) {
			if(player.getClass() == HumanPlayer.class) {
				foundHuman = true;
			}
			else if (player.getClass() == ComputerPlayer.class) {
				foundComp = true;
			}
		}	
		assertTrue(foundHuman);
		assertTrue(foundComp);
	}

	@Test
	public void testFirstPlayer() {
		// Test first player name, color, type, and location
		ArrayList<Player> testPlayers = board.getPlayers();
		Player firstPlayer = testPlayers.get(0);
		assertEquals("Player 1", firstPlayer.getName());
		assertEquals(Color.RED, firstPlayer.getColor());
		assertEquals(HumanPlayer.class, firstPlayer.getClass());
		assertEquals(0, firstPlayer.getColumn());
		assertEquals(7, firstPlayer.getRow());
	}

	@Test
	public void testThirdPlayer() {
		// Test third player name, color, type, and location
		ArrayList<Player> testPlayers = board.getPlayers();
		Player thirdPlayer = testPlayers.get(2);
		assertEquals("Player 3", thirdPlayer.getName());
		assertEquals(Color.YELLOW, thirdPlayer.getColor());
		assertEquals(ComputerPlayer.class, thirdPlayer.getClass());
		assertEquals(15, thirdPlayer.getColumn());
		assertEquals(0, thirdPlayer.getRow());
	}

	@Test
	public void testLastPlayer() {
		// Test last player name, color, type, and location
		ArrayList<Player> testPlayers = board.getPlayers();
		Player lastPlayer = testPlayers.get(5);
		assertEquals("Player 6", lastPlayer.getName());
		assertEquals(Color.PINK, lastPlayer.getColor());
		assertEquals(ComputerPlayer.class, lastPlayer.getClass());
		assertEquals(14, lastPlayer.getColumn());
		assertEquals(20, lastPlayer.getRow());
	}

	/*
	Test deck creation
	 */

	@Test
	public void testCardAmount() {
		// Test amount of cards in the deck
		ArrayList<Card> testDeck = board.getDeck();
		assertEquals(testDeck.size(), 21);
	}

	@Test
	public void testPlayerCardAmount() {
		// Test amount of player cards in deck
		ArrayList<Card> testDeck = board.getDeck();
		int playerCount = 0;
		for (Card card : testDeck) {
			if (card.getCardType().equals(CardType.PERSON))
				playerCount++;
		}
		// Compare size against player list size
		ArrayList<Player> testPlayers = board.getPlayers();
		assertEquals(playerCount, 6);
		assertEquals(playerCount, testPlayers.size());
	}

	@Test
	public void testWeaponCardAmount() {
		// Test amount of weapon cards in deck
		ArrayList<Card> testDeck = board.getDeck();
		int weaponCount = 0;
		for (Card card : testDeck) {
			if (card.getCardType().equals(CardType.WEAPON))
				weaponCount++;
		}
		// Compare size against weapon list size
		ArrayList<String> testWeapons = board.getWeapons();
		assertEquals(weaponCount, 6);
		assertEquals(weaponCount, testWeapons.size());
	}

	@Test
	public void testRoomCardAmount() {
		// Test amount of room cards in deck
		ArrayList<Card> testDeck = board.getDeck();
		int roomCount = 0;
		for (Card card : testDeck) {
			if (card.getCardType().equals(CardType.ROOM))
				roomCount++;
		}
		// Compare size against room list size
		ArrayList<String> testRooms = board.getRooms();
		assertEquals(roomCount, 9);
		assertEquals(roomCount, testRooms.size());
	}

	@Test
	public void testRandomHand() {
		ArrayList<Card> testDeck = board.getDeck();
		ArrayList<Player> testPlayers = board.getPlayers();
		ArrayList<String> testWeapons = board.getWeapons();
		ArrayList<String> testRooms = board.getRooms();

		// Grab a random player, weapon, and room to see if they're in deck
		Random rand = new Random();
		String randomPlayer = testPlayers.get(rand.nextInt(testPlayers.size())).getName();
		String randomWeapon = testWeapons.get(rand.nextInt(testWeapons.size()));
		String randomRoom = testRooms.get(rand.nextInt(testRooms.size()));
		boolean foundPlayer = false;
		boolean foundWeapon = false;
		boolean foundRoom = false;
		for (Card card : testDeck) {
			if (card.getCardName().equals(randomPlayer)) {
				foundPlayer = true;
			} else if (card.getCardName().equals(randomWeapon)) {
				foundWeapon = true;
			} else if (card.getCardName().equals(randomRoom)) {
				foundRoom = true;
			}
		}
		assertTrue(foundPlayer);
		assertTrue(foundWeapon);
		assertTrue(foundRoom);
	}

	/*
	Test card dealing
	 */

	@Test
	public void testPlayerHandSize() {
		// TODO See if players have similar amount of cards in their hand
		
	}

	@Test
	public void testUniquePlayerHands() {
		// TODO Make sure no card is owned by more than 1 player, all unique hands
	}
}