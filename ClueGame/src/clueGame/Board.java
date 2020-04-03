/*
 * Shania Jo RunningRabbit and Amira Ramirez Gonzalez
 */

package clueGame;

import java.awt.Color;
import java.io.*;
import java.util.*;

/*
Game board that contains individual board cells
 */
public class Board {
    public final static int MAX_BOARD_SIZE = 50;
    private int numRows;
    private int numColumns;
    private String boardConfigFile;
    private String roomConfigFile;
    private String playerConfigFile;
    private String weaponConfigFile;
    private BoardCell[][] gameBoard;
    private Map<Character, String> legend;
    private Map<BoardCell, Set<BoardCell>> adjacencyMatrix;
    private Set<BoardCell> targets;
    private Set<BoardCell> visited;
    private int originalPathLength;
    private ArrayList<Player> players;
    private ArrayList<String> weapons;
    private ArrayList<String> rooms;
    private ArrayList<Card> deck;
    private Solution solution;

    // Variable used for singleton pattern
    private static Board theInstance = new Board();

    // Constructor is private to ensure only one can be created
    private Board() {}

    // This method returns the only Board
    public static Board getInstance() {
        return theInstance;
    }
    
    // Getters //
    
    public Map<Character, String> getLegend() {		// Returns room legend
        return legend;
    }

    public int getNumRows() {						// Returns number of rows
        return numRows;
    }

    public int getNumColumns() {					// Returns number of columns
        return numColumns;
    }

    public BoardCell getCellAt(int row, int col) {	// Returns cell at corresponding location
        return gameBoard[row][col];
    }

    private int getBoardLength() {                    // Return length of board (number of columns)
        return gameBoard.length;
    }

    private int getBoardWidth() {                     // Return width of board (number of rows)
        return gameBoard[0].length;
    }

    public Set<BoardCell> getAdjList(int row, int col) {  // Returns adjacency list for particular cell
        return adjacencyMatrix.get(getCellAt(row, col));
    }

    public Set<BoardCell> getTargets() {
        return targets;
    }
        
    public ArrayList<Player> getPlayers() {					// Returns player list (for testing)		
    	return players;
    }
    
    public int getNumPlayers() {							// Returns numbers of players in the game
    	return players.size();		
    }

    public ArrayList<String> getWeapons() {                 // Returns weapon list
        return weapons;
    }

