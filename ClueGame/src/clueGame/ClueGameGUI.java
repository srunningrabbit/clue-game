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
		currentPlayerName = name;
		c.gridy = 1;
		c.insets = new Insets(MARGIN_SIZE / 2, 0, 0, MARGIN_SIZE);
		turnPanel.add(name, c);

		panel.add(turnPanel);
		return panel;
	}

	// Adds buttons to first row
	public void createButtons(JPanel panel) {
		GridBagConstraints c = new GridBagConstraints();

		JButton nextPlayer = new JButton("Next Player");
		nextPlayer.addActionListener(new ActionListener() {	// Handles next player functionality
			public void actionPerformed(ActionEvent e) {
				if (!canAdvanceTurn) {
					//could add an error message here
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

	public static int dieRoll() {
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
					canAdvanceTurn = true;
					break;
				}
			}
		}
		// Error message is square is selected that is not a target
		if (!clickedTarget && board.getCurrentPlayer() instanceof HumanPlayer) {
			JFrame frame = new JFrame("Target Selection Error");
			JOptionPane.showMessageDialog(frame, "You need to select a target location highlighted in cyan.", "Target Selection Error", JOptionPane.INFORMATION_MESSAGE);
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