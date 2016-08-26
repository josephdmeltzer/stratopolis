package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

import java.util.ArrayList;

import static comp1110.ass2.Colour.GREEN;
import static comp1110.ass2.Colour.RED;
import static java.time.format.SignStyle.NORMAL;

public class Board extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final String URI_BASE = "assets/";


    private BoardState boardTurn = new BoardState(GREEN);
    private PlayerG playerG = new PlayerG();
    private PlayerR playerR = new PlayerR();
    private String moveHistory;


    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group placementGrp = new Group();
    private GridPane playingBoard = new GridPane();
    TextField textField;


    public void makeBoard(){
        /*Note: the size of the tiles on the board are still 48x48 pixels */
        playingBoard.setPrefSize(675, 675);
        playingBoard.setMaxSize(700, 700);

        for (int i = 0; i < 27; i++) {
            RowConstraints row = new RowConstraints(24);
            playingBoard.getRowConstraints().add(row);
        }
        for (int i = 0; i < 27; i++) {
            ColumnConstraints column = new ColumnConstraints(24);
            playingBoard.getColumnConstraints().add(column);
        }

        for (int i=1;i<27;i++){
            String dummy = Character.toString( (char) (64+i) );
            Text label1 = new Text(dummy);
            label1.setFill(Color.WHITE);
            label1.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
            playingBoard.getChildren().add(label1);
            GridPane.setRowIndex(label1,0);
            GridPane.setColumnIndex(label1,i);
            GridPane.setHalignment(label1, HPos.CENTER);
            GridPane.setValignment(label1, VPos.CENTER);

            Text label2 = new Text(dummy);
            label2.setFill(Color.WHITE);
            label2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
            playingBoard.getChildren().add(label2);
            GridPane.setRowIndex(label2,i);
            GridPane.setColumnIndex(label2,0);
            GridPane.setHalignment(label2, HPos.CENTER);
            GridPane.setValignment(label2, VPos.CENTER);
        }

        /*Make clickable grid*/
        /*BUG: Tiles further down/left than MN don't react when clicked*/
        ArrayList<Tiles> tiles= new ArrayList<Tiles>();
        for (int i=1; i<27;i++){
            for (int j=1; j<27; j++){

                Rectangle r = new Rectangle(23, 23);
                r.setFill(Color.WHITE);
                playingBoard.getChildren().add(r);
                GridPane.setRowIndex(r,i);
                GridPane.setColumnIndex(r,j);
                GridPane.setHalignment(r, HPos.CENTER);
                GridPane.setValignment(r, VPos.CENTER);

                addPane(i,j);
            }
        }


        /*This line is for debugging purposes only. When set to true, it shows grid lines*/
        playingBoard.setGridLinesVisible(false);

        /*Styles board with actual grid lines using CSS*/
        playingBoard.setHgap(1);
        playingBoard.setVgap(1);
        playingBoard.setStyle("-fx-background-color: black");
        playingBoard.setLayoutX(10);
        playingBoard.setLayoutY(10);

        placementGrp.getChildren().add(playingBoard);
    }

    private void addPane(int colIndex, int rowIndex){
        /*BUG: Event stops working when it has an image covering it*/
        Pane pane = new Pane();
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                char col = (char) (colIndex+64);
                char row = (char) (rowIndex+64);
                String placement = new StringBuilder().append(col).append(row).append((playerG.available_tiles).get(playerG.used_tiles)).append(playerG.rotation).toString();

                makeGUIPlacement(placement);
            }
        });
        playingBoard.add(pane, colIndex, rowIndex);
    }

    void makeGUIPlacement(String placement) {
        /*BUG: no contingency if used_tiles goes out of bounds*/
        /*BUG: check if it only throws Bad placement when the placement is actually invalid*/
        if ((!StratoGame.isPlacementValid(moveHistory + placement)) && (!placement.equals("MMUA"))){
            throw new IllegalArgumentException("Bad placement " + placement);
        } else{
            ImageView iv1 = new ImageView();
            iv1.setImage(new Image(Viewer.class.getResource(URI_BASE + placement.charAt(2) + ".png").toString()));
            iv1.setRotate((((int) placement.charAt(3))-65)*90);
            iv1.setFitWidth(48);
            iv1.setPreserveRatio(true);
            iv1.setSmooth(true);
            iv1.setCache(true);
            playingBoard.getChildren().add(iv1);
            GridPane.setRowSpan(iv1,2);
            GridPane.setColumnSpan(iv1,2);
            switch (placement.charAt(3)){
                case 'A':
                    GridPane.setColumnIndex(iv1,(((int) placement.charAt(0))-64));
                    GridPane.setRowIndex(iv1,(((int) placement.charAt(1))-64));
                    break;
                case 'B':
                    GridPane.setColumnIndex(iv1,(((int) placement.charAt(0))-64-1));
                    GridPane.setRowIndex(iv1,(((int) placement.charAt(1))-64));
                    break;
                case 'C':
                    GridPane.setColumnIndex(iv1,(((int) placement.charAt(0))-64-1));
                    GridPane.setRowIndex(iv1,(((int) placement.charAt(1))-64-1));
                    break;
                case 'D':
                    GridPane.setColumnIndex(iv1,(((int) placement.charAt(0))-64));
                    GridPane.setRowIndex(iv1,(((int) placement.charAt(1))-64-1));
                    break;
            }

        /*BUG: it only places green tiles*/

            if (boardTurn.playerTurn==GREEN){
                playerG.used_tiles = playerG.used_tiles+1;
                boardTurn.playerTurn=RED;
            }
            else{
                playerR.used_tiles = playerR.used_tiles+1;
                boardTurn.playerTurn=GREEN;
            }
            moveHistory = moveHistory + placement;
        }
    }







    private void initialSettings() {
        Text hi = new Text("Choose playing mode (note: only two-player works for now)");

        placementGrp.getChildren().add(hi);
        Button playasgreen = new Button("Play as Green");
        playasgreen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                placementGrp.getChildren().clear();
                /*function that creates the game*/
            }
        });

        Button playasred = new Button("Play as Red");
        playasred.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                placementGrp.getChildren().clear();
                /*funstion that creates the game*/
            }
        });


        Button twoplayer = new Button("Two players");
        twoplayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                placementGrp.getChildren().clear();
                makeTwoPlayer();
            }
        });
        VBox vb = new VBox();
        vb.getChildren().addAll(hi,twoplayer,playasred,playasgreen);
        vb.setSpacing(10);
        vb.setLayoutX(300);
        vb.setLayoutY(150);

        placementGrp.getChildren().addAll(vb);

        /*controls.getChildren().add(later);*/
    }



    private void makeTwoPlayer(){
        /*Make the control pane*/


        GridPane playerControls = new GridPane();
        playerControls.setPrefSize(120, 650);
        playerControls.setMaxSize(120, 650);

        Text greentxt = new Text("Green");
        greentxt.setFill(Color.GREEN);
        greentxt.setFont(Font.font("Verdana", FontWeight.BOLD, 14));

        Text redtxt = new Text("Red");
        redtxt.setFill(Color.RED);
        redtxt.setFont(Font.font("Verdana", 14));

        ImageView ivg = new ImageView();
        ivg.setImage(new Image(Viewer.class.getResource(URI_BASE + (playerG.available_tiles).get(playerG.used_tiles) + ".png").toString()));
        ivg.setRotate((((int) (playerG.rotation)-65)*90));
        ivg.setFitWidth(80);
        ivg.setPreserveRatio(true);
        ivg.setSmooth(true);
        ivg.setCache(true);

        ImageView ivr = new ImageView();
        ivr.setImage(new Image(Viewer.class.getResource(URI_BASE + (playerR.available_tiles).get(playerR.used_tiles) + ".png").toString()));
        ivr.setRotate((((int) (playerR.rotation)-65)*90));
        ivr.setFitWidth(80);
        ivr.setPreserveRatio(true);
        ivr.setSmooth(true);
        ivr.setCache(true);

        Button rotateG = new Button("Rotate");
        rotateG.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                playerG.rotation = rotateTile(playerG.rotation);
                ivg.setRotate((((int) (playerG.rotation)-65)*90));
            }
        });

        Button rotateR = new Button("Rotate");
        rotateR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                playerR.rotation = rotateTile(playerR.rotation);
                ivr.setRotate((((int) (playerR.rotation)-65)*90));
            }
        });

        playerControls.getChildren().addAll(greentxt,redtxt,rotateG,rotateR,ivg,ivr);

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

        playerControls.setGridLinesVisible(false);
        playerControls.setLayoutX(740);
        playerControls.setLayoutY(50);

        playerControls.setHgap(10);
        playerControls.setVgap(10);

        controls.getChildren().add(playerControls);


        /*Make the playing board*/
        makeBoard();

        /*Still have to create transparent events for each panel that
        passes a 4-char string to makeGUIPlacement,
        and updates whose turn it its with a corrosponding change in which text is bolded.*/


        makeGUIPlacement("MMUA");
    }


    private char rotateTile(char rotation){
        if (rotation=='A'){
            return 'B';
        }
        if (rotation=='B'){
            return 'C';
        }
        if (rotation=='C'){
            return 'D';
        }
        if (rotation=='D'){
            return 'A';
        } else{
            return 'A';
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



    // FIXME Task 8: Implement a basic playable Strato Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 9: Implement scoring

    // FIXME Task 11: Implement a game that can play valid moves (even if they are weak moves)

    // FIXME Task 12: Implement a game that can play good moves

    // Week 7: we need to create an initial three buttons for three choices.
    // 1. Once a button is pressed, the buttons are cleared, PlayerA and PlayerB generated, and their top tiles (index used_tiles
    // of char-array available_tiles) and whose turn it is are displayed.
    // 2. There should be a rotate button for the player who has the current turn
    // (and this rotation is stored as part of the Player object)
    // 3. Each box in the GridPane (in Viewer) would need to be a clickable event that concatenates
    // its coords with the tile and the rotation
    // 4. The move can then be passed to makePlacement
    // 5. There needs to be a method to update BoardState (player turn, 7., etc.). The move should be passed to this with the current BoardState.
    // 6. Maybe the entire string of moves so far can be stored as a member variable of BoardState to calculate the score later
    // 7. Or just alter BoardState to have a [26][26] array of heights and top colour.
    // (^ This would be easier to implement if you've done isPlacementValid())
}
