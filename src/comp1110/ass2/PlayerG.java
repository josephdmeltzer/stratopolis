package comp1110.ass2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static comp1110.ass2.Colour.GREEN;

/**
 * Created by manalmohania on 11/08/2016.
 */

/*Implemented by Zhixian Wu*/

public class PlayerG extends Player {
    public static Colour playerColour = GREEN;

    /*Generates a valid list of tiles and shuffles them*/
    private List movesG(){
        ArrayList<Character> moves = new ArrayList<>();
        moves.add('K');
        moves.add('L');
        moves.add('M');
        moves.add('N');
        moves.add('O');
        moves.add('P');
        moves.add('Q');
        moves.add('R');
        moves.add('S');
        moves.add('T');
        moves.add('K');
        moves.add('L');
        moves.add('M');
        moves.add('N');
        moves.add('O');
        moves.add('P');
        moves.add('Q');
        moves.add('R');
        moves.add('S');
        moves.add('T');
        Collections.shuffle(moves);
        return moves;
    }
    /*Constructor for the class*/
    public PlayerG(){
        super();
        this.available_tiles = movesG();
        this.playerColour = GREEN;
    }


}
