/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package tests;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

public class GameSetupTests {
	private static Board board;

	@BeforeClass
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueRooms.txt","data/PlayerLegend.txt");
		board.initialize();
	}

	/*
	Test player config file
	 */

	@Test
	public void testPlayerTypes() {
		//Test for at least one human and one computer
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
		//Test first player name, color, type, and location
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
		//Test third player name, color, type, and location
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
		//Test last player name, color, type, and location
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
		// TODO Check if amount of cards is = 21 (6 suspects, 6 weapons, 9 rooms)
	}

	@Test
	public void testPlayerCardAmount() {
		// TODO Check amount of player cards, 6
	}

	@Test
	public void testWeaponCardAmount() {
		// TODO Check amount of player cards, 6
	}

	@Test
	public void testRoomCardAmount() {
		// TODO Check amount of player cards, 9
	}

	@Test
	public void testRandomHand() {
		// TODO Test a random hand by pulling a random suspect, weapon, room to see if they're in deck
	}

	/*
	Test card dealing
	 */

	@Test
	public void testDeckSize() {
		// TODO Deck needs to have (players + weapons + rooms) amount of cards
	}

	@Test
	public void testPlayerHandSize() {
		// TODO See if players have similar amount of cards in their hand
	}

	@Test
	public void testUniquePlayerHands() {
		// TODO Make sure no card is owned by more than 1 player, all unique hands
	}
}