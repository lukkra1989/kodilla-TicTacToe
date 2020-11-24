package tictactoe.logic;

import tictactoe.Board;
import tictactoe.FieldValue;
import tictactoe.Tile;

public class JavaFxBoardToLogicBoardConverter {

    public LogicBoard convert(Board board, FieldValue humanPlayer) {
        LogicBoard logicBoard = new LogicBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile currentTile = board.fields[i][j];
                Player convertedPlayer;
                if (currentTile.getFieldValue() == humanPlayer) {
                    convertedPlayer = Player.HUMAN;
                } else if (currentTile.getFieldValue() == FieldValue.EMPTY) {
                    convertedPlayer = Player.BLANK;
                } else {
                    convertedPlayer = Player.COMPUTER;
                }
                logicBoard.placeToken(i, j, convertedPlayer);
            }
        }
        return logicBoard;
    }
}
