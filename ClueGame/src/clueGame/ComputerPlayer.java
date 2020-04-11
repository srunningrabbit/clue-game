/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	private ArrayList<Card> possiblePeople = new ArrayList<>();
	private ArrayList<Card> possibleWeapons = new ArrayList<>();
	private ArrayList<Card> seenCards;
	private Solution suggestion;
	
	public ComputerPlayer(String name, int row, int col, Color color) {
		super(name, row, col, color);
		seenCards = this.getHand();
	}

	/*
	Getters and setters, including those for testing purposes
	 */

	public ArrayList<Card> getPossiblePeople() {
		return possiblePeople;
	}

	public ArrayList<Card> getPossibleWeapons() {
		return possibleWeapons;
	}

	public Solution getSuggestion() {
		return suggestion;
	}

	public void setSeenCards(ArrayList<Card> seenCards) {
		this.seenCards = seenCards;
	}

	/*
	Methods
	 */

	public BoardCell pickLocation(Set<BoardCell> targets) {
		Random random = new Random();
		Board board = Board.getInstance();
		char currInitial = board.getCellAt(this.getRow(), this.getColumn()).getInitial();
		
		// If a room is in reach and not previously in it, must return it
		for(BoardCell cell : targets) {
			if(cell.isDoorway() && cell.getInitial() != currInitial) {
				return cell;
			}
		}
		// Else pick a random location
		int index = random.nextInt(targets.size());
		Iterator<BoardCell> iter = targets.iterator();
		for (int i = 0; i < index; i++) {
		    iter.next();
		}
		return iter.next();
	}
	
	public void makeAccusation() {
		
	}

	// Create a new suggestion that contains a person, a weapon, and a room
	public void createSuggestion() {
		Board board = Board.getInstance();
		Random rand = new Random();
		for (Card card : board.getDeck()) {
			if (!seenCards.contains(card) && card != null) {
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
		seenCards.add(board.handleSuggestion(suggestion, this));
	}

	// Add unseen cards to corresponding lists, people or weapons
	public void possibleCard(Card card) {
		if (card.getCardType().equals(CardType.PERSON))
			possiblePeople.add(card);
		if (card.getCardType().equals(CardType.WEAPON))
			possibleWeapons.add(card);
	}
}
