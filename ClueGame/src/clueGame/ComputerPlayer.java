package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	private ArrayList<Card> possiblePeople = new ArrayList<>();
	private ArrayList<Card> possibleWeapons = new ArrayList<>();
	private Set<Card> seenCards;
	private Solution suggestion;
	
	public ComputerPlayer(String name, int row, int col, Color color) {
		super(name, row, col, color);
	}

	public ArrayList<Card> getPossiblePeople() {
		return possiblePeople;
	}

	public ArrayList<Card> getPossibleWeapons() {
		return possibleWeapons;
	}

	public Solution getSuggestion() {
		return suggestion;
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		return null;
	}
	
	public void makeAccusation() {
		
	}

	// Create a new suggestion that contains a person, a weapon, and a room
	public void createSuggestion() {
		Board board = Board.getInstance();
		Random rand = new Random();
		for (Card card : board.getDeck()) {
			if (!seenCards.contains(card)) {
				possibleCard(card);
			}
		}

		// Pick a person depending on seen people
		String person = "";
		if (possiblePeople.size() == 1) {
			person = possiblePeople.get(0).getCardName();
		} else if (possiblePeople.size() > 1) {
			person = possiblePeople.get(rand.nextInt(possiblePeople.size())).getCardName();
		}

		// Pick a weapon depending on seen weapons
		String weapon = "";
		if (possibleWeapons.size() == 1) {
			weapon = possibleWeapons.get(0).getCardName();
		} else if (possibleWeapons.size() > 1) {
			weapon = possibleWeapons.get(rand.nextInt(possibleWeapons.size())).getCardName();
		}

		// Room is automatically the room the player is in
		String room = board.getLegend().get(board.getCellAt(this.getRow(), this.getColumn()).getInitial());

		suggestion = new Solution(person, weapon, room);
	}

	// Add unseen cards to corresponding lists, people or weapons
	public void possibleCard(Card card) {
		if (card.getCardType().equals(CardType.PERSON))
			possiblePeople.add(card);
		if (card.getCardType().equals(CardType.WEAPON))
			possibleWeapons.add(card);
	}
}
