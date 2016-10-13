package comp1110.ass2;

import org.junit.Test;

import java.util.Random;

import static comp1110.ass2.TestUtility.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * All the tests in this section were created by Manal Mohania
 * */


public class ScoringAndWinning {

    /** Tests for scoring
     *
     *
    * Properties of score-
    * 1. Score >= 0
    * 2. Total Score <= ((n + 1)/2)*(-1 + 3n), where n is the number of pieces on board
    * 3. We know final scores in some strings
    * 4. Max possible score for a certain colour is 450. Not sure if it can be achieved.
    * 5. Score(RED) <= (1 + 2n)*(1 + (n + 1)/2), where n is the number of pieces on board except U
     *
     * The following tests check if these 5 properties of the score are satisfied
    */

    @Test
    public void testNonNegative(){
        for (String PLACEMENT : PLACEMENTS) {
            for (int j = 1; j < PLACEMENT.length() / 4; j++) {
                String temp = PLACEMENT.substring(0, 4 * j);
                assertTrue("Score for " + temp + " must be positive for red, but returned " + StratoGame.getScoreForPlacement(temp, false), StratoGame.getScoreForPlacement(temp, false) >= 0);
                assertTrue("Score for " + temp + " must be positive for green, but returned " + StratoGame.getScoreForPlacement(temp, false), StratoGame.getScoreForPlacement(temp, true) >= 0);
            }
        }
    }

    @Test
    public void testTotalBound(){
        for (String PLACEMENT : PLACEMENTS) {
            for (int j = 1; j < PLACEMENT.length() / 4; j++) {
                String temp = PLACEMENT.substring(0, 4 * j); // has length 4*j -> j pieces
                assertTrue("Total score for placement " + temp + " must be less than or equal to " + ((j + 1) / 2) * (3 * j - 1) + ", but returned " + (StratoGame.getScoreForPlacement(temp, false) + StratoGame.getScoreForPlacement(temp, true)), (StratoGame.getScoreForPlacement(temp, true) + StratoGame.getScoreForPlacement(temp, false)) <= ((j + 1) / 2) * (3 * j - 1));
            }
        }
    }

    @Test
    public void testLessThan450(){
        for (String PLACEMENT : PLACEMENTS) {
            assertFalse("The score for no player can be more than 450, but red returned " + StratoGame.getScoreForPlacement(PLACEMENT, false), StratoGame.getScoreForPlacement(PLACEMENT, false) > 450);
            assertFalse("The score for no player can be more than 450, but green returned " + StratoGame.getScoreForPlacement(PLACEMENT, true), StratoGame.getScoreForPlacement(PLACEMENT, true) > 450);
        }
    }

    @Test
    public void testAgainstStrings(){
        assertTrue("The score for placement MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBMLDANPLDNNBAONMCLOFAPQTC for green should be 8 but returned " + StratoGame.getScoreForPlacement("MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBMLDANPLDNNBAONMCLOFAPQTC", true), StratoGame.getScoreForPlacement("MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBMLDANPLDNNBAONMCLOFAPQTC", true) == 8);
        assertTrue("The score for placement MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBMLDANPLDNNBAONMCLOFAPQTC for red should be 33 but returned " + StratoGame.getScoreForPlacement("MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBMLDANPLDNNBAONMCLOFAPQTC", false), StratoGame.getScoreForPlacement("MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBMLDANPLDNNBAONMCLOFAPQTC", false) == 33);
    }

    @Test
    public void testRedScoreBound(){
        for (String PLACEMENT : PLACEMENTS) {
            for (int j = 2; j < PLACEMENT.length() / 4; j++) {
                String temp = PLACEMENT.substring(0, 4 * j);
                assertTrue("The score for red for a placement string of length " + 4 * j + " can be at most " + (2 * j - 1) * (1 + j / 2) + " but returned " + StratoGame.getScoreForPlacement(temp, false), StratoGame.getScoreForPlacement(temp, false) <= (2 * j - 1) * (1 + j / 2));
            }
        }
    }


