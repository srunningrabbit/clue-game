/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;

/*
ComputerPlayer

A computer player in the game, a child of Player
 */

public class ComputerPlayer extends Player {
	private ArrayList<Card> possiblePeople = new ArrayList<>();
	private ArrayList<Card> possibleWeapons = new ArrayList<>();
	private ArrayList<Card> seenCards;
	private ArrayList<String> stringSeenCards;
	private Solution suggestion;
	private char lastRoom;
	private static Card lastSeenCard;
	private boolean hasAccusation;
	private Solution accusation;
	private boolean madeFakeSuggestion;
	
	
	public ComputerPlayer(String name, int row, int col, Color color) {
		super(name, row, col, color);
		seenCards = this.getHand();
		lastRoom = ' ';
		hasAccusation = false;
		stringSeenCards = new ArrayList<String>();
		madeFakeSuggestion = true;
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

	public void setSeenCards(ArrayList<Card> seenCards) {
		this.seenCards = seenCards;
	}

	public static Card getLastSeenCard() {
		return lastSeenCard;
	}
	 public void setFakeSuggestion(boolean b) {
		 madeFakeSuggestion = b;
	 }
	 
	 public boolean getIsFakeSuggestion() {
		 return madeFakeSuggestion;
	 }

	/*
	Methods
	 */

	// Pick location on board from targets
	public BoardCell pickLocation(Set<BoardCell> targets) {
		Random random = new Random();
		
		// If a room is in reach and not previously in it, must return it
		for(BoardCell cell : targets) {
			if(cell.isDoorway() && cell.getInitial() != lastRoom) {
				lastRoom = cell.getInitial();
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
		Board board = Board.getInstance();
		if (accusation != null) {
			if (accusation.compareTo(board.getSolution()) > 0) {
				JOptionPane.showMessageDialog(
						null,
						playerName + " guessed correctly! The murderer was " + accusation.getPerson() + " who used a " + accusation.getWeapon() + " to kill in " + accusation.getRoom() + ".",
						playerName + " won!",
						JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			} else {
				JOptionPane.showMessageDialog(
						null,
						playerName + " accused the murderer was " + accusation.getPerson() + " who used a " + accusation.getWeapon() + " to kill in " + accusation.getRoom() + ". The accusation was incorrect.",
						playerName + " loses!",
						JOptionPane.INFORMATION_MESSAGE);
				board.removePlayer(this);
			}
		}
		
	}
	
	// If a suggestion was not disproved
	public void foundSolution(Solution accusation) {
		hasAccusation = true;
		this.accusation = accusation;
	}
	
	public boolean hasAccusation() {
		return hasAccusation;
	}
	
	public void makeMove() {
		Board board = Board.getInstance();
		board.calcTargets(this.row, this.col, board.dieRoll);
		Set<BoardCell> targets = board.getTargets();
		BoardCell cell = pickLocation(targets);
		this.row = cell.row;
		this.col = cell.col;
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
		String room = this.getCurrentRoomName();

		super.setSuggestion(new Solution(person, weapon, room));
		lastSeenCard = board.handleSuggestion(super.getSuggestion(), this);
		seenCards.add(lastSeenCard);
	}
	
	
	// Add unseen cards to corresponding lists, people or weapons
	public void possibleCard(Card card) {
		if (card.getCardType().equals(CardType.PERSON))
			possiblePeople.add(card);
		if (card.getCardType().equals(CardType.WEAPON))
			possibleWeapons.add(card);
	}
}
