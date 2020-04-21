/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/*
ClueGameGUI

Displays the control panel to the game, buttons that allow the game to progress
 */

public class ClueGameGUI extends JPanel implements MouseListener {
	private static final int MARGIN_SIZE = 10;
	private Board board;
	private JTextField currentPlayerName;
	private JTextField dieRoll;
	private boolean canAdvanceTurn = true;

	public ClueGameGUI() {
		board = Board.getInstance();
		setLayout(new GridLayout(2,0));
		setUp();
		setVisible(true);
		board.addMouseListener(this);
		addMouseListener(this);
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
	private JPanel createWhoseTurn() {
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
		currentPlayerName = name;
		c.gridy = 1;
		c.insets = new Insets(MARGIN_SIZE / 2, 0, 0, MARGIN_SIZE);
		turnPanel.add(name, c);

		panel.add(turnPanel);
		return panel;
	}

	// Adds buttons to first row
	private void createButtons(JPanel panel) {
		GridBagConstraints c = new GridBagConstraints();

		JButton nextPlayer = new JButton("Next Player");
		nextPlayer.addActionListener(new ActionListener() {	// Handles next player functionality
			public void actionPerformed(ActionEvent e) {
				if (!canAdvanceTurn) {
					JFrame frame = new JFrame("Cannot Advance Turn");
					JOptionPane.showMessageDialog(
							frame,
							"You need to complete your turn before moving on.",
							"Cannot Advance Turn",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int roll = dieRoll();
				board.setDieRoll(roll);
				dieRoll.setText(Integer.toString(roll));
				canAdvanceTurn = false; // Turns true when player moves
				board.nextPlayer();
				board.hasMoved = false;
				board.repaint();
				Player player = board.getCurrentPlayer();
				currentPlayerName.setText(player.getName());
				if (player instanceof HumanPlayer) {
					// show targets ...
				} else if (player instanceof ComputerPlayer) {
					((ComputerPlayer) player).makeMove();
					board.repaint();
					if (board.getCellAt(player.getRow(), player.getColumn()).isDoorway()) {
						((ComputerPlayer) player).createSuggestion();
					}
					canAdvanceTurn = true;
				}

			}
		}
		);
		c.insets = new Insets(0, MARGIN_SIZE, 0, MARGIN_SIZE);
		panel.add(nextPlayer, c);

		JButton makeAccusation = new JButton("Make an Accusation");
		c.insets = new Insets(0, MARGIN_SIZE, 0, 0);
		panel.add(makeAccusation, c);
	}

	public void createSuggestionGUI() {
		Player player = board.getCurrentPlayer();
		if (player instanceof HumanPlayer) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(0, 1));
			String selectedPerson = "";
			String selectedWeapon = "";
			String selectedRoom = board.getCurrentPlayer().getCurrentRoomName();

			// Selection of people
			JComboBox people = new JComboBox();
			for (Player person : board.getPlayers()) {
				people.addItem(person.getName());
			}
			panel.add(people);

			// Selection of weapons
			JComboBox weapons = new JComboBox();
			for (String weapon : board.getWeapons()) {
				weapons.addItem(weapon);
			}
			panel.add(weapons);

			// Selection of room, only one option which is room player is in
			JComboBox rooms = new JComboBox();
			rooms.addItem(board.getCurrentPlayer().getCurrentRoomName());
			rooms.setEditable(false);
			panel.add(rooms);

			// Show dialog with drop down boxes for options
			JOptionPane.showMessageDialog(null, panel, "Create Suggestion", JOptionPane.QUESTION_MESSAGE);
			selectedPerson = (String) people.getSelectedItem();
			selectedWeapon = (String) weapons.getSelectedItem();

			Solution suggestion = new Solution(selectedPerson, selectedWeapon, selectedRoom);
			board.handleSuggestion(suggestion, player);
		}
	}

	// Creates bottom row fields
	private JPanel createFields() {
		JPanel rowPanel = new JPanel();

		// Die roll entry
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Roll");
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, label.getFont().getSize()));
		panel.add(label);

		JTextField info = new JTextField(4);
		info.setEditable(false);
		dieRoll = info;
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

	private static int dieRoll() {
		return (int) (6.0 * Math.random()) + 1;
	}

	// When mouse is clicked, method checks if that click is on a valid target
	// If valid, player can move to that spot
	@Override
	public void mouseClicked(MouseEvent e) {
		int cellSize = BoardCell.getCellSize();
		boolean clickedTarget = false;
		for (BoardCell target : board.getTargets()) {
			if (target.col * cellSize < e.getX() && e.getX() < target.col * cellSize + cellSize && !board.hasMoved) {
				if (target.row * cellSize < e.getY() && e.getY() < target.row * cellSize + cellSize) {
					clickedTarget = true;
					board.getHumanPlayer().setRow(target.row);
					board.getHumanPlayer().setCol(target.col);
					board.hasMoved = true;
					board.repaint();
					if (board.getCurrentPlayer() instanceof HumanPlayer && board.getCellAt(target.row, target.col).isDoorway()) {
						createSuggestionGUI();
					}
					canAdvanceTurn = true;
					break;
				}
			}
		}
		// Error message is square is selected that is not a target
		if (!clickedTarget && board.getCurrentPlayer() instanceof HumanPlayer && !board.hasMoved) {
			JFrame frame = new JFrame("Target Selection Error");
			JOptionPane.showMessageDialog(
					frame,
					"You need to select a target location highlighted in cyan.",
					"Target Selection Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}