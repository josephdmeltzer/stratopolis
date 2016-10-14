package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
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
import static comp1110.ass2.Difficulty.*;
import static comp1110.ass2.Player.MAX_TILES;
import static comp1110.ass2.StratoGame.*;
import static javafx.scene.paint.Color.*;

public class Board extends Application {

/*The majority of this class was done by Zhixian Wu, with some functions and
layout by Manal Mohania and Joseph Meltzer*/

/*All elements using CSS was done by Manal Mohania, with inspiration and any
* pieces of adapted code in the G-originality*/

/*OVERVIEW: The first function called by the stage is initialSettings(), which
* creates the first screen many buttons to choose the playing mode (Human v.s
* Hard AI, Human vs. Human, Easy AI vs. Cheating AI, etc.
* (And buttons for the instructions, sound, etc.)*/

/*The difficulty buttons change the GameState, which changes what kind of board
* is created, what difficulty AI to call, etc. */

/*Whenyou click 'Start', makeGame() is called
* makeGame() calls makeControls() and makeBoard(). */

/*makeControls() is pretty much the same for all playing modes, except it omits
* a "Rotate" button if it's single-player. And if the game is AI vs. AI, it creates
* a button Next Move which progresses the game when clicked*/

/*makeBoard() is pretty much the same again.
* makeBoard() modifies some GridPanes:
 * 1. playingBoard so it looks like a board
 * 2. heightLabels so its rows and columns align with the playingBoard
 * 3. clickablePanes so its rows and columns align with the playingBoard
 * The big difference is that depending if the game is Human vs. AI, AI vs. Human,
 * or Human vs. Human, it calls different addPane function: addPanePlayerGreen,
 * addPanePlayerRed, or addPaneTwoPlayer.
 * If it's AI vs. Human, it also makes the AI's first move by calling makeAIMove.
 * If it's AI vs. AI, it doesn't add clickable panes to the board*/

/*Each 'addPane' function creates a pane at the specified row and column
* on the GridPane clickablePanes.
* When a pane is clicked, the two player version of the function makes a
* move (by calling makeGUIPlacement) based on whose turn it is.
* The one player version makes the player's move and calls makeAIMove().
* These panes also hold the events for the previews of tile placements*/

/*Apart from putting an image in the right place, the makeGUIPlacement function
* modifies whose turn it is, how far through their stack of tiles each player
* is at, the heights of the tiles (by calling displayHeights()), what the current
* score is (by calling updateScores()), check if the game is over and display
* the Game Over Screen if it is, etc.*/



    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final String URI_BASE = "assets/";
    private static final int TILE_SIZE = 25; /*This is half the width of each tile*/
    private static final int BOARD_SIZE = 26; /*As in a 26x26 board*/
    private static final String PLACEMENT_URI = Viewer.class.getResource(URI_BASE
            + "sound.wav").toString();

    /*Objects that need to be accessible to many functions.*/
    private GameState gameState;
    private Player playerG;
    private Player playerR;

    /*Nodes that need to be accessible by many functions.*/
    private ImageView ivg = new ImageView();
    private ImageView ivr = new ImageView();
    private Text greentxt = new Text("Green");
    private Text redtxt = new Text("Red");
    private Button rotateG = new Button("Rotate");
    private Button rotateR = new Button("Rotate");
    private Button nextMove = new Button("Next Move");
    private Text errorMessage = new Text("Invalid move!");
    private Text aiThink = new Text("Thinking...");
    private Text redScore = new Text("1");
    private Text greenScore = new Text("1");
    private Text redTilesLeft = new Text("");
    private Text greenTilesLeft = new Text("");
    private ImageView sound_icon = new ImageView();

    /*Various Groups that organise the screen.*/
    private final Group root = new Group();
    private final Group popUp1 = new Group();
    private final Group popUp2 = new Group();
    private final Group controls = new Group();
    private final GridPane playerControls = new GridPane();
    private final Group placementGrp = new Group();
    private final GridPane playingBoard = new GridPane();
    private final GridPane heightLabels = new GridPane();
    private final GridPane clickablePanes = new GridPane();

    /*We change the background colour the the scene in the game*/
    private Scene scene;

    /*A counter that tells you if this is the first game played*/
    private boolean firstGame = true;
    /*If the sound is muted*/
    private boolean soundOn = true;

    /*the audio clip played when a placement is made*/
    /*The sound was downloaded from
    * <http://www.sounds.beachware.com/2illionzayp3may/jspjrz/SWITCH.mp3>*/
    private final AudioClip audio = new AudioClip(PLACEMENT_URI);


