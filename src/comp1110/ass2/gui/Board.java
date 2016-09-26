package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
import static comp1110.ass2.Player.MAX_TILES;
import static comp1110.ass2.StratoGame.genMoveMedium;
import static comp1110.ass2.StratoGame.generateMove;
import static comp1110.ass2.PlayingMode.PlayerIsGreen;
import static comp1110.ass2.PlayingMode.PlayerIsRed;
import static comp1110.ass2.PlayingMode.TwoPlayers;
import static comp1110.ass2.StratoGame.heightArray;

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
    private static final int TILE_SIZE = 24;
    private static final int BOARD_SIZE = 26;

    /*Some fields for initial conditions.*/
    private BoardState boardState;
    private PlayerG playerG;
    private PlayerR playerR;

    /*Nodes that need to be accessible by many functions.*/
    private ImageView ivg = new ImageView();
    private ImageView ivr = new ImageView();
    private Text greentxt = new Text("Green");
    private Text redtxt = new Text("Red");
    private Text errormessage = new Text("Invalid move!");
    private Text aiThink = new Text("Thinking...");
    private Text redScore = new Text("1");
    private Text greenScore = new Text("1");

    /*Various Groups that organise the screen.*/
    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group placementGrp = new Group();
    private GridPane playingBoard = new GridPane();
    private GridPane heightLabels = new GridPane();
    private GridPane clickablePanes = new GridPane();

    /*A counter that tells you if this is the first game played.*/
    private Boolean firstGame = true;



    /*Function by Zhixian Wu*/
    private void initialSettings() {
        boardState  = new BoardState(BLACK, TwoPlayers);

        Text introtext = new Text("Choose playing mode");

        placementGrp.getChildren().add(introtext);

        /*Each of these buttons tell the game if you want a two player game, or
        * to play as green or red against an AI*/
        Button playAsGreen = new Button("Play as Green");
        playAsGreen.setOnAction(event-> {
            placementGrp.getChildren().clear();
            boardState.playingMode = PlayerIsGreen;

            makePlayer();
        });

        Button playAsRed = new Button("Play as Red");
        playAsRed.setOnAction(event-> {
            placementGrp.getChildren().clear();
            boardState.playingMode = PlayerIsRed;

            makePlayer();

                /*Makes the opponent's move first*/
            char redTile = (char) (playerR.available_tiles).get(playerR.used_tiles);
            char greenTile = (char) (playerG.available_tiles).get(playerG.used_tiles);
            String opponent;
            /* Condition for using the probabilistic AI. pAI is only really playable for the last 1 move. */
            if (AI.piecesLeft(boardState.moveHistory, true).size() <= 1) {
                opponent = generateMove(boardState.moveHistory, greenTile, redTile);
            }
            else {
                opponent = genMoveMedium(boardState.moveHistory, greenTile, redTile);
            }
            makeGUIPlacement(opponent);
        });

        Button twoPlayer = new Button("Two players");
        twoPlayer.setOnAction(event-> {
            placementGrp.getChildren().clear();
            boardState.playingMode = TwoPlayers;
            makePlayer();
        });

        /*A button that created a scrolling text node that displays the instructions*/
        Button instructions = new Button("How to Play");
        instructions.setOnAction(event->  getInstructions() );

        /*Layout*/
        VBox vb = new VBox();
        vb.getChildren().addAll(introtext,twoPlayer,playAsRed,playAsGreen,instructions);
        vb.setSpacing(10);
        vb.setLayoutX(300);
        vb.setLayoutY(150);

        placementGrp.getChildren().addAll(vb);
    }

    /*Function by Zhixian Wu. This function displays the instructions when called.*/
    private void getInstructions(){
        GridPane mainInstruc = new GridPane();
        mainInstruc.setLayoutY(50);
        mainInstruc.setLayoutX(105);

        ScrollPane scroll = new ScrollPane();

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

        scroll.setPrefViewportHeight(500.0);
        scroll.setPrefViewportWidth(700.0);

        Button exitBtn = new Button("X");
        exitBtn.setOnAction(event->  root.getChildren().remove(mainInstruc) );

        mainInstruc.getChildren().addAll(scroll,exitBtn);
        GridPane.setRowIndex(scroll,1);
        GridPane.setColumnIndex(scroll,0);
        GridPane.setRowIndex(exitBtn,0);
        GridPane.setColumnIndex(exitBtn,1);

        root.getChildren().add(mainInstruc);
    }

    /*Function by Zhixian Wu*/
    private void makePlayer(){
        playerG = new PlayerG();
        playerR = new PlayerR();

        /*Make the playing board*/
        makeBoard();

        /*Makes the controls for the game, separately from the board*/
        makeControls();

        makeGUIPlacement("MMUA");
    }

    /*Function mostly by Zhixian Wu, with the running score by Manal Mohania*/
    private void makeControls(){
        /*Make the control pane as a GridPane. This is the stuff on the right*/
        GridPane playerControls = new GridPane();
        playerControls.setPrefSize(120, 200);
        playerControls.setMaxSize(120, 200);

        /*The text labeling Green and Red's tiles, which you see on the right*/
        if (boardState.playingMode==PlayerIsRed){
            greentxt.setFill(Color.GREEN);
            greentxt.setFont(Font.font("Verdana", 14));

            redtxt.setFill(Color.RED);
            redtxt.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        } else{
            greentxt.setFill(Color.GREEN);
            greentxt.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

            redtxt.setFill(Color.RED);
            redtxt.setFont(Font.font("Verdana", 14));
        }


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
        if (boardState.playingMode==TwoPlayers) playerControls.getChildren().addAll(greentxt,redtxt,rotateG,rotateR,ivg,ivr);
        if (boardState.playingMode==PlayerIsGreen) playerControls.getChildren().addAll(greentxt,redtxt,rotateG,ivg,ivr);
        if (boardState.playingMode==PlayerIsRed) playerControls.getChildren().addAll(greentxt,redtxt,rotateR,ivg,ivr);

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

        playerControls.setLayoutX((TILE_SIZE+1)*BOARD_SIZE+85);
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

            firstGame = false;

            initialSettings();
        });
        controls.getChildren().add(menu);
        menu.setLayoutX(835);
        menu.setLayoutY(650);

        /*Scores by Manal Mohania*/
        Rectangle r = new Rectangle();
        r.setLayoutY(50);
        r.setLayoutX(735);
        r.setWidth(170);
        r.setHeight(80);
        r.setArcHeight(20);
        r.setArcWidth(20);
        r.setFill(Color.SANDYBROWN);
        controls.getChildren().add(r);

        Text score = new Text("SCORES");
        score.setLayoutX(790);
        score.setLayoutY(65);
        controls.getChildren().add(score);

        greenScore.setLayoutX(750);
        greenScore.setLayoutY(103);
        greenScore.setFill(Color.GREEN);
        greenScore.setFont(Font.font("", FontWeight.EXTRA_BOLD, 40));
        controls.getChildren().add(greenScore);

        redScore.setLayoutX(830);
        redScore.setLayoutY(103);
        redScore.setFill(Color.RED);
        redScore.setFont(Font.font("", FontWeight.EXTRA_BOLD, 40));
        controls.getChildren().add(redScore);

    }

    /*Function mostly by Zhixian Wu, with minor changes by Manal Mohania (indicated below)*/
    private void makeBoard(){
        int size = (TILE_SIZE + 1) * BOARD_SIZE;
        int offset = (BOARD_HEIGHT - size) / 2;
        playingBoard.setPrefSize(size, size);
        playingBoard.setMaxSize(size, size);

        if (firstGame){
            /*determines the size of the rows and columns of the playing board*/
            for (int i = 0; i < BOARD_SIZE; i++) {
                RowConstraints row = new RowConstraints(TILE_SIZE+1);
                playingBoard.getRowConstraints().add(row);
            }
            for (int i = 0; i < BOARD_SIZE; i++) {
                ColumnConstraints column = new ColumnConstraints(TILE_SIZE+1);
                playingBoard.getColumnConstraints().add(column);
            }
        }



        /*Makes the board background black using CSS*/
        playingBoard.setStyle("-fx-background-color: black");

        /*Creates white squares on a black background for the board*/
        for (int i=0; i<BOARD_SIZE;i++){
            for (int j=0; j<BOARD_SIZE; j++){
                int rectSize = TILE_SIZE-1;
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
        Rectangle thickBorder = new Rectangle();
        thickBorder.setWidth(size+8);
        thickBorder.setHeight(size+8);
        thickBorder.setArcHeight(7);
        thickBorder.setArcWidth(7);
        thickBorder.setFill(Color.BLACK);
        thickBorder.setLayoutX(offset-4);
        thickBorder.setLayoutY(offset-4);
        placementGrp.getChildren().add(thickBorder);

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
                RowConstraints row = new RowConstraints(TILE_SIZE+1);
                heightLabels.getRowConstraints().add(row);
            }
            for (int i = 0; i < BOARD_SIZE; i++) {
                ColumnConstraints column = new ColumnConstraints(TILE_SIZE+1);
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
                RowConstraints row = new RowConstraints(TILE_SIZE+1);
                clickablePanes.getRowConstraints().add(row);
            }
            for (int i = 0; i < BOARD_SIZE; i++) {
                ColumnConstraints column = new ColumnConstraints(TILE_SIZE+1);
                clickablePanes.getColumnConstraints().add(column);
            }
        }

        /*What kind of function the pane calls when clicked depends on the playingMode.
        * Instead of checking what the playingMode is everytime a pane is clicked,
        * we check it now and create different panes depending on the playingMode*/
        if (boardState.playingMode==PlayerIsGreen) {
            for (int i=0; i<BOARD_SIZE;i++){
                for (int j=0; j<BOARD_SIZE; j++){
                    addPanePlayerGreen(i,j);
                    addPanePlayerGreen(j,i);
                }
            }
        }
        if (boardState.playingMode==PlayerIsRed) {
            for (int i=0; i<BOARD_SIZE;i++){
                for (int j=0; j<BOARD_SIZE; j++){
                /*Creates the clickable panes of the board*/
                    addPanePlayerRed(i,j);
                    addPanePlayerRed(j,i);
                }
            }
        }
        if (boardState.playingMode==TwoPlayers) {
            for (int i=0; i<BOARD_SIZE;i++){
                for (int j=0; j<BOARD_SIZE; j++){
                /*Creates the clickable panes of the board*/
                    addPaneTwoPlayer(i,j);
                    addPaneTwoPlayer(j,i);
                }
            }
        }
        /*Layout*/
        clickablePanes.setLayoutX(offset);
        clickablePanes.setLayoutY(offset);

        /*The must be added in this order so the heights show on top of the tiles
        * and the interactive panes are on top of all of them.*/
        placementGrp.getChildren().add(playingBoard);
        placementGrp.getChildren().add(heightLabels);
        placementGrp.getChildren().add(clickablePanes);
    }


    /*The clickable panes for when there are two players*/
    /*Function by Zhixian Wu and Manal Mohania.*/
    /*Idea of how to recursively creates panes that remember what position they
    were created for is from StackOverflow (URL in the C-u5807060 originality statement)*/
    private void addPaneTwoPlayer(int colIndex, int rowIndex){
        Pane pane = new Pane();
        ImageView iv = new ImageView();

        /*Event by Zhixian Wu, this makes the player's move when they click on a pane*/
        pane.setOnMouseClicked(event -> {
                char col = (char) (colIndex+'A');
                char row = (char) (rowIndex+'A');
                switch (boardState.playerTurn){
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
                        boardState.playerTurn = GREEN;
                        break;
                }

        });
        /*Event by Manal Mohania, this creates the preview piece*/
        pane.setOnMouseEntered(event -> {
            char col = (char) (colIndex + 'A');
            char row = (char) (rowIndex + 'A');

            switch (boardState.playerTurn){
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

            int length = boardState.moveHistory.length()-2;
            /*We only suggest the AI is thinking if it actually is, i.e. your move was valid,
             i.e. if the last move in moveHistory was yours*/
            if ('K'<=boardState.moveHistory.charAt(length) && boardState.moveHistory.charAt(length)<='T'){
                aiThink.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
                controls.getChildren().add(aiThink);
                aiThink.setLayoutX(740);
                aiThink.setLayoutY(400);
            }
        });

        /*Event by Zhixian Wu and Joseph Meltzer. This event causes the AI to make its move when the mouse is released.*/
        pane.setOnMouseReleased(event -> {
            int length = boardState.moveHistory.length()-2;

            /*Zhixian Wu: The AI only makes its move if your move was valid, i.e. if the
            last move in moveHistory was yours*/
            if ('K'<=boardState.moveHistory.charAt(length) && boardState.moveHistory.charAt(length)<='T'){
                char redTile = (char) (playerR.available_tiles).get(playerR.used_tiles);
                char greenTile = (char) (playerG.available_tiles).get(playerG.used_tiles);
                String opponent;
                /* Joseph Meltzer: Condition for using the probabilistic AI. AI is only really playable for the last 1 move. */
                if (AI.piecesLeft(boardState.moveHistory, false).size() <= 1) {
                    opponent = generateMove(boardState.moveHistory, redTile, greenTile);
                }
                else {
                    opponent = genMoveMedium(boardState.moveHistory, redTile, greenTile);
                }
                makeGUIPlacement(opponent);
                /*System.out.println("AI generates: "+opponent);
                if (opponent=="") {
                    System.out.println("Empty string generated by AI");
                }*/
            } /*else{
                System.out.println("AI did not move");
            }*/
        });

        clickablePanes.getChildren().add(pane);
        GridPane.setRowIndex(pane,rowIndex);
        GridPane.setColumnIndex(pane,colIndex);
    }

    /*The clickable panes for when the human player is Red*/
    /*Function by Zhixian Wu and Manal Mohania.*/
    /*Idea of how to recursively creates panes that remember what position they
    were created for is from StackOverflow (URL in the in the C-u5807060 originality statement)*/
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

            int length = boardState.moveHistory.length()-2;
            /*We only suggest the AI is thinking if it actually is, i.e. if your
            move was valid, i.e. if the last move in moveHistory was yours*/
            if ('A'<=boardState.moveHistory.charAt(length) && boardState.moveHistory.charAt(length)<='J'){
                aiThink.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
                controls.getChildren().add(aiThink);
                aiThink.setLayoutX(740);
                aiThink.setLayoutY(400);
            }

        });

        /*Event by Zhixian Wu. This event causes the AI to make its move when the mouse is released.*/
        pane.setOnMouseReleased(event -> {
            int length = boardState.moveHistory.length()-2;

            /*The first two conditions check if your move was valid,
            by checking if the last move in moveHistory was yours.
            The AI only makes its move if your move was valid.
              The last condition checks if the game is not over yet,
            so te AI doesn't try to make a move after the game is over*/
            if ('A'<=boardState.moveHistory.charAt(length) && boardState.moveHistory.charAt(length)<='J' && boardState.moveHistory.length()<MAX_TILES*8){
                char redTile = (char) (playerR.available_tiles).get(playerR.used_tiles);
                char greenTile = (char) (playerG.available_tiles).get(playerG.used_tiles);
                String opponent;
                /* Condition for using the probabilistic AI. pAI is only really playable for the last 1 move. */
                if (AI.piecesLeft(boardState.moveHistory, true).size() <= 1) {
                    opponent = generateMove(boardState.moveHistory, greenTile, redTile);
                }
                else {
                    opponent = genMoveMedium(boardState.moveHistory, greenTile, redTile);
                }
                /*System.out.println("AI generates: "+opponent);
                if (opponent=="") System.out.println("Empty string generated by AI");*/
                makeGUIPlacement(opponent);
            } else{
                /*System.out.println("AI did not move");*/
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
        if (StratoGame.isPlacementValid(boardState.moveHistory.concat(placement))) {
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
        String placement = boardState.moveHistory;
        controls.getChildren().remove(greenScore);
        int score = StratoGame.getScoreForPlacement(placement, true);
        greenScore.setText("" + score);
        controls.getChildren().add(greenScore);
        int offset = (Integer.toString(score)).length() * 10;
        greenScore.setLayoutX(785-offset);
        greenScore.setLayoutY(107);
        greenScore.setFill(Color.GREEN);
        greenScore.setFont(Font.font("", FontWeight.EXTRA_BOLD, 40));
    }

    private void updateRedScore(){
        String placement = boardState.moveHistory;
        controls.getChildren().remove(redScore);
        int score = StratoGame.getScoreForPlacement(placement, false);
        redScore.setText("" + score);
        controls.getChildren().add(redScore);
        int offset = (Integer.toString(score)).length() * 10;
        redScore.setLayoutX(865 - offset);
        redScore.setLayoutY(107);
        redScore.setFill(Color.RED);
        redScore.setFont(Font.font("", FontWeight.EXTRA_BOLD, 40));
    }

    /*The method that makes a placement*/
    /*Function by Zhixian Wu*/
    private void makeGUIPlacement(String placement) {
        controls.getChildren().remove(errormessage);
        controls.getChildren().remove(aiThink);
        System.out.println("Someone tried: " + placement); /*For debugging purposes*/

        String tempMove = boardState.moveHistory.concat(placement);
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
            boardState.updateMoves(placement);

            /*Update the heights we're supposed to display*/
            displayHeights();

            /*Update the scores displayed*/
            updateRedScore();
            updateGreenScore();

            /*Update the top tiles shown on the control panel, whose turn it is, and whose turn is bolded.*/
            switch (boardState.playerTurn) {
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
                        ivr.setRotate(0);
                        ivr.setImage(new Image(Viewer.class.getResource(URI_BASE + "outoftiles.png").toString()));
                        ivr.setRotate(0);
                        playerR.getNextTile();
                    }
                    /*Update whose turn it is, and whose turn is bolded.*/
                    boardState.playerTurn = GREEN;
                    greentxt.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                    redtxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
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
                        ivg.setRotate(0);
                        ivg.setImage(new Image(Viewer.class.getResource(URI_BASE + "outoftiles.png").toString()));
                        ivg.setRotate(0);
                        playerG.getNextTile();
                    }
                    /*Update whose turn it is, and whose turn is bolded.*/
                    boardState.playerTurn = RED;
                    greentxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
                    redtxt.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                    break;
                case BLACK:
                    boardState.playerTurn = GREEN;
                    break;
            }

            /*Checks if the game is over. If it is, we clear the board and display the winner.*/
            if (boardState.moveHistory.length() > MAX_TILES*8) {
                placementGrp.getChildren().clear();
                /*If green wins*/
                if (Scoring.getWinner(boardState.moveHistory)){
                    Text score = new Text("Green Wins!");
                    score.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
                    placementGrp.getChildren().add(score);
                    score.setLayoutX(300);
                    score.setLayoutY(300);
                } else{ /*if red wins*/
                    Text score = new Text("Red Wins!");
                    score.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
                    placementGrp.getChildren().add(score);
                    score.setLayoutX(300);
                    score.setLayoutY(300);
                }
            }
        }
    }

    /*Display the height at each position*/
    /*Function by Zhixian Wu*/
    private void displayHeights(){
        /*Clear existing heights*/
        heightLabels.getChildren().clear();
        /*Make 2D array of the height at each position*/
        int[][] heights = heightArray(boardState.moveHistory);
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
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        root.getChildren().add(controls);
        root.getChildren().add(placementGrp);

        initialSettings();

        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
