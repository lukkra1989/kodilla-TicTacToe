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
                //Probuj przewidzec co sie stanie gdy komputer postawi w tym miejscu żeton
                if (logicBoard.getToken(i, j) == Player.BLANK) {
                    //Postaw żeton na próbę
                    logicBoard.setToken(i, j, player);
                    //Zobacz co się stanie
                    GameState predictedGameState = logicBoard.calculateGameState();
                    //cofamy próbny ruch na potrzeby kolejnych wykonań pętli
                    logicBoard.setToken(i, j, Player.BLANK);
                    //jesli ruch prowadzi do wygranej to
                    if (predictedGameState == GameState.COMPUTER_WIN) {
                        //pożądany ruch
                        return new BoardIndex(i, j);
                    }
                }
            }
        }
        //W innym wypadku losujemy pole
        while (true) {
            int randomRow = random.nextInt(3);
            int randomColumn = random.nextInt(3);
            if (logicBoard.getToken(randomRow, randomColumn) == Player.BLANK) {
                return new BoardIndex(randomRow, randomColumn);
            }
        }
    }
}
