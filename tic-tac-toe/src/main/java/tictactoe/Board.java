package tictactoe;

import tictactoe.logic.Ai;

public class Board {

    public Tile[][] fields = new Tile[3][3];

    public Board(Ai ai) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile(FieldValue.EMPTY, ai);
                tile.setTranslateX(i * 200);
                tile.setTranslateY(j * 200);
                fields[i][j] = tile;
            }
        }
    }
}