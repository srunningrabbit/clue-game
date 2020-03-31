/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package tests;

import clueGame.Board;
import clueGame.BoardCell;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameSetupTests {
	private static Board board;

	@BeforeClass
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueRooms.txt");
		board.initialize();
	}

	/*
	Test player config file
	 */

	@Test
	public void testPlayerTypes() {
		// TODO Test for at least one human and one computer
	}

	@Test
	public void testFirstPlayer() {
		// TODO See if first player has a name, color, type, location
	}

	@Test
	public void testThirdPlayer() {
		// TODO See if first player has a name, color, type, location
	}

	@Test
	public void testLastPlayer() {
		// TODO See if first player has a name, color, type, location
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