    public ArrayList<String> getRooms() {                   // Returns room list
        return rooms;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
    
    //			//

    public void initialize() {
        try {
            loadRoomConfig();
            loadBoardConfig();
            loadPlayerConfig();
            loadWeaponConfig();
        } catch (BadConfigFormatException | IOException e) {
            System.err.println(e.getMessage());
        }
        calcAdjacencies();
        createDeck();
        shuffleDeck();
        shuffleDeck();
        dealCards();
    }

    // Set both the board config file and room config file
    public void setConfigFiles(String boardLayout, String roomLegend, String playerLegend, String weaponLegend) {
        boardConfigFile = boardLayout;
        roomConfigFile = roomLegend;
        playerConfigFile = playerLegend;
        weaponConfigFile = weaponLegend;
    }
    
    // Load all files?
    public void loadConfigFiles() {
    	// TODO read in player and weapons config files
    }
    
    // Read in player into player Set
    public void loadPlayerConfig() throws BadConfigFormatException, IOException {
    	players = new ArrayList<Player>();
    	File playerConfig = new File(playerConfigFile);
        Scanner fileInput = new Scanner(playerConfig);

        while (fileInput.hasNextLine()) {
            String[] player = fileInput.nextLine().split(", ");
            if (!player[2].equals("Human") && !player[2].equals("Computer"))
                throw new BadConfigFormatException("Error: Player type is not Human or Computer");
            Player p;
            String name = player[0];
            // Color setter
            Color color = null;
            switch(player[1]) {
                case "Red":
                    color = Color.RED;
                    break;
                case "Orange":
                    color = Color.ORANGE;
                    break;
                case "Yellow":
                    color = Color.YELLOW;
                    break;
                case "Green":
                    color = Color.GREEN;
                    break;
                case "Blue":
                    color = Color.BLUE;
                    break;
                case "Pink":
                    color = Color.PINK;
                    break;
                default:
                    break;
            }
            // Location
            String[] coordinates = player[3].split(";");
            int col = Integer.parseInt(coordinates[0]);
            int row = Integer.parseInt(coordinates[1]);
            // If human, initialize human
            if (player[2].equals("Human")) {
            	p = new HumanPlayer(name, col, row, color);
            } else {
            	p = new ComputerPlayer(name, col, row, color);
            }
            players.add(p);
        }
        fileInput.close();
    }

    // Read in weapons
    public void loadWeaponConfig() throws IOException, BadConfigFormatException {
        weapons = new ArrayList<>();
        File weaponConfig = new File(weaponConfigFile);
        Scanner fileInput = new Scanner(weaponConfig);

        while (fileInput.hasNextLine()) {
            weapons.add(fileInput.nextLine());
        }
        fileInput.close();
    }

    // Load room legend configuration
    public void loadRoomConfig() throws IOException, BadConfigFormatException {
        legend = new HashMap<>();
        rooms = new ArrayList<>();
        File roomConfig = new File(roomConfigFile);
        Scanner fileInput = new Scanner(roomConfig);

        while (fileInput.hasNextLine()) {
            String[] legendRow = fileInput.nextLine().split(", ");
            // Check if room type is correct
            if (!legendRow[2].equals("Card") && !legendRow[2].equals("Other"))
                throw new BadConfigFormatException("Error: Room type is not Card or Other");
            char initial = legendRow[0].charAt(0);
            String roomName = legendRow[1];
            String roomType = legendRow[2];
            legend.put(initial, roomName);
            if (roomType.equals("Card"))
                rooms.add(roomName);
        }
        fileInput.close();
    }

    // Load board layout configuration
    public void loadBoardConfig() throws IOException, BadConfigFormatException {
        File boardConfig = new File(boardConfigFile);
        Scanner fileInput = new Scanner(boardConfig);

        // Find number of board rows
        numRows = findFileLength(boardConfig);
        if (numRows > MAX_BOARD_SIZE)
            throw new BadConfigFormatException("Error: Number of rows exceeds maximum board size of " + MAX_BOARD_SIZE);
        BoardCell[][] board = new BoardCell[numRows][];

        for (int row = 0; row < numRows; row++) {
            String[] boardRow;
            if (fileInput.hasNextLine()) {
                boardRow = fileInput.nextLine().split(",");
            } else break;

            // Find number of board columns or throw exception if columns are mismatched
            if (row > 0 && boardRow.length != numColumns)
                throw new BadConfigFormatException("Error: Rows are not all the same length");
            numColumns = boardRow.length;
            if (numColumns > MAX_BOARD_SIZE)
                throw new BadConfigFormatException("Error: Number of columns exceeds maximum board size of " + MAX_BOARD_SIZE);
            BoardCell[] boardCellArray = new BoardCell[numColumns];

            for (int col = 0; col < boardRow.length; col++) {
                BoardCell cell;
                char initial = boardRow[col].charAt(0);
                // Check if room is valid according to legend
                if (!legend.containsKey(initial))
                    throw new BadConfigFormatException("Error: Room not in legend");

                if (boardRow[col].length() > 1) {
                    // Add a direction if it is a door
                    DoorDirection doorDirection = DoorDirection.NONE;
                    switch (boardRow[col].charAt(1)) {
                        case 'U':
                            doorDirection = DoorDirection.UP;
                            break;
                        case 'D':
                            doorDirection = DoorDirection.DOWN;
                            break;
                        case 'L':
                            doorDirection = DoorDirection.LEFT;
                            break;
                        case 'R':
                            doorDirection = DoorDirection.RIGHT;
                            break;
                        case 'N':
                        default:
                            break;
                    }
                    // Add cell with second character
                    cell = new BoardCell(row, col, initial, doorDirection);
                } else {
                    // Add cell without second character
                    cell = new BoardCell(row, col, initial, DoorDirection.NONE);
                }
                boardCellArray[col] = cell;
            }
            board[row] = boardCellArray;
        }
        this.gameBoard = board;
        fileInput.close();
    }

    // Calculate adjacencies around every cell
    private void calcAdjacencies() {
        adjacencyMatrix = new HashMap<>();
        for (BoardCell[] boardCell : gameBoard) {
            for (BoardCell cell : boardCell) {
                // If cell is a room, the cell has no adjacencies since you can't move in a room
                if (cell.isRoom()) {
                    adjacencyMatrix.put(cell, Collections.emptySet());
                    continue;
                }
                int row = cell.row;
                int col = cell.col;
                Set<BoardCell> adjacentCells = new HashSet<BoardCell>();
                BoardCell adjCell;
                if (row - 1 >= 0) {
                    adjCell = getCellAt(row - 1, col);
                    if (cell.isDoorway()) {
                        if (adjCell.isWalkway() && cell.getDoorDirection().equals(DoorDirection.UP))
                            adjacentCells.add(adjCell);
                    } else if (cell.isWalkway()) {
                        if (adjCell.isWalkway() || adjCell.isDoorway() && adjCell.getDoorDirection().equals(DoorDirection.DOWN))
                            adjacentCells.add(adjCell);
                    }
                }
                if (row + 1 < getBoardLength()) {
                    adjCell = getCellAt(row + 1, col);
                    if (cell.isDoorway()) {
                        if (adjCell.isWalkway() && cell.getDoorDirection().equals(DoorDirection.DOWN))
                            adjacentCells.add(adjCell);
                    } else if (cell.isWalkway()) {
                        if (adjCell.isWalkway() || adjCell.isDoorway()  && adjCell.getDoorDirection().equals(DoorDirection.UP))
                            adjacentCells.add(adjCell);
                    }
                }
                if (col - 1 >= 0) {
                    adjCell = getCellAt(row, col - 1);
                    if (cell.isDoorway()) {
                        if (adjCell.isWalkway() && cell.getDoorDirection().equals(DoorDirection.LEFT))
                            adjacentCells.add(adjCell);
                    } else if (cell.isWalkway()) {
                        if (adjCell.isWalkway() || adjCell.isDoorway()  && adjCell.getDoorDirection().equals(DoorDirection.RIGHT))
                            adjacentCells.add(adjCell);
                    }
                }
                if (col + 1 < getBoardWidth()) {
                    adjCell = getCellAt(row, col + 1);
                    if (cell.isDoorway()) {
                        if (adjCell.isWalkway() && cell.getDoorDirection().equals(DoorDirection.RIGHT))
                            adjacentCells.add(adjCell);
                    } else if (cell.isWalkway()) {
                        if (adjCell.isWalkway() || adjCell.isDoorway() && adjCell.getDoorDirection().equals(DoorDirection.LEFT))
                            adjacentCells.add(adjCell);
                    }
                }
                adjacencyMatrix.put(cell, adjacentCells);
            }
        }
    }

    // Reset targets/visited before each run
    public void calcTargets(int row, int col, int pathLength) {
        targets = new HashSet<>();
        visited = new HashSet<>();
        originalPathLength = pathLength;
        calcTargetHelper(row, col, pathLength);
        targets.remove(getCellAt(row, col));
    }

    // Calculate targets within length of path
    private void calcTargetHelper(int row, int col, int pathLength) {
        visited.add(getCellAt(row, col));
        for (BoardCell cell : getAdjList(row, col)) {
            if (isDeadEnd(cell.row, cell.col) && originalPathLength > 2 + pathLength) return;
            if (!visited.contains(cell)) {
                if (pathLength == 1 || cell.isDoorway()) {
                    targets.add(cell);
                } else {
                    calcTargetHelper(cell.row, cell.col, pathLength - 1);
                }
            }
        }
        visited.clear();
    }
    
    /**
     * getFileLength
     * @param   file
     * @return  Returns number of lines in file which is ultimately the number of rows in board
     * @throws  FileNotFoundException
     */
    private int findFileLength(File file) throws FileNotFoundException {	// Returns length of file
        Scanner fileInput = new Scanner(file);
        int lines = 0;
        while (fileInput.hasNextLine()) {
            lines++;
            fileInput.nextLine();
        }
        fileInput.close();
        return lines;
    }
    
    /**
     * isDeadEnd(row, col)
     * @param   row
     * @param   col
     * @return  Returns if cell at row, col is a dead end meaning only 1 adjacency and not a doorway
     */
    private boolean isDeadEnd(int row, int col) {
        return getAdjList(row, col).size() == 1 && !getCellAt(row, col).isDoorway();
    }

    // Create a new deck of cards with each type of card
    public void createDeck() {
        deck = new ArrayList<Card>();

        for (Player player : players) {
            deck.add(new Card(player.getName(), CardType.PERSON));
        }
        for (String weapon : weapons) {
            deck.add(new Card(weapon, CardType.WEAPON));
        }
        for (String room : rooms) {
            deck.add(new Card(room, CardType.ROOM));
        }
    }
    
    // Shuffle the deck
    public void shuffleDeck() {
    	ArrayList<Card> tempDeck = new ArrayList<Card>();
    	Random random = new Random();
    	for(int i = deck.size(); i > 0; i--) {
    		int index = random.nextInt(deck.size());
    		tempDeck.add(deck.get(index));
    		deck.remove(index);    		
    	}
    	deck = tempDeck;
    }
    
    // Deals the deck cards
    public void dealCards() {
    	int numPlayers = getNumPlayers();
    	for (int i = 0; i < deck.size(); i++) {
    		Card card = deck.get(i);
    		players.get(i % numPlayers).addCard(card);
    	}
    }

    // Pick out an answer
    public void selectAnswer() {
    	// TODO select 3 cards to be the solution
    }
    
    // Set Solution TESTING PURPOSES ONLY
    public void setAnswer(Solution solution) {
    	// TODO sets answer
    }
    
    // Handle a suggestion
    public Card handleSuggestion() {
    	// TODO complete
    	return null;
    }
    
    // Handle accusation making
    public boolean checkAccusation(Solution accusation) {
    	// TODO complete
    	return false;
    }
}