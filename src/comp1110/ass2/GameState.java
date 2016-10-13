package comp1110.ass2;

import static comp1110.ass2.Colour.GREEN;
import static comp1110.ass2.Colour.RED;

/**
 * Created by u5807060 on 11/08/2016.
 */
/*Implemented by Zhixian Wu*/
/* This class contains all the variables that are reset for
*  each new game, so they can be reset just by creating a
*  new GameState object.
*/
public class GameState {
    public Colour playerTurn; /*Who has the current turn*/
    public Difficulty greenPlayer;
    public Difficulty redPlayer;
    public String moveHistory; /*The moves made so far*/

    /**
     * The class constructor
     * @param colour    Who ahs the current turn (playerTurn)
     * @param diffG     The difficulty of the green player
     * @param diffR     The difficulty of the red player
     */
    public GameState(Colour colour, Difficulty diffG, Difficulty diffR){
        this.playerTurn = colour;
        this.greenPlayer = diffG;
        this.redPlayer = diffR;
        this.moveHistory = "";
    }

    /*Update the string of all the placements so far with the new placement
    * @param placement   the new placement*/
    public void updateMoves(String placement){ this.moveHistory = this.moveHistory + placement;}

    /*Updates whose turn it is*/
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
