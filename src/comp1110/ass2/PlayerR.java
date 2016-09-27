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
    public List available_tiles;

    private List movesG(){
        //this generates a valid list of tiles
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
    public PlayerR(){
        this.available_tiles = movesG();
        this.playerColour = RED;
        this.used_tiles = 0;
        this.rotation = 'A';
    }

}