    /** Tests for winner determination
        The placement string must be of max possible length
        Assumes the scoring methods work correctly


    * Properties of winner-
    * 1. Score (RED) > Score (Green) => FALSE
    * 2. SCORE (GREEN) > Score (RED) => TRUE
    * 3. Winner of a symmetrical game must be randomly chosen
     *
     * The next test checks properties 1 and 2
     *
     * The one after that checks property 3
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
    }

    /*Creating a symmetric string
    * 1. Identify the symmetrically opposite pieces
    * 2. Identify the symmetrically opposite orientation
    * 3. Identify the symmetrically opposite coordinates
    * 4. Ensure nothing other than the centre piece lies in the middle
    * 5. To ensure symmetry of board available, nothing should go into the rightmost column
    * */

    /** This method creates a symmetric placement of tiles
     * using the technique mentioned above
     * */
    private static String createSymmetric(String original){
        String x = "";
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

        if (original.charAt(0) == 'Z')
            return x;

        char oppCol = (char) (original.charAt(0) + 2 * ('M' - original.charAt(0)));
        char oppRow = (char) (original.charAt(1) + 2 * (0.5 + (float) ('M' - original.charAt(1))));

        return "" + oppCol + oppRow + oppPiece + oppOr;
    }

    /** Given a char array s, the function shuffles it and returns the result */
    private static char[] shuffle(char[] s){
        int pos = 0;
        int len = s.length;
        Random random = new Random();
        int[] check = new int[len];
        char[] shuffled = new char[len];
        for (int i = 0; i < len; i ++){
            int x = random.nextInt(len - i);
            for (int j = 0; j < len; j++){
                if (x == 0 && check[j] == 0){
                    pos = j;
                    break;
                }


                if (check[j] == 0) x--;
            }

            if (pos == -1){ System.out.printf("Something's not right here "); System.exit(255);}

            shuffled[i] = s[pos];
            /*update check*/
            check[pos] = 1;

        }
        return shuffled;
    }

    /** THis test checks that the winner of a symmetrical board is random */
    @Test
    public void testSymmetricPlacement(){

        char[] redPieces = new char[20];

        for (int i = 0; i < 20; i++){
            redPieces[i] = ((char)('K' + i) > 'T' ? (char)('K' + i - 10) : (char)('K' + i));
        }

        char[] orientaions = {'A', 'B', 'C', 'D'};


        char[] cols;
        char[] rows;
        char[] temp = new char[26];

        for (int i = 0; i < 26; i++){
            temp[i] = (char)('A' + i);
        }

        for (int iter = 0; iter < 25; iter++) {
            String placement = "MMUA";
            cols = shuffle(temp);
            rows = shuffle(temp);
            char[] orShuffled = shuffle(orientaions);
            char[] shuffled = shuffle(redPieces);
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    for (int k = 0; k < 20; k++) {
                        for (int l = 0; l < 4; l++) {
                            if (StratoGame.isPlacementValid(placement + cols[i] + rows[j] + shuffled[k] + orShuffled[l]) && cols[i] != 'M') {
                                placement += "" + cols[i] + rows[j] + shuffled[k] + orShuffled[l];
                                placement += createSymmetric("" + cols[i] + rows[j] + shuffled[k] + orShuffled[l]);

                            }
                        }
                    }
                }
            }

            /*at this point we have the symmetric placement string. Need to check if the winner is random*/
            boolean redWin = false;
            boolean greenWin = false;
            for (int i = 0; i < 10000; i++){
                if (StratoGame.greenHasWon(placement))
                    greenWin = true;
                else
                    redWin = true;
                if (greenWin && redWin)
                    break;
            }
            assertTrue("The placement string "+ placement + " is symmetric and needs to have a random winner. However, " + (StratoGame.greenHasWon(placement) ? "green"  : "red") + "  has won each time in 10000 iterations", redWin && greenWin);
        }
    }
}
