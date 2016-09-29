package comp1110.ass2;

/**
 * Created by u5807060 on 11/08/2016.
 */
/*Implemented by Zhixian Wu*/
public class BoardState {
    public Colour playerTurn; /*Who has the current turn*/
    public PlayingMode playingMode; /*If the game is two-player,
          one-player with you playing as red, or one-player with you playing as green*/
    public String moveHistory; /*The moves made so far*/

    /*The class constructor
    * @param colour    Who has the current turn (playerTurn)
    * @param mode      playingMode*/
    public BoardState(Colour colour, PlayingMode mode){
        this.playerTurn = colour;
        this.playingMode = mode;
        this.moveHistory = "";
    }

    public void updateMoves(String placement){
        this.moveHistory = this.moveHistory + placement;
    }

}
