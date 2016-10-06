package comp1110.ass2;

import static comp1110.ass2.AI.alphabeta;
import static comp1110.ass2.AI.alphabetaCheat;
import static comp1110.ass2.AI.validTiles;
import static comp1110.ass2.Colour.*;
import static comp1110.ass2.Pieces.getColours;
import static comp1110.ass2.Player.MAX_TILES;
import static comp1110.ass2.Scoring.getScore;
import static comp1110.ass2.Scoring.getWinner;
import static java.lang.StrictMath.min;


/**
 * This class provides the text interface for the Strato Game
 *
 * The game is based directly on Gigamic's Stratopolis game
 * (http://boardgamegeek.com/boardgame/125022/stratopolis)
 */
public class StratoGame {

    static boolean isTilePlacementWellFormed(String tilePlacement) {
        if (tilePlacement.length() != 4) {
            return false;
        } else {
            return ('A'<=tilePlacement.charAt(0) && tilePlacement.charAt(0)<='Z' && 'A'<=tilePlacement.charAt(1) && tilePlacement.charAt(1)<='Z' && 'A'<=tilePlacement.charAt(2) && tilePlacement.charAt(2)<='U' && 'A'<=tilePlacement.charAt(3) && tilePlacement.charAt(3)<='D');
        }
    }

    /**
     * Determine whether a placement string is well-formed:
     *  - it consists of exactly N four-character tile placements (where N = 1 .. 41)
     *  - each tile placement is well-formed
     *  - the first tile placement is 'MMUA'
     *  - the second tile placement (if any) is for a green tile
     *  - remaining tile placements alternate between red and green
     *  - no tile appears more than twice in the placement
     *
     * @param placement A string describing a placement of one or more tiles
     * @return True if the placement is well-formed
     */
    /* Method by Manal Mohania and Joseph Meltzer */
     static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 4: determine whether a placement is well-formed
        if (placement == null) return false;
        int len = placement.length();
        int numPieces = len/4;
        int[] counter = new int[20];
        Boolean c1 = (len % 4 == 0) && (numPieces >=1) && (numPieces <= 41);
        if (!c1) return false;
        for (int i = 0; i < len; i += 4){ /*note: name.substring(0,n) returns only up to the (n-1)the character*/
            if (!(isTilePlacementWellFormed(placement.substring(i,i+4))))
                return false;
        }
        if (! placement.substring(0,4).equals("MMUA")) return false;

        for (int i = 6; i< len - 1; i += 8) {
            if (!(placement.charAt(i) >= 'K' && placement.charAt(i) <= 'T')) return false;
        }

        for (int i = 10; i < len - 1; i += 8) {
            if (!(placement.charAt(i) >= 'A' && placement.charAt(i) <= 'J')) return false;
        }

