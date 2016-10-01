package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import comp1110.ass2.StratoGame;


import static comp1110.ass2.Colour.BLACK;
import static comp1110.ass2.Colour.GREEN;
import static comp1110.ass2.Colour.RED;
import static comp1110.ass2.Difficulty.*;
import static comp1110.ass2.Player.MAX_TILES;
import static comp1110.ass2.StratoGame.*;
import static javafx.scene.paint.Color.*;

public class Board extends Application {

    /*TODO: Change instructions text so it actually tells you the instructions*/

    /*TODO: Better Game Over screen*/



/*OVERVIEW: The first function called by the stage is initialSettings(), which
* creates the first screen with three buttons to choose the playing mode.
* (And a button to tell you how to play the game)*/

/*The buttons set a variable called playingMode as PlayerIsRed, PlayerIsGreen,
* or TwoPlayer. Then they all call makePlayer(). If the player is red, it also
* makes a move for the AI as the first (after MMUA) move.*/

/*makePlayer() calls makeControls() and makeBoard()*/

/*makeControls() is pretty much the same for all playing modes, except it omits
* a "Rotate" button if it's single-player. */

/*makeBoard() is pretty much the same again. The big difference is that
* depending on which playingMode it is, it calls different addPane function:
* addPanePlayerGreen, addPanePlayerRed, or addPaneTwoPlayer.
* makeBoard() modifies the GridPane playingBoard so it looks like a board*/

/*Each 'addPane' function creates a pane at the specified row and column
* on the GridPane clickableTiles.
* When a pane is clicked, the two player version of the function makes a
* move based on whose turn it is.
* The one player version makes the player's move and calls the AI with the
* appropriate input on which player it is supposed to be.
* 'addPane' also holds the events for the previews of tile placements*/

/*Many of the buttons, text, and images were defined as class fields to be
 modified by functions, instead of being created by functions  because they
 need to be accessible by many different functions.*/



    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final String URI_BASE = "assets/";
    private static final int TILE_SIZE = 25;
    private static final int BOARD_SIZE = 26;
    private static final String SOUND_URI = Viewer.class.getResource(URI_BASE + "sound.mp3").toString();

    /*Some fields for initial conditions.*/
    private GameState gameState;
    private Player playerG;
    private Player playerR;

    /*Nodes that need to be accessible by many functions.*/
    private ImageView ivg = new ImageView();
    private ImageView ivr = new ImageView();
    private Text greentxt = new Text("Green");
    private Text redtxt = new Text("Red");
    private Text errormessage = new Text("Invalid move!");
    private Text aiThink = new Text("Thinking...");
    private Text redScore = new Text("1");
    private Text greenScore = new Text("1");
    private Text redTilesLeft = new Text("");
    private Text greenTilesLeft = new Text("");
    ImageView sound_icon = new ImageView();

    /*Various Groups that organise the screen.*/
    private final Group root = new Group();
    private final Group popUp1 = new Group();
    private final Group controls = new Group();
    private final GridPane playerControls = new GridPane();
    private final Group placementGrp = new Group();
    private final GridPane playingBoard = new GridPane();
    private final GridPane heightLabels = new GridPane();
    private final GridPane clickablePanes = new GridPane();

    /*A counter that tells you if this is the first game played.*/
    private boolean firstGame = true;
    private boolean soundOn = true;

    /*the audio clip*/
    private AudioClip audio = new AudioClip(SOUND_URI);



