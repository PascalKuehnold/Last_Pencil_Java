package lastpencil;

public class GamePrinter {
    String pencil = "|";
    GameManager gameManager;

    public GamePrinter() {
        gameManager = GameManager.getInstance();
    }

    public void printCurrentTurn() {
        printPencils();
        printTurn();
    }

    private void printPencils() {
        System.out.println(pencil.repeat(gameManager.getRemainingPencils()));
    }


    private void printTurn() {
        System.out.println(gameManager.getCurrentPlayer().getPlayerName() + "'s turn!");
    }

    public void printWinner() {
        if(gameManager.getCurrentPlayer() == gameManager.getBotPlayer() && gameManager.getRemainingPencils() <= 1){
            System.out.println(gameManager.getHumanPlayer().getPlayerName() + " won!");
            return;
        }

        System.out.println(gameManager.getBotPlayer().getPlayerName() + " won!");
    }

}
