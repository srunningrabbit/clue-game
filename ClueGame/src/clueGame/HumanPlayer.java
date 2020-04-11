package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, int row, int col, Color color) {
		super(name, row, col, color);
	}

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
	
//	public Card disproveSuggestion(Solution suggestion) {
//		return null;
//	}
	
	public void createSuggestion() {
		
	}
}
