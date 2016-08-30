package comp1110.ass2;

import static comp1110.ass2.Colour.*;
import static comp1110.ass2.Pieces.getColours;
import static comp1110.ass2.Scoring.getScore;
import static comp1110.ass2.Scoring.getWinner;


/**
 * This class provides the text interface for the Strato Game
 *
 * The game is based directly on Gigamic's Stratopolis game
 * (http://boardgamegeek.com/boardgame/125022/stratopolis)
 */
public class StratoGame {

    int[][] flags; // NOTE: DO NOT WRITE TO THIS.

    /**
     * Determine whether a tile placement is well-formed according to the following:
     * - it consists of exactly four characters
     * - the first character is in the range A .. Z
     * - the second character is in the range A .. Z
     * - the third character is in the range A .. U
     * - the fourth character is in the range A .. D
     *
     * @param tilePlacement A string describing a tile placement
     * @return True if the tile placement is well-formed
     */
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
    public static boolean isPlacementWellFormed(String placement) {
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

    // There seems to be no place where we have checked that no part of the tile falls out of the board
    // I am not adding that functionality just yet because the spec doesn't mention that.
    // It shouldn't be too hard though -- Manal

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
        if (!isPlacementAdjacent(placement)) return false;
        if (!tileStraddle(placement)) return false;
        if (!checkBounds(placement)) return false;
        return areColoursAlright(placement);
    }



    /*This method assumes that the placement string is valid*/
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

            coverage[col][row] = getColours(piece)[0];

            if (orientation == 'A'){
                coverage[col + 1][row] = getColours(piece)[1];
                coverage[col][row + 1] = getColours(piece)[2];
            }
            else if (orientation == 'B'){
                coverage[col][row + 1] = getColours(piece)[1];
                coverage[col - 1][row] = getColours(piece)[2];
            }
            else if (orientation == 'C'){
                coverage[col - 1][row] = getColours(piece)[1];
                coverage[col][row - 1] = getColours(piece)[2];
            }
            else if (orientation == 'D'){
                coverage[col][row - 1] = getColours(piece)[1];
                coverage[col + 1][row] = getColours(piece)[2];
            }
            else
                System.out.println("colourArray: should not reach here");
        }
        
        return coverage;
    }
    
    static int[][] heightArray(String placement){
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

    private static boolean isOnTop(String piece, String placement){
        int[][] coverage;
        coverage = heightArray(placement);

        int idx1 = piece.charAt(0) - 'A';
        int idx2 = piece.charAt(1) - 'A';
        if (coverage[idx1][idx2] == 0)
            return false;

        if (piece.charAt(3) == 'A')
            if (!(coverage[1 + idx1][idx2] == coverage[idx1][1 + idx2] && coverage[1 + idx1][idx2] == coverage[idx1][idx2]))
                return false;
        else if (piece.charAt(3) == 'B')
            if(!(coverage[idx1][idx2] == coverage[-1 + idx1][idx2] && coverage[-1 + idx1][idx2] == coverage[idx1][1 + idx2]))
                return false;
        else if (piece.charAt(3) == 'C')
            if (!(coverage[-1 + idx1][idx2] == coverage[idx1][idx2] && coverage[idx1][idx2] == coverage[idx1][-1 + idx2]))
                return false;
        else if (piece.charAt(3) == 'D')
            if (!(coverage[1 + idx1][idx2] == coverage[idx1][idx2] && coverage[idx1][idx2] == coverage[idx1][-1 + idx2]))
                return false;
        else
            System.out.println("call from isPlacementAdjacent - should not reach here");
        return true;
    }

    /* This method checks if tiles are adjacent to one another, and if they are stacked there must be no tile dangling
    *
    * To make this method more efficient the call to isOnTop can be removed and the code can be adjusted here.
    * Saves the copying of a 26x26 array numerous times.
    * */

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
                if (!isOnTop(placement.substring(i, i + 4), placement.substring(0, i))) {
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

                coverage[col][row] = 1;
                coverage[1 + col][row] = 1;
                coverage[col][-1 + row] = 1;
            }
            else
                System.out.println("isPlacementAdjacent: should not reach here");
        }
        return true;
    }

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

    /*returns true if green wins*/
    /*ASSUMES THAT THE PLACEMENT STRING IS VALID*/
    static boolean hasGreenWon(String placement){
        return getWinner(placement);
    }

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
     */

    static int getScoreForPlacement(String placement, boolean green) {
        // FIXME Task 7: determine the score for a player given a placement

        /*I have this here for the moment but will remove it once main gets implemented*/
        if (!isPlacementValid(placement))
            return -1;
        // 1. convert the placement string into a 2d array
        // 2. find the largest area to determine the score
        // 3. parallel to 2, find the max height in the SAME region
        // 4. determine score
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
    static String generateMove(String placement, char piece, char opponentsPiece) {
        // FIXME Task 10: generate a valid move
        String bestMove = "";
        int bestScore = 0;
        for (char x='A'; x<='Z'; x++) {
            for (char y='A'; y<='Z'; y++) {
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
        }
        return bestMove;
    }
}
