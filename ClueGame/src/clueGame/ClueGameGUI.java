package clueGame;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ClueGameGUI extends JFrame{
	
	public ClueGameGUI() {
		setTitle("Clue Game");
		setSize(600,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2,0));
		
	}
	
	//Creates whose turn is it panel
	public JPanel createWhoseTurn() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout (1,2));
		JLabel label = new JLabel("Current Turn");
		JTextField name = new JTextField(20); // not sure if this should be a text field or something else
		panel.add(label);
		panel.add(name);
		return panel;
	}
	
	// Adds button to first row
	public void createButtons(JPanel panel) {
		//JPanel panel = new JPanel();
		JButton nextPlayer = new JButton("Next Player");
		JButton makeAccusation = new JButton("Make an Accusation");
		panel.add(nextPlayer);
		panel.add(makeAccusation);		
		//return panel;
	}
	
	// Creates bottom row fields
	public JPanel createFields() {
		JPanel rowPanel = new JPanel();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout (2,3));
		JLabel label = new JLabel("Roll");
		JTextField info = new JTextField(4);
		panel.add(label);
		panel.add(info);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Die"));
		rowPanel.add(panel);
		
		panel = new JPanel();
		label = new JLabel("Guess");
		info = new JTextField(20);
		panel.add(label);
		panel.add(info);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		rowPanel.add(panel);
		
		panel = new JPanel();
		label = new JLabel("Response");
		info = new JTextField(20);
		panel.add(label);
		panel.add(info);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
		rowPanel.add(panel);
		
		return rowPanel;
	}
	

	public static void main(String[] args) {
		ClueGameGUI gui = new ClueGameGUI();
		gui.setVisible(true);
		
		// Adding first row
		JPanel panel = gui.createWhoseTurn();
		gui.createButtons(panel);
		gui.add(panel);
		// Adding second row
		panel = gui.createFields();
		gui.add(panel);

	}

}
