package lastpencil;

import lastpencil.player.BotPlayer;
import lastpencil.player.HumanPlayer;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * The GameInputReader class manages user input for the Last Pencil game.
 * It reads the number of pencils to be used, initializes the players, and handles player input during the game.
 */
public class GameInputReader {

    /**
     * The singleton instance of the GameInputReader class.
     */
    private static GameInputReader INSTANCE;

    /**
     * The GameManager object responsible for managing the overall game logic.
     */
    private final GameManager gameManager;
    private final Scanner scanner;

    /**
     * Gets the singleton instance of the GameInputReader class.
     *
     * @return The singleton instance of the GameInputReader class.
     */
    public static GameInputReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameInputReader();
        }

        return INSTANCE;
    }

    /**
     * Initializes the GameInputReader class and reads the initial game parameters.
     */
    private GameInputReader() {
        gameManager = GameManager.getInstance();
        scanner = new Scanner(System.in);
        startInputReader();
    }

    private void startInputReader() {
        readPencilAmount();
        readPlayerNames();
    }

    /**
     * Reads the number of pencils to be used in the game.
     */
    private void readPencilAmount() {
        System.out.println("How many pencils would you like to use:");
        int pencils;

        while (true) {
            String input = scanner.nextLine();

            try {
                pencils = Integer.parseInt(input);

                if (pencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                } else {
                    gameManager.setRemainingPencils(pencils);
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }
    }

    /**
     * Prompts the user to select the first player from the given options and initializes the game with the selected player.
     */
    private void readPlayerNames() {
        System.out.printf("Who will be the first (%s, %s):\n", gameManager.getPlayerOne(), gameManager.getPlayerTwo());

        String player;

        while (true) {
            try {
                player = scanner.nextLine();

                if (player.equals(gameManager.getPlayerOne())) {
                    gameManager.initializePlayers(new HumanPlayer());
                    break;
                } else if (player.equals(gameManager.getPlayerTwo())) {
                    gameManager.initializePlayers(new BotPlayer());
                    break;
                }
                System.out.println("Choose between 'John' and 'Jack'");

            } catch (Exception e) {
                System.out.println();
            }
        }


    }

    /**
     * Prompts the current player to remove a number of pencils from the game and updates the game state accordingly.
     *
     * @return the amount of pencils to be removed
     * @throws InputMismatchException If the user enters an invalid input, such as a non-numeric value or a value outside the allowed range.
     */
    public int removePencils() {
        int pencilsToRemove = 0;

        do {
            try {
                pencilsToRemove = scanner.nextInt();
                boolean isValidAmount = pencilsToRemove == 1 || pencilsToRemove == 2 || pencilsToRemove == 3;

                if (pencilsToRemove < 0 || pencilsToRemove > GameManager.MAX_PENCILS_TO_REMOVE || !isValidAmount) {
                    System.out.println("Possible values: '1', '2' or '3'");
                } else if (pencilsToRemove > GameManager.getInstance().getRemainingPencils()) {
                    System.out.println("Too many pencils were taken");
                }
            } catch (InputMismatchException e) {
                System.out.println("Possible values: '1', '2' or '3'");
                scanner.nextLine(); // Consume the invalid input
            }
        } while (pencilsToRemove <= 0 || pencilsToRemove > GameManager.MAX_PENCILS_TO_REMOVE || pencilsToRemove > GameManager.getInstance().getRemainingPencils());

        gameManager.setRemainingPencils(gameManager.getRemainingPencils() - pencilsToRemove);

        return pencilsToRemove;
    }

}
