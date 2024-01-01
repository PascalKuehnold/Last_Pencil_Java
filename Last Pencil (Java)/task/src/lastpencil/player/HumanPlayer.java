package lastpencil.player;

/**
 * The HumanPlayer class represents a human player in the Last Pencil game.
 * It extends the Player class and implements the removePencils() method to interact with the user for selecting the number of pencils to remove.
 */
public class HumanPlayer extends Player{

    public HumanPlayer() {
        super();
    }

    @Override
    protected void setPlayer() {
        this.playerName = gameManager.getPlayerOne();
    }

    /**
     * Asks the user to input the number of pencils to remove and updates the game state accordingly.
     * @see lastpencil.GameInputReader removePencils();
     */
    @Override
    public int removePencils() {
        return gameManager.getGameInputReader().removePencils();
    }
}
