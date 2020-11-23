package tictactoe.logic;

import java.util.Random;

public class MediumAi implements Ai {
    Random random;

    public MediumAi() {
        random = new Random();
    }

    @Override
    public BoardIndex calculateMove(LogicBoard logicBoard, Player player) {
        if (logicBoard.calculateGameState() != GameState.IN_PROGRESS) {
            return null;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (logicBoard.getToken(i, j) == Player.BLANK) {
                    logicBoard.setToken(i, j, player);
                    GameState predictedGameState = logicBoard.calculateGameState();
                    logicBoard.setToken(i, j, Player.BLANK);
                    if (predictedGameState == GameState.COMPUTER_WIN) {
                        return new BoardIndex(i, j);
                    }
                }
            }
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
