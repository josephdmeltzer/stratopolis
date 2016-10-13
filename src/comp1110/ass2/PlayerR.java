package comp1110.ass2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static comp1110.ass2.Colour.RED;

/**
 * Created by manalmohania on 11/08/2016.
 */

/*Implemented by Zhixian Wu*/

public class PlayerR extends Player {
    public static Colour playerColour = RED;

    /*Generates a valid list of tiles and shuffles them*/
    private List movesG(){
        ArrayList<Character> moves = new ArrayList<>();
        moves.add('A');
        moves.add('B');
        moves.add('C');
        moves.add('D');
        moves.add('E');
        moves.add('F');
        moves.add('G');
        moves.add('H');
        moves.add('I');
        moves.add('J');
        moves.add('A');
        moves.add('B');
        moves.add('C');
        moves.add('D');
        moves.add('E');
        moves.add('F');
        moves.add('G');
        moves.add('H');
        moves.add('I');
        moves.add('J');
        Collections.shuffle(moves);
        return moves;
    }
    /*Constructor for the class*/
    public PlayerR(){
        super();
        this.available_tiles = movesG();
        this.playerColour = RED;
    }

}
