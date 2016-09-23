package comp1110.ass2;

/**
 * Created by Aftran261 on 11/08/2016.
 */
/*This describes the entire board, like in Kalaha.*/
public class BoardState {
    public Colour playerTurn;
    public PlayingMode playingMode;
    public String moveHistory;

    public BoardState(Colour colour, PlayingMode mode){
        this.playerTurn = colour;
        this.playingMode = mode;
        this.moveHistory = "";
    }

    public void updateMoves(String placement){
        this.moveHistory = this.moveHistory + placement;
    }

}
