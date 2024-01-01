package lastpencil;

import lastpencil.player.BotPlayer;
import lastpencil.player.HumanPlayer;
import lastpencil.player.Player;

/**
 * The GameManager class manages the overall game logic for Last Pencil.
 * It handles player management, game loop, and printing game information.
 */
public class GameManager {
    public static Integer MAX_PENCILS_TO_REMOVE = 3;
    private String playerOne = "John";
    private String playerTwo = "Jack";

    /**
     * The singleton instance of the GameManager class.
     */
    private static GameManager INSTANCE;
    private int remainingPencils;

    private Player currentPlayer;

    private GamePrinter gamePrinter;
    private GameInputReader gameInputReader;
    private Player humanPlayer;
    private Player botPlayer;

    /**
     * Gets the singleton instance of the GameManager class.
     *
     * @return The singleton instance of the GameManager class.
     */
    public static GameManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameManager();
        }

        return INSTANCE;
    }

    /**
     * Starts the game by initializing players, setting up the game loop, and printing the initial game state.
     */
    public void startGame() {
        gamePrinter = new GamePrinter();

        gameInputReader = GameInputReader.getInstance();

        gamePrinter.printCurrentTurn();

        gameLoop();
    }

    /**
     * Switches the current player to the other player.
     */
    public void switchPlayer() {
        if (currentPlayer == humanPlayer) {
            currentPlayer = botPlayer;
        } else {
            currentPlayer = humanPlayer;
        }
    }

    /**
     * The game loop that runs until there is a winner.
     */
    private void gameLoop() {
        try {
            while (true) {
                int currentPencils = remainingPencils;
                int removedPencils = currentPlayer.removePencils();

                switchPlayer();


                //Checks if the first player takes all the available pencils in first turn
                if(removedPencils == currentPencils){
                    switchPlayer();
                    gamePrinter.printWinner();
                    break;
                }

                gamePrinter.printCurrentTurn();

                if (remainingPencils <= 1) {
                    currentPlayer.removePencils();
                    gamePrinter.printWinner();
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the players for the game, based on the first player provided.
     * Initializing starts after the first player was read by the input reader
     *
     * @param firstPlayer The player who will take the first turn.
     * @see GameInputReader readPlayerNames()
     */
    public void initializePlayers(Player firstPlayer) {
        if (firstPlayer.getClass() == HumanPlayer.class) {
            setCurrentPlayer(firstPlayer);
            setHumanPlayer(firstPlayer);
            setBotPlayer(new BotPlayer());
            return;
        }

        if (firstPlayer.getClass() == BotPlayer.class) {
            setCurrentPlayer(firstPlayer);
            setBotPlayer(firstPlayer);
            setHumanPlayer(new HumanPlayer());
        }
    }


    //region Getters/Setters
    public int getRemainingPencils() {
        return remainingPencils;
    }

    public void setRemainingPencils(int remainingPencils) {
        this.remainingPencils = remainingPencils;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


    public Player getHumanPlayer() {
        return humanPlayer;
    }

    public void setHumanPlayer(Player humanPlayer) {
        this.humanPlayer = humanPlayer;
    }

    public Player getBotPlayer() {
        return botPlayer;
    }

    public void setBotPlayer(Player botPlayer) {
        this.botPlayer = botPlayer;
    }

    public GameInputReader getGameInputReader() {
        return gameInputReader;
    }

    //endregion

}
