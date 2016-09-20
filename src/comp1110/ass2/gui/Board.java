package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import static comp1110.ass2.StratoGame.generateMove;
import static comp1110.ass2.PlayingMode.PlayerIsGreen;
import static comp1110.ass2.PlayingMode.PlayerIsRed;
import static comp1110.ass2.PlayingMode.TwoPlayers;
import static comp1110.ass2.StratoGame.heightArray;

public class Board extends Application {

/*OVERVIEW: The first function called by the stage is initialSettings(), which
* creates the first screen with three buttons to choose the playing mode.*/

/*The buttons set a variable called playingMode as PlayerIsRed, PlayerIsGreen,
* or TwoPlayer. Then they all call makePlayer(). If the player is red, it also
* makes a move for the AI as the first (after MMUA) move.*/

/*makePlayer() calls makeControls() and makeBoard()*/

/*makeControls() is pretty much the same for all playing modes, except it omits
* a "Rotate" button if it's single-player. makeControls() calls makeBoard()*/

/*makeBoard() is pretty much the same again. The big difference is that
* depending on which playingMode it is, it calls different addPane function:
* addPanePlayerGreen, addPanePlayerRed, or addPaneTwoPlayer.
* makeBoard() modifies the GridPane playingBoard so it looks like a board*/

/*Each 'addPane' function creates a pane at the specified row and column
* on the GridPane clickableTiles.
* When a pane is clicked, the two player version of the function makes a
* move based on whose turn it is.
* The one player version makes the player's move and calls the AI with the
* appropriate input on which player it is supposed to be.*/

/*Many of the buttons, text, and images were defined as class fields to be
 modified by functions, instead of being created by functions  because they
 need to be accessible by many different functions.*/

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final String URI_BASE = "assets/";
    private static final int TILE_SIZE = 24;

    /*Some initial conditions*/
    private BoardState boardState  = new BoardState(BLACK, TwoPlayers);
    private PlayerG playerG = new PlayerG();
    private PlayerR playerR = new PlayerR();
    private String moveHistory = "";

    /*Nodes that need to be accessable by many functions*/
    private ImageView ivg = new ImageView();
    private ImageView ivr = new ImageView();
    private Text greentxt = new Text("Green");
    private Text redtxt = new Text("Red");
    private Text errormessage = new Text("Error: Invalid move");

    /*Various Groups that organise the screen*/
    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group placementGrp = new Group();
    private GridPane playingBoard = new GridPane();
    private GridPane heightLabels = new GridPane();
    private GridPane clickablePanes = new GridPane();




