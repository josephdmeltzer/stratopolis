package comp1110.ass2;

import static comp1110.ass2.Colour.BLACK;
import static comp1110.ass2.Colour.GREEN;
import static comp1110.ass2.Colour.RED;

/**
 * Created by u5807060 on 11/08/2016.
 */
/*Implemented by Zhixian Wu*/
public class GameState {
    public Colour playerTurn; /*Who has the current turn*/
    public Difficulty greenPlayer;
    public Difficulty redPlayer;
    public String moveHistory; /*The moves made so far*/


    /*The class constructor
    * @param colour    Who has the current turn (playerTurn)
    * @param mode      playingMode*/
    public GameState(Colour colour, Difficulty diffG, Difficulty diffR){
        this.playerTurn = colour;
        this.greenPlayer = diffG;
        this.redPlayer = diffR;
        this.moveHistory = "";
    }

    public void updateMoves(String placement){ this.moveHistory = this.moveHistory + placement;}


    public void nextTurn(){
        switch (this.playerTurn){
            case GREEN: {
                this.playerTurn=RED;
                break;
            }
            case RED: {
                this.playerTurn=GREEN;
                break;
            }
            case BLACK: {
                this.playerTurn=GREEN;
                break;
            }
        }
    }

}
