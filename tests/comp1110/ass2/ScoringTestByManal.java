package comp1110.ass2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static comp1110.ass2.TestUtility.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by manalmohania on 2/09/2016.
 */

/*This class contains my tests for scoring and winner determination*/


public class ScoringTestByManal {



    /*Tests for scoring*/

    /*
    * Properties of score-
    * 1. Score > 0
    * 2. Total Score <= ((n + 1)/2)*(-1 + 3n), where n is the number of pieces on board
    * 3. We know final scores in some strings
    * 4. Max possible score for a certain colour is 450. Not sure if it can be achieved.
    * 5. Score(RED) <= (1 + 2n)*(1 + (n + 1)/2), where n is the number of pieces on board except U
    */

    @Test
    public void testPositive(){
        for (int i = 0; i < PLACEMENTS.length; i++){
            for (int j = 1; j < PLACEMENTS[i].length()/4; j++){
                String temp = PLACEMENTS[i].substring(0, 4*j);
                assertTrue("Score for "+ temp + " must be positive for red, but returned " + StratoGame.getScoreForPlacement(temp, false), StratoGame.getScoreForPlacement(temp, false) > 0);
                assertTrue("Score for "+ temp + " must be positive for green, but returned " + StratoGame.getScoreForPlacement(temp, false), StratoGame.getScoreForPlacement(temp, true) > 0);
            }
        }
    }

    @Test
    public void testTotalBound(){
        for (int i = 0; i < PLACEMENTS.length; i++){
            for (int j = 1; j < PLACEMENTS[i].length()/4; j++){
                String temp = PLACEMENTS[i].substring(0, 4*j); // has length 4*j -> j pieces
                assertTrue("Total score for placement "+ temp + " must be less than or equal to " + ((j + 1)/2)*(3*j - 1) + ", but returned " + (StratoGame.getScoreForPlacement(temp, false) + StratoGame.getScoreForPlacement(temp, true)), (StratoGame.getScoreForPlacement(temp, true) + StratoGame.getScoreForPlacement(temp, false)) <= ((j + 1)/2)*(3*j - 1));
            }
        }
    }

    @Test
    public void testLessThan450(){
        for (int i = 0; i < PLACEMENTS.length; i++){
            assertFalse("The score for no player can be more than 450, but red returned " + StratoGame.getScoreForPlacement(PLACEMENTS[i], false), StratoGame.getScoreForPlacement(PLACEMENTS[i], false) > 450);
            assertFalse("The score for no player can be more than 450, but green returned " + StratoGame.getScoreForPlacement(PLACEMENTS[i], true), StratoGame.getScoreForPlacement(PLACEMENTS[i], true) > 450);
        }
    }

    @Test
    public void testRedScoreBound(){
        for (int i = 0; i < PLACEMENTS.length; i++){
            for (int j = 2; j < PLACEMENTS[i].length()/4; j++){
                String temp = PLACEMENTS[i].substring(0, 4*j);
                assertTrue("The score for red for a placement string of length " + 4*j + " can be at most " + (2*j - 1)*(1 + j/2) + " but returned " + StratoGame.getScoreForPlacement(temp, false), StratoGame.getScoreForPlacement(temp, false) <= (2*j - 1)*(1 + j/2));
            }
        }
    }

    /*Tests for winner determination*/
    /*The placement string must be of max possible length*/
    /*Assumes the scoring methods work correctly*/

    /*
    * Properties of winner-
    * 1. Score (RED) > Score (Green) => FALSE
    * 2. SCORE (GREEN) > Score (RED) => TRUE
    * 3. Winner of a symmetrical game must be randomly chosen
    * */

    @Test
    public void testInequalityDecidesWinner(){
        for (String PLACEMENT : PLACEMENTS) {
            if (StratoGame.getScoreForPlacement(PLACEMENT, false) > StratoGame.getScoreForPlacement(PLACEMENT, true)) {
                assertTrue("Green has been declared winner in the placement string " + PLACEMENT, !StratoGame.greenHasWon(PLACEMENT));
            } else if (StratoGame.getScoreForPlacement(PLACEMENT, false) < StratoGame.getScoreForPlacement(PLACEMENT, true)) {
                assertTrue("Red has been declared winner in the placement string " + PLACEMENT, StratoGame.greenHasWon(PLACEMENT));
            }
        }
        System.out.println(createSymmetric("NLOB"));
    }


    /*Creating a symmetric string
    * 1. Identify the symmetrically opposite pieces
    * 2. Identify the symmetrically opposite orientation
    * 3. Identify the symmetrically opposite coordinates
    * 4. Ensure nothing other than the centre piece lies in the middle
    * 5. To ensure symmetry of board available, nothing should go into the bottom row or rightmost column
    * */

    /*returns 0000 if original string does not satisfy conditions*/
    private static String createSymmetric(String original){
        String x = "0000";
        if (original.length() != 4)
            return x;

        char oppOr;

        switch(original.charAt(3)) {
            case 'A': oppOr = 'C'; break;
            case 'B': oppOr = 'D'; break;
            case 'C': oppOr = 'A'; break;
            case 'D': oppOr = 'B'; break;
            default: return x;
        }

        char oppPiece;

        if (original.charAt(2) >= 'A' && original.charAt(2) <= 'J'){
            oppPiece = (char)(original.charAt(2) + 'K' - 'A');
        }
        else if (original.charAt(2) >= 'K' && original.charAt(2) <= 'T'){
            oppPiece = (char)(original.charAt(2) - 'K' + 'A');
        }

        else{return x;}

        if (original.charAt(0) == 'Z' || original.charAt(1) == 'A')
            return x;

        char oppCol = (char) (original.charAt(0) + 2 * ('M' - original.charAt(0)));
        char oppRow = (char) (original.charAt(1) + 2 * (0.5 + (float) ('M' - original.charAt(1))));

        return "" + oppCol + oppRow + oppPiece + oppOr;
    }

    // How do I create a random placement?
    // Identify centre. Go in random direction
    // Test spot with a series of random orientations
    // If placement is valid, place it, else repeat step 2 with the new centre being the current coordinate
}