    private void initialSettings() {
        Text introtext = new Text("Choose playing mode");

        placementGrp.getChildren().add(introtext);

        Button playAsGreen = new Button("Play as Green");
        playAsGreen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                placementGrp.getChildren().clear();
                boardState.playingMode = PlayerIsGreen;

                makePlayer();
            }
        });

        Button playAsRed = new Button("Play as Red");
        playAsRed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                placementGrp.getChildren().clear();
                boardState.playingMode = PlayerIsRed;

                makePlayer();

                /*Makes the opponent's move first*/
                char redTile = (char) (playerR.available_tiles).get(playerR.used_tiles);
                char greenTile = (char) (playerG.available_tiles).get(playerG.used_tiles);
                String opponent = generateMove(moveHistory, greenTile,redTile);
                makeGUIPlacement(opponent);
            }
        });

        Button twoPlayer = new Button("Two players");
        twoPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                placementGrp.getChildren().clear();
                boardState.playingMode = TwoPlayers;
                makePlayer();
            }
        });
        VBox vb = new VBox();
        vb.getChildren().addAll(introtext,twoPlayer,playAsRed,playAsGreen);
        vb.setSpacing(10);
        vb.setLayoutX(300);
        vb.setLayoutY(150);

        placementGrp.getChildren().addAll(vb);
    }


    private void makePlayer(){
        /*Makes the controls for the game*/
        makeControls();

        /*Make the playing board, separately from the controls*/
        makeBoard();

        makeGUIPlacement("MMUA");

    }

    private void makeControls(){
        /*Make the control pane as a GridPane. This is the stuff on the right*/
        GridPane playerControls = new GridPane();
        playerControls.setPrefSize(120, 650);
        playerControls.setMaxSize(120, 650);

        /*The text you see on the right*/
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


        /*The tiles on display on the right*/
        ivg.setImage(new Image(Viewer.class.getResource(URI_BASE + (playerG.available_tiles).get(playerG.used_tiles) + ".png").toString()));
        ivg.setRotate((((int) (playerG.rotation)-65)*90));
        ivg.setFitWidth(80);
        ivg.setPreserveRatio(true);
        ivg.setSmooth(true);
        ivg.setCache(true);

        ivr.setImage(new Image(Viewer.class.getResource(URI_BASE + (playerR.available_tiles).get(playerR.used_tiles) + ".png").toString()));
        ivr.setRotate((((int) (playerR.rotation)-65)*90));
        ivr.setFitWidth(80);
        ivr.setPreserveRatio(true);
        ivr.setSmooth(true);
        ivr.setCache(true);

        /*The buttons that rotates the tiles*/
        Button rotateG = new Button("Rotate");
        rotateG.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                playerG.rotateTile();
                ivg.setRotate((((int) (playerG.rotation)-65)*90));
            }
        });

        Button rotateR = new Button("Rotate");
        rotateR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                playerR.rotateTile();
                ivr.setRotate((((int) (playerR.rotation)-65)*90));
            }
        });

        /*Adding the nodes. Which ones we add depends on the playingMode*/
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

        playerControls.setLayoutX((TILE_SIZE+1)*27+65);
        playerControls.setLayoutY(50);

        playerControls.setHgap(10);
        playerControls.setVgap(10);

        controls.getChildren().add(playerControls);

        /*This line is for debugging purposes only. When set to true, it shows grid lines*/
        playerControls.setGridLinesVisible(false);
    }
    private void makeBoard(){
        /*Note: the size of the tiles on the board are still 48x48 pixels */
        int size = (TILE_SIZE+1)*27;
        playingBoard.setPrefSize(size, size);
        playingBoard.setMaxSize(size, size);

        /*determines the size of the rows and columns of the playing board*/
        for (int i = 0; i < 27; i++) {
            RowConstraints row = new RowConstraints(TILE_SIZE);
            playingBoard.getRowConstraints().add(row);
        }
        for (int i = 0; i < 27; i++) {
            ColumnConstraints column = new ColumnConstraints(TILE_SIZE);
            playingBoard.getColumnConstraints().add(column);
        }

        /*Adds labels for the rows and columns: A,B,C, etc.*/
        for (int i=1;i<27;i++){
            String dummy = Character.toString( (char) (64+i) );

            /*Adds the row of labels for the positions*/
            Text label1 = new Text(dummy);
            label1.setFill(Color.WHITE);
            label1.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
            playingBoard.getChildren().add(label1);
            GridPane.setRowIndex(label1,0);
            GridPane.setColumnIndex(label1,i);
            GridPane.setHalignment(label1, HPos.CENTER);
            GridPane.setValignment(label1, VPos.CENTER);

            /*Adds the column of labels for the positions*/
            Text label2 = new Text(dummy);
            label2.setFill(Color.WHITE);
            label2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
            playingBoard.getChildren().add(label2);
            GridPane.setRowIndex(label2,i);
            GridPane.setColumnIndex(label2,0);
            GridPane.setHalignment(label2, HPos.CENTER);
            GridPane.setValignment(label2, VPos.CENTER);
        }

        /*Makes the board background black using CSS*/
        playingBoard.setStyle("-fx-background-color: black");
        /*Creates white squares on a black background for the board*/
        for (int i=1; i<27;i++){
            for (int j=1; j<27; j++){
                int rectSize = TILE_SIZE-1;
                Rectangle r = new Rectangle(rectSize, rectSize);
                r.setFill(Color.WHITE);
                playingBoard.getChildren().add(r);
                GridPane.setRowIndex(r,i);
                GridPane.setColumnIndex(r,j);
                GridPane.setHalignment(r, HPos.CENTER);
                GridPane.setValignment(r, VPos.CENTER);
            }
        }

        /*This line is for debugging purposes only. When set to true, it shows grid lines*/
        playingBoard.setGridLinesVisible(false);

        /*Layout*/
        playingBoard.setHgap(1);
        playingBoard.setVgap(1);
        playingBoard.setLayoutX(10);
        playingBoard.setLayoutY(10);

        /*An GridPane on top of playingBoard, laid out identically to playingBoard
         that shows the height of the tile on that position*/
        heightLabels.setPrefSize(675, 675);
        heightLabels.setMaxSize(700, 700);
        /*Determines the size of the grid rows and columns*/
        for (int i = 0; i < 27; i++) {
            RowConstraints row = new RowConstraints(TILE_SIZE);
            heightLabels.getRowConstraints().add(row);
        }
        for (int i = 0; i < 27; i++) {
            ColumnConstraints column = new ColumnConstraints(TILE_SIZE);
            heightLabels.getColumnConstraints().add(column);
        }
        /*Layout*/
        heightLabels.setHgap(1);
        heightLabels.setVgap(1);
        heightLabels.setLayoutX(10);
        heightLabels.setLayoutY(10);



        /*A GridPane on top of playingBoard and heightLabels, laid out identically to playingBoard,
         holding the interactive tiles for the game*/
        clickablePanes.setPrefSize(675, 675);
        clickablePanes.setMaxSize(700, 700);
        /*Determines the size of the grid rows and columns*/
        for (int i = 0; i < 27; i++) {
            RowConstraints row = new RowConstraints(TILE_SIZE);
            clickablePanes.getRowConstraints().add(row);
        }
        for (int i = 0; i < 27; i++) {
            ColumnConstraints column = new ColumnConstraints(TILE_SIZE);
            clickablePanes.getColumnConstraints().add(column);
        }
        /*What kind of function the pane calls when clicked depends on the playingMode.
        * Instead of checking what the playingMode is everytime a pane is clicked,
        * we check it now and create different panes depending on the playingMode*/
        if (boardState.playingMode==PlayerIsGreen) {
            for (int i=1; i<27;i++){
                for (int j=1; j<27; j++){
                    addPanePlayerGreen(i,j);
                    addPanePlayerGreen(j,i);
                }
            }
        }
        if (boardState.playingMode==PlayerIsRed) {
            for (int i=1; i<27;i++){
                for (int j=1; j<27; j++){
                /*Creates the clickable panes of the board*/
                    addPanePlayerRed(i,j);
                    addPanePlayerRed(j,i);
                }
            }
        }
        if (boardState.playingMode==TwoPlayers) {
            for (int i=1; i<27;i++){
                for (int j=1; j<27; j++){
                /*Creates the clickable panes of the board*/
                    addPaneTwoPlayer(i,j);
                    addPaneTwoPlayer(j,i);
                }
            }
        }
        /*Layout*/
        clickablePanes.setHgap(1);
        clickablePanes.setVgap(1);
        clickablePanes.setLayoutX(10);
        clickablePanes.setLayoutY(10);

        /*The must be added in this order so the heights show on top of the tiles
        * and the interactive panes are on top of all of them.*/
        placementGrp.getChildren().add(playingBoard);
        placementGrp.getChildren().add(heightLabels);
        placementGrp.getChildren().add(clickablePanes);
    }



    /*The clickable panes for when there are two players*/
    private void addPaneTwoPlayer(int colIndex, int rowIndex){
        Pane pane = new Pane();
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                char col = (char) (colIndex+64);
                char row = (char) (rowIndex+64);
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
            }
        });
        pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        clickablePanes.getChildren().add(pane);
        GridPane.setRowIndex(pane,rowIndex);
        GridPane.setColumnIndex(pane,colIndex);
    }

    /*The clickable panes for when you are playing as green*/
    private void addPanePlayerGreen(int colIndex, int rowIndex){
        Pane pane = new Pane();
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                char col = (char) (colIndex+64);
                char row = (char) (rowIndex+64);

                String placement = new StringBuilder().append(col).append(row).append((playerG.available_tiles).get(playerG.used_tiles)).append(playerG.rotation).toString();
                String tempMove = moveHistory.concat(placement);
                makeGUIPlacement(placement);

                /*The AI only makes its move if your move was valid*/
                if (StratoGame.isPlacementValid(tempMove)){
                    char redTile = (char) (playerR.available_tiles).get(playerR.used_tiles);
                    char greenTile = (char) (playerG.available_tiles).get(playerG.used_tiles);
                    String opponent = generateMove(moveHistory, redTile, greenTile);
                    makeGUIPlacement(opponent);
                    System.out.println("AI generates: "+opponent);
                    if (opponent=="") {
                        System.out.println("Empty string generated by AI");
                    }
                } else{
                    System.out.println("AI did not move");
                }
            }
        });
        clickablePanes.getChildren().add(pane);
        GridPane.setRowIndex(pane,rowIndex);
        GridPane.setColumnIndex(pane,colIndex);
    }

    /*The clickable panes for when you are playing as red*/
    private void addPanePlayerRed(int colIndex, int rowIndex){
        Pane pane = new Pane();
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                char col = (char) (colIndex+64);
                char row = (char) (rowIndex+64);

                String placement = new StringBuilder().append(col).append(row).append((playerR.available_tiles).get(playerR.used_tiles)).append(playerR.rotation).toString();
                String tempMove = moveHistory.concat(placement);
                makeGUIPlacement(placement);

                /*The AI only makes its move if your move was valid*/
                System.out.println("tempMove: "+tempMove);
                if (StratoGame.isPlacementValid(tempMove)){
                    char redTile = (char) (playerR.available_tiles).get(playerR.used_tiles);
                    char greenTile = (char) (playerG.available_tiles).get(playerG.used_tiles);
                    String opponent = generateMove(moveHistory, greenTile,redTile);
                    makeGUIPlacement(opponent);
                    System.out.println("AI generates: "+opponent);
                    if (opponent=="") {
                        System.out.println("Empty string generated by AI");
                    }
                } else{
                    System.out.println("AI did not move");
                }
            }
        });
        clickablePanes.getChildren().add(pane);
        GridPane.setRowIndex(pane,rowIndex);
        GridPane.setColumnIndex(pane,colIndex);
    }


    /*The method that makes a placement*/
    private void makeGUIPlacement(String placement) {
        String tempMove = moveHistory.concat(placement);
        controls.getChildren().remove(errormessage);
        System.out.println("Someone tried: "+tempMove);

        if (!StratoGame.isPlacementValid(tempMove)) {
            errormessage.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
            controls.getChildren().add(errormessage);
            errormessage.setLayoutX(710);
            errormessage.setLayoutY(300);
        } else {
            /*create the image that'll go on the board*/
            ImageView iv1 = new ImageView();
            iv1.setImage(new Image(Viewer.class.getResource(URI_BASE + placement.charAt(2) + ".png").toString()));
            iv1.setRotate((((int) placement.charAt(3)) - 65) * 90);
            iv1.setFitWidth(TILE_SIZE*2);
            iv1.setPreserveRatio(true);
            iv1.setSmooth(true);
            iv1.setCache(true);
            playingBoard.getChildren().add(iv1);
            GridPane.setRowSpan(iv1, 2);
            GridPane.setColumnSpan(iv1, 2);
            /*Place the image, in the correct rotation, in the correct place on the board*/
            switch (placement.charAt(3)) {
                case 'A':
                    GridPane.setColumnIndex(iv1, (((int) placement.charAt(0)) - 64));
                    GridPane.setRowIndex(iv1, (((int) placement.charAt(1)) - 64));
                    break;
                case 'B':
                    GridPane.setColumnIndex(iv1, (((int) placement.charAt(0)) - 64 - 1));
                    GridPane.setRowIndex(iv1, (((int) placement.charAt(1)) - 64));
                    break;
                case 'C':
                    GridPane.setColumnIndex(iv1, (((int) placement.charAt(0)) - 64 - 1));
                    GridPane.setRowIndex(iv1, (((int) placement.charAt(1)) - 64 - 1));
                    break;
                case 'D':
                    GridPane.setColumnIndex(iv1, (((int) placement.charAt(0)) - 64));
                    GridPane.setRowIndex(iv1, (((int) placement.charAt(1)) - 64 - 1));
                    break;
            }
            moveHistory = tempMove;

            /*Update the heights we're supposed to display*/
            displayHeights();

            /*Update the top tiles shown on the control panel, whose turn it is, and whose turn is bolded.*/
            switch (boardState.playerTurn) {
                case RED:
                    if (playerR.used_tiles<19){ /*If red still has tiles left*/
                        /*Update the top red tile shown*/
                        ivr.setImage(new Image(Viewer.class.getResource(URI_BASE + (playerR.available_tiles).get(playerR.used_tiles) + ".png").toString()));
                        ivr.setFitWidth(80);
                        ivr.setPreserveRatio(true);
                        ivr.setSmooth(true);
                        ivr.setCache(true);
                    } else{ /*If red does not still have tiles left*/
                        ivr.setRotate(0);
                        ivr.setImage(new Image(Viewer.class.getResource(URI_BASE + "outoftiles.png").toString()));
                        ivr.setRotate(0);
                    }
                    if (boardState.playingMode==TwoPlayers){
                        /*Update whose turn it is, and whose turn is bolded.*/
                        greentxt.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                        redtxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
                    }
                    /*Update the red player's tile index*/
                    playerR.getNextTile();
                    /*Update whose turn it is*/
                    boardState.playerTurn = GREEN;
                    break;
                case GREEN:
                    if (playerG.used_tiles<19){ /*If green still has tiles left*/
                        /*Update the top green tile shown*/
                        ivg.setImage(new Image(Viewer.class.getResource(URI_BASE + (playerG.available_tiles).get(playerG.used_tiles) + ".png").toString()));
                        ivg.setFitWidth(80);
                        ivg.setPreserveRatio(true);
                        ivg.setSmooth(true);
                        ivg.setCache(true);
                    } else{ /*If green does not still have tiles left*/
                        ivg.setRotate(0);
                        ivg.setImage(new Image(Viewer.class.getResource(URI_BASE + "outoftiles.png").toString()));
                        ivg.setRotate(0);
                    }
                    if (boardState.playingMode==TwoPlayers){
                        /*Update whose turn is bolded.*/
                        greentxt.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
                        redtxt.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                    }
                    /*Update the red player's tile index*/
                    playerG.getNextTile();
                    /*Update whose turn it is*/
                    boardState.playerTurn = RED;
                    break;
                case BLACK:
                    boardState.playerTurn = GREEN;
                    break;
            }

            /*Checks if the game is over. If it is, we clear the board and display the winner.*/
            if (moveHistory.length() >= MAX_TILES*8+2) {
                placementGrp.getChildren().clear();
                if (Scoring.getWinner(moveHistory)){
                    Text score = new Text("Green Wins!");
                    score.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
                    placementGrp.getChildren().add(score);
                    score.setLayoutX(300);
                    score.setLayoutY(300);
                } else{
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
    private void displayHeights(){
        int[][] heights = heightArray(moveHistory);
        for (int i=1; i<27;i++){
            for (int j=1; j<27; j++){
                String tall = Integer.toString(heights[i-1][j-1]);
                if (heights[i-1][j-1]!=0){
                    Text label1 = new Text(tall);
                    label1.setFill(Color.GREY);
                    label1.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
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
        primaryStage.setTitle("StratoGame Board");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        root.getChildren().add(controls);
        root.getChildren().add(placementGrp);

        initialSettings();

        primaryStage.setScene(scene);
        primaryStage.show();

    }

   /*This version definately works. Please don't overwrite it.*/

    // FIXME Task 8: Implement a basic playable Strato Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 9: Implement scoring

    // FIXME Task 11: Implement a game that can play valid moves (even if they are weak moves)

    // FIXME Task 12: Implement a game that can play good moves

}
