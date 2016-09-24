package comp1110.ass2;



import java.util.Random;

import static comp1110.ass2.StratoGame.*;
import static comp1110.ass2.Colour.*;

/**
 * Created by manalmohania on 25/08/2016.
 */

/**
 * This class contains methods relating to scoring and winner determination.
 * All methods implemented by Manal Mohania.
 * */

public final class Scoring {
    private static final int BOARD_SIZE = 26;

    /* The following declarations are global for efficiency purposes only
    *  Do NOT add any methods to try and access/write to them. */
    private static int[][] flags = new int[BOARD_SIZE][BOARD_SIZE];
    private static Colour[][] colours;
    private static Colour[][] colours2;
    private static int[][] heights = new int[BOARD_SIZE][BOARD_SIZE];
    private static int[][] candidates = new int[400][2]; // An upper bound for the number of contiguous regions of a certain colour
    private static boolean winnerByChance; // If this bool is true, you'll know that the winner has been determined by chance.

    /**
     * Given a placement string, this method returns the winner according to the rules of the game.
     *
     * @param placement: a valid placement string (of max length)
     * @return the winner as a boolean - true means that green has won and false that red is the winner.
     * */
    public static boolean getWinner(String placement) {

        winnerByChance = false;
        int[][] greenStuff = new int[400][2];
        int[][] redStuff = new int[400][2];

        /*1. call getScore for green
        * 2. have a copy of the candidates
        * 3. call getScore fo red, if they're not equal, well and good. Bye bye.
        * 4. else have a copy of the candidates for red.
        * 5. Remove those entries, shift each valid entry to the left by one
        * 6. Repeat processes above.
        * 7. Base case - rand value*/
        int greenScore = getScore(placement, true);

        for (int i = 0; i < 400; i++) {
            greenStuff[i][0] = candidates[i][0];
            greenStuff[i][1] = candidates[i][1];
            if (candidates[i][0] == 0)
                break;
        }

        int redScore = getScore(placement, false);

        if (redScore > greenScore) {
            return false;
        }

        if (greenScore > redScore) {
            return true;
        }

        /*At this point we know that both are tied up till now*/

        for (int i = 0; i < 400; i++) {
            redStuff[i][0] = candidates[i][0];
            redStuff[i][1] = candidates[i][1];
            if (candidates[i][0] == 0)
                break;
        }

        return nextResult(redStuff, greenStuff);
    }

    /**
     * This function is used only when there has been a tie due to equal scores.
     * In case there is a tie again, based on the second largest areas, this function recursively calls itself until a winner is decided
     *
     * @param red: a 2d array containing the area of the width of contiguous regions and the max height in it for the red player
     * @param green: a 2d array containing the area of the width of contiguous regions and the max height in it for the green player
     *
     * @return the winner based on the boolean values as described above*/

    private static boolean nextResult(int[][] red, int[][] green) {
        /*identify max*/
        int redMax = 0;
        int greenMax = 0;

        for (int i = 0; i < 400; i++) {
            redMax = redMax > red[i][0] ? redMax : red[i][0];
            if (red[i][0] == 0)
                break;
        }

        for (int i = 0; i < 400; i++) {
            greenMax = greenMax > green[i][0] ? greenMax : green[i][0];
            if (green[i][0] == 0)
                break;
        }

        int redH = 0;
        int greenH = 0;

        for (int i = 0; i < 400; i++) {
            if (red[i][0] == 0)
                break;
            redH = (red[i][0] == redMax && red[i][1] > redH ? red[i][1] : redH);
        }

        for (int i = 0; i < 400; i++) {
            if (green[i][0] == 0)
                break;
            greenH = (green[i][0] == greenMax && green[i][1] > greenH ? green[i][1] : greenH);
        }

        /*remove max and shift everything*/
        int flag = 0;

        for (int i = 0; i < 400; i++) {
            if (red[i][0] == redMax && red[i][1] == redH) {
                if (flag == 0) {
                    flag = 1;
                    continue;
                }
                flag = 1;
            }

            if (flag == 1) {
                red[i - 1][0] = red[i][0];
                red[i - 1][1] = red[i][1];
            }

            if (red[i][0] == 0)
                break;
        }

        /*same thing with the green array*/
        flag = 0;

        for (int j = 0; j < 400; j++) {
            if (green[j][0] == greenMax && green[j][1] == greenH) {
                if (flag == 0) {
                    flag += 1; // Fooled IntelliJ into stop complaining about duplicated code. Remove that `+` and you'll see what I mean
                    continue;
                }
                flag = 1;
            }

            if (flag == 1) {
                green[j - 1][0] = green[j][0];
                green[j - 1][1] = green[j][1];
            }

            if (green[j][0] == 0)
                break;
        }

        /*check if there are regions left*/
        if (greenMax == 0 && redMax > 0)
            return false;
        if (redMax == 0 && greenMax > 0)
            return true;
        if (greenMax == 0 && redMax == 0) {
            winnerByChance = true;
            Random r = new Random();
            return r.nextBoolean();
        }

        /*call itself with the modified arrays*/
        return nextResult(red, green);
    }

