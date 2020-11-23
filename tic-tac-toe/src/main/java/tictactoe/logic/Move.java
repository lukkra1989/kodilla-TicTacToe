package tictactoe.logic;

public class Move {

    private BoardIndex boardIndex;
    int score;

    public Move(BoardIndex boardIndex, int score) {
        this.boardIndex = boardIndex;
        this.score = score;
    }

    public BoardIndex getBoardIndex() {
        return boardIndex;
    }

    public int getScore() {
        return score;
    }
}
