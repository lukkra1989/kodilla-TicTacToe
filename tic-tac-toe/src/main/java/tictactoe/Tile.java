package tictactoe;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import tictactoe.logic.Ai;
import tictactoe.logic.BoardIndex;
import tictactoe.logic.JavaFxBoardToLogicBoardConverter;
import tictactoe.logic.Player;

import java.util.Random;

public class Tile extends StackPane {

    private static Image cross = new Image("file:src/main/resources/cross.png");
    private static Image circle = new Image("file:src/main/resources/circle.png");
    private Ai aiAlgorithm;
    private FieldValue fieldValue;

    public FieldValue getFieldValue() {
        return fieldValue;
    }

    public void setAiAlgorithm(Ai aiAlgorithm) {
        this.aiAlgorithm = aiAlgorithm;

    }

    public void setFieldValue(FieldValue fieldValue) {
        this.fieldValue = fieldValue;
    }

    Tile(FieldValue fieldValue, Ai aiAlgorithm) {
        this.fieldValue = fieldValue;
        this.aiAlgorithm = aiAlgorithm;

        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(200);
        rectangle.setWidth(200);
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLUEVIOLET);

        setAlignment(Pos.CENTER);
        getChildren().addAll(rectangle);

        if (getFieldValue() == FieldValue.EMPTY) {
            rectangle.setFill(null);
        }

        setOnMouseClicked(action -> {
            if (!TheGame.possibleMove) {
                return;
            }
            if (TheGame.vsComputer) {
                if (action.getButton() == MouseButton.SECONDARY) {
                    return;
                }
                if (action.getButton() == MouseButton.PRIMARY) {
                    if (!TheGame.turnX || getFieldValue() == FieldValue.CIRCLE || getFieldValue() == FieldValue.CROSS) {
                        return;
                    }
                    setFieldValue(FieldValue.CROSS);
                    rectangle.setFill(new ImagePattern(cross));
                    TheGame.turnX = false;
                    checkState();
                    if (!TheGame.possibleMove) {
                        return;
                    }
                }
                //AI
                JavaFxBoardToLogicBoardConverter javaFxBoardToLogicBoardConverter = new JavaFxBoardToLogicBoardConverter();
                BoardIndex boardIndex = this.aiAlgorithm.calculateMove(javaFxBoardToLogicBoardConverter.convert(TheGame.board, FieldValue.CROSS), Player.COMPUTER);

                Tile tile = TheGame.board.fields[boardIndex.getI()][boardIndex.getJ()];
                Rectangle node = (Rectangle) tile.getChildren().get(0);
                tile.setFieldValue(FieldValue.CIRCLE);
                node.setFill(new ImagePattern(circle));
                TheGame.turnX = true;
                checkState();
            }

            if (!TheGame.vsComputer) {
                if (action.getButton() == MouseButton.PRIMARY) {
                    if (!TheGame.turnX || getFieldValue() == FieldValue.CIRCLE || getFieldValue() == FieldValue.CROSS) {
                        return;
                    }
                    setFieldValue(FieldValue.CROSS);
                    rectangle.setFill(new ImagePattern(cross));
                    TheGame.turnX = false;
                    checkState();
                }
                if (action.getButton() == MouseButton.SECONDARY) {
                    if (TheGame.turnX || getFieldValue() == FieldValue.CROSS || getFieldValue() == FieldValue.CIRCLE) {
                        return;
                    }
                    setFieldValue(FieldValue.CIRCLE);
                    rectangle.setFill(new ImagePattern(circle));
                    TheGame.turnX = true;
                    checkState();
                }
            }
        });
    }

    public void checkState() {
        Score score = State.gameResult();
        System.out.println(score);
        if (score == Score.O_WIN) {

            TheGame.possibleMove = false;
            TheGame.label.setText(" O WINS !!!");
            TheGame.label.setTextFill(Color.BLACK);
            return;
        }
        if (score == Score.X_WIN) {

            TheGame.possibleMove = false;
            TheGame.label.setText("X WINS !!!");
            TheGame.label.setTextFill(Color.RED);
            return;
        }
        if (score == Score.EVEN) {
            TheGame.possibleMove = false;
            TheGame.label.setText("TIE");
            TheGame.label.setTextFill(Color.GREEN);
            return;
        }
        if (score == Score.IN_PROGRESS) {
            if (TheGame.turnX) {
                TheGame.label.setText("X TURN");
                TheGame.label.setTextFill(Color.RED);
            }
            if (!TheGame.turnX) {
                TheGame.label.setText("O TURN");
                TheGame.label.setTextFill(Color.BLACK);
            }
        }
    }
}