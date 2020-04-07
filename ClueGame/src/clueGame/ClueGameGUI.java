/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ClueGameGUI extends JFrame {

	public ClueGameGUI() {
		setTitle("Clue Game");
		setSize(750,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2,0));
		setLocationRelativeTo(null);
	}

	// Creates whose turn is it panel
	public JPanel createWhoseTurn() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Label for current player text field
		JLabel label = new JLabel("Current Turn");
		c.gridx = 0;
		c.insets = new Insets(0, 0, 5, 10);
		panel.add(label, c);

		// Field for showing current player
		JTextField name = new JTextField(15); // not sure if this should be a text field or something else
		name.setEditable(false);
		c.gridx = 0;
		c.insets = new Insets(5, 0, 0, 10);
		panel.add(name, c);

		return panel;
	}

	// Adds button to first row
	public void createButtons(JPanel panel) {
		GridBagConstraints c = new GridBagConstraints();

		JButton nextPlayer = new JButton("Next Player");
		c.insets = new Insets(0, 10, 0, 5);
		panel.add(nextPlayer, c);

		JButton makeAccusation = new JButton("Make an Accusation");
		c.insets = new Insets(0, 5, 0, 0);
		panel.add(makeAccusation, c);
	}

	// Creates bottom row fields
	public JPanel createFields() {
		JPanel rowPanel = new JPanel();

		// Die roll entry
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Roll");
		JTextField info = new JTextField(4);
		info.setEditable(false);
		panel.add(label);
		panel.add(info);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		rowPanel.add(panel);

		// Guess entry
		panel = new JPanel();
		label = new JLabel("Guess");
		info = new JTextField(20);
		info.setEditable(false);
		panel.add(label);
		panel.add(info);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		rowPanel.add(panel);

		// Response entry
		panel = new JPanel();
		label = new JLabel("Response");
		info = new JTextField(15);
		info.setEditable(false);
		panel.add(label);
		panel.add(info);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		rowPanel.add(panel);

		return rowPanel;
	}

	public static void main(String[] args) {
		ClueGameGUI gui = new ClueGameGUI();

		// Adding first row
		JPanel panel = gui.createWhoseTurn();
		gui.createButtons(panel);
		gui.add(panel);

		// Adding second row
		panel = gui.createFields();
		gui.add(panel);

		gui.setVisible(true);
	}
}