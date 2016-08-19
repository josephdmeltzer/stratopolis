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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import comp1110.ass2.gui.Setup;
import comp1110.ass2.StratoGame;


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
    private final Group placementGrp = new Group(); // I've added this -- might turn out to be useless
    TextField textField;




    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */

    void makePlacement(String placement) {
        // FIXME Task 5: implement the simple placement viewer

        /** The next blocked comment checks if the placement string is valid. Unfortunately, isPlacementWellFormed is NOT public.
         * If you're sure that it's okay to do so, make it public and uncomment the next statement.
         * -- Manal
         * */
        /*
        if (!StratoGame.isPlacementWellFormed(placement))
            throw new IllegalArgumentException("Bad placement" + placement);
        */

            //place tile based on i and (i+1)
            //select tile based on (i+2) -- Done: Manal
            //rotate tile based on (i+3)

        ImageView iv1 = new ImageView();
        placementGrp.getChildren().clear(); // uncomment this line if you do not need the image to be removed each time
        iv1.setImage(new Image(Viewer.class.getResource(URI_BASE + placement.charAt(2) + ".png").toString()));
        HBox hb = new HBox();
        hb.getChildren().add(iv1);
        placementGrp.getChildren().add(hb);
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
