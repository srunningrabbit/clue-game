package clueGame;

import java.awt.Color;

public class Player {
	private String playerName;
	private int col;
	private int row;
	private Color color;
	
	public Player(String name, int col, int row, Color color) {
		this.playerName = name;
		this.col = col;
		this.row = row;
		this.color = color;
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

}
