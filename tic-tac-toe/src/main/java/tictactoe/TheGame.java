package tictactoe;

import tictactoe.logic.MediumAi;
import tictactoe.logic.RandomAi;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.stream.Collectors;


public class TheGame extends Application {

    private static Image back = new Image("file:src/main/resources/back.png");
    public static Board board = new Board(new MediumAi());
    public static boolean turnX = true;
    public static boolean possibleMove = true;
    public static Label label = new Label();
    public static Label vsWho = new Label();
    public static Label counter = new Label();
    public static Counter playerX = new Counter(0);
    public static Counter playerO = new Counter(0);
    public static boolean vsComputer = true;

    private static Parent createScene() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        Pane root = new Pane();
        root.setPrefSize(600, 780);
        root.setBackground(background);

        Button playAgain = new Button();
        playAgain.setText("Play Again");
        playAgain.setFont(new Font("Arial Black", 15));
        playAgain.setLayoutX(450);
        playAgain.setLayoutY(601);
        playAgain.setPrefSize(150, 49);
        playAgain.setOnAction(action -> {
            State.gameReset(null);
        });


        /////       BUTTON EASY LEVEL     //////////
        Button easyLevel = new Button();
        easyLevel.setText("Play: Level Easy");
        easyLevel.setFont(new Font("Arial Black", 15));
        easyLevel.setLayoutX(0);
        easyLevel.setLayoutY(600);
        easyLevel.setPrefSize(185, 49);
        easyLevel.setOnAction(action -> {
            vsComputer = true;
            playerO.setCount(0);
            playerX.setCount(0);
            counter.setText("X  [" + playerX.getCount()
                    + "] : [" + playerO.getCount() + "]  O");
            vsWho.setText("PLAY vs EASY COMPUTER");
            State.gameReset(new RandomAi());
        });

        ////////////   BUTTON HARD LEVEL  ///////////
        Button hardLevel = new Button();
        hardLevel.setText("Play: Level Hard");
        hardLevel.setFont(new Font("Arial Black", 15));
        hardLevel.setLayoutX(0);
        hardLevel.setLayoutY(650);
        hardLevel.setPrefSize(185, 49);
        hardLevel.setOnAction(action -> {
            vsComputer = true;
            playerO.setCount(0);
            playerX.setCount(0);
            counter.setText("X  [" + playerX.getCount()
                    + "] : [" + playerO.getCount() + "]  O");
            //    vsWho.setText("PLAYER vs COMPUTER");
            vsWho.setText("PLAYER vs HARD COMPUTER");
            State.gameReset(new MediumAi());
        });

        Button vsPlay = new Button();
        vsPlay.setText("2 Players");
        vsPlay.setWrapText(true);
        vsPlay.setFont(new Font("Arial Black", 15));
        vsPlay.setLayoutX(0);
        vsPlay.setLayoutY(700);
        vsPlay.setPrefSize(185, 49);
        vsPlay.setOnAction(action -> {
            vsComputer = false;
            playerX.setCount(0);
            playerO.setCount(0);
            counter.setText("X  [" + playerX.getCount()
                    + "] : [" + playerO.getCount() + "]  O");
            vsWho.setText("PLAYER VS PLAYER2");
            State.gameReset(null);
        });

        Button quit = new Button();
        quit.setText("QUIT");
        quit.setFont(new Font("Arial Black", 25));
        quit.setLayoutX(450);
        quit.setLayoutY(651);
        quit.setPrefSize(150, 50);
        quit.setOnAction(action -> {
            Platform.exit();
        });

        label.setText("X TURN");
        label.setTextFill(Color.RED);
        label.setFont(new Font("Arial Black", 30));
        label.layoutXProperty().bind(root.widthProperty().subtract(label.widthProperty()).divide(2));
        label.setLayoutY(605);

        counter.setText("X  [" + playerX.getCount() + "] : [" + playerO.getCount() + "]  O");
        counter.setTextFill(Color.BLACK);
        counter.setFont(new Font("Arial Black", 30));
        counter.layoutXProperty().bind(root.widthProperty().subtract(counter.widthProperty()).divide(2));
        counter.setLayoutY(638);

        vsWho.setText("YOU vs. COMPUTER");
        vsWho.setTextFill(Color.GREEN);
        vsWho.setFont(new Font("Arial Black", 15));
        vsWho.layoutXProperty().bind(root.widthProperty().subtract(vsWho.widthProperty()).divide(2));
        vsWho.setLayoutY(671);

        root.getChildren().addAll(Arrays.stream(board.fields).flatMap(Arrays::stream).collect(Collectors.toList()));
        root.getChildren().addAll(quit, vsPlay, hardLevel, easyLevel, playAgain,label, counter, vsWho);
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(TheGame.createScene());

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}