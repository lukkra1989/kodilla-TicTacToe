package tictactoe;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tictactoe.logic.Ai;

public class State {

    public static Ai aiAlgorithm;
    public static void gameReset(){
        gameReset(null);
    }
    public static void gameReset(Ai aiAlgorithm) {
        Tile[][] fields = TheGame.board.fields;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                fields[x][y].setFieldValue(FieldValue.EMPTY);
                if(aiAlgorithm!=null){
                fields[x][y].setAiAlgorithm(aiAlgorithm);}
                Rectangle node = (Rectangle) fields[x][y].getChildren().get(0);
                node.setFill(null);
            }
        }
        TheGame.turnX = true;
        TheGame.possibleMove = true;
        TheGame.label.setText("Now X: ");
        TheGame.label.setTextFill(Color.MAGENTA);
    }

    public static Score gameResult() {
        Tile[][] fields = TheGame.board.fields;

        for (int y = 0; y < 3; y++) {
            if (fields[0][y].getFieldValue() == fields[1][y].getFieldValue()
                    && fields[0][y].getFieldValue() == fields[2][y].getFieldValue()) {
                if (fields[0][y].getFieldValue() == FieldValue.CIRCLE) {
                    return Score.O_WIN;
                } else if (fields[0][y].getFieldValue() == FieldValue.CROSS) {
                    return Score.X_WIN;
                }
            }
        }
        for (int x = 0; x < 3; x++) {
            if (fields[x][0].getFieldValue() == fields[x][1]
                    .getFieldValue()
                    && fields[x][0].getFieldValue() == fields[x][2].getFieldValue()) {
                if (fields[x][0].getFieldValue() == FieldValue.CIRCLE) {
                    return Score.O_WIN;
                } else if (fields[x][0].getFieldValue() == FieldValue.CROSS) {
                    return Score.X_WIN;
                }
            }
        }
        if (fields[0][0].getFieldValue() == fields[1][1].getFieldValue() &&
                fields[0][0].getFieldValue() == fields[2][2].getFieldValue()) {
            if (fields[0][0].getFieldValue() == FieldValue.CIRCLE) {
                return Score.O_WIN;
            } else if (fields[0][0].getFieldValue() == FieldValue.CROSS) {
                return Score.X_WIN;
            }
        }
        if (fields[2][0].getFieldValue() == fields[1][1].getFieldValue() &&
                fields[2][0].getFieldValue() == fields[0][2].getFieldValue()) {
            if (fields[2][0].getFieldValue() == FieldValue.CIRCLE) {
                return Score.O_WIN;
            } else if (fields[2][0].getFieldValue() == FieldValue.CROSS) {
                return Score.X_WIN;
            }
        }
        boolean isEmpty = false;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (fields[x][y].getFieldValue() == FieldValue.EMPTY) {
                    isEmpty = true;
                }
            }
        }
        if (!isEmpty) {
            return Score.EVEN;
        }
        return Score.IN_PROGRESS;
    }
}