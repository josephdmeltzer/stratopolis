package comp1110.ass2.gui;

import comp1110.ass2.PlayerG;
import comp1110.ass2.PlayerR;
import comp1110.ass2.StratoGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import comp1110.ass2.AI;

import static java.time.format.SignStyle.NORMAL;

public class Board extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final String URI_BASE = "assets/";




    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group placementGrp = new Group();
    TextField textField;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */

    void makeGUIPlacement(String placement) {

        if (!StratoGame.isPlacementValid(placement)){
            throw new IllegalArgumentException("Bad placement " + placement);
        } else{
            GridPane gridPane = new GridPane();
            gridPane.setPrefSize(624, 624);
            gridPane.setMaxSize(624, 624);

            gridPane.setGridLinesVisible(true);
            for (int i = 0; i < 26; i++) {
                RowConstraints row = new RowConstraints(24);
                gridPane.getRowConstraints().add(row);
            }
            for (int i = 0; i < 26; i++) {
                ColumnConstraints column = new ColumnConstraints(24);
                gridPane.getColumnConstraints().add(column);
            }


            for (int i=0; i<(placement.length()/4); i++){
                ImageView iv1 = new ImageView();
                iv1.setImage(new Image(Viewer.class.getResource(URI_BASE + placement.charAt(4*i+2) + ".png").toString()));
                iv1.setRotate((((int) placement.charAt(4*i+3))-65)*90);
                iv1.setFitWidth(48);
                iv1.setPreserveRatio(true);
                iv1.setSmooth(true);
                iv1.setCache(true);
                gridPane.getChildren().add(iv1);
                GridPane.setRowSpan(iv1,2);
                GridPane.setColumnSpan(iv1,2);
                switch (placement.charAt(4*i+3)){
                    case 'A':
                        GridPane.setColumnIndex(iv1,(((int) placement.charAt(4*i))-65));
                        GridPane.setRowIndex(iv1,(((int) placement.charAt(4*i+1))-65));
                        break;
                    case 'B':
                        GridPane.setColumnIndex(iv1,(((int) placement.charAt(4*i))-65-1));
                        GridPane.setRowIndex(iv1,(((int) placement.charAt(4*i+1))-65));
                        break;
                    case 'C':
                        GridPane.setColumnIndex(iv1,(((int) placement.charAt(4*i))-65-1));
                        GridPane.setRowIndex(iv1,(((int) placement.charAt(4*i+1))-65-1));
                        break;
                    case 'D':
                        GridPane.setColumnIndex(iv1,(((int) placement.charAt(4*i))-65));
                        GridPane.setRowIndex(iv1,(((int) placement.charAt(4*i+1))-65-1));
                        break;
                }
            }
            placementGrp.getChildren().add(gridPane);

        }
    }


    private void initialSettings() {
        Text hi = new Text("Choose playing mode (note: only two-player works for now)");

        root.getChildren().add(hi);
        Button playasgreen = new Button("Play as Green");
        playasgreen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                root.getChildren().clear();
                /*create the game*/
            }
        });

        Button playasred = new Button("Play as Red");
        playasred.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                root.getChildren().clear();
                /*create the game*/
            }
        });


        Button twoplayer = new Button("Two players");
        twoplayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                root.getChildren().clear();
                makeTwoPlayer();
            }
        });
        VBox vb = new VBox();
        vb.getChildren().addAll(hi,twoplayer,playasred,playasgreen);
        vb.setSpacing(10);
        vb.setLayoutX(300);
        vb.setLayoutY(150);

        root.getChildren().addAll(vb);

        /*controls.getChildren().add(later);*/
    }

    private void makeTwoPlayer(){


        PlayerG playerG = new PlayerG();
        PlayerR playerB = new PlayerR();

        /*makes two players (gridpane?) options on the right*/
        GridPane playerControls = new GridPane();
        playerControls.setPrefSize(120, 650);
        playerControls.setMaxSize(120, 650);

        Button rotateG = new Button("Rotate");
        rotateG.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                /*change an image in this*/
            }
        });
        Button rotateR = new Button("Rotate");
        rotateR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                /*change an image in this*/
            }
        });
        Text greentxt = new Text("Green");
        greentxt.setFill(Color.GREEN);
        greentxt.setFont(Font.font("Verdana", FontWeight.BOLD, 8));
        Text redtxt = new Text("Red");
        redtxt.setFill(Color.RED);
        redtxt.setFont(Font.font("Verdana", 8));

        playerControls.getChildren().addAll(greentxt,redtxt,rotateG,rotateR/*imgG,imgR*/);
        GridPane.setColumnIndex(rotateG,0);
        GridPane.setRowIndex(rotateG,1);
        GridPane.setColumnIndex(rotateR,2);
        GridPane.setRowIndex(rotateR,1);
        GridPane.setColumnIndex(greentxt,0);
        GridPane.setRowIndex(greentxt,2);
        GridPane.setColumnIndex(redtxt,1);
        GridPane.setRowIndex(redtxt,2);

        playerControls.setGridLinesVisible(true);
        playerControls.setLayoutX(700);
        playerControls.setLayoutY(25);

        controls.getChildren().add(playerControls);


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
