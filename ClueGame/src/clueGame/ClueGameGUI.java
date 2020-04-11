/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ClueGameGUI extends JPanel {
	private static final int MARGIN_SIZE = 10;

	public ClueGameGUI() {
		setLayout(new GridLayout(2,0));
		setUp();
		setVisible(true);
	}

	private void setUp() {
	    // Adding first row
        JPanel panel = createWhoseTurn();
        createButtons(panel);
        add(panel);

        // Adding second row
        panel = createFields();
        add(panel);
    }

	// Creates whose turn is it panel
	public JPanel createWhoseTurn() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Separate panel for the label and text field
		JPanel turnPanel = new JPanel();
		turnPanel.setLayout(new GridBagLayout());

		// Label for current player text field
		JLabel label = new JLabel("Current Turn");
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, MARGIN_SIZE / 2, MARGIN_SIZE);
		turnPanel.add(label, c);

		// Field for showing current player
		JTextField name = new JTextField(15); // not sure if this should be a text field or something else
		name.setEditable(false);
		c.gridy = 1;
		c.insets = new Insets(MARGIN_SIZE / 2, 0, 0, MARGIN_SIZE);
		turnPanel.add(name, c);

		panel.add(turnPanel);
		return panel;
	}

	// Adds button to first row
	public void createButtons(JPanel panel) {
		GridBagConstraints c = new GridBagConstraints();

		JButton nextPlayer = new JButton("Next Player");
		c.insets = new Insets(0, MARGIN_SIZE, 0, MARGIN_SIZE);
		panel.add(nextPlayer, c);

		JButton makeAccusation = new JButton("Make an Accusation");
		c.insets = new Insets(0, MARGIN_SIZE, 0, 0);
		panel.add(makeAccusation, c);
	}

	// Creates bottom row fields
	public JPanel createFields() {
		JPanel rowPanel = new JPanel();

		// Die roll entry
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Roll");
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, label.getFont().getSize()));
		panel.add(label);

		JTextField info = new JTextField(4);
		info.setEditable(false);
		panel.add(info);

		TitledBorder titledBorder = new TitledBorder(new EtchedBorder(), "Die");
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		panel.setBorder(titledBorder);
		rowPanel.add(panel);

		// Guess entry
		panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		label = new JLabel("Guess");
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, label.getFont().getSize()));
		panel.add(label);

		info = new JTextField(25);
		info.setEditable(false);
		panel.add(info);

		titledBorder = new TitledBorder(new EtchedBorder(), "Guess");
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		panel.setBorder(titledBorder);
		rowPanel.add(panel);

		// Response entry
		panel = new JPanel();
		label = new JLabel("Response");
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, label.getFont().getSize()));
		panel.add(label);

		info = new JTextField(15);
		info.setEditable(false);
		panel.add(info);

		titledBorder = new TitledBorder(new EtchedBorder(), "Response");
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		panel.setBorder(titledBorder);
		rowPanel.add(panel);

		return rowPanel;
	}
}