    /*Function mostly by Zhixian Wu, with all button styling by Manal Mohania*/
    private void initialSettings() {

        /*The scene actually changes colour from the start screen to the actual game.
        * This was done by Manal Mohania*/
        scene.setFill(Color.LIGHTGREY);
        /*Set the opacity back to normal and re-enable a button after the last game ended*/
        placementGrp.setOpacity(1);
        playingBoard.setOpacity(1);
        heightLabels.setOpacity(1);
        nextMove.setDisable(false);

        /*A new game*/
        gameState = new GameState(BLACK, HUMAN, HUMAN);

        /*The Stratopolis logo on the start screen*/
        ImageView logo = new ImageView();
        logo.setImage(new Image(Viewer.class.getResource(URI_BASE + "stratopolis"
                + ".png").toString()));
        placementGrp.getChildren().add(logo);
        logo.setLayoutX(220);
        logo.setLayoutY(180);

     /*The options for the game: Human vs Hard AI, etc.*/
        /*This is the text describing these options*/
        Text greenText = new Text("Player Green: Human");
        greenText.setFill(Color.GREEN);
        greenText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Text green1 = new Text("Human: ");
        green1.setFill(Color.GREEN);
        green1.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Text green2 = new Text("AI:   ");
        green2.setFill(Color.GREEN);
        green2.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Text redText = new Text("Player Red: Human");
        redText.setFill(Color.RED);
        redText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Text red1 = new Text("Human: ");
        red1.setFill(Color.RED);
        red1.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Text red2 = new Text("AI:   ");
        red2.setFill(Color.RED);
        red2.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        /*Each of these buttons tell the game which players you want to be
        * human, and which to be AIs*/
        Button greenHuman = new Button("Human");
        greenHuman.setOnAction(event-> {
            gameState.greenPlayer = HUMAN;
            greenText.setText("Player Green: Human");
        });

        Button greenEasy = new Button("Easy");
        greenEasy.setOnAction(event-> {
            gameState.greenPlayer = EASY;
            greenText.setText("Player Green: Easy");
        });

        Button greenMedium = new Button("Medium");
        greenMedium.setOnAction(event-> {
            gameState.greenPlayer = MEDIUM;
            greenText.setText("Player Green: Medium");
        });

        Button greenHard = new Button("Hard");
        greenHard.setOnAction(event-> {
            gameState.greenPlayer = HARD;
            greenText.setText("Player Green: Hard");
        });

        Button greenCheating = new Button("Cheating");
        greenCheating.setOnAction(event-> {
            gameState.greenPlayer = CHEATING;
            greenText.setText("Player Green: Cheating");
        });

        Button redHuman = new Button("Human");
        redHuman.setOnAction(event-> {
            gameState.redPlayer = HUMAN;
            redText.setText("Player Red: Human");
        });

        Button redEasy = new Button("Easy");
        redEasy.setOnAction(event-> {
            gameState.redPlayer = EASY;
            redText.setText("Player Red: Easy");
        });

        Button redMedium = new Button("Medium");
        redMedium.setOnAction(event-> {
            gameState.redPlayer = MEDIUM;
            redText.setText("Player Red: Medium");
        });

        Button redHard = new Button("Hard");
        redHard.setOnAction(event-> {
            gameState.redPlayer = HARD;
            redText.setText("Player Red: Hard");
        });

        Button redCheating = new Button("Cheating");
        redCheating.setOnAction(event-> {
            gameState.redPlayer = CHEATING;
            redText.setText("Player Red: Cheating");
        });

        DropShadow shadow = new DropShadow();
        for (Button b : new Button[] {greenHuman, greenEasy, greenMedium, greenHard, greenCheating,
                                      redHuman, redEasy, redMedium, redHard, redCheating}) {
            b.setStyle("-fx-font: 14 Arial; -fx-background-color: \n" +
                    "        #090a0c,\n" +
                    "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                    "        linear-gradient(#20262b, #191d22),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, " +
                    "rgba(114,131,148,0.9), rgba(255,255,255,0));" +
                    "-fx-text-fill: white;");
            b.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> b.setEffect(shadow));
            b.addEventHandler(MouseEvent.MOUSE_EXITED, event -> b.setEffect(null));
        }

        /*Layout of the options by Zhixian Wu and Manal Mohania*/
        HBox ghb1 = new HBox(5);
        ghb1.getChildren().addAll(green1,greenHuman);
        HBox ghb2 = new HBox(5);
        ghb2.getChildren().addAll(green2,greenEasy,greenMedium,greenHard,greenCheating);
        ghb2.setMargin(green2, new Insets(0,28,0,0));
        ghb2.setSpacing(7);
        VBox green = new VBox(5);
        green.getChildren().addAll(greenText,ghb1,ghb2);
        green.setSpacing(15);
        green.setLayoutX(30);
        green.setLayoutY(480);

        HBox rhb1 = new HBox(5);
        rhb1.getChildren().addAll(red1,redHuman);
        HBox rhb2 = new HBox(5);
        rhb2.getChildren().addAll(red2,redEasy,redMedium,redHard,redCheating);
        rhb2.setMargin(red2, new Insets(0,28,0,0));
        rhb2.setSpacing(7);
        VBox red = new VBox(5);
        red.getChildren().addAll(redText,rhb1,rhb2);
        red.setSpacing(15);
        red.setLayoutX(540);
        red.setLayoutY(480);


        /*This button starts the game*/
        Button startGame = new Button("Start");
        startGame.setOnAction(event-> {
            placementGrp.getChildren().clear();
            makeGame();
        });
        startGame.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#ffd65b, #e68400),\n" +
                "        linear-gradient(#ffef84, #f2ba44),\n" +
                "        linear-gradient(#ffea6a, #efaa22),\n" +
                "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9)," +
                " rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #654b00;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 26px;\n" +
                "    -fx-padding: 10 25 10 25;");