    /*Function by Zhixian Wu*/
    private void initialSettings() {
        playingBoard.setOpacity(1);
        heightLabels.setOpacity(1);

        gameState  = new GameState(BLACK, HUMAN, HUMAN);

        ImageView logo = new ImageView();
        logo.setImage(new Image(Viewer.class.getResource(URI_BASE + "stratopolis" + ".png").toString()));
        placementGrp.getChildren().add(logo);
        logo.setLayoutX(220);
        logo.setLayoutY(230);

        Text greenText = new Text("Player Green: Human");
        greenText.setFill(Color.GREEN);
        greenText.setFont(Font.font("Verdana", FontWeight.BOLD, 24));

        Text green1 = new Text("Human: ");
        green1.setFill(Color.GREEN);
        green1.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        Text green2 = new Text("AI:   ");
        green2.setFill(Color.GREEN);
        green2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        Text redText = new Text("Player Red: Human");
        redText.setFill(Color.RED);
        redText.setFont(Font.font("Verdana", FontWeight.BOLD, 24));

        Text red1 = new Text("Human: ");
        red1.setFill(Color.RED);
        red1.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        Text red2 = new Text("AI:   ");
        red2.setFill(Color.RED);
        red2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));


        /*Each of these buttons tell the game if you want a two player game, or
        * to play as green or red against an AI*/
        Button greenHuman = new Button("Human");
        greenHuman.setOnAction(event-> {
            gameState.greenPlayer = HUMAN;
            greenText.setText("Player Green: Human");
        });
        greenHuman.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        DropShadow shadow = new DropShadow();

        greenHuman.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> greenHuman.setEffect(shadow));
        greenHuman.addEventHandler(MouseEvent.MOUSE_EXITED, event -> greenHuman.setEffect(null));

        Button greenEasy = new Button("Easy");
        greenEasy.setOnAction(event-> {
            gameState.greenPlayer = EASY;
            greenText.setText("Player Green: Easy");
        });
        greenEasy.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        greenEasy.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> greenEasy.setEffect(shadow));
        greenEasy.addEventHandler(MouseEvent.MOUSE_EXITED, event -> greenEasy.setEffect(null));

        Button greenMedium = new Button("Medium");
        greenMedium.setOnAction(event-> {
            gameState.greenPlayer = MEDIUM;
            greenText.setText("Player Green: Medium");
        });
        greenMedium.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        greenMedium.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> greenMedium.setEffect(shadow));
        greenMedium.addEventHandler(MouseEvent.MOUSE_EXITED, event -> greenMedium.setEffect(null));

        Button greenHard = new Button("Hard");
        greenHard.setOnAction(event-> {
            gameState.greenPlayer = HARD;
            greenText.setText("Player Green: Hard");
        });
        greenHard.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        greenHard.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> greenHard.setEffect(shadow));
        greenHard.addEventHandler(MouseEvent.MOUSE_EXITED, event -> greenHard.setEffect(null));


        Button greenCheating = new Button("Cheating");
        greenCheating.setOnAction(event-> {
            gameState.greenPlayer = CHEATING;
            greenText.setText("Player Green: Cheating");
        });
        greenCheating.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        greenCheating.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> greenCheating.setEffect(shadow));
        greenCheating.addEventHandler(MouseEvent.MOUSE_EXITED, event -> greenCheating.setEffect(null));

        Button redHuman = new Button("Human");
        redHuman.setOnAction(event-> {
            gameState.redPlayer = HUMAN;
            redText.setText("Player Red: Human");
        });
        redHuman.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        redHuman.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> redHuman.setEffect(shadow));
        redHuman.addEventHandler(MouseEvent.MOUSE_EXITED, event -> redHuman.setEffect(null));

        Button redEasy = new Button("Easy");
        redEasy.setOnAction(event-> {
            gameState.redPlayer = EASY;
            redText.setText("Player Red: Easy");
        });
        redEasy.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        redEasy.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> redEasy.setEffect(shadow));
        redEasy.addEventHandler(MouseEvent.MOUSE_EXITED, event -> redEasy.setEffect(null));

        Button redMedium = new Button("Medium");
        redMedium.setOnAction(event-> {
            gameState.redPlayer = MEDIUM;
            redText.setText("Player Red: Medium");
        });
        redMedium.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        redMedium.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> redMedium.setEffect(shadow));
        redMedium.addEventHandler(MouseEvent.MOUSE_EXITED, event -> redMedium.setEffect(null));

        Button redHard = new Button("Hard");
        redHard.setOnAction(event-> {
            gameState.redPlayer = HARD;
            redText.setText("Player Red: Hard");
        });
        redHard.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        redHard.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> redHard.setEffect(shadow));
        redHard.addEventHandler(MouseEvent.MOUSE_EXITED, event -> redHard.setEffect(null));

        Button redCheating = new Button("Cheating");
        redCheating.setOnAction(event-> {
            gameState.redPlayer = CHEATING;
            redText.setText("Player Red: Cheating");
        });
        redCheating.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        redCheating.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> redCheating.setEffect(shadow));
        redCheating.addEventHandler(MouseEvent.MOUSE_EXITED, event -> redCheating.setEffect(null));

        Button startGame = new Button("Start");
        startGame.setOnAction(event-> {
            placementGrp.getChildren().clear();
            makePlayer();
        });
        startGame.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        startGame.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> startGame.setEffect(shadow));
        startGame.addEventHandler(MouseEvent.MOUSE_EXITED, event -> startGame.setEffect(null));
        startGame.setLayoutX(440);
        startGame.setLayoutY(650);

        /*A button that created a scrolling text node that displays the instructions*/
        Button instructions = new Button("How to Play");
        instructions.setOnAction(event-> getInstructions());
        instructions.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        instructions.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> instructions.setEffect(shadow));
        instructions.addEventHandler(MouseEvent.MOUSE_EXITED, event -> instructions.setEffect(null));
        instructions.setLayoutX(420);
        instructions.setLayoutY(610);

        /*Layout*/
        HBox ghb1 = new HBox(5);
        ghb1.getChildren().addAll(green1,greenHuman);
        HBox ghb2 = new HBox(5);
        ghb2.getChildren().addAll(green2,greenEasy,greenMedium,greenHard,greenCheating);
        ghb2.setMargin(green2, new Insets(0,28,0,0));
        VBox green = new VBox(5);
        green.getChildren().addAll(greenText,ghb1,ghb2);
        green.setLayoutX(30);
        green.setLayoutY(580);

        HBox rhb1 = new HBox(5);
        rhb1.getChildren().addAll(red1,redHuman);
        HBox rhb2 = new HBox(5);
        rhb2.getChildren().addAll(red2,redEasy,redMedium,redHard,redCheating);
        rhb2.setMargin(red2, new Insets(0,28,0,0));
        VBox red = new VBox(5);
        red.getChildren().addAll(redText,rhb1,rhb2);
        red.setLayoutX(580);
        red.setLayoutY(580);

        placementGrp.getChildren().addAll(green, red, startGame, instructions);
    }

    /*Function by Zhixian Wu. This function displays the instructions when called.*/
    private void getInstructions(){
        placementGrp.setDisable(true);
        controls.setDisable(true);

        GridPane mainInstruc = new GridPane();
        mainInstruc.setLayoutY(50);
        mainInstruc.setLayoutX(105);
        mainInstruc.setHgap(5);
        mainInstruc.setVgap(5);

        ScrollPane scroll = new ScrollPane();

        Rectangle thickBorder = new Rectangle(750,520,Color.BEIGE);
        thickBorder.setArcHeight(7);
        thickBorder.setArcWidth(7);
        thickBorder.setLayoutX(87);
        thickBorder.setLayoutY(40);
        thickBorder.setOpacity(0.5);

        Text instructions = new Text("Lorem ipsum dolor sit amet, consectetur adipisicing elit," +
                " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim" +
                " ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
                " ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate" +
                " velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat" +
                " cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum" + "\n" + "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit," +
                " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim" +
                " ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
                " ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate" +
                " velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat" +
                " cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum" + "\n" + "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit," +
                " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim" +
                " ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
                " ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate" +
                " velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat" +
                " cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum" + "\n" + "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit," +
                " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim" +
                " ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
                " ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate" +
                " velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat" +
                " cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum" + "\n" + "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit," +
                " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim" +
                " ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
                " ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate" +
                " velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat" +
                " cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum" + "\n" + "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit," +
                " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim" +
                " ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
                " ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate" +
                " velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat" +
                " cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum");

        instructions.setFont(Font.font("Arial", 16));
        instructions.setWrappingWidth(680);

        scroll.setContent(instructions);
        scroll.setPrefViewportHeight(450.0);
        scroll.setPrefViewportWidth(700.0);

        Button exitBtn = new Button("x");
        exitBtn.setOnAction(event->  {
            root.getChildren().remove(popUp1);
            placementGrp.setDisable(false);
            controls.setDisable(false);
        } );
        exitBtn.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        DropShadow shadow = new DropShadow();
        exitBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, event ->  exitBtn.setEffect(shadow));
        exitBtn.addEventHandler(MouseEvent.MOUSE_EXITED, event -> exitBtn.setEffect(null));

        mainInstruc.getChildren().addAll(scroll,exitBtn);
        GridPane.setRowIndex(scroll,1);
        GridPane.setColumnIndex(scroll,0);
        GridPane.setRowIndex(exitBtn,0);
        GridPane.setColumnIndex(exitBtn,0);
        GridPane.setHalignment(exitBtn, HPos.RIGHT);

        popUp1.getChildren().addAll(thickBorder,mainInstruc);
        root.getChildren().add(popUp1);
    }

    /*Function by Zhixian Wu*/
    private void makePlayer(){
        playerG = new PlayerG();
        playerR = new PlayerR();

        /*Make the playing board*/
        makeBoard();

        /*Makes the controls for the game, separately from the board*/
        makeControls();

        /*Plays the game for two AI*/
        if (gameState.greenPlayer!=HUMAN && gameState.redPlayer!=HUMAN) {
            makeGUIPlacement("MMUA");


            Button nextMove = new Button("Next Move");
            nextMove.setOnMousePressed(event->  {
                aiThink.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
                controls.getChildren().add(aiThink);
                aiThink.setLayoutX(750);
                aiThink.setLayoutY(420);
            });
            nextMove.setOnAction(event->  makeAIMove());
            nextMove.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                    "        #090a0c,\n" +
                    "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                    "        linear-gradient(#20262b, #191d22),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                    "-fx-text-fill: white;");

            DropShadow shadow = new DropShadow();
            nextMove.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> nextMove.setEffect(shadow));
            nextMove.addEventHandler(MouseEvent.MOUSE_EXITED, event -> nextMove.setEffect(null));
            nextMove.setLayoutX(TILE_SIZE*BOARD_SIZE+60);
            nextMove.setLayoutY(650);
            controls.getChildren().add(nextMove);
        }


    }

    /*Function mostly by Zhixian Wu, with the running score by Manal Mohania*/
    private void makeControls(){
        /*Make the control pane as a GridPane. This is the stuff on the right*/
        playerControls.setPrefSize(120, 200);
        playerControls.setMaxSize(120, 200);

        /*The text labeling Green and Red's tiles, which you see on the right*/
        greentxt.setFill(Color.GREEN);
        greentxt.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        redtxt.setFill(Color.RED);
        redtxt.setFont(Font.font("Verdana", 16));

        sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon" + ".png").toString()));
        sound_icon.setFitWidth(25);
        sound_icon.setPreserveRatio(true);
        sound_icon.setSmooth(true);
        sound_icon.setCache(true);
        sound_icon.setLayoutX(900);
        sound_icon.setLayoutY(15);
        sound_icon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (soundOn){
                    sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon_off" + ".png").toString()));
                    sound_icon.setFitWidth(25);
                    sound_icon.setPreserveRatio(true);
                    sound_icon.setSmooth(true);
                    sound_icon.setCache(true);
                    soundOn = false;
                } else{
                    sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon" + ".png").toString()));
                    sound_icon.setFitWidth(25);
                    sound_icon.setPreserveRatio(true);
                    sound_icon.setSmooth(true);
                    sound_icon.setCache(true);
                    soundOn = true;
                }
                event.consume();
            }
        });
        controls.getChildren().add(sound_icon);



        /*The tiles at the "top" of each player's "stack", displayed on the right*/
        ivg.setImage(new Image(Viewer.class.getResource(URI_BASE + (playerG.available_tiles).get(playerG.used_tiles) + ".png").toString()));
        ivg.setRotate((((int) (playerG.rotation)-'A')*90));
        ivg.setFitWidth(80);
        ivg.setPreserveRatio(true);
        ivg.setSmooth(true);
        ivg.setCache(true);

        ivr.setImage(new Image(Viewer.class.getResource(URI_BASE + (playerR.available_tiles).get(playerR.used_tiles) + ".png").toString()));
        ivr.setRotate((((int) (playerR.rotation)-'A')*90));
        ivr.setFitWidth(80);
        ivr.setPreserveRatio(true);
        ivr.setSmooth(true);
        ivr.setCache(true);

        /*The buttons that rotate the tiles*/
        Button rotateG = new Button("Rotate");
        rotateG.setOnAction(event-> {
            playerG.rotateTile();
            ivg.setRotate((((int) (playerG.rotation)-'A')*90));
        });

        Button rotateR = new Button("Rotate");
        rotateR.setOnAction(event-> {
            playerR.rotateTile();
            ivr.setRotate((((int) (playerR.rotation)-'A')*90));
        });



        /*Adding the nodes. We may omit the a rotate button depending on the playingMode*/
        playerControls.getChildren().addAll(greentxt,redtxt,ivg,ivr);
        if (gameState.greenPlayer==HUMAN) playerControls.getChildren().add(rotateG);
        if (gameState.redPlayer==HUMAN) playerControls.getChildren().add(rotateR);

        /*Layout*/

        GridPane.setColumnIndex(ivg,0);
        GridPane.setRowIndex(ivg,0);
        GridPane.setColumnIndex(ivr,1);
        GridPane.setRowIndex(ivr,0);
        GridPane.setColumnIndex(rotateG,0);
        GridPane.setRowIndex(rotateG,1);
        GridPane.setColumnIndex(rotateR,1);
        GridPane.setRowIndex(rotateR,1);
        GridPane.setColumnIndex(greentxt,0);
        GridPane.setRowIndex(greentxt,2);
        GridPane.setColumnIndex(redtxt,1);
        GridPane.setRowIndex(redtxt,2);

        GridPane.setValignment(redTilesLeft, VPos.TOP);

        playerControls.setLayoutX(TILE_SIZE*BOARD_SIZE+85);
        playerControls.setLayoutY(200);

        playerControls.setHgap(10);
        playerControls.setVgap(10);

        controls.getChildren().add(playerControls);


        /*This line is for debugging purposes only. When set to true, it shows grid lines*/
        playerControls.setGridLinesVisible(false);

        /*A main menu button. It clears the current game and calls initialSettings()*/
        Button menu = new Button("Main Menu");
        menu.setOnAction(event->{
            controls.getChildren().clear();
            placementGrp.getChildren().clear();
            playingBoard.getChildren().clear();
            heightLabels.getChildren().clear();
            clickablePanes.getChildren().clear();
            playerControls.getChildren().clear();

            firstGame = false;

            initialSettings();
        });
        controls.getChildren().add(menu);
        menu.setLayoutX(835);
        menu.setLayoutY(650);

        /*Changes have been made from this line onwards*/
        menu.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        DropShadow shadow = new DropShadow();

        menu.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> menu.setEffect(shadow));
        menu.addEventHandler(MouseEvent.MOUSE_EXITED, event -> menu.setEffect(null));




        /*Scores by Manal Mohania*/
        Rectangle r = new Rectangle(170,80,Color.SANDYBROWN);
        r.setLayoutY(50);
        r.setLayoutX(735);
        r.setArcHeight(20);
        r.setArcWidth(20);
        controls.getChildren().add(r);

        Text score = new Text("SCORES");
        score.setLayoutX(790);
        score.setLayoutY(65);
        controls.getChildren().add(score);

        greenScore.setLayoutX(750);
        greenScore.setLayoutY(103);
        greenScore.setFill(Color.GREEN);
        greenScore.setFont(Font.font("", FontWeight.EXTRA_BOLD, 40));
        updateGreenScore();

        redScore.setLayoutX(830);
        redScore.setLayoutY(103);
        redScore.setFill(Color.RED);
        redScore.setFont(Font.font("", FontWeight.EXTRA_BOLD, 40));
        updateRedScore();

        /*Tiles left by Zhixian Wu*/
        Rectangle r2 = new Rectangle(170,45,Color.SANDYBROWN);
        r2.setArcHeight(20);
        r2.setArcWidth(20);

        Text tiles_left = new Text("TILES LEFT");

        greenTilesLeft.setFill(Color.GREEN);
        greenTilesLeft.setFont(Font.font("", FontWeight.EXTRA_BOLD, 16));

        redTilesLeft.setFill(Color.RED);
        redTilesLeft.setFont(Font.font("", FontWeight.EXTRA_BOLD, 16));

        updateTilesLeft();

        GridPane tileCounter = new GridPane();
        tileCounter.getChildren().addAll(r2,tiles_left,greenTilesLeft,redTilesLeft);
        for (int i = 0; i < 2; i++) {
            ColumnConstraints column = new ColumnConstraints(85);
            tileCounter.getColumnConstraints().add(column);
        }
        GridPane.setColumnIndex(r2,0);
        GridPane.setRowIndex(r2,0);
        GridPane.setColumnSpan(r2,2);
        GridPane.setRowSpan(r2,2);
        GridPane.setValignment(r2, VPos.TOP);
        GridPane.setColumnIndex(tiles_left,0);
        GridPane.setRowIndex(tiles_left,0);
        GridPane.setColumnSpan(tiles_left,2);
        GridPane.setHalignment(tiles_left, HPos.CENTER);
        GridPane.setValignment(tiles_left, VPos.BOTTOM);
        GridPane.setColumnIndex(greenTilesLeft,0);
        GridPane.setRowIndex(greenTilesLeft,1);
        GridPane.setHalignment(greenTilesLeft, HPos.CENTER);
        GridPane.setValignment(greenTilesLeft, VPos.TOP);
        GridPane.setColumnIndex(redTilesLeft,1);
        GridPane.setRowIndex(redTilesLeft,1);
        GridPane.setHalignment(redTilesLeft, HPos.CENTER);
        GridPane.setValignment(redTilesLeft, VPos.TOP);

        tileCounter.setLayoutX(TILE_SIZE*BOARD_SIZE+80);
        tileCounter.setLayoutY(360);
        controls.getChildren().add(tileCounter);

    }
    private void updateTilesLeft(){
        if (gameState.moveHistory.length()<=MAX_TILES*8-4){
            String green = Integer.toString(MAX_TILES-playerG.used_tiles);
            System.out.println("G "+playerG.used_tiles);
            String red = Integer.toString(MAX_TILES-playerR.used_tiles);
            System.out.println("R "+playerR.used_tiles);
            greenTilesLeft.setText(green);
            redTilesLeft.setText(red);
        } else{
            if (MAX_TILES*8-4<=gameState.moveHistory.length() && gameState.moveHistory.length()<=MAX_TILES*8){
                greenTilesLeft.setText("0");
                String red = Integer.toString(MAX_TILES-playerR.used_tiles);
                redTilesLeft.setText(red);
            } else{
                greenTilesLeft.setText("0");
                redTilesLeft.setText("0");
            }
        }

    }

    /*Function mostly by Zhixian Wu, with minor changes by Manal Mohania (indicated below)*/
    private void makeBoard(){
        int size = TILE_SIZE * BOARD_SIZE;
        int offset = (BOARD_HEIGHT - size) / 2;
        playingBoard.setPrefSize(size, size);
        playingBoard.setMaxSize(size, size);

        if (firstGame){
            /*determines the size of the rows and columns of the playing board*/
            for (int i = 0; i < BOARD_SIZE; i++) {
                RowConstraints row = new RowConstraints(TILE_SIZE);
                playingBoard.getRowConstraints().add(row);
            }
            for (int i = 0; i < BOARD_SIZE; i++) {
                ColumnConstraints column = new ColumnConstraints(TILE_SIZE);
                playingBoard.getColumnConstraints().add(column);
            }
        }



        /*Makes the board background black using CSS*/
        playingBoard.setStyle("-fx-background-color: black");

        /*Creates white squares on a black background for the board*/
        for (int i=0; i<BOARD_SIZE;i++){
            for (int j=0; j<BOARD_SIZE; j++){
                int rectSize = TILE_SIZE-2;
                Rectangle r = new Rectangle(rectSize, rectSize);
                r.setFill(Color.web("rgb(230,228,221)")); /*Colour done by Manal Mohania*/
                playingBoard.getChildren().add(r);
                GridPane.setRowIndex(r,i);
                GridPane.setColumnIndex(r,j);
                GridPane.setHalignment(r, HPos.CENTER);
                GridPane.setValignment(r, VPos.CENTER);
            }
        }
        /*Give the board thicker outer edges*/
        Rectangle thickBorder = new Rectangle(size+8,size+8,Color.BLACK);
        thickBorder.setArcHeight(7);
        thickBorder.setArcWidth(7);
        thickBorder.setLayoutX(offset-4);
        thickBorder.setLayoutY(offset-4);

        /*This line is for debugging purposes only. When set to true, it shows grid lines*/
        playingBoard.setGridLinesVisible(false);

        /*Layout*/
        playingBoard.setLayoutX(offset);
        playingBoard.setLayoutY(offset);

        /*An GridPane on top of playingBoard, laid out identically to playingBoard
         that shows the height of the tile on that position*/
        heightLabels.setPrefSize(size, size);
        heightLabels.setMaxSize(size, size);
        if (firstGame){
            /*Determines the size of the grid rows and columns*/
            for (int i = 0; i < BOARD_SIZE; i++) {
                RowConstraints row = new RowConstraints(TILE_SIZE);
                heightLabels.getRowConstraints().add(row);
            }
            for (int i = 0; i < BOARD_SIZE; i++) {
                ColumnConstraints column = new ColumnConstraints(TILE_SIZE);
                heightLabels.getColumnConstraints().add(column);
            }
        }
        /*Layout*/
        heightLabels.setLayoutX(offset);
        heightLabels.setLayoutY(offset);


        /*A GridPane on top of playingBoard and heightLabels, laid out identically to playingBoard,
         holding the interactive tiles for the game*/
        clickablePanes.setPrefSize(size, size);
        clickablePanes.setMaxSize(size, size);
        if (firstGame){
            /*Determines the size of the grid rows and columns*/
            for (int i = 0; i < BOARD_SIZE; i++) {
                RowConstraints row = new RowConstraints(TILE_SIZE);
                clickablePanes.getRowConstraints().add(row);
            }
            for (int i = 0; i < BOARD_SIZE; i++) {
                ColumnConstraints column = new ColumnConstraints(TILE_SIZE);
                clickablePanes.getColumnConstraints().add(column);
            }
        }

        Button instructions = new Button("How to Play");


        instructions.setOnAction(event->getInstructions());

        instructions.setStyle("-fx-font: 14 arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

        DropShadow shadow = new DropShadow();
        instructions.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> instructions.setEffect(shadow));
        instructions.addEventHandler(MouseEvent.MOUSE_EXITED, event -> instructions.setEffect(null));

        /*What kind of function the pane calls when clicked depends on the playingMode.
        * Instead of checking what the playingMode is everytime a pane is clicked,
        * we check it now and create different panes depending on the playingMode*/
        if (gameState.greenPlayer==HUMAN && gameState.redPlayer==HUMAN) {
            for (int i=0; i<BOARD_SIZE;i++){
                for (int j=0; j<BOARD_SIZE; j++){
                /*Creates the clickable panes of the board*/
                    addPaneTwoPlayer(i,j);
                    addPaneTwoPlayer(j,i);
                }
            }
            controls.getChildren().add(instructions);
            instructions.setLayoutX(TILE_SIZE*BOARD_SIZE+60);
            instructions.setLayoutY(650);

            makeGUIPlacement("MMUA");
        }
        if (gameState.greenPlayer==HUMAN && gameState.redPlayer!=HUMAN) {
            for (int i=0; i<BOARD_SIZE;i++){
                for (int j=0; j<BOARD_SIZE; j++){
                    addPanePlayerGreen(i,j);
                    addPanePlayerGreen(j,i);
                }
            }
            controls.getChildren().add(instructions);
            instructions.setLayoutX(TILE_SIZE*BOARD_SIZE+60);
            instructions.setLayoutY(650);

            makeGUIPlacement("MMUA");
        }
        if (gameState.greenPlayer!=HUMAN && gameState.redPlayer==HUMAN) {
            for (int i=0; i<BOARD_SIZE;i++){
                for (int j=0; j<BOARD_SIZE; j++){
                /*Creates the clickable panes of the board*/
                    addPanePlayerRed(i,j);
                    addPanePlayerRed(j,i);
                }
            }
            controls.getChildren().add(instructions);
            instructions.setLayoutX(TILE_SIZE*BOARD_SIZE+60);
            instructions.setLayoutY(650);

            makeGUIPlacement("MMUA");

            /*Makes the opponent's move first*/
            char redTile = (char) (playerR.available_tiles).get(playerR.used_tiles);
            char greenTile = (char) (playerG.available_tiles).get(playerG.used_tiles);

            String opponent = genMoveEasy(gameState.moveHistory, greenTile, redTile);
            if (gameState.greenPlayer == MEDIUM) opponent = genMoveMedium(gameState.moveHistory, greenTile, redTile);
            if (gameState.greenPlayer == HARD) opponent = generateMove(gameState.moveHistory, greenTile, redTile);

            makeGUIPlacement(opponent);
        }


        /*Layout*/
        clickablePanes.setLayoutX(offset);
        clickablePanes.setLayoutY(offset);

        /*The must be added in this order so the heights show on top of the tiles
        * and the interactive panes are on top of all of them.*/
        placementGrp.getChildren().addAll(thickBorder,playingBoard,heightLabels,clickablePanes);
    }


    /*The clickable panes for when there are two players*/
    /*Function by Zhixian Wu and Manal Mohania.*/
    /*Idea of how to recursively creates panes that remember what position they
    were created for is from StackOverflow (URL in the C-u5807060 originality statement)*/
    /*@param colIndex   The column the pane is on
    * @param rowIndex   The row the pane is on*/
    private void addPaneTwoPlayer(int colIndex, int rowIndex){
        Pane pane = new Pane();
        ImageView iv = new ImageView();

        /*Event by Zhixian Wu, this makes the player's move when they click on a pane*/
        pane.setOnMouseClicked(event -> {
                char col = (char) (colIndex+'A');
                char row = (char) (rowIndex+'A');
                switch (gameState.playerTurn){
                    case RED:
                        String placement = new StringBuilder().append(col).append(row).append((playerR.available_tiles).get(playerR.used_tiles)).append(playerR.rotation).toString();
                        makeGUIPlacement(placement);
                        break;
                    case GREEN:
                        String placement2 = new StringBuilder().append(col).append(row).append((playerG.available_tiles).get(playerG.used_tiles)).append(playerG.rotation).toString();
                        makeGUIPlacement(placement2);
                        break;
                    case BLACK:
                        makeGUIPlacement("MMUA");
                        gameState.playerTurn = GREEN;
                        break;
                }

        });
        /*Event by Manal Mohania, this creates the preview piece*/
        pane.setOnMouseEntered(event -> {
            char col = (char) (colIndex + 'A');
            char row = (char) (rowIndex + 'A');

            switch (gameState.playerTurn){
                case RED:
                    String placement = "" + col + row + (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
                    makeTempPlacement(iv, placement);
                    break;
                case GREEN:
                    String placement2 = "" + col + row + (playerG.available_tiles).get(playerG.used_tiles) + playerG.rotation;
                    makeTempPlacement(iv, placement2);
                    break;
            }
        });

        /*Event by Manal Mohania, this removes the preview piece*/
        pane.setOnMouseExited(event -> removeTempPlacement(iv));

        clickablePanes.getChildren().add(pane);
        GridPane.setRowIndex(pane,rowIndex);
        GridPane.setColumnIndex(pane,colIndex);
    }

    /*The clickable panes for when the human player is Green*/
    /*Function by Zhixian Wu and Manal Mohania.*/
    /*Idea of how to recursively creates panes that remember what position they
    were created for is from StackOverflow (URL in the in the C-u5807060 originality statement)*/
    /*@param colIndex   The column the pane is on
    * @param rowIndex   The row the pane is on*/
    private void addPanePlayerGreen(int colIndex, int rowIndex){
        Pane pane = new Pane();
        ImageView iv = new ImageView();

        /*Event by Manal Mohania, this adds the preview piece*/
        pane.setOnMouseEntered(event -> {
            char col = (char) (colIndex+'A');
            char row = (char) (rowIndex+'A');

            String placement = "" + col + row + playerG.available_tiles.get(playerG.used_tiles) + playerG.rotation;
            makeTempPlacement(iv, placement);
        });

        /*Event by Manal Mohania, this removes the preview piece*/
        pane.setOnMouseExited(event -> removeTempPlacement(iv));

        /*Event by Zhixian Wu. This event makes the player's move when they press on a pane.*/
        pane.setOnMousePressed(event -> {
            char col = (char) (colIndex+'A');
            char row = (char) (rowIndex+'A');

            String placement = new StringBuilder().append(col).append(row).append((playerG.available_tiles).get(playerG.used_tiles)).append(playerG.rotation).toString();
            makeGUIPlacement(placement);

            int length = gameState.moveHistory.length()-2;
            /*We only suggest the AI is thinking if it actually is, i.e. your move was valid,
             i.e. if the last move in moveHistory was yours*/
            if ('K'<=gameState.moveHistory.charAt(length) && gameState.moveHistory.charAt(length)<='T'){
                aiThink.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
                controls.getChildren().add(aiThink);
                aiThink.setLayoutX(750);
                aiThink.setLayoutY(420);
            }
        });

        /*Event by Zhixian Wu. This event causes the AI to make its move when the mouse is released.*/
        pane.setOnMouseReleased(event -> {
            int length = gameState.moveHistory.length()-2;
            System.out.println("addPanePlayerGreen, the moveHistory the AI uses: " + gameState.moveHistory);

            /*Zhixian Wu: The AI only makes its move if your move was valid, i.e. if the
            last move in moveHistory was yours*/
            if ('K'<=gameState.moveHistory.charAt(length) && gameState.moveHistory.charAt(length)<='T'){
                makeAIMove();
            } else{
                System.out.println("AI did not move");
            }
        });

        clickablePanes.getChildren().add(pane);
        GridPane.setRowIndex(pane,rowIndex);
        GridPane.setColumnIndex(pane,colIndex);
    }

    /*The clickable panes for when the human player is Red*/
    /*Function by Zhixian Wu and Manal Mohania.*/
    /*Idea of how to recursively creates panes that remember what position they
    were created for is from StackOverflow (URL in the in the C-u5807060 originality statement)*/
    /*@param colIndex   The column the pane is on
    * @param rowIndex   The row the pane is on*/
    private void addPanePlayerRed(int colIndex, int rowIndex){
        Pane pane = new Pane();
        ImageView iv = new ImageView();

        /*Event by Manal Mohania, the adds the preview piece*/
        pane.setOnMouseEntered(event -> {
            char col = (char) (colIndex + 'A');
            char row = (char) (rowIndex + 'A');

            String placement = "" + col + row + (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
            makeTempPlacement(iv, placement);
        });

        /*Event by Manal Mohania, this removes the preview piece*/
        pane.setOnMouseExited(event -> removeTempPlacement(iv));

        /*Event by Zhixian Wu. This event makes the player's move when they press on a pane.*/
        pane.setOnMousePressed(event -> {
            char col = (char) (colIndex+'A');
            char row = (char) (rowIndex+'A');

            String placement = new StringBuilder().append(col).append(row).append((playerR.available_tiles).get(playerR.used_tiles)).append(playerR.rotation).toString();
            System.out.println("Red tried: " + placement);
            makeGUIPlacement(placement);

            int length = gameState.moveHistory.length()-2;
            /*We only suggest the AI is thinking if it actually is, i.e. if your
            move was valid, i.e. if the last move in moveHistory was yours*/
            if ('A'<=gameState.moveHistory.charAt(length) && gameState.moveHistory.charAt(length)<='J'){
                aiThink.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
                controls.getChildren().add(aiThink);
                aiThink.setLayoutX(740);
                aiThink.setLayoutY(420);
            }

        });

        /*Event by Zhixian Wu. This event causes the AI to make its move when the mouse is released.*/
        pane.setOnMouseReleased(event -> {
            int length = gameState.moveHistory.length()-2;

            /*The first two conditions check if your move was valid,
            by checking if the last move in moveHistory was yours.
            The AI only makes its move if your move was valid.
              The last condition checks if the game is not over yet,
            so te AI doesn't try to make a move after the game is over*/
            if ('A'<=gameState.moveHistory.charAt(length) && gameState.moveHistory.charAt(length)<='J' && gameState.moveHistory.length()<MAX_TILES*8){
                makeAIMove();
            } else{
                System.out.println("AI did not move");
                controls.getChildren().remove(aiThink);
            }

        });

        clickablePanes.getChildren().add(pane);
        GridPane.setRowIndex(pane,rowIndex);
        GridPane.setColumnIndex(pane,colIndex);
    }

    /**
     * This function removes the temporary placement created due to mouseover (if any)
     * Function by Manal Mohania
     * */
    /*@param iv   The preview image to be removed*/
    private void removeTempPlacement(ImageView iv){
        if (iv == null)
            return;

        playingBoard.getChildren().remove(iv);
    }

    /**
     * This function
     * 1. creates a temporary placement upon mouseover - the placement pieces are of different opacity
     *    depending upon the validity of the placement
     * 2. ensures that the individual piece does not lie outside the board when making the placement
     * 3. removes error messages if a valid placement is reached
     *
     * Function by Manal Mohania
     * Minor edits by Joseph Meltzer
   */
    /*@param iv          The preview image to be added
    * @param placement   The placement string*/
    private void makeTempPlacement(ImageView iv, String placement){


        /*The following ensure that the piece does not fall out of the board*/
        if ((placement.charAt(0) == 'Z') && ((placement.charAt(3) == 'A') || (placement.charAt(3) == 'D'))){
            return;
        }

        if ((placement.charAt(0) == 'A') && ((placement.charAt(3) == 'B') || (placement.charAt(3) == 'C'))){
            return;
        }

        if ((placement.charAt(1) == 'Z') && ((placement.charAt(3) == 'A') || (placement.charAt(3) == 'B'))){
            return;
        }

        if ((placement.charAt(1) == 'A') && ((placement.charAt(3) == 'C') || (placement.charAt(3) == 'D'))){
            return;
        }

        /*remove error messages, if any. And set image according to the validity of the placement*/
        controls.getChildren().remove(errormessage);
        if (StratoGame.isPlacementValid(gameState.moveHistory.concat(placement))) {
            iv.setImage(new Image(Viewer.class.getResource(URI_BASE + placement.charAt(2) + "_h.png").toString()));
            iv.setOpacity(0.8);
        }
        else {
            iv.setImage(new Image(Viewer.class.getResource(URI_BASE + placement.charAt(2) + "_hx.png").toString()));
            iv.setOpacity(0.5);
        }

        /* set up the piece */
        iv.setRotate((((int) placement.charAt(3)) - 'A') * 90);
        iv.setFitWidth(TILE_SIZE * 2);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        GridPane.setHalignment(iv, HPos.CENTER);
        GridPane.setValignment(iv, VPos.CENTER);
        playingBoard.getChildren().add(iv);

        GridPane.setRowSpan(iv, 2);
        GridPane.setColumnSpan(iv, 2);

        /* Ensure correct rotation and correct coordinates for the piece */
        switch (placement.charAt(3)) {
            case 'A':
                GridPane.setColumnIndex(iv, placement.charAt(0) - 'A');
                GridPane.setRowIndex(iv, placement.charAt(1) - 'A');
                break;
            case 'B':
                GridPane.setColumnIndex(iv, placement.charAt(0) - 'A' - 1);
                GridPane.setRowIndex(iv, placement.charAt(1) - 'A');
                break;
            case 'C':
                GridPane.setColumnIndex(iv, placement.charAt(0) - 'A' - 1);
                GridPane.setRowIndex(iv, placement.charAt(1) - 'A' - 1);
                break;
            case 'D':
                GridPane.setColumnIndex(iv, placement.charAt(0) - 'A');
                GridPane.setRowIndex(iv, placement.charAt(1) - 'A' - 1);
                break;
        }


    }
    /**
     * The next two functions update the score of the green and the red players respectively.
     *
     * These functions were written by Manal Mohania
     * Some minor edits by Joseph Meltzer and Zhixian Wu
     * */

    private void updateGreenScore(){
        String placement = gameState.moveHistory;
        controls.getChildren().remove(greenScore);
        int score = StratoGame.getScoreForPlacement(placement, true);
        greenScore.setText("" + score);
        controls.getChildren().add(greenScore);
        int offset = (Integer.toString(score)).length() * 15;
        greenScore.setLayoutX(790-offset);
        greenScore.setLayoutY(107);
        greenScore.setFill(Color.GREEN);
        greenScore.setFont(Font.font("", FontWeight.EXTRA_BOLD, 40));
    }

    private void updateRedScore(){
        String placement = gameState.moveHistory;
        controls.getChildren().remove(redScore);
        int score = StratoGame.getScoreForPlacement(placement, false);
        redScore.setText("" + score);
        controls.getChildren().add(redScore);
        int offset = (Integer.toString(score)).length() * 15;
        redScore.setLayoutX(870 - offset);
        redScore.setLayoutY(107);
        redScore.setFill(Color.RED);
        redScore.setFont(Font.font("", FontWeight.EXTRA_BOLD, 40));
    }

    /*The method that makes a placement*/
    /*Function by Zhixian Wu*/
    /* @param placement   The placement string*/
    private void makeGUIPlacement(String placement) {
        controls.getChildren().remove(errormessage);
        controls.getChildren().remove(aiThink);
        System.out.println("Someone tried: " + placement); /*For debugging purposes*/

        String tempMove = gameState.moveHistory.concat(placement);
        if (!StratoGame.isPlacementValid(tempMove)) { /*If the attempted move is invalid*/
            errormessage.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
            controls.getChildren().add(errormessage);
            errormessage.setLayoutX(740);
            errormessage.setLayoutY(450);
        } else {
            /*create the image that'll go on the board*/
            ImageView iv1 = new ImageView();
            iv1.setImage(new Image(Viewer.class.getResource(URI_BASE + placement.charAt(2) + "_b.png").toString()));
            iv1.setRotate((((int) placement.charAt(3)) - 'A') * 90);
            iv1.setFitWidth(TILE_SIZE * 2);
            iv1.setPreserveRatio(true);
            iv1.setSmooth(true);
            iv1.setCache(true);
            playingBoard.getChildren().add(iv1);
                /*make sure it spans two rows and columns*/
            GridPane.setRowSpan(iv1, 2);
            GridPane.setColumnSpan(iv1, 2);
               /*make sure it's centered*/
            GridPane.setHalignment(iv1, HPos.CENTER);
            GridPane.setValignment(iv1, VPos.CENTER);
            /*Place the image, in the correct rotation, in the correct place on the board*/
            switch (placement.charAt(3)) {
                case 'A':
                    GridPane.setColumnIndex(iv1, (((int) placement.charAt(0)) - 'A'));
                    GridPane.setRowIndex(iv1, (((int) placement.charAt(1)) - 'A'));
                    break;
                case 'B':
                    GridPane.setColumnIndex(iv1, (((int) placement.charAt(0)) - 'A' - 1));
                    GridPane.setRowIndex(iv1, (((int) placement.charAt(1)) - 'A'));
                    break;
                case 'C':
                    GridPane.setColumnIndex(iv1, (((int) placement.charAt(0)) - 'A' - 1));
                    GridPane.setRowIndex(iv1, (((int) placement.charAt(1)) - 'A' - 1));
                    break;
                case 'D':
                    GridPane.setColumnIndex(iv1, (((int) placement.charAt(0)) - 'A'));
                    GridPane.setRowIndex(iv1, (((int) placement.charAt(1)) - 'A' - 1));
                    break;
            }
            gameState.updateMoves(placement);
            System.out.println("makeGUIPlacement updated moveHistory: " + gameState.moveHistory);

            /*Update the heights we're supposed to display*/
            displayHeights();

            /*Update the scores displayed*/
            updateRedScore();
            updateGreenScore();

            if (soundOn) audio.play();



            /*Update the top tiles shown on the control panel, whose turn it is, and whose turn is bolded.*/
            switch (gameState.playerTurn) {
                case RED:
                    if (playerR.used_tiles<19){ /*If red still has tiles left*/
                        /*Update the red player's tile index*/
                        playerR.getNextTile();
                        /*Update the top red tile shown*/
                        ivr.setImage(new Image(Viewer.class.getResource(URI_BASE + (playerR.available_tiles).get(playerR.used_tiles) + ".png").toString()));
                        ivr.setFitWidth(80);
                        ivr.setPreserveRatio(true);
                        ivr.setSmooth(true);
                        ivr.setCache(true);
                    } else{ /*If red does not still have tiles left, say they're our of tiles*/
                        Text outoftiles = new Text("Out of\n tiles");
                        outoftiles.setFont(Font.font("", FontWeight.BOLD, 24));
                        GridPane.setColumnIndex(outoftiles,1);
                        GridPane.setRowIndex(outoftiles,0);
                        playerControls.getChildren().remove(ivr);
                        playerControls.getChildren().add(outoftiles);
                        playerR.getNextTile();
                    }
                    /*Update whose turn it is, and whose turn is bolded.*/
                    gameState.playerTurn = GREEN;
                    greentxt.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
                    redtxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
                    break;
                case GREEN:
                    if (playerG.used_tiles<19){ /*If green still has tiles left*/
                        /*Update the red player's tile index*/
                        playerG.getNextTile();
                        /*Update the top green tile shown*/
                        ivg.setImage(new Image(Viewer.class.getResource(URI_BASE + (playerG.available_tiles).get(playerG.used_tiles) + ".png").toString()));
                        ivg.setFitWidth(80);
                        ivg.setPreserveRatio(true);
                        ivg.setSmooth(true);
                        ivg.setCache(true);
                    } else{ /*If green does not still have tiles left, say they're out of tiles*/
                        Text outoftiles = new Text("Out of\n tiles");
                        outoftiles.setFont(Font.font("", FontWeight.BOLD, 24));
                        GridPane.setColumnIndex(outoftiles,0);
                        GridPane.setRowIndex(outoftiles,0);
                        playerControls.getChildren().remove(ivg);
                        playerControls.getChildren().add(outoftiles);
                        playerG.getNextTile();
                    }
                    /*Update whose turn it is, and whose turn is bolded.*/
                    gameState.playerTurn = RED;
                    greentxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
                    redtxt.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
                    break;
                case BLACK:
                    gameState.playerTurn = GREEN;
                    break;
            }
            /*Update the number of tiles left*/
            updateTilesLeft();

            /*Checks if the game is over. If it is, we clear the board and display the winner.*/
            if (gameState.moveHistory.length() > MAX_TILES*8) {
                clickablePanes.getChildren().clear();
                playingBoard.setOpacity(0.2);
                heightLabels.setOpacity(0.2);
                /*If green wins*/
                if (Scoring.getWinner(gameState.moveHistory)){
                    Text score = new Text("Green Wins!");
                    score.setFill(Color.GREEN);
                    score.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
                    placementGrp.getChildren().add(score);
                    score.setLayoutX(280);
                    score.setLayoutY(300);
                } else{ /*if red wins*/
                    Text score = new Text("Red Wins!");
                    score.setFill(Color.RED);
                    score.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
                    placementGrp.getChildren().add(score);
                    score.setLayoutX(290);
                    score.setLayoutY(300);
                }
            }
        }
    }

    private void makeAIMove(){
        if (gameState.moveHistory.length()<=MAX_TILES*8){
            if (gameState.playerTurn==GREEN){
                char greenTile = (char) (playerG.available_tiles).get(playerG.used_tiles);
                char redTile = (char) (playerR.available_tiles).get(playerR.used_tiles);

                String opponent = "";

                switch (gameState.greenPlayer){
                    case EASY:
                        opponent = genMoveEasy(gameState.moveHistory, greenTile, redTile);
                        break;
                    case MEDIUM:
                        opponent = genMoveMedium(gameState.moveHistory, greenTile, redTile);
                        break;
                    case HARD:
                        opponent = generateMove(gameState.moveHistory, greenTile, redTile);
                        break;
                    case CHEATING:
                        opponent = genMoveCheating(gameState.moveHistory, playerG, playerR);
                        break;
                }

                makeGUIPlacement(opponent);
            } else{
                char greenTile = (char) (playerG.available_tiles).get(playerG.used_tiles);
                char redTile = (char) (playerR.available_tiles).get(playerR.used_tiles);

                String opponent = "";

                switch (gameState.redPlayer){
                    case EASY:
                        opponent = genMoveEasy(gameState.moveHistory, redTile, greenTile);
                        break;
                    case MEDIUM:
                        opponent = genMoveMedium(gameState.moveHistory, redTile, greenTile);
                        break;
                    case HARD:
                        opponent = generateMove(gameState.moveHistory, redTile, greenTile);
                        break;
                    case CHEATING:
                        opponent = genMoveCheating(gameState.moveHistory, playerR, playerG);
                        break;
                }

                makeGUIPlacement(opponent);
            }
        }
    }

    /*Display the height at each position*/
    /*Function by Zhixian Wu*/
    private void displayHeights(){
        /*Clear existing heights*/
        heightLabels.getChildren().clear();
        /*Make 2D array of the height at each position*/
        int[][] heights = heightArray(gameState.moveHistory);
        /*Recursively go through each tile and label its height*/
        for (int i=0; i<BOARD_SIZE;i++){
            for (int j=0; j<BOARD_SIZE; j++){
                String tall = Integer.toString(heights[i][j]);
                if (heights[i][j]>1){
                    Text label1 = new Text(tall);
                    label1.setFill(Color.WHITE);
                    label1.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                    heightLabels.getChildren().add(label1);
                    GridPane.setRowIndex(label1,j);
                    GridPane.setColumnIndex(label1,i);
                    GridPane.setHalignment(label1, HPos.CENTER);
                    GridPane.setValignment(label1, VPos.CENTER);
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Stratopolis");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image((Viewer.class.getResource(URI_BASE + "icon.png").toString())));
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT, WHITESMOKE);

        root.getChildren().add(controls);
        root.getChildren().add(placementGrp);

        initialSettings();

        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
