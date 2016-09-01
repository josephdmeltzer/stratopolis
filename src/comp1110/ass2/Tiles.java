package comp1110.ass2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Aftran261 on 11/08/2016.
 */
/*All 26x26 tiles will be objects of this class*/
public class Tiles {
    public Rectangle rect;
    public String coord;

    public Tiles(char row, char col){
        Rectangle r = new Rectangle(23, 23);
        r.setFill(Color.WHITE);
        this.rect = r;
        this.coord = new StringBuilder().append(col).append(row).toString();

    }
}
