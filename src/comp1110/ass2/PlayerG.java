package comp1110.ass2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static comp1110.ass2.Colour.GREEN;

/**
 * Created by manalmohania on 11/08/2016.
 */

/*Player A*/

public class PlayerG extends Player {
    public static Colour playerColour = GREEN;
    public List available_tiles;

    private List movesG(){
        //this generates a valid list of tiles
        List moves = new ArrayList();
        moves.add('K');
        moves.add('L');
        moves.add('N');
        moves.add('O');
        moves.add('P');
        moves.add('Q');
        moves.add('R');
        moves.add('S');
        moves.add('T');
        moves.add('M');
        moves.add('K');
        moves.add('L');
        moves.add('N');
        moves.add('O');
        moves.add('P');
        moves.add('Q');
        moves.add('R');
        moves.add('S');
        moves.add('T');
        moves.add('M');
        Collections.shuffle(moves);
        return moves;
    }
    public PlayerG(){
        this.available_tiles = movesG();
        this.playerColour = GREEN;
        this.used_tiles = 0;
        this.rotation = 'A';
    }
}
