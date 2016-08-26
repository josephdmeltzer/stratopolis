package comp1110.ass2;

import static comp1110.ass2.StratoGame.*;
import static comp1110.ass2.Colour.*;

/**
 * Created by manalmohania on 25/08/2016.
 */

final class Scoring {
    /*I wanted to have a separate class to handle scoring -- Manal*/
    private static final int BOARD_SIZE = 26;

    /* The following declarations are global for efficiency purposes only
    *  Do NOT add any methods to try and access/write to them. */
    private static int[][] flags = new int[BOARD_SIZE][BOARD_SIZE];
    private static Colour[][] colours;
    private static Colour[][] colours2;
    private static int[][] heights = new int[BOARD_SIZE][BOARD_SIZE];

    static int getScore(String placement, boolean green){

        colours = colourArray(placement);
        colours2 = colourArray(placement);
        heights = heightArray(placement);

        int[][] candidates = new int[400][2]; // An upper bound for the number of contiguous regions of a certain colour

        /*set all flags to zero*/
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                flags[i][j] = 0;
            }
        }
        int k = 0;

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
                }
                else if (!green && colours[i][j] == RED && flags[i][j] == 0){
                    int val = floodFill(i, j, RED);
                    candidates[k][0] = val;
                    candidates[k][1] = floodHeight(i, j, RED, 0);
                    k++;
                }
            }
        }

        int maxArea = 1;
        int maxHeight = 1;

        for (int i = 0; i < 400; i++){
            if (candidates[i][0] == 0)
                break;
            maxArea = (candidates[i][0] > maxArea ? candidates[i][0] : maxArea);
        }

        for (int i = 0; i < 400; i++){
            if (candidates[i][0] == 0)
                break;
            maxHeight = (candidates[i][0] == maxArea && candidates[i][1] > maxHeight ? candidates[i][1] : maxHeight);
        }

        return maxArea*maxHeight;
    }

    private static int floodHeight(int col, int row, Colour colour, int max){

        int val;

        if (!(col >= 0 && row >= 0 && col <= 26 && row <= 26)){
            return max;
        }

        if (colours2[col][row] != colour) {
            return max;
        }

        colours2[col][row] = BLACK;

        val = heights[col][row] > max ? heights[col][row] : max;

        return myMax(floodHeight(col + 1, row, colour, val), floodHeight(col - 1, row, colour, val), floodHeight(col, row + 1, colour, val), floodHeight(col, row - 1, colour, val));
    }

    private static int floodFill(int col, int row, Colour colour){

        int val;

        if (!(col >= 0 && row >= 0 && col <= 26 && row <= 26)){
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

    private static int myMax(int a, int b, int c, int d){
        return Math.max(Math.max(Math.max(a, b), c),d);
    }

    public static void main(String[] args) {
        System.out.println(getScore("MMUA", false));
    }

}
