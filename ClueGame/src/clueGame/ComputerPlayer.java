package clueGame;

import java.awt.Color;
import java.util.Set;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(String name, int col, int row, Color color) {
		super(name, col, row, color);
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		return null;
	}
	
	public void makeAccusation() {
		
	}
	
	public void createSuggestiong() {
		
	}
}
