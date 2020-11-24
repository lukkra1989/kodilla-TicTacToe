package tictactoe.logic;

public class LogicBoard {

    private Player grid[][];

    public LogicBoard() {
        grid = new Player[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = Player.BLANK;
            }
        }
    }

    public boolean placeToken(int i, int j, Player player) {
        if (grid[i][j] != Player.BLANK) {
            return false;
        }
        grid[i][j] = player;
        return true;
    }

    public void clearToken(int i, int j){
        grid[i][j]=Player.BLANK;
    }

    public Player getToken(int i, int j) {
        return grid[i][j];
    }

    public GameState calculateGameState() {
        for (int y = 0; y < 3; y++) {
            if (grid[0][y] == grid[1][y]
                    && grid[0][y] == grid[2][y]) {
                if (grid[0][y] == Player.COMPUTER) {
                    return GameState.COMPUTER_WIN;
                } else if (grid[0][y] == Player.HUMAN) {
                    return GameState.HUMAN_WIN;
                }
            }
        }
        for (int x = 0; x < 3; x++) {
            if (grid[x][0] == grid[x][1]

                    && grid[x][0] == grid[x][2]) {
                if (grid[x][0] == Player.COMPUTER) {
                    return GameState.COMPUTER_WIN;
                } else if (grid[x][0] == Player.HUMAN) {
                    return GameState.HUMAN_WIN;
                }
            }
        }
        if (grid[0][0] == grid[1][1] &&
                grid[0][0] == grid[2][2]) {
            if (grid[0][0] == Player.COMPUTER) {
                return GameState.COMPUTER_WIN;
            } else if (grid[0][0] == Player.HUMAN) {
                return GameState.HUMAN_WIN;
            }
        }
        if (grid[2][0] == grid[1][1] &&
                grid[2][0] == grid[0][2]) {
            if (grid[2][0] == Player.COMPUTER) {
                return GameState.COMPUTER_WIN;
            } else if (grid[2][0] == Player.HUMAN) {
                return GameState.HUMAN_WIN;
            }
        }
        boolean isEmpty = false;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (grid[x][y] == Player.BLANK) {
                    isEmpty = true;
                }
            }
        }
        if (!isEmpty) {
            return GameState.DRAW;
        }
        return GameState.IN_PROGRESS;
    }
}
