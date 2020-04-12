/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

/*
DetectiveNotesGUI

A panel for displaying notes that can be used by the human player if they choose
 */

public class DetectiveNotesGUI extends JPanel {
    public DetectiveNotesGUI() {
        setLayout(new GridLayout(3, 0));
        createPeopleRow();
        createWeaponRow();
        createRoomRow();
        setVisible(true);
    }

    // Create the row about people and which guess for a person
    public void createPeopleRow() {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new GridLayout(1, 0));

        JPanel personGuess = new JPanel();
        JComboBox guess = new JComboBox();

        JPanel people = new JPanel();
        people.setLayout(new GridLayout(0, 2));
        JCheckBox checkBox;
        // Loop through players to add as checkboxes and options to drop down menu
        ArrayList<String> playerNames = Board.getInstance().getPlayerNames();
        for (String name : playerNames) {
            checkBox = new JCheckBox(name);
            people.add(checkBox);
            guess.addItem(name);
        }

        TitledBorder titledBorder = new TitledBorder(new EtchedBorder(), "People");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        people.setBorder(titledBorder);
        titledBorder = new TitledBorder(new EtchedBorder(), "Person Guess");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        personGuess.setBorder(titledBorder);

        personGuess.add(guess);
        rowPanel.add(people);
        rowPanel.add(personGuess);

        add(rowPanel);
    }

    // Create the row about weapons and which guess for a weapon
    public void createWeaponRow() {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new GridLayout(1, 0));

        JPanel weaponGuess = new JPanel();
        JComboBox guess = new JComboBox();

        JPanel weapons = new JPanel();
        weapons.setLayout(new GridLayout(0, 2));
        JCheckBox checkBox;
        // Loop through weapons to add as checkboxes and options to drop down menu
        ArrayList<String> weaponNames = Board.getInstance().getWeapons();
        for (String name : weaponNames) {
            checkBox = new JCheckBox(name);
            weapons.add(checkBox);
            guess.addItem(name);
        }

        TitledBorder titledBorder = new TitledBorder(new EtchedBorder(), "Weapons");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        weapons.setBorder(titledBorder);
        titledBorder = new TitledBorder(new EtchedBorder(), "Weapon Guess");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        weaponGuess.setBorder(titledBorder);

        weaponGuess.add(guess);
        rowPanel.add(weapons);
        rowPanel.add(weaponGuess);

        add(rowPanel);
    }

    // Create the row about rooms and which guess for a room
    public void createRoomRow() {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new GridLayout(1, 0));

        JPanel roomGuess = new JPanel();
        JComboBox guess = new JComboBox();

        JPanel rooms = new JPanel();
        rooms.setLayout(new GridLayout(0, 2));
        JCheckBox checkBox;
        // Loop through rooms to add as checkboxes and options to drop down menu
        ArrayList<String> roomNames = Board.getInstance().getRooms();
        for (String name : roomNames) {
            checkBox = new JCheckBox(name);
            rooms.add(checkBox);
            guess.addItem(name);
        }

        TitledBorder titledBorder = new TitledBorder(new EtchedBorder(), "Rooms");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        rooms.setBorder(titledBorder);
        titledBorder = new TitledBorder(new EtchedBorder(), "Room Guess");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        roomGuess.setBorder(titledBorder);

        roomGuess.add(guess);
        rowPanel.add(rooms);
        rowPanel.add(roomGuess);

        add(rowPanel);
    }
}
