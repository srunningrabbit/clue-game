/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import java.awt.*;
import java.util.ArrayList;

/*
Player

A generic player in the game
 */

public class Player {
	private String playerName;
	protected int row;
	protected int col;
	private Color color;
	private ArrayList<Card> hand;
	private int dieRoll;
	private Solution suggestion;
	
	public Player(String name, int row, int col, Color color) {
		this.playerName = name;
		this.row = row;
		this.col = col;
		this.color = color;
		this.hand = new ArrayList<Card>(); 
	}
	
	/*
	Getters and setters, including those for testing purposes
	 */
	
	public String getName() {
		return playerName;
	}

	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return col;
	}
	
	public Color getColor() {
		return color;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getDieRoll() {
		return dieRoll;
	}

	public void setDieRoll(int dieRoll) {
		this.dieRoll = dieRoll;
	}

	public String getCurrentRoomName() {
		return Board.getInstance().getLegend().get(Board.getInstance().getCellAt(row, col).getInitial());
	}

	public Solution getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(Solution suggestion) {
		this.suggestion = suggestion;
	}

	/*
	Methods
	 */

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(col * BoardCell.getCellSize(),
				row * BoardCell.getCellSize(),
				BoardCell.getCellSize() - 1,
				BoardCell.getCellSize() - 1);
	}

	public Card disproveSuggestion(Solution suggestion) {
		for (Card card : hand) {
			if (card == null) continue;
			if (card.getCardName().equals(suggestion.getPerson()) || card.getCardName().equals(suggestion.getWeapon()) || card.getCardName().equals(suggestion.getRoom())) {
				return card;
			}
		}
		return null;
	}
}
