package comp1110.ass2.gui;

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
import javafx.stage.Stage;
import comp1110.ass2.StratoGame;

/**
 * A very simple viewer for piece placements in the link game.
 *
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 *
 * Code written by Zhixian Wu
 */


public class Viewer extends Application {

    /* board layout */
    private static final int VIEWER_WIDTH = 750;
    private static final int VIEWER_HEIGHT = 700;

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

    void makePlacement(String placement) {
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



    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField ();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh"); /*creates the 'Refresh' button*/
        button.setOnAction(new EventHandler<ActionEvent>() {
            /*the bit in these curly braces is the behaviour that is executed when the button is pressed*/
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StratoGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);
        root.getChildren().add(placementGrp);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
