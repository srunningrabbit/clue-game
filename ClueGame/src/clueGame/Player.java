package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
	private String playerName;
	private int col;
	private int row;
	private Color color;
	private ArrayList<Card> hand;
	
	public Player(String name, int row, int col, Color color) {
		this.playerName = name;
		this.row = row;
		this.col = col;
		this.color = color;
		this.hand = new ArrayList<Card>(); 
	}
	
	public Card disproveSuggestion(Solution suggestion) {
		return null;
	}
	
	//Getters for testing purposes
	
	public String getName() {
		return playerName;
	}
	
	public int getColumn() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	public Color getColor() {
		return color;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	public Solution getSuggestion() {
		return null; //not sure if will use computerPlayer's overriden function (made me make one in Player class)
	}

	public void createSuggestion() {
		//not sure if will use computerPlayer's overriden function (made me make one in Player class)
	}

}
