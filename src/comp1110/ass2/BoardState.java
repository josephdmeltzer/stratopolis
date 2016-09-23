package comp1110.ass2;

/**
 * Created by u5807060 on 11/08/2016.
 */
/*Implemented by Zhixian Wu*/
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
