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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import comp1110.ass2.gui.Setup;
import comp1110.ass2.StratoGame;
import comp1110.ass2.Pieces;

import static comp1110.ass2.Pieces.K;
import static javafx.scene.layout.GridPane.getColumnIndex;
import static javafx.scene.layout.GridPane.getRowIndex;


/**
 * A very simple viewer for piece placements in the link game.
 *
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
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
        // FIXME Task 5: implement the simple placement viewer
        char[] rowcol = {'A', 'B', 'C', 'D', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        /*String[] variableNames = {"iv1","iv2","iv3","iv4","iv5","iv6","iv7","iv8","iv9","iv10","iv11","iv12","iv13","iv14","iv15","iv16","iv17","iv18","iv19","iv20","iv21","iv22","iv23","iv24","iv25","iv26","iv27","iv28","iv29","iv30","iv31","iv32","iv33","iv34","iv35","iv36","iv37","iv38","iv39","iv40"};*/
        /*Image[] tiles = */
        placementGrp.getChildren().clear();

        if (!StratoGame.isPlacementWellFormed(placement)){
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
                iv1.setRotate((((int) placement.charAt(i+3))-65)*90);
                iv1.setFitWidth(48);
                iv1.setPreserveRatio(true);
                iv1.setSmooth(true);
                iv1.setCache(true);
                gridPane.getChildren().add(iv1);
                GridPane.setRowIndex(iv1,5+i);
                GridPane.setColumnIndex(iv1,5+i);
                GridPane.setRowSpan(iv1,2);
                GridPane.setColumnSpan(iv1,2);
                placementGrp.getChildren().add(iv1);

                /*Zhixian: the following just displays the coordinates of the tile on the screen for debugging
                *It seems that the setRowIndex is working for the text, but not for the tile images*/
                String coord = java.lang.Integer.toString(getRowIndex(iv1))+java.lang.Integer.toString(getColumnIndex(iv1));
                Text testing = new Text("coord: "+ coord );
                gridPane.getChildren().add(testing);
                GridPane.setRowIndex(testing,1+i);
                GridPane.setColumnIndex(testing,2+i);
                GridPane.setRowSpan(testing,4);
            }

            root.getChildren().add(gridPane);
        }



            //place tile based on i and (i+1)
            //select tile based on (i+2) -- Done: Manal
            //rotate tile based on (i+3)


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
            } /*Zhixian: this part is what the 'refresh' button does. It takes the text in the
            * text field and passes it to the function makePlacement, then clears the text field for the next move*/
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
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
