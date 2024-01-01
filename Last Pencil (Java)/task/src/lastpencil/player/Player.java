package lastpencil.player;

import lastpencil.GameManager;


/**
 * The abstract Player class represents a general player in the Last Pencil game.
 * It defines the basic properties and methods common to all players, including player's name and the ability to remove pencils.
 */
public abstract class Player {
    String playerName;
    protected GameManager gameManager;

    public Player() {
        gameManager = GameManager.getInstance();
        setPlayer();
    }

    protected abstract void setPlayer();

    /**
     * Removes a number of pencils from the game based on the player's strategy.
     * This method is overridden by subclasses to implement different player behaviors.
     *
     * @return the amount of pencils which were removed
     */
    public abstract int removePencils();

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
