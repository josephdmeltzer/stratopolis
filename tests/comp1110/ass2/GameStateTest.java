package comp1110.ass2;

import org.junit.Test;

import static comp1110.ass2.Colour.BLACK;
import static comp1110.ass2.Colour.GREEN;
import static comp1110.ass2.Colour.RED;
import static comp1110.ass2.Difficulty.HUMAN;
import static org.junit.Assert.*;

/**
 * Implemented by Zhixian Wu
 */
public class GameStateTest {

    /*This test tests if the length of the new moveHistory string in a GameState object
    * is equal to the sum of its previous length and the length of its input*/
    @Test
    public void testUpdateMovesLength(){
        GameState gs1 = new GameState(BLACK, HUMAN, HUMAN);
        String[] array = {"MMUA","ZVCT","CXDO","BFLMQBMBH","FBFPDNKFWE"};
        for (String test: array){
            int previousLength = gs1.moveHistory.length();
            gs1.updateMoves(test);
            int inputLength = test.length();
            int totalLength = gs1.moveHistory.length();
            assertTrue("The total length of the new moveHistory is equal to " + totalLength
                + "but its length before was " + previousLength + "and the string added had length "
                + inputLength, previousLength+inputLength==totalLength);
        }
    }


    /*This test checks if last character of the new moveHistory is the same as
    * the last character of the input string. It then checks this for the second
    * last character, and so on until it has interated over the whole input string*/
    @Test
    public void testUpdateMovesCharacterOrder(){
        GameState gs1 = new GameState(BLACK, HUMAN, HUMAN);
        String[] array = {"MMUA","ZVCT","CXDO","BFLMQBMBH","FBFPDNKFWE"};
        for (String test: array){
            gs1.updateMoves(test);
            int totalLength = gs1.moveHistory.length();
            int inputLength = test.length();
            for (int i=1; i <= inputLength; i++){
                assertTrue("The character " + (i-1) + "away from the end of the output"
                                + " string is different from the corrosponding " + i
                                + " to last character of the string that was supposed to "
                                +"be appended to it. The test string was: " + test,
                        test.charAt(inputLength-i)==gs1.moveHistory.charAt(totalLength-i));
            }
        }
    }

    /*This test checks if for each possible colour turn, the next turn
    * assignmed by nextTurn() is correct*/
    @Test
    public void testNextTurn(){
        GameState gs1 = new GameState(BLACK, HUMAN, HUMAN);
        for (int i=0;i<6;i++){
            Colour lastTurn = gs1.playerTurn;
            gs1.nextTurn();
            if (lastTurn==BLACK)
                assertTrue("Turn after a BLACK (automatic MMUA) turn is not GREEN",
                        gs1.playerTurn==GREEN);
            if (lastTurn==GREEN)
                assertTrue("Turn after a GREEN turn is not RED", gs1.playerTurn==RED);
            if (lastTurn==RED)
                assertTrue("Turn after a RED turn is not GREEN", gs1.playerTurn==GREEN);
        }

    }

}