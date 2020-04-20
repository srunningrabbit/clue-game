/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/*
ClueGame

Main driver for the game, involves displaying everything and updating everything
 */

public class ClueGame extends JFrame {
    private final static int MARGIN = 80;
    private static Board board = Board.getInstance();

    public ClueGame() {
        setTitle("Clue Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        createMenuBar();
    }

    // Create menu bar on top
    public void createMenuBar() {
        JMenu menu = new JMenu("File");
        JDialog dialog = new JDialog();
        menu.add(createDetectiveNotesItem(dialog));
        menu.add(createFileExitItem());

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(menu);
    }

    // Add Detective Notes option in file drop down
    public JMenuItem createDetectiveNotesItem(JDialog dialog) {
        JMenuItem item = new JMenuItem("Detective Notes");
        class DetectiveNotesListener extends JFrame implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                // Create new detectiveNotes object, add to a dialog box
                DetectiveNotesGUI detectiveNotes = new DetectiveNotesGUI();
                dialog.add(detectiveNotes);
                dialog.setSize(650, 650);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        }
        item.addActionListener(new DetectiveNotesListener());
        return item;
    }

    // Add exit option in file drop down
    public JMenuItem createFileExitItem() {
        JMenuItem item = new JMenuItem("Exit");
        class MenuItemListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        item.addActionListener(new MenuItemListener());
        return item;
    }

    public static JPanel displayPlayerCards() {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(3, 0));

        ArrayList<String> cardNames = Board.getInstance().getHumanPlayer().getCardNames();

        // Person card(s)
        JPanel panel = new JPanel();
        JTextArea info = new JTextArea(5, 15);
        info.setText(cardNames.get(0));
        info.setEditable(false);
        panel.add(info);
        TitledBorder titledBorder = new TitledBorder(new EtchedBorder(), "People");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(titledBorder);
        playerPanel.add(panel);

        // Weapon card(s)
        panel = new JPanel();
        info = new JTextArea(5, 15);
        info.setText(cardNames.get(1));
        info.setEditable(false);
        panel.add(info);
        titledBorder = new TitledBorder(new EtchedBorder(), "Weapons");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(titledBorder);
        playerPanel.add(panel);

        // Room card(s)
        panel = new JPanel();
        info = new JTextArea(5, 15);
        info.setText(cardNames.get(2));
        info.setEditable(false);
        panel.add(info);
        titledBorder = new TitledBorder(new EtchedBorder(), "Rooms");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(titledBorder);
        playerPanel.add(panel);

        titledBorder = new TitledBorder(new EtchedBorder(), "My Cards");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        playerPanel.setBorder(titledBorder);

        return playerPanel;
    }
    
    public static void showStartingMessage() {
    	JFrame frame = new JFrame("Initial Message");
    	String playerName = Board.getInstance().getHumanPlayer().getName();
    	JOptionPane.showMessageDialog(frame, "You are " + playerName + ", press Next Player to begin play", "Welcome to Clue!", JOptionPane.INFORMATION_MESSAGE);
    }

    public static int dieRoll() {
        return (int) (6.0 * Math.random()) + 1;
    }

    public static void main(String[] args) {
        ClueGame game = new ClueGame();

        // Initialize game
        board.setConfigFiles("data/ClueLayout.csv", "data/ClueRooms.txt","data/PlayerLegend.txt", "data/WeaponLegend.txt");
        board.initialize();
        game.add(board, BorderLayout.CENTER);

        // Grab bottom game interface
        ClueGameGUI gui = new ClueGameGUI();
        game.add(gui, BorderLayout.PAGE_END);

        // Human player card display
        JPanel cardsPanel = displayPlayerCards();
        game.add(displayPlayerCards(), BorderLayout.LINE_END);

        // Set size of window depending on content sizes
        int width = gui.getPreferredSize().width + MARGIN;
        if (board.getWidth() + cardsPanel.getPreferredSize().width > width)
            width += (board.getWidth() + cardsPanel.getPreferredSize().width) - width;
        int height = board.getHeight() + MARGIN + gui.getPreferredSize().height;
        game.setSize(width, height);

        game.setLocationRelativeTo(null);
        game.setVisible(true);
        showStartingMessage();
    }
}
