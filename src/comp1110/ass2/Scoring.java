package comp1110.ass2;

import static comp1110.ass2.StratoGame.*;
import static comp1110.ass2.Colour.*;

/**
 * Created by manalmohania on 25/08/2016.
 */
public final class Scoring {
    /*I wanted to have a separate class to handle scoring -- Manal*/
    private static final int BOARD_SIZE = 26;

    /*The following declarations are global for efficiency purposes*/
    private static int[][] flags = new int[BOARD_SIZE][BOARD_SIZE];
    private static Colour[][] colours;
    private static int[][] heights = new int[BOARD_SIZE][BOARD_SIZE];


    static int getScore(String placement, boolean green){

        colours = colourArray(placement);
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
                    int[] val = floodFill(i, j, GREEN, 0);
                    candidates[k][0] = val[0];
                    candidates[k][1] = val[1];
                    k++;
                }
                else if (!green && colours[i][j] == RED && flags[i][j] == 0){
                    int[] val = floodFill(i, j, RED, 0);
                    candidates[k][0] = val[0];
                    candidates[k][1] = val[1];
                    k++;
                    System.out.println(val[0] + " " + val[1]);
                }
            }
        }


        int prod = 1;
        for (int i = 0; i < candidates.length; i++){
            int temp = candidates[i][0] * candidates[i][1];
            prod = (temp > prod ? temp : prod);
        }
        return prod;
    }

    private static int[] floodFill(int col, int row, Colour colour, int height){

        int[] val = new int[2];
        val[1] = height;

        if (!(col >= 0 && row >= 0 && col <= 26 && row <= 26)){
            val[0] = 0;
            return val;
        }


        flags[col][row] = 1;

        if (colours[col][row] != colour) {
            val[0] = 0;
            return val;
        }

        int currHeight = heights[col][row] > height ? heights[col][row] : height;

        // System.out.println("col:" + col + "\t" + "row:" + row + "\t" + "height:" + currHeight);

        colours[col][row] = BLACK;

        val[0] = 1 + floodFill(col + 1, row, colour, currHeight)[0] +
                floodFill(col - 1, row, colour, currHeight)[0] +
                floodFill(col, row + 1, colour, currHeight)[0] +
                floodFill(col, row - 1, colour, currHeight)[0];

        val[1] = myMax(floodFill(col + 1, row, colour, currHeight)[1], floodFill(col - 1, row, colour, currHeight)[1], floodFill(col, row + 1, colour, currHeight)[1], floodFill(col, row - 1, colour, currHeight)[1]);

        System.out.printf("Row: %d\tCol: %d\tmyMax: %d\nval[1] for children\n%d %d %d %d\n\n", row, col, val[1], floodFill(col + 1, row, colour, currHeight)[1], floodFill(col - 1, row, colour, currHeight)[1], floodFill(col, row + 1, colour, currHeight)[1], floodFill(col, row - 1, colour, currHeight)[1]);

        return val;
    }

    private static int myMax(int a, int b, int c, int d){
        if (a >= b) {
            if (c >= d) {
                if (a >= c)
                    return a;
                return c;
            }
        }
        else {
            if (c >= d) {
                if (b >= c)
                    return b;
                return c;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(getScore("MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBMLDANPLDNNBAONMCLOFAPQTC", false));
    }

}
