package comp1110.ass2;


/**
 * Created by Aftran261 on 11/08/2016.
 */
/*This describes the entire board, like in Kalaha.*/
public class BoardState {
    public Colour playerTurn;
    public BoardState(Colour colour){
        this.playerTurn = colour;
    }
}