        startGame.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> startGame.setEffect(shadow));
        startGame.addEventHandler(MouseEvent.MOUSE_EXITED, event -> startGame.setEffect(null));
        startGame.setLayoutX(400);
        startGame.setLayoutY(620);

        /*The mute button by Zhixian Wu*/
        /*The mute button's image*/
        /*The image for unmuted sound is from
        * <https://pixabay.com/en/icon-loudspeaker-speaker-horn-1628258/>
        * The image from the muted sound is an edited version of that image*/
        if (soundOn) sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon"
                + ".png").toString()));
        else sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon_off"
                + ".png").toString()));
        sound_icon.setFitWidth(25);
        sound_icon.setPreserveRatio(true);
        sound_icon.setSmooth(true);
        sound_icon.setCache(true);
        sound_icon.setLayoutX(900);
        sound_icon.setLayoutY(15);
        /*The mute button's clickable pane*/
        Pane sound_pane = new Pane();
        sound_pane.setPrefSize(25,25);
        sound_pane.setOnMouseClicked(event -> {
            if (soundOn){
                sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon_off"
                        + ".png").toString()));
                sound_icon.setFitWidth(25);
                sound_icon.setPreserveRatio(true);
                sound_icon.setSmooth(true);
                sound_icon.setCache(true);
                soundOn = false;
            } else{
                sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon"
                        + ".png").toString()));
                sound_icon.setFitWidth(25);
                sound_icon.setPreserveRatio(true);
                sound_icon.setSmooth(true);
                sound_icon.setCache(true);
                soundOn = true;
            }
        });
        sound_pane.setLayoutX(900);
        sound_pane.setLayoutY(15);

        /*The button that brings up the instructions*/
        Button instructions = new Button("?");
        instructions.setOnAction(event-> getInstructions());
        instructions.setStyle("-fx-background-color: #9932cc;" +
                "-fx-background-radius: 55em; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px; " +
                "-fx-max-width: 30px; " +
                "-fx-max-height: 30px;" +
                "-fx-text-fill: #ffd65b"
        );
        instructions.addEventHandler(MouseEvent.MOUSE_ENTERED, event ->
                instructions.setEffect(shadow));
        instructions.addEventHandler(MouseEvent.MOUSE_EXITED, event ->
                instructions.setEffect(null));
        instructions.setLayoutX(860);
        instructions.setLayoutY(12);

        placementGrp.getChildren().addAll(green, red, startGame, instructions,
                sound_icon, sound_pane);
    }

    /*Function by Zhixian Wu. This function displays the instructions when called.*/
    private void getInstructions(){
        /*Disable the interactive stuff from these groups when the instructions are up*/
        placementGrp.setDisable(true);
        controls.setDisable(true);

        /*Layout*/
        GridPane mainInstruc = new GridPane();
        mainInstruc.setLayoutY(90);
        mainInstruc.setLayoutX(130);
        mainInstruc.setHgap(5);
        mainInstruc.setVgap(5);
        /*A nice outline around the instructions*/
        Rectangle thickBorder = new Rectangle(655,470,Color.BEIGE);
        thickBorder.setArcHeight(7);
        thickBorder.setArcWidth(7);
        thickBorder.setLayoutX(120);
        thickBorder.setLayoutY(80);
        thickBorder.setOpacity(0.5);

        /*Some of the instructions copied from
        * <https://boardgamegeek.com/boardgame/125022/stratopolis>*/
        Text instructions = new Text("\n  Stratopolis a two player strategy based game." +
                " Each player starts the game with twenty L-shaped tiles, each comprising "
                + "three squares; one player has tiles showing all green squares, green and "
                + "neutral squares, or two green squares and one red square, while the other "
                + "player's tiles reverse red and green. Players shuffle and stack these "
                + "tiles face down, revealing only the topmost tile. \n \n"
                + "  To start the game, a two-square tile (one red, one green) is placed "
                + "on the table. Players then take turns adding their topmost tile to "
                + "the display. A placement is made by clicking on the board. Invalid placements "
                + "have duller preview images. \n\n"
                + "  Apart from using the 'Rotate' button, the tiles can be rotated by scrolling "
                + "while hovering over the board.\n\n"
                + "  A tile can be placed (1) on the table with at least one "
                + "edge adjacent to an edge in play or (2) on top of at least two tiles "
                + "already in play. When placed on a higher level, each square of the "
                + "tile must be supported, the tile must be level, and red and green "
                + "tile must be supported, the tile must be level, and red and green "
                + "squares cannot cover one another. (Every other color play – such as "
                + "green on neutral or red on red – is legal.)\n \n"
                + "  Each player's score is the area of the largest connected area of "
                + "their colour, times the maximum height of that area.\n \n"
                + "  If a tie breaker is required, the next largest area for both "
                + "players is used. If a tie breaker is still required, we go on "
                + "to the next largest area, and so on. If this does not produce a "
                + "winner, the winner is determined randomly.\n\n"
                + "  You can choose a two-player game, play against an AI of any "
                + "difficulty (the cheating AI can peek into both your decks), or "
                + "watch two AIs play against each other. The two-AI game is advanced "
                + "by clicking the 'Next Move' button. \n"
        );

        instructions.setFont(Font.font("Arial", 16));
        instructions.setWrappingWidth(610);

        /*The scroll-capable pane the instructions go in*/
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(instructions);
        scroll.setPrefViewportHeight(400.0);
        scroll.setPrefViewportWidth(620.0);

        /*The button that removes the instructions and makes the groups interactive again*/
        Button exitBtn = new Button("x");
        exitBtn.setOnAction(event->  {
            root.getChildren().remove(popUp1);
            placementGrp.setDisable(false);
            controls.setDisable(false);
        } );
        exitBtn.setStyle("-fx-font: 14 Arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), " +
                "  rgba(255,255,255,0));" +
                "-fx-text-fill: white;");
        DropShadow shadow = new DropShadow();
        exitBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, event ->  exitBtn.setEffect(shadow));
        exitBtn.addEventHandler(MouseEvent.MOUSE_EXITED, event -> exitBtn.setEffect(null));

        /*Layout*/
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
    private void makeGame(){
        playerG = new PlayerG();
        playerR = new PlayerR();

        /*Make the playing board*/
        makeBoard();

        /*Makes the controls for the game, separately from the board*/
        makeControls();
    }

    /*Function mostly by Zhixian Wu, with the running score and button styling by Manal Mohania*/
    private void makeControls(){

        scene.setFill(Color.WHITESMOKE);
        /*Make the control pane as a GridPane. This is the stuff on the right*/
        playerControls.setPrefSize(120, 200);
        playerControls.setMaxSize(120, 200);

        /*The text labeling Green and Red's tiles, which you see on the right*/
        greentxt.setFill(Color.GREEN);
        greentxt.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        redtxt.setFill(Color.RED);
        redtxt.setFont(Font.font("Verdana", 16));

        /*The mute button's image*/
        if (soundOn) sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon"
                + ".png").toString()));
        else sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon_off"
                + ".png").toString()));
        sound_icon.setFitWidth(25);
        sound_icon.setPreserveRatio(true);
        sound_icon.setSmooth(true);
        sound_icon.setCache(true);
        sound_icon.setLayoutX(900);
        sound_icon.setLayoutY(15);
        /*The mute button's clickable pane*/
        Pane sound_pane = new Pane();
        sound_pane.setPrefSize(25,25);
        sound_pane.setOnMouseClicked(event -> {
            if (soundOn){
                sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon_off"
                        + ".png").toString()));
                sound_icon.setFitWidth(25);
                sound_icon.setPreserveRatio(true);
                sound_icon.setSmooth(true);
                sound_icon.setCache(true);
                soundOn = false;
            } else{
                sound_icon.setImage(new Image(Viewer.class.getResource(URI_BASE + "sound_icon"
                        + ".png").toString()));
                sound_icon.setFitWidth(25);
                sound_icon.setPreserveRatio(true);
                sound_icon.setSmooth(true);
                sound_icon.setCache(true);
                soundOn = true;
            }
        });
        sound_pane.setLayoutX(900);
        sound_pane.setLayoutY(15);

        /*The button that brings up the instructions*/
        Button instructions = new Button("?");
        instructions.setOnAction(event-> getInstructions());
        instructions.setStyle("-fx-background-color: #9932cc;" +
                "-fx-background-radius: 55em; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px; " +
                "-fx-max-width: 30px; " +
                "-fx-max-height: 30px;" +
                "-fx-text-fill: #ffd65b"
        );
        DropShadow shadow = new DropShadow();
        instructions.addEventHandler(MouseEvent.MOUSE_ENTERED, event ->
                instructions.setEffect(shadow));
        instructions.addEventHandler(MouseEvent.MOUSE_EXITED, event ->
                instructions.setEffect(null));
        instructions.setLayoutX(860);
        instructions.setLayoutY(12);

        controls.getChildren().addAll(sound_icon,sound_pane,instructions);

        /*The tiles at the "top" of each player's "stack", displayed on the right*/
        ivg.setImage(new Image(Viewer.class.getResource(URI_BASE +
                (playerG.available_tiles).get(playerG.used_tiles) + ".png").toString()));
        ivg.setRotate((((int) (playerG.rotation)-'A')*90));
        ivg.setFitWidth(80);
        ivg.setPreserveRatio(true);
        ivg.setSmooth(true);
        ivg.setCache(true);

        ivr.setImage(new Image(Viewer.class.getResource(URI_BASE +
                (playerR.available_tiles).get(playerR.used_tiles) + ".png").toString()));
        ivr.setRotate((((int) (playerR.rotation)-'A')*90));
        ivr.setFitWidth(80);
        ivr.setPreserveRatio(true);
        ivr.setSmooth(true);
        ivr.setCache(true);

        /*The events for the buttons that rotate the tiles*/
        rotateG.setOnAction(event-> {
            playerG.rotateTile();
            ivg.setRotate((((int) (playerG.rotation)-'A')*90));
        });
        rotateR.setOnAction(event-> {
            playerR.rotateTile();
            ivr.setRotate((((int) (playerR.rotation)-'A')*90));
        });



        /*Adding the nodes. We may omit the a rotate button depending on the playingMode*/
        playerControls.getChildren().addAll(greentxt,redtxt,ivg,ivr);
        if (gameState.greenPlayer==HUMAN) playerControls.getChildren().add(rotateG);
        if (gameState.redPlayer==HUMAN) playerControls.getChildren().add(rotateR);

        /*Layout for the controls*/
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
            placementGrp.setOpacity(1);
            controls.getChildren().clear();
            placementGrp.getChildren().clear();
            playingBoard.getChildren().clear();
            heightLabels.getChildren().clear();
            clickablePanes.getChildren().clear();
            playerControls.getChildren().clear();
            root.getChildren().remove(popUp2);

            firstGame = false;

            initialSettings();
        });
        controls.getChildren().add(menu);
        menu.setLayoutX(820);
        menu.setLayoutY(650);

        menu.setStyle("-fx-font: 14 Arial; -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), " +
                "    rgba(255,255,255,0));" +
                "-fx-text-fill: white;");

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
        score.setLayoutX(800);
        score.setLayoutY(65);
        controls.getChildren().add(score);

        greenScore.setLayoutX(750);
        greenScore.setLayoutY(110);
        greenScore.setFill(Color.GREEN);
        greenScore.setFont(Font.font("Gentium", 44));

        redScore.setLayoutX(830);
        redScore.setLayoutY(110);
        redScore.setFill(Color.RED);
        redScore.setFont(Font.font("Gentium", 44));
        controls.getChildren().addAll(greenScore,redScore);
        updateScores();

        /*Counter of tiles left by Zhixian Wu*/
        Text tiles_left = new Text("TILES LEFT");

        greenTilesLeft.setFill(Color.GREEN);
        greenTilesLeft.setFont(Font.font("Gentium", 24));

        redTilesLeft.setFill(Color.RED);
        redTilesLeft.setFont(Font.font("Gentium", 24));

        updateTilesLeft();

        GridPane tileCounter = new GridPane();
        tileCounter.getChildren().addAll(tiles_left,greenTilesLeft,redTilesLeft);
        for (int i = 0; i < 2; i++) {
            ColumnConstraints column = new ColumnConstraints(85);
            tileCounter.getColumnConstraints().add(column);
        }
        /*Layout of tiles left*/
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

        tileCounter.setLayoutX(TILE_SIZE*BOARD_SIZE+70);
        tileCounter.setLayoutY(360);
        controls.getChildren().add(tileCounter);

        /*If both players are AI: */
        if (gameState.greenPlayer!=HUMAN && gameState.redPlayer!=HUMAN) {
            /*Makes the first move*/
            makeGUIPlacement("MMUA");

            /*The button that tells the AI to make a move, click this to progress the game*/
            nextMove.setOnMousePressed(event->  {
                if (gameState.moveHistory.length()<=MAX_TILES*8){
                    aiThink.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                    controls.getChildren().add(aiThink);
                    aiThink.setLayoutX(750);
                    aiThink.setLayoutY(450);
                }
            });
            nextMove.setOnAction(event->  makeAIMove());

            nextMove.setStyle("-fx-font: 14 Arial; -fx-background-color: \n" +
                    "        #090a0c,\n" +
                    "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                    "        linear-gradient(#20262b, #191d22),\n" +
                    "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), " +
                    "    rgba(255,255,255,0));" +
                    "-fx-text-fill: white;");

            nextMove.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> nextMove.setEffect(shadow));
            nextMove.addEventHandler(MouseEvent.MOUSE_EXITED, event -> nextMove.setEffect(null));
            nextMove.setLayoutX(TILE_SIZE*BOARD_SIZE+60);
            nextMove.setLayoutY(650);
            controls.getChildren().add(nextMove);
        }

    }
    /*Function by Zhixian Wu*/
    /*It updates the TILES LEFT field on the board*/
    private void updateTilesLeft(){
        if (gameState.moveHistory.length()<=MAX_TILES*8-4){
            String green = Integer.toString(MAX_TILES-playerG.used_tiles);
            String red = Integer.toString(MAX_TILES-playerR.used_tiles);
            greenTilesLeft.setText(green);
            redTilesLeft.setText(red);
        } else{
            if (MAX_TILES*8-4<=gameState.moveHistory.length() &&
                    gameState.moveHistory.length()<=MAX_TILES*8){
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
            /*Makes the first move*/
            makeGUIPlacement("MMUA");
        }
        if (gameState.greenPlayer==HUMAN && gameState.redPlayer!=HUMAN) {
            for (int i=0; i<BOARD_SIZE;i++){
                for (int j=0; j<BOARD_SIZE; j++){
                    addPanePlayerGreen(i,j);
                    addPanePlayerGreen(j,i);
                }
            }
            /*Makes the first move*/
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
            /*Makes the first move*/
            makeGUIPlacement("MMUA");

            /*Makes the opponent's move first*/
            char redTile = (char) (playerR.available_tiles).get(playerR.used_tiles);
            char greenTile = (char) (playerG.available_tiles).get(playerG.used_tiles);

            String opponent = genMoveEasy(gameState.moveHistory, greenTile);
            if (gameState.greenPlayer == MEDIUM) opponent =
                    genMoveMedium(gameState.moveHistory, greenTile, redTile);
            if (gameState.greenPlayer == HARD) opponent =
                    generateMove(gameState.moveHistory, greenTile, redTile);

            makeGUIPlacement(opponent);
        }

        /*Layout*/
        clickablePanes.setLayoutX(offset);
        clickablePanes.setLayoutY(offset);

        /*The must be added in this order so the heights show on top of the tiles
        * and the interactive panes are on top of all of them.*/
        placementGrp.getChildren().addAll(thickBorder,playingBoard,heightLabels,clickablePanes);
    }

    /**
     * The clickable panes for when there are two players
     * Function by Zhixian Wu, Manal Mohania, and Joseph Meltzer.
     * Idea of how to recursively creates panes that remember what position they were
     * created for is from StackOverflow:
     * <http://stackoverflow.com/questions/31095954/how-to-get-gridpane-row-and-column
       -ids-on-mouse-entered-in-each-cell-of-grid-in>
     * @param colIndex      The column the pane is on
     * @param rowIndex      The row the pane is on
     */
    private void addPaneTwoPlayer(int colIndex, int rowIndex){
        Pane pane = new Pane();
        ImageView iv = new ImageView();

        char col = (char) (colIndex+'A');
        char row = (char) (rowIndex+'A');

        /*Event by Zhixian Wu, this makes the player's move when they click on a pane*/
        pane.setOnMouseClicked(event -> {
                switch (gameState.playerTurn){
                    case RED:
                        String placement = String.valueOf(col) + row +
                                (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
                        makeGUIPlacement(placement);
                        break;
                    case GREEN:
                        String placement2 = String.valueOf(col) + row +
                                (playerG.available_tiles).get(playerG.used_tiles) + playerG.rotation;
                        makeGUIPlacement(placement2);
                        break;
                    case BLACK:
                        makeGUIPlacement("MMUA");
                        break;
                }

        });

        /*Event by Joseph Meltzer, rotates the piece on scrolling*/
        pane.setOnScroll(event -> {
            removeTempPlacement(iv);
            if (gameState.playerTurn==GREEN) {
                playerG.rotateTile();
                if (event.getDeltaY()>0) {
                    playerG.rotateTile(); playerG.rotateTile();
                }
                ivg.setRotate((((int) (playerG.rotation) - 'A') * 90));
                iv.setRotate(((int) (playerG.rotation) - 'A') * 90);
            }
            else {
                playerR.rotateTile();
                if (event.getDeltaY()>0) {
                    playerR.rotateTile(); playerR.rotateTile();
                }
                ivr.setRotate(((int) (playerR.rotation) - 'A') * 90);
                iv.setRotate(((int) (playerR.rotation) - 'A') * 90);
            }

            switch (gameState.playerTurn){
                case RED:
                    String placement = "" + col + row +
                            (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
                    makeTempPlacement(iv, placement);
                    break;
                case GREEN:
                    String placement2 = "" + col + row +
                            (playerG.available_tiles).get(playerG.used_tiles) + playerG.rotation;
                    makeTempPlacement(iv, placement2);
                    break;
            }
        });

        /*Event by Manal Mohania, this creates the preview piece*/
        pane.setOnMouseEntered(event -> {

            switch (gameState.playerTurn){
                case RED:
                    String placement = "" + col + row +
                            (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
                    makeTempPlacement(iv, placement);
                    break;
                case GREEN:
                    String placement2 = "" + col + row +
                            (playerG.available_tiles).get(playerG.used_tiles) + playerG.rotation;
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

    /**
     * The clickable panes for when the human player is Green
     * Function by Zhixian Wu, Manal Mohania, and Joseph Meltzer.
     * Idea of how to recursively creates panes that remember what position
     * they were created for is from StackOverflow:
     * <http://stackoverflow.com/questions/31095954/how-to-get-gridpane-row
       -and-column-ids-on-mouse-entered-in-each-cell-of-grid-in>
     * @param colIndex      The column the pane is on
     * @param rowIndex      The row the pane is on
     */
    private void addPanePlayerGreen(int colIndex, int rowIndex){
        Pane pane = new Pane();
        ImageView iv = new ImageView();
        char col = (char) (colIndex+'A');
        char row = (char) (rowIndex+'A');

        /*Event by Joseph Meltzer, rotates the piece on scrolling*/
        pane.setOnScroll(event -> {
            removeTempPlacement(iv);
            playerG.rotateTile();
            if (event.getDeltaY()>0) {
                playerG.rotateTile(); playerG.rotateTile();
            }
            ivg.setRotate((((int) (playerG.rotation) - 'A') * 90));
            iv.setRotate(((int) (playerG.rotation) - 'A') * 90);

            String placement2 = "" + col + row +
                    (playerG.available_tiles).get(playerG.used_tiles) + playerG.rotation;
            makeTempPlacement(iv, placement2);
            });

        /*Event by Manal Mohania, this adds the preview piece*/
        pane.setOnMouseEntered(event -> {

            String placement = "" + col + row +
                    playerG.available_tiles.get(playerG.used_tiles) + playerG.rotation;
            makeTempPlacement(iv, placement);
        });

        /*Event by Manal Mohania, this removes the preview piece*/
        pane.setOnMouseExited(event -> removeTempPlacement(iv));

        /*Event by Zhixian Wu. This event makes the player's move when they press on a pane.*/
        pane.setOnMousePressed(event -> {
            String placement = String.valueOf(col) + row +
                    (playerG.available_tiles).get(playerG.used_tiles) + playerG.rotation;
            makeGUIPlacement(placement);

            int length = gameState.moveHistory.length()-2;
            /*This adds the image that suggests tha AI is thinking.
            We only suggest the AI is thinking if it actually is, i.e. your
            move was valid, i.e. if the last move in moveHistory was yours*/
            if ('K'<=gameState.moveHistory.charAt(length) && gameState.moveHistory.charAt(length)<='T'){
                aiThink.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                controls.getChildren().add(aiThink);
                aiThink.setLayoutX(750);
                aiThink.setLayoutY(450);
            }
        });

        /*Event by Zhixian Wu. This event causes the AI to make its move when the mouse is released.*/
        pane.setOnMouseReleased(event -> {
            int length = gameState.moveHistory.length()-2;

            /*The AI only makes its move if your move was valid, i.e. if the
            last move in moveHistory was yours*/
            if ('K'<=gameState.moveHistory.charAt(length) &&
                    gameState.moveHistory.charAt(length)<='T'){
                makeAIMove();
            }
        });

        clickablePanes.getChildren().add(pane);
        GridPane.setRowIndex(pane,rowIndex);
        GridPane.setColumnIndex(pane,colIndex);
    }

    /**
     * The clickable panes for when the human player is Red
     * Function by Zhixian Wu, Manal Mohania, and Joseph Meltzer.
     * Idea of how to recursively creates panes that remember what position they
     * were created for is from StackOverflow (URL in the in the C-u5807060 originality statement)
     * @param colIndex      The column the pane is on
     * @param rowIndex      The row the pane is on
     */
    private void addPanePlayerRed(int colIndex, int rowIndex){
        Pane pane = new Pane();
        ImageView iv = new ImageView();
        char col = (char) (colIndex+'A');
        char row = (char) (rowIndex+'A');

        /*Event by Joseph Meltzer, rotates the piece on scrolling*/
        pane.setOnScroll(event -> {
            removeTempPlacement(iv);
            playerR.rotateTile();
            if (event.getDeltaY()>0) {
                playerR.rotateTile(); playerR.rotateTile();
            }
            ivr.setRotate(((int) (playerR.rotation) - 'A') * 90);
            iv.setRotate(((int) (playerR.rotation) - 'A') * 90);

            String placement = "" + col + row +
                    (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
            makeTempPlacement(iv, placement);
        });

        /*Event by Manal Mohania, the adds the preview piece*/
        pane.setOnMouseEntered(event -> {
            String placement = "" + col + row +
                    (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
            makeTempPlacement(iv, placement);
        });

        /*Event by Manal Mohania, this removes the preview piece*/
        pane.setOnMouseExited(event -> removeTempPlacement(iv));

        /*Event by Zhixian Wu. This event makes the player's move when they press on a pane.*/
        pane.setOnMousePressed(event -> {
            String placement = String.valueOf(col) + row +
                    (playerR.available_tiles).get(playerR.used_tiles) + playerR.rotation;
            makeGUIPlacement(placement);

            int length = gameState.moveHistory.length()-2;
            /*This adds the image that suggests tha AI is thinking.
            We only suggest the AI is thinking if it actually is, i.e. your
            move was valid, i.e. if the last move in moveHistory was yours*/
            if ('A'<=gameState.moveHistory.charAt(length) &&
                    gameState.moveHistory.charAt(length)<='J'){
                aiThink.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                controls.getChildren().add(aiThink);
                aiThink.setLayoutX(740);
                aiThink.setLayoutY(450);
            }

        });

        /*Event by Zhixian Wu. This event causes the AI to make its move when the mouse is released.*/
        pane.setOnMouseReleased(event -> {
            int length = gameState.moveHistory.length()-2;

            /*The first two conditions check if your move was valid, by
            checking if the last move in moveHistory was yours. The AI only
            makes its move if your move was valid.
            *  The last condition checks if you are out of tiles, so the AI
            doesn't try to make a move you are out of tiles*/
            if ('A'<=gameState.moveHistory.charAt(length) &&
                    gameState.moveHistory.charAt(length)<='J' && gameState.moveHistory.length()<MAX_TILES*8){
                makeAIMove();
            } else{
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
     * @param iv        The preview image to be removed
     */
    private void removeTempPlacement(ImageView iv){
        if (iv == null)
            return;

        playingBoard.getChildren().remove(iv);
    }

    /**
     * This function
     * 1. creates a temporary placement upon mouseover - the placement pieces are
     *    of different opacity depending upon the validity of the placement
     * 2. ensures that the individual piece does not lie outside the board when making the placement
     * 3. removes error messages if a valid placement is reached
     *
     * @param iv          The preview image to be added
     * @param placement   the placement string
     *
     * Function by Manal Mohania
     * Minor edits by Joseph Meltzer
   */

    private void makeTempPlacement(ImageView iv, String placement){

        /*remove error messages, if any*/
        controls.getChildren().remove(errorMessage);

        /*The following ensure that the piece does not fall out of the board,
        * and thus they are not part of the preview*/
        if ((placement.charAt(0) == 'Z') && ((placement.charAt(3) == 'A')
                || (placement.charAt(3) == 'D'))) return;

        if ((placement.charAt(0) == 'A') && ((placement.charAt(3) == 'B')
                || (placement.charAt(3) == 'C'))) return;

        if ((placement.charAt(1) == 'Z') && ((placement.charAt(3) == 'A')
                || (placement.charAt(3) == 'B'))) return;

        if ((placement.charAt(1) == 'A') && ((placement.charAt(3) == 'C')
                || (placement.charAt(3) == 'D'))) return;

        /*set image according to the validity of the placement*/
        if (StratoGame.isPlacementValid(gameState.moveHistory.concat(placement))) {
            iv.setImage(new Image(Viewer.class.getResource(URI_BASE +
                    placement.charAt(2) + "_h.png").toString()));
            iv.setOpacity(0.8);
        }
        else {
            iv.setImage(new Image(Viewer.class.getResource(URI_BASE +
                    placement.charAt(2) + "_hx.png").toString()));
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
     * The next function updates the score of the green and the red players.
     *
     * These functions were written by Manal Mohania
     * Some minor edits by Joseph Meltzer and Zhixian Wu
     * */
    private void updateScores(){
        String placement = gameState.moveHistory;
        int score = StratoGame.getScoreForPlacement(placement, true);
        greenScore.setText("" + score);
        int offset = (Integer.toString(score)).length() * 15;
        greenScore.setLayoutX(790-offset);

        int score2 = StratoGame.getScoreForPlacement(placement, false);
        redScore.setText("" + score2);
        int offset2 = (Integer.toString(score2)).length() * 15;
        redScore.setLayoutX(870 - offset2);
    }

    /**
     * The method that makes a placement
     * Function by Zhixian Wu
     * @param placement     The placement string
     */
    private void makeGUIPlacement(String placement) {
        /*Remove some messages if they are on screen*/
        controls.getChildren().remove(errorMessage);
        controls.getChildren().remove(aiThink);

        String tempMove = gameState.moveHistory.concat(placement);
        if (!StratoGame.isPlacementValid(tempMove)) { /*If the attempted move is invalid*/
            errorMessage.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
            controls.getChildren().add(errorMessage);
            errorMessage.setLayoutX(740);
            errorMessage.setLayoutY(450);
        } else {
            /*create the image that'll go on the board*/
            ImageView iv1 = new ImageView();
            iv1.setImage(new Image(Viewer.class.getResource(URI_BASE +
                    placement.charAt(2) + "_b.png").toString()));
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
            /*Update the string of all the placements so far*/
            gameState.updateMoves(placement);

            /*Update the heights we're supposed to display*/
            displayHeights();

            /*Update the scores displayed*/
            updateScores();

            if (soundOn) audio.play();

            /*Update the top tiles shown on the control panel,
            * whose turn it is, and whose turn is bolded.*/
            switch (gameState.playerTurn) {
                case RED:
                    if (playerR.used_tiles<19){ /*If red still has tiles left*/
                        /*Update the red player's tile index*/
                        playerR.getNextTile();
                        /*Update the top red tile shown*/
                        ivr.setImage(new Image(Viewer.class.getResource(URI_BASE +
                                (playerR.available_tiles).get(playerR.used_tiles) +
                                ".png").toString()));
                        ivr.setFitWidth(80);
                        ivr.setPreserveRatio(true);
                        ivr.setSmooth(true);
                        ivr.setCache(true);
                    } else{ /*If red does not still have tiles left, say they're our of tiles*/
                        Text outoftiles = new Text("Out of\n tiles");
                        outoftiles.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                        GridPane.setColumnIndex(outoftiles,1);
                        GridPane.setRowIndex(outoftiles,0);
                        playerControls.getChildren().remove(ivr);
                        playerControls.getChildren().add(outoftiles);
                        playerR.getNextTile();
                    }
                    /*Update whose turn is bolded, and which rotate button is greyed out.*/
                    greentxt.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
                    redtxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
                    rotateG.setDisable(false);
                    rotateR.setDisable(true);
                    break;
                case GREEN:
                    if (playerG.used_tiles<19){ /*If green still has tiles left*/
                        /*Update the red player's tile index*/
                        playerG.getNextTile();
                        /*Update the top green tile shown*/
                        ivg.setImage(new Image(Viewer.class.getResource(URI_BASE +
                                (playerG.available_tiles).get(playerG.used_tiles) +
                                ".png").toString()));
                        ivg.setFitWidth(80);
                        ivg.setPreserveRatio(true);
                        ivg.setSmooth(true);
                        ivg.setCache(true);
                    } else{ /*If green does not still have tiles left, say they're out of tiles*/
                        Text outoftiles = new Text("Out of\n tiles");
                        outoftiles.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                        GridPane.setColumnIndex(outoftiles,0);
                        GridPane.setRowIndex(outoftiles,0);
                        playerControls.getChildren().remove(ivg);
                        playerControls.getChildren().add(outoftiles);
                        playerG.getNextTile();
                    }
                    /*Update whose turn is bolded, and which rotate button is greyed out.*/
                    greentxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
                    redtxt.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
                    rotateG.setDisable(true);
                    rotateR.setDisable(false);
                    break;
                case BLACK:
                    rotateG.setDisable(false);
                    rotateR.setDisable(true);
                    break;
            }
            /*Update whose turn it is*/
            gameState.nextTurn();

            /*Update the number of tiles left*/
            updateTilesLeft();

            /*Checks if the game is over. If it is, we display the winner.*/
            if (gameState.moveHistory.length() > MAX_TILES*8) {
                /*Get rid of the panes*/
                clickablePanes.getChildren().clear();
                /*Lower opacity of the board*/
                placementGrp.setOpacity(0.5);
                playingBoard.setOpacity(0.3);
                heightLabels.setOpacity(0.3);
                nextMove.setDisable(true);
                /*If green wins*/
                if (Scoring.getWinner(gameState.moveHistory)){
                    Text score = new Text("Green Wins!");
                    score.setStyle("-fx-stroke: black; -fx-stroke-width: 1");
                    score.setFill(Color.GREEN);
                    score.setFont(Font.font("Arial", FontWeight.BOLD, 36));
                    popUp2.getChildren().clear(); /*get rid of the last game's result*/
                    popUp2.getChildren().add(score);
                    score.setLayoutX(265);
                    score.setLayoutY(300);
                    root.getChildren().add(popUp2);
                } else{ /*if red wins*/
                    Text score = new Text("Red Wins!");
                    score.setFill(Color.RED);
                    score.setFont(Font.font("Arial", FontWeight.BOLD, 36));
                    score.setStyle("-fx-stroke: black; -fx-stroke-width: 1");
                    popUp2.getChildren().clear(); /*get rid of the last game's result*/
                    popUp2.getChildren().add(score);
                    score.setLayoutX(270);
                    score.setLayoutY(300);
                    root.getChildren().add(popUp2);
                }
                greentxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
                redtxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
            }
        }
    }

    /*Function by Zhixian Wu
    * It makes a move for the AI, which plays as whichever player whose turn it is*/
    private void makeAIMove(){
        if (gameState.moveHistory.length()<=MAX_TILES*8){ /*Checks the game is not over*/

            char greenTile = (char) (playerG.available_tiles).get(playerG.used_tiles);
            char redTile = (char) (playerR.available_tiles).get(playerR.used_tiles);

            String opponent = "";

            if (gameState.playerTurn==GREEN){ /*if the current turn is green*/
                switch (gameState.greenPlayer){
                    case EASY:
                        opponent = genMoveEasy(gameState.moveHistory, greenTile);
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
            } else{ /*if the current turn is red*/
                switch (gameState.redPlayer){
                    case EASY:
                        opponent = genMoveEasy(gameState.moveHistory, redTile);
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
                if (heights[i][j]>1){
                    String tall = Integer.toString(heights[i][j]);
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
        primaryStage.getIcons().add(new Image((Viewer.class.getResource(URI_BASE +
                "icon.png").toString())));
        scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT, LIGHTGREY);

        root.getChildren().add(controls);
        root.getChildren().add(placementGrp);

        initialSettings();

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
