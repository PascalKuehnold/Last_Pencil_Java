package lastpencil.player;


import lastpencil.GameInputReader;
import lastpencil.GameManager;

import java.util.Random;

/**
 * The BotPlayer class represents a computer opponent in the Last Pencil game.
 * It extends the Player class and implements the removePencils() method to interact with the user for selecting the number of pencils to remove.
 */
public class BotPlayer extends Player{

    public BotPlayer() {
        super();
    }

    @Override
    protected void setPlayer() {
        this.playerName = gameManager.getPlayerTwo();
    }

    /**
     * Removes a number of pencils from the game according to a predefined strategy.
     */
    @Override
    public int removePencils() {
        int currentPencils = this.gameManager.getRemainingPencils();
        int pencilsToRemove = 1;

        if(gameManager.getRemainingPencils() == 1){
            System.out.println(pencilsToRemove);
            this.gameManager.setRemainingPencils(currentPencils - pencilsToRemove);
            return pencilsToRemove;
        }

        if(gameManager.getRemainingPencils() % 4 == 1){
            pencilsToRemove = getRandomNumber();
            System.out.println(pencilsToRemove);
            this.gameManager.setRemainingPencils(currentPencils - pencilsToRemove);
            return pencilsToRemove;
        }

        if(gameManager.getRemainingPencils() % 4 == 0){
            pencilsToRemove = 3;
            System.out.println(pencilsToRemove);
            this.gameManager.setRemainingPencils(currentPencils - pencilsToRemove);
            return pencilsToRemove;
        }

        if(gameManager.getRemainingPencils() % 2 == 1){
            pencilsToRemove = 2;
            System.out.println(pencilsToRemove);
            this.gameManager.setRemainingPencils(currentPencils - pencilsToRemove);
            return pencilsToRemove;
        }

        System.out.println(pencilsToRemove);
        this.gameManager.setRemainingPencils(currentPencils - pencilsToRemove);

        return pencilsToRemove;
    }

    private int getRandomNumber(){
        Random random = new Random();
        return random.nextInt(GameManager.MAX_PENCILS_TO_REMOVE) + 1;
    }
}
