/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import java.awt.Color;
import java.util.ArrayList;

/*
HumanPlayer

A human player in the game, a child of Player
 */

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, int row, int col, Color color) {
		super(name, row, col, color);
	}

	/*
	Getters
	 */

	// Grab the names of card to easily traverse through when displaying
	public ArrayList<String> getCardNames() {
		ArrayList<String> cardNames = new ArrayList<>();
		String people = "";
		String weapons = "";
		String rooms = "";
		for (Card card : getHand()) {
			if (card.getCardType().equals(CardType.PERSON)) {
				people += card.getCardName() + "\n";
			} else if (card.getCardType().equals(CardType.WEAPON)) {
				weapons += card.getCardName() + "\n";
			} else if (card.getCardType().equals(CardType.ROOM)) {
				rooms += card.getCardName() + "\n";
			}
		}
		cardNames.add(people);
		cardNames.add(weapons);
		cardNames.add(rooms);
		return cardNames;
	}
}
