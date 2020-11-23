package tictactoe.logic;

import java.util.Random;

public class RandomAi implements Ai {

    private Random random;

    public RandomAi() {
        random = new Random();
    }

    @Override
    public BoardIndex calculateMove(LogicBoard logicBoard, Player player) {
        if (logicBoard.calculateGameState() != GameState.IN_PROGRESS) {
            return null;
        }
        while (true) {
            int randomRow = random.nextInt(3);
            int randomColumn = random.nextInt(3);
            if (logicBoard.getToken(randomRow, randomColumn) == Player.BLANK) {
                return new BoardIndex(randomRow, randomColumn);
            }
        }
    }
}