        for (int i = 6; i < len; i += 4){
            int idx = placement.charAt(i) - 'A';
            counter[idx]++;
            if (counter[idx] > 2) return false;
        }
        return true;
    }


    /**
     * Determine whether a placement is valid.  To be valid, the placement must be well-formed
     * and each tile placement must follow the game's placement rules.
     *
     * @param placement A placement string
     * @return True if the placement is valid
     */
    public static boolean isPlacementValid(String placement) {
        // FIXME Task 6: determine whether a placement is valid
        if (!isPlacementWellFormed(placement)) return false;
        if (!isPlacementAdjacent2(placement)) {return false;}
        if (!tileStraddle(placement)) return false;
       // if (!checkBounds(placement)) return false; // most likely this is not needed anymore, as its stuff has been incorporated in placementAdjacent. Do not remove this line though.
        return areColoursAlright(placement);
    }

    /**
     * Convert a placement string to a 26x26 array of colours
     *
     * @param placement: A _valid_ placement string
     * @return a 26 x 26 array of colours
     *
     * function written by Manal Mohania
     * */
    static Colour[][] colourArray(String placement){
        Colour[][] coverage = new Colour[26][26];
        coverage[12][12] = RED;
        coverage[12][13] = GREEN;
        
        for (int i = 4; i < placement.length(); i += 4){
            /*jump to required position*/
            int col = placement.charAt(i) - 'A';
            int row = placement.charAt(i + 1) - 'A';
            char piece = placement.charAt(i + 2);
            char orientation = placement.charAt(i + 3);
            /*based on the orientation change the appropriate colours*/

            coverage[col][row] = Pieces.valueOf(""+piece).colours[0];

            if (orientation == 'A'){
                coverage[col + 1][row] = Pieces.valueOf(""+piece).colours[1];
                coverage[col][row + 1] = Pieces.valueOf(""+piece).colours[2];
            }
            else if (orientation == 'B'){
                coverage[col][row + 1] = Pieces.valueOf(""+piece).colours[1];
                coverage[col - 1][row] = Pieces.valueOf(""+piece).colours[2];
            }
            else if (orientation == 'C'){
                coverage[col - 1][row] = Pieces.valueOf(""+piece).colours[1];
                coverage[col][row - 1] = Pieces.valueOf(""+piece).colours[2];
            }
            else if (orientation == 'D'){
                coverage[col][row - 1] = Pieces.valueOf(""+piece).colours[1];
                coverage[col + 1][row] = Pieces.valueOf(""+piece).colours[2];
            }
            else
                System.out.println("colourArray: should not reach here");
        }
        
        return coverage;
    }

    /**
     * Convert a placement string to a 26x26 array of heights
     *
     * @param placement: A _valid_ placement string
     * @return a 26 x 26 array of heights
     *
     * function written by Manal Mohania
     * */
    public static int[][] heightArray(String placement){
        int[][] coverage = new int[26][26];
        coverage[12][12] = 1;
        coverage[12][13] = 1;

        for (int i = 4; i < placement.length(); i += 4){
            int col = placement.charAt(i) - 'A';
            int row = placement.charAt(i + 1) - 'A';
            coverage[col][row]++;
            if (placement.charAt(i+3) == 'A'){
                coverage[1 + col][row]++;
                coverage[col][1 + row]++;
            }
            else if (placement.charAt(i + 3) == 'B'){
                coverage[-1 + col][row]++;
                coverage[col][1 + row]++;
            }
            else if (placement.charAt(i + 3) == 'C'){
                coverage[-1 + col][row]++;
                coverage[col][-1 + row]++;
            }
            else if (placement.charAt(i + 3) == 'D'){
                coverage[1 + col][row]++;
                coverage[col][-1 + row]++;
            }
        }
        return coverage;
    }

    /*Exploit the fact that a tile on top must completely lie within the boundaries formed by other placements*/
    private static boolean isPlacementAdjacent2(String placement){

        int coverage[][] = new int[26][26];
        coverage[12][12] = 1;
        coverage[12][13] = 1;

        for (int i = 4; i < placement.length(); i += 4){
            int col = placement.charAt(i) - 'A';
            int row = placement.charAt(i + 1) - 'A';

            if (coverage[col][row] != 0){
                if (placement.charAt(i + 3) == 'A'){
                    if (col == 25 || row == 25){
                        return false;
                    }
                    if (!(coverage[1 + col][row] == coverage[col][1 + row] && coverage[1 + col][row] == coverage[col][row])) {
                        return false;
                    }
                    coverage[col + 1][row]++;
                    coverage[col][row + 1]++;
                    coverage[col][row]++;
                    continue;
                }

                else if (placement.charAt(i + 3) == 'B'){
                    if (col == 0 || row == 25){return false;}
                    if (!(coverage[col][row] == coverage[-1 + col][row] && coverage[-1 + col][row] == coverage[col][1 + row])) {
                        return false;
                    }
                    coverage[-1 + col][row]++;
                    coverage[col][row + 1]++;
                    coverage[col][row]++;
                    continue;
                }

                else if (placement.charAt(i + 3) == 'C'){
                    if (col == 0 || row == 0){return false;}
                    if (!(coverage[-1 + col][row] == coverage[col][row] && coverage[col][row] == coverage[col][-1 + row])) {
                        return false;
                    }
                    coverage[-1 + col][row]++;
                    coverage[col][-1 + row]++;
                    coverage[col][row]++;
                    continue;
                }
                else if (placement.charAt(i + 3) == 'D'){
                    if (col == 25 || row == 0){return false;}
                    if (!(coverage[1 + col][row] == coverage[col][row] && coverage[col][row] == coverage[col][-1 + row])) {
                        return false;
                    }
                    coverage[col + 1][row]++;
                    coverage[col][-1 + row]++;
                    coverage[col][row]++;
                    continue;
                }
                else{
                    System.out.println("isOnTop, adjacent2: should not reach here");
                }
            }

            if (placement.charAt(i+3) == 'A'){
                if ((col < 25 && coverage[1 + col][row] != 0) ||
                        (row < 25 && coverage[col][1 + row] != 0)){
                    return false;}
                if ((!(2 + col < 26) || coverage[2 + col][row] == 0) &&
                        (!(1 + col < 26 && row - 1 >= 0) || coverage[1 + col][-1 + row] == 0) &&
                        (!(col + 1 < 26 && row + 1 < 26) || coverage[1 + col][1 + row] == 0) &&
                        (!(row - 1 >= 0) || coverage[col][-1 + row] == 0) &&
                        (!(row + 2 < 26) || coverage[col][2 + row] == 0) &&
                        (!(col - 1 >= 0) || coverage[-1 + col][row] == 0) &&
                        (!(col - 1 >= 0 && row + 1 < 26) || coverage[-1 + col][1 + row] == 0)){
                    return false;
                }

                if (col == 25 || row == 25) {
                    return false;
                }
                coverage[col][row] = 1;
                coverage[1 + col][row] = 1;
                coverage[col][1 + row] = 1;
            }

            else if (placement.charAt(i+3) == 'B'){
                if ((col - 1 >= 0 && coverage[-1 + col][row] != 0) ||
                        (row + 1 < 26 && coverage[col][1 + row] != 0)) {
                    return false;
                }
                if (((!(1 + col < 26) || coverage[1 + col][row] == 0) &&
                        (!(1 + col < 26 && 1 + row < 26) || coverage[1 + col][1 + row] == 0) &&
                        (!(row - 1 >= 0) || coverage[col][-1 + row] == 0) &&
                        (!(row + 2 < 26) || coverage[col][2 + row] == 0) &&
                        (!(col - 1 >= 0 && row - 1 >= 0) || coverage[-1 + col][-1 + row] == 0) &&
                        (!(col - 1 >= 0 && row + 1 < 26) || coverage[-1 + col][1 + row] == 0) &&
                        (!(col - 2 >= 0) || coverage[-2 + col][row] == 0))) {
                    return false;
                }

                if (col == 0 || row == 25) {
                    return false;
                }
                coverage[col][row] = 1;
                coverage[-1 + col][row] = 1;
                coverage[col][1 + row] = 1;
            }

            else if (placement.charAt(i+3) == 'C'){
                if ((col - 1 >= 0 && coverage[-1 + col][row] != 0) ||
                        (row - 1 >= 0 && coverage[col][-1 + row] != 0)) {
                    return false;
                }
                if ((!(col + 1 < 26) || coverage[1 + col][row] == 0 ) &&
                        (!(col + 1 < 26 && row - 1 >= 0) || coverage[1 + col][-1 + row] == 0) &&
                        (!(row - 2 >= 0) || coverage[col][-2 + row] == 0) &&
                        (!(row + 1 < 26) || coverage[col][1 + row] == 0) &&
                        (!(col - 1 >= 0 && row - 1 >= 0) || coverage[-1 + col][-1 + row] == 0) &&
                        (!(col - 1 >= 0 && row + 1 < 26) || coverage[-1 + col][1 + row] == 0) &&
                        (!(col - 2 >= 0) || coverage[-2 + col][row] == 0)){
                    return false;
                }

                if (col == 0  || row == 0 ) {
                    return false;
                }
                coverage[col][row] = 1;
                coverage[-1 + col][row] = 1;
                coverage[col][-1 + row] = 1;
            }

            else if (placement.charAt(i+3) == 'D'){
                if ((col + 1 < 26 && coverage[1 + col][row] != 0) ||
                        (row - 1 >= 0 && coverage[col][-1 + row] != 0)){
                    return false;
                }
                if ((!(col + 2 < 26) || coverage[2 + col][row] == 0) &&
                        (!(col + 1 < 26 && row - 1 >= 0) || coverage[1 + col][-1 + row] == 0) &&
                        (!(col + 1 < 26 && row + 1 < 26) || coverage[1 + col][1 + row] == 0) &&
                        (!(row - 2 >= 0) || coverage[col][-2 + row] == 0) &&
                        (!(row + 1 < 26) || coverage[col][1 + row] == 0) &&
                        (!(col - 1 >= 0) || coverage[-1 + col][row] == 0) &&
                        (!(col - 1 >= 0 && row - 1 >= 0) || coverage[-1 + col][-1 + row] == 0)){
                    return false;
                }

                if (col == 25 || row == 0) {
                    return false;
                }
                coverage[col][row] = 1;
                coverage[1 + col][row] = 1;
                coverage[col][-1 + row] = 1;
            }
            else {
                System.out.println("isPlacementAdjacent: should not reach here");
            }

        }
        return true;
    }

    /**
     * This method is called internally from isPlacementAdjacent.
     * Checks if a given piece is dangling on the placements that are already made
     *
     * @param piece: the piece which is being checked for the overhanging
     * @param placement: the placement string of the previous placements
     *
     * @return true iff the piece is not overhanging
     *
     * function written by Manal Mohania
     * */
    private static boolean isOnTop(String piece, String placement){
        int[][] coverage;
        coverage = heightArray(placement);

        int idx1 = piece.charAt(0) - 'A';
        int idx2 = piece.charAt(1) - 'A';
        if (coverage[idx1][idx2] == 0)
            return false;


        if (piece.charAt(3) == 'A') {
            if (idx1 == 25 || idx2 == 25){return false;}

            if (!(coverage[1 + idx1][idx2] == coverage[idx1][1 + idx2] && coverage[1 + idx1][idx2] == coverage[idx1][idx2])) {
                return false;
            }
        }
        else if (piece.charAt(3) == 'B') {
            if (idx1 == 0 || idx2 == 25){return false;}
            if (!(coverage[idx1][idx2] == coverage[-1 + idx1][idx2] && coverage[-1 + idx1][idx2] == coverage[idx1][1 + idx2])) {
                return false;
            }
        }
        else if (piece.charAt(3) == 'C') {
            if (idx1 == 0 || idx2 == 0){return false;}
            if (!(coverage[-1 + idx1][idx2] == coverage[idx1][idx2] && coverage[idx1][idx2] == coverage[idx1][-1 + idx2])) {
                return false;
            }
        }
        else if (piece.charAt(3) == 'D') {
            if (idx1 == 25 || idx2 == 0){return false;}
            if (!(coverage[1 + idx1][idx2] == coverage[idx1][idx2] && coverage[idx1][idx2] == coverage[idx1][-1 + idx2]))
                return false;
        }
        else {
            System.out.println("call from isPlacementAdjacent - should not reach here");
        }
        return true;
    }

    /**
     * This method checks if tiles are adjacent to one another, and if they are stacked there must be no tile dangling
     *
     * @param placement a placement string
     * @return true iff tiles are adjacent and there is no overhanging
     *
     * To make this method more efficient the call to isOnTop can be removed and the code can be adjusted here.
     *
     * function written by Manal Mohania
    */

    private static boolean isPlacementAdjacent(String placement){
        /*
           The next array is used to identify if a position on the board has been covered
           I'll have the two middle tiles as 1 since they're covered since the beginning
        */
        int[][] coverage = new int[26][26];
        coverage[12][12] = 1;
        coverage[12][13] = 1;

        for (int i = 4; i < placement.length(); i += 4){

            //The first four characters must be MMUA .. skipping them

            int col = placement.charAt(i) - 'A';
            int row = placement.charAt(i + 1) - 'A';

            if (coverage[col][row] != 0){
                if (!(isOnTop(placement.substring(i, i + 4), placement.substring(0, i)))) {
                    return false;
                }
                continue;
            }

            if (placement.charAt(i+3) == 'A'){
                if ((col < 25 && coverage[1 + col][row] == 1) ||
                        (row < 25 && coverage[col][1 + row] == 1)){
                    return false;}
                if ((!(2 + col < 26) || coverage[2 + col][row] == 0) &&
                        (!(1 + col < 26 && row - 1 >= 0) || coverage[1 + col][-1 + row] == 0) &&
                        (!(col + 1 < 26 && row + 1 < 26) || coverage[1 + col][1 + row] == 0) &&
                        (!(row - 1 >= 0) || coverage[col][-1 + row] == 0) &&
                        (!(row + 2 < 26) || coverage[col][2 + row] == 0) &&
                        (!(col - 1 >= 0) || coverage[-1 + col][row] == 0) &&
                        (!(col - 1 >= 0 && row + 1 < 26) || coverage[-1 + col][1 + row] == 0)){
                    return false;
                }

                if (col == 25 || row == 25) {
                    return false;
                }
                coverage[col][row] = 1;
                coverage[1 + col][row] = 1;
                coverage[col][1 + row] = 1;
            }

            else if (placement.charAt(i+3) == 'B'){
                if ((col - 1 >= 0 && coverage[-1 + col][row] == 1) ||
                        (row + 1 < 26 && coverage[col][1 + row] == 1)) {
                    return false;
                }
                if (((!(1 + col < 26) || coverage[1 + col][row] == 0) &&
                        (!(1 + col < 26 && 1 + row < 26) || coverage[1 + col][1 + row] == 0) &&
                        (!(row - 1 >= 0) || coverage[col][-1 + row] == 0) &&
                        (!(row + 2 < 26) || coverage[col][2 + row] == 0) &&
                        (!(col - 1 >= 0 && row - 1 >= 0) || coverage[-1 + col][-1 + row] == 0) &&
                        (!(col - 1 >= 0 && row + 1 < 26) || coverage[-1 + col][1 + row] == 0) &&
                        (!(col - 2 >= 0) || coverage[-2 + col][row] == 0))) {
                    return false;
                }

                if (col == 0 || row == 25) {
                    return false;
                }
                coverage[col][row] = 1;
                coverage[-1 + col][row] = 1;
                coverage[col][1 + row] = 1;
            }

            else if (placement.charAt(i+3) == 'C'){
                if ((col - 1 >= 0 && coverage[-1 + col][row] == 1) ||
                        (row - 1 >= 0 && coverage[col][-1 + row] == 1)) {
                    return false;
                }
                if ((!(col + 1 < 26) || coverage[1 + col][row] == 0 ) &&
                        (!(col + 1 < 26 && row - 1 >= 0) || coverage[1 + col][-1 + row] == 0) &&
                        (!(row - 2 >= 0) || coverage[col][-2 + row] == 0) &&
                        (!(row + 1 < 26) || coverage[col][1 + row] == 0) &&
                        (!(col - 1 >= 0 && row - 1 >= 0) || coverage[-1 + col][-1 + row] == 0) &&
                        (!(col - 1 >= 0 && row + 1 < 26) || coverage[-1 + col][1 + row] == 0) &&
                        (!(col - 2 >= 0) || coverage[-2 + col][row] == 0)){
                    return false;
                }

                if (col == 0  || row == 0 ) {
                    return false;
                }
                coverage[col][row] = 1;
                coverage[-1 + col][row] = 1;
                coverage[col][-1 + row] = 1;
            }

            else if (placement.charAt(i+3) == 'D'){
                if ((col + 1 < 26 && coverage[1 + col][row] == 1) ||
                        (row - 1 >= 0 && coverage[col][-1 + row] == 1)){
                    return false;
                }
                if ((!(col + 2 < 26) || coverage[2 + col][row] == 0) &&
                        (!(col + 1 < 26 && row - 1 >= 0) || coverage[1 + col][-1 + row] == 0) &&
                        (!(col + 1 < 26 && row + 1 < 26) || coverage[1 + col][1 + row] == 0) &&
                        (!(row - 2 >= 0) || coverage[col][-2 + row] == 0) &&
                        (!(row + 1 < 26) || coverage[col][1 + row] == 0) &&
                        (!(col - 1 >= 0) || coverage[-1 + col][row] == 0) &&
                        (!(col - 1 >= 0 && row - 1 >= 0) || coverage[-1 + col][-1 + row] == 0)){
                    return false;
                }

                if (col == 25 || row == 0) {
                    return false;
                }
                coverage[col][row] = 1;
                coverage[1 + col][row] = 1;
                coverage[col][-1 + row] = 1;
            }
            else
                System.out.println("isPlacementAdjacent: should not reach here");
        }
        return true;
    }

    /**
     * Check if the tiles don't fall out of the board
     *
     * @param placement a placement string
     * @return true iff each tile falls within the board range
     *
     * function written by Manal Mohania
     * */

    private static boolean checkBounds(String placement){
        /*Inspect the positions of the origins of the pieces*/

        int flag = 0;

        for (int i = 0; i < placement.length(); i += 4){
            if (placement.charAt(i) == 'A' || placement.charAt(i + 1) == 'A' || placement.charAt(i) == 'Z' || placement.charAt(i + 1) == 'Z'){
                flag = 1;
                break;
            }
        }
        /*return true if it has nothing on the periphery of the grid*/
        if (flag == 0)
            return true;

        /*else check*/
        // if on the top row, the rotation must not be C or D
        // if on the rightmost column, the rotation must not be A or D
        // if on the bottom row, the rotation must not be A or B
        // if on the leftmost column, rotation must not be B or C

        for (int i = 0; i < placement.length(); i += 4) {
            if (placement.charAt(i) == 'A'){
                if (placement.charAt(i + 3) == 'B' || placement.charAt(i + 3) == 'C')
                    return false;
            }
            else if (placement.charAt(i) == 'Z'){
                if (placement.charAt(i + 3) == 'A' || placement.charAt(i + 3) == 'D')
                    return false;
            }
            else if (placement.charAt(i + 1) == 'A'){
                if (placement.charAt(i + 3) == 'C' || placement.charAt(i + 3) == 'D')
                    return false;
            }
            else if (placement.charAt(i + 1) == 'Z'){
                if (placement.charAt(i + 3) == 'A' || placement.charAt(i + 3) == 'B')
                    return false;
            }
        }
        return true;
    }

    /* Method by Joseph Meltzer */
    private static boolean tileStraddle(String placement) {
        int[][] tileTable = new int[26][26];

        for (int i = 4; i < placement.length(); i+=4) {
            int col = placement.charAt(i) - 'A';
            int row = placement.charAt(i+1) - 'A';

            if (placement.charAt(i+3) == 'A'){
                if (tileTable[col][row] == tileTable[col+1][row] && tileTable[col][row] == tileTable[col][row+1] && tileTable[col][row] != 0) return false;
                tileTable[col][row] = i;
                tileTable[col+1][row] = i;
                tileTable[col][row+1] = i;
            }
            else if (placement.charAt(i + 3) == 'B'){
                if (tileTable[col][row] == tileTable[col-1][row] && tileTable[col][row] == tileTable[col][row+1] && tileTable[col][row] != 0) return false;
                tileTable[col][row] = i;
                tileTable[col-1][row] = i;
                tileTable[col][row+1] = i;
            }
            else if (placement.charAt(i + 3) == 'C'){
                if (tileTable[col][row] == tileTable[col-1][row] && tileTable[col][row] == tileTable[col][row-1] && tileTable[col][row] != 0) return false;
                tileTable[col][row] = i;
                tileTable[col-1][row] = i;
                tileTable[col][row-1] = i;
            }
            else if (placement.charAt(i + 3) == 'D'){
                if (tileTable[col][row] == tileTable[col+1][row] && tileTable[col][row] == tileTable[col][row-1] && tileTable[col][row] != 0) return false;
                tileTable[col][row] = i;
                tileTable[col+1][row] = i;
                tileTable[col][row-1] = i;
            }
        }
        return true;
    }

    /**
     * This method returns true if green has won the game.
     * Currently, the method does not say if green has won by scoring higher points or by virtue of luck.
     * Adding that functionality should be fairly simple though.
     * NOTE: THE PLACEMENT STRING IS ASSUMED TO BE VALID
     *
     * function written by Manal Mohania
     * */
    static boolean greenHasWon(String placement){
        return getWinner(placement);
    }

    /* Method by Joseph Meltzer */
    private static boolean areColoursAlright(String placement){
        Colour[][] colourTable = new Colour[26][26];
        colourTable[12][12] = RED;
        colourTable[12][13] = GREEN;

        for (int i=4; i < placement.length(); i+=4) {
            int col = placement.charAt(i) - 'A';
            int row = placement.charAt(i+1) - 'A';

            if ((colourTable[col][row] != RED || getColours(placement.charAt(i+2))[0] != GREEN) && (colourTable[col][row] != GREEN || getColours(placement.charAt(i+2))[0] != RED)) {
                colourTable[col][row] = getColours(placement.charAt(i+2))[0];
            }
            else return false;

            if (placement.charAt(i+3) == 'A') {

                if ((colourTable[col+1][row] != RED || getColours(placement.charAt(i+2))[1] != GREEN) && (colourTable[col+1][row] != GREEN || getColours(placement.charAt(i+2))[1] != RED)) {
                    colourTable[col+1][row] = getColours(placement.charAt(i+2))[1];
                }
                else return false;
                if ((colourTable[col][row+1] != RED || getColours(placement.charAt(i+2))[2] != GREEN) && (colourTable[col][row+1] != GREEN || getColours(placement.charAt(i+2))[2] != RED)) {
                    colourTable[col][row+1] = getColours(placement.charAt(i+2))[2];
                }
                else return false;
            }
            else if (placement.charAt(i+3) == 'B') {
                if ((colourTable[col][row+1] != RED || getColours(placement.charAt(i+2))[1] != GREEN) && (colourTable[col][row+1] != GREEN || getColours(placement.charAt(i+2))[1] != RED)) {
                    colourTable[col][row+1] = getColours(placement.charAt(i+2))[1];
                }
                else return false;
                if ((colourTable[col-1][row] != RED || getColours(placement.charAt(i+2))[2] != GREEN) && (colourTable[col-1][row] != GREEN || getColours(placement.charAt(i+2))[2] != RED)) {
                    colourTable[col-1][row] = getColours(placement.charAt(i+2))[2];
                }
                else return false;
            }
            else if (placement.charAt(i+3) == 'C') {
                if ((colourTable[col-1][row] != RED || getColours(placement.charAt(i+2))[1] != GREEN) && (colourTable[col-1][row] != GREEN || getColours(placement.charAt(i+2))[1] != RED)) {
                    colourTable[col-1][row] = getColours(placement.charAt(i+2))[1];
                }
                else return false;
                if ((colourTable[col][row-1] != RED || getColours(placement.charAt(i+2))[2] != GREEN) && (colourTable[col][row-1] != GREEN || getColours(placement.charAt(i+2))[2] != RED)) {
                    colourTable[col][row-1] = getColours(placement.charAt(i+2))[2];
                }
                else return false;
            }
            else if (placement.charAt(i+3) == 'D') {
                if ((colourTable[col][row-1] != RED || getColours(placement.charAt(i+2))[1] != GREEN) && (colourTable[col][row-1] != GREEN || getColours(placement.charAt(i+2))[1] != RED)) {
                    colourTable[col][row-1] = getColours(placement.charAt(i+2))[1];
                }
                else return false;
                if ((colourTable[col+1][row] != RED || getColours(placement.charAt(i+2))[2] != GREEN) && (colourTable[col+1][row] != GREEN || getColours(placement.charAt(i+2))[2] != RED)) {
                    colourTable[col+1][row] = getColours(placement.charAt(i+2))[2];
                }
                else return false;
            }
        }
        return true;
    }

    /**
     * Determine the score for a player given a placement, following the
     * scoring rules for the game.
     *
     * @param placement A placement string
     * @param green True if the score for the green player is requested,
     *              otherwise the score for the red player should be returned
     * @return the score for the requested player, given the placement
     *
     * function written by Manal Mohania
     */

    public static int getScoreForPlacement(String placement, boolean green) {
        // FIXME Task 7: determine the score for a player given a placement

        /*I have this here for the moment but will remove it once main gets implemented*/
        if (!isPlacementValid(placement))
            return -1;

        return getScore(placement, green);
    }

    /**
     * Generate a valid move that follows from: the given placement, a piece to
     * play, and the piece the opponent will play next.
     *
     * @param placement  A valid placement string indicating a game state
     * @param piece  The piece you are to play ('A' to 'T')
     * @param opponentsPiece The piece your opponent will be asked to play next ('A' to 'T' or 0 if last move).
     * @return A string indicating a valid tile placement that represents your move.
     */
    /* Method by Joseph Meltzer:
       Search two deterministic levels and one probabilistic level into the game tree.
       Computation time is around 1 minute at the start of the game, runs into excess of 20 at the end of the game.
       Since even 1 minute of wait time is unreasonable, this generator is used only for the very last move.
       Computation speed will be improved with efficiency changes to isPlacementValid and its constituents,
       the tile search range, and tile search order.
       Once computation time is down to a reasonable level, this generator will be used exclusively when
       the AI difficulty is set to 'Hard'. (To be implemented)
       */
    public static String generateMove(String placement, char piece, char opponentsPiece) {
        // FIXME Task 10: generate a valid move
        boolean green = (piece>='K' && piece<='T');
        return alphabeta(placement, piece, opponentsPiece, 2, 1, -100, 1000, true, green).move;
    }

    /* Previous version of the generateMove function: used as a faster, but less powerful generator.
    *  Its computation time is around 1 second, or less, per move.
    *  Will be used exclusively when the AI difficulty setting is set to 'Medium'. (To be implemented)*/
    public static String genMoveMedium(String placement, char piece, char opponentsPiece) {
        boolean green = (piece>='K' && piece<='T');
        return alphabeta(placement, piece, opponentsPiece, 2, 0, -100, 1000, true, green).move;
    }
    /* Even older version of the generateMove function. Only looks at the immediately available moves.
       Seemingly instant computation time.
       Will be used exclusively when the AI difficulty setting is set to 'Easy'. (To be implemented)*/
    static char[] checkOrder = {'M','L','N','K','O','J','P','I','Q','H','R','G','S','F','T','E','U','D','V','C','W','B','X','A','Y','Z'};

    public static String genMoveEasy(String placement, char piece, char opponentsPiece) {
        String bestMove = "";
        int bestScore = 0;
        for (String move : validTiles(placement)) {
            char x = move.charAt(0);
            char y = move.charAt(1);
            for (char o='A'; o<='D'; o++) {
                if (piece>='A' && piece <='J') {
                    if (isPlacementValid(placement + x + y + piece + o) && getScoreForPlacement(placement + x + y + piece + o, false)>bestScore ) {
                        bestMove = ""+x+y+piece+o;
                        bestScore = getScoreForPlacement(placement + x + y + piece + o, false);
                    }
                }
                if (piece>='K' && piece <='T') {
                    if (isPlacementValid(placement + x + y + piece + o) && getScoreForPlacement(placement + x + y + piece + o, true)>bestScore ) {
                        bestMove = ""+x+y+piece+o;
                        bestScore = getScoreForPlacement(placement + x + y + piece + o, true);
                    }
                }
            }
        }
        return bestMove;
    }
    public static String genMoveCheating(String placement, Player us, Player opponent){
        int depth = min(MAX_TILES-us.used_tiles-1, 3);
        char piece = (char) (us.available_tiles).get(us.used_tiles);
        char opiece = (char) (opponent.available_tiles).get(opponent.used_tiles);
        boolean green = (piece>='K' && piece<='T');
        System.out.println("We are: "+green);
        if (depth>0) return alphabetaCheat(placement, us, opponent, depth, depth, -1000, 1000, true, green).move;
        else {
            System.out.println("genMoveNotEasy");
            return genMoveNotEasy(placement,piece,opiece);
        }
    }
    public static String genMoveNotEasy(String placement, char piece, char opponentsPiece) {
        String bestMove = "";
        int bestScore = -100;
        for (char x='A'; x<='Z'; x++) {
            for (char y='A'; y<='Z'; y++) {
                for (char o='A'; o<='D'; o++) {
                    if (piece>='A' && piece <='J') {
                        if (isPlacementValid(placement + x + y + piece + o) && getScoreForPlacement(placement + x + y + piece + o, false)>bestScore ) {
                            bestMove = ""+x+y+piece+o;
                            bestScore = getScoreForPlacement(placement + x + y + piece + o, false)-getScoreForPlacement(placement + x + y + piece + o, true);
                        }
                    }
                    if (piece>='K' && piece <='T') {
                        if (isPlacementValid(placement + x + y + piece + o) && getScoreForPlacement(placement + x + y + piece + o, true)>bestScore ) {
                            bestMove = ""+x+y+piece+o;
                            bestScore = getScoreForPlacement(placement + x + y + piece + o, true)-getScoreForPlacement(placement + x + y + piece + o, false);
                        }
                    }
                }
            }
        }
        return bestMove;
    }
}