    /**given a placement string, this function returns the score based on the rules of the game
     *
     * @param placement: A valid placement string
     * @param green: A booleean value denoting if it's green's turn
     *
     * @return the score*/
    static int getScore(String placement, boolean green) {

        colours = colourArray(placement);
        colours2 = colourArray(placement);
        heights = heightArray(placement);

        /*for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                System.out.println("i: " + i + " j: " + j + " " + heights[i][j]);
            }
        }*/

        /*reset all flags to zero*/
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                flags[i][j] = 0;
            }
        }
        int k = 0;

        /*reset candidate values to 0*/
        for (int i = 0; i < 400; i++) {
            candidates[i][0] = 0;
            candidates[i][1] = 0;
        }

        flags[12][12] = 1;
        flags[12][13] = 1;

        // check if colour is the required one and corresponding flag is 0. If yes, proceed to find the corresponding score

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (colours[i][j] == GREEN && flags[i][j] == 0 && green) {
                    int val = floodFill(i, j, GREEN);
                    candidates[k][0] = val;
                    candidates[k][1] = floodHeight(i, j, GREEN, 0);
                    k++;
                } else if (!green && colours[i][j] == RED && flags[i][j] == 0) {
                    int val = floodFill(i, j, RED);
                    candidates[k][0] = val;
                    candidates[k][1] = floodHeight(i, j, RED, 0);
                    k++;
                }
            }
        }

        int maxArea = 1;
        int maxHeight = 1;

        for (int i = 0; i < 400; i++) {
            if (candidates[i][0] == 0)
                break;
            // System.out.println(i + "\t" + candidates[i][0] + "\t" + candidates[i][1]);
            maxArea = (candidates[i][0] > maxArea ? candidates[i][0] : maxArea);
        }

        for (int i = 0; i < 400; i++) {
            if (candidates[i][0] == 0)
                break;
            maxHeight = (candidates[i][0] == maxArea && candidates[i][1] > maxHeight ? candidates[i][1] : maxHeight);
        }

        return maxArea * maxHeight;
    }

    /**
     * This function calculates the maximum height in a contiguous region given the coordinates of any one cell on it
     *
     * @param col: The column value of the cell of which the height is required
     * @param row: The rwo value of the cell of which the height is required
     * @param colour: The colour of the cell of which the height is being evaluated
     * @param max: The maximum height calculated in the region so far
     *
     * @return the max height in the region*/
    private static int floodHeight(int col, int row, Colour colour, int max) {

        int val;

        if (!(col >= 0 && row >= 0 && col <= 25 && row <= 25)) {
            return max;
        }

        if (colours2[col][row] != colour) {
            return max;
        }

        colours2[col][row] = BLACK;
        val = heights[col][row] > max ? heights[col][row] : max;

        return myMax(floodHeight(col + 1, row, colour, val), floodHeight(col - 1, row, colour, val), floodHeight(col, row + 1, colour, val), floodHeight(col, row - 1, colour, val));
    }

    /**
     * This function calculates the are of a contiguous region of one colour given the coordinates of any one cell on it
     *
     * @param col: The column value of the cell for which the area is required
     * @param row: The rwo value of the cell of for which the area is required
     * @param colour: The colour of the cell of for which the area being evaluated
     *
     * @return the area of the region*/
    private static int floodFill(int col, int row, Colour colour) {

        int val;

        if (!(col >= 0 && row >= 0 && col <= 25 && row <= 25)) {
            return 0;
        }

        flags[col][row] = 1;

        if (colours[col][row] != colour) {
            return 0;
        }

        colours[col][row] = BLACK;
        val = 1 + floodFill(col + 1, row, colour) +
                floodFill(col - 1, row, colour) +
                floodFill(col, row + 1, colour) +
                floodFill(col, row - 1, colour);

        return val;
    }

    /**Calculates the maximum of four intgers
     * @params a, b, c, d: the four integers
     * @return the max of the four
     * */
    private static int myMax(int a, int b, int c, int d) {
        return Math.max(Math.max(Math.max(a, b), c), d);
    }

    /**
     * The method checks if the winner has been determined by chance
     *
     * @return a boolean, true if the winner has been determined by chance
     *
     * NOTE: This method hasn't been called as yet, because it will only be used in the very end*/
    static boolean isWinnerByChance() {
        return winnerByChance;
    }

}
