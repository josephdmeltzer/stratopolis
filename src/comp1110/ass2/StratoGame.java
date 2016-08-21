package comp1110.ass2;
import java.util.Arrays;

import static comp1110.ass2.Colour.GREEN;
import static comp1110.ass2.Colour.RED;
import static comp1110.ass2.Colour.BLACK;
import static comp1110.ass2.Pieces.getColoursS;
import static java.util.Arrays.binarySearch;


/**
 * This class provides the text interface for the Strato Game
 *
 * The game is based directly on Gigamic's Stratopolis game
 * (http://boardgamegeek.com/boardgame/125022/stratopolis)
 */
public class StratoGame {

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
        char[] rowcol = {'A', 'B', 'C', 'D', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] tiles = {'A', 'B', 'C', 'D', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U'};
        char[] rote = {'A', 'B', 'C', 'D'};
        if (tilePlacement.length() != 4) {
            return false;
        } else {
            if ((binarySearch(rowcol, (tilePlacement.charAt(0)))) < 0) {
                return false;
            } else {
                if ((binarySearch(rowcol, (tilePlacement.charAt(1)))) < 0) {
                    return false;
                } else {
                    if ((binarySearch(rowcol, (tilePlacement.charAt(2)))) < 0) {
                        return false;
                    } else {
                        if ((binarySearch(rowcol, (tilePlacement.charAt(3)))) < 0) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
            }
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
    static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 4: determine whether a placement is well-formed
        int len = placement.length();
        int numPieces = len/4;
        int[] counter = new int[20];
        Boolean c1 = len % 4 == 0 && numPieces >=1 && numPieces <= 41;
        if (!c1) return false;
        for (int i = 0; i < len; i += 4){
            if (!isTilePlacementWellFormed("" + placement.charAt(i) + placement.charAt(i+1) + placement.charAt(i+2) + placement.charAt(i+3)))
                return false;
        }
        if (! placement.substring(0,3).equals("MMUA")) return false;
        if (!((len >= 4 && placement.charAt(6) >= 'K' && placement.charAt(6) <= 'T') || len < 4))
            return false;
        for (int i = 14; i< len - 1; i += 8) {
            if (!(placement.charAt(i) >= 'K' && placement.charAt(i) <= 'T')) return false;
        }
        for (int i = 10; i < len - 1; i += 8) {
            if (!(placement.charAt(i) >= 'A' && placement.charAt(i) <= 'J')) return false;
        }

        for (int i = 6; i < len; i += 4){
            int idx = placement.charAt(i) - 'A';
            counter[idx]++;
            if (counter[idx] >= 2)
                return false;
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
    static boolean isPlacementValid(String placement) {
        // FIXME Task 6: determine whether a placement is valid
        if (!isPlacementWellFormed(placement))
            return false;
        if (!isPlacementAdjacent(placement))
            return false;
        return areColoursAlright(placement);
    }

    private static boolean areColoursAlright(String placement){
        /*To Joseph: You need to implement this function*/
        /*What I think you could do is declare a 26 by 26 grid of Colours
        * and for each tile over write the assocaited piece colour
        * If you end up overwriting green on red or red on grren return false
        * Also, have a look at the comments over the isPlacementAdjacent function
        * Have a look at the Pieces enum. I've represented the tiles and added an associated function
        * which *might* be useful. In the process you might have to implement a method which for a given piece and for a
        * given orientation returns the position and/or comolurs of wach of the three blocks of the tiles -- Manal*/

        Colour[][] colourTable = new Colour[26][26];
        colourTable[12][12] = RED;
        colourTable[12][13] = GREEN;

        for (int i=4; i < placement.length(); i+=4) {
            int col = placement.charAt(i) - 'A';
            int row = placement.charAt(i+1) - 'A';

            if (colourTable[col][row] == BLACK || getColoursS(placement.charAt(i+2))[0] == BLACK || colourTable[col][row] == getColoursS(placement.charAt(i+2))[0]) {
                colourTable[col][row] = getColoursS(placement.charAt(i+2))[0];
            }
            else return false;

            if (placement.charAt(i+3) == 'A') {

                if (colourTable[col+1][row] == BLACK || getColoursS(placement.charAt(i+2))[1] == BLACK || colourTable[col][row+1] == getColoursS(placement.charAt(i+2))[1]) {
                    colourTable[col+1][row] = getColoursS(placement.charAt(i+2))[1];
                }
                else return false;
                if (colourTable[col][row+1] == BLACK || getColoursS(placement.charAt(i+2))[2] == BLACK || colourTable[col][row+1] == getColoursS(placement.charAt(i+2))[2]) {
                    colourTable[col][row+1] = getColoursS(placement.charAt(i+2))[2];
                }
                else return false;
            }
            else if (placement.charAt(i+3) == 'B') {
                if (colourTable[col][row-1] == BLACK || getColoursS(placement.charAt(i+2))[1] == BLACK || colourTable[col][row-1] == getColoursS(placement.charAt(i+2))[1]) {
                    colourTable[col][row-1] = getColoursS(placement.charAt(i+2))[1];
                }
                else return false;
                if (colourTable[col-1][row] == BLACK || getColoursS(placement.charAt(i+2))[2] == BLACK || colourTable[col-1][row] == getColoursS(placement.charAt(i+2))[2]) {
                    colourTable[col-1][row] = getColoursS(placement.charAt(i+2))[2];
                }
                else return false;
            }
            else if (placement.charAt(i+3) == 'C') {
                if (colourTable[col-1][row] == BLACK || getColoursS(placement.charAt(i+2))[1] == BLACK || colourTable[col-1][row] == getColoursS(placement.charAt(i+2))[1]) {
                    colourTable[col-1][row] = getColoursS(placement.charAt(i+2))[1];
                }
                else return false;
                if (colourTable[col][row-1] == BLACK || getColoursS(placement.charAt(i+2))[2] == BLACK || colourTable[col][row-1] == getColoursS(placement.charAt(i+2))[2]) {
                    colourTable[col][row-1] = getColoursS(placement.charAt(i+2))[2];
                }
                else return false;
            }
            else if (placement.charAt(i+3) == 'D') {
                if (colourTable[col][row+1] == BLACK || getColoursS(placement.charAt(i+2))[1] == BLACK || colourTable[col][row+1] == getColoursS(placement.charAt(i+2))[1]) {
                    colourTable[col][row+1] = getColoursS(placement.charAt(i+2))[1];
                }
                else return false;
                if (colourTable[col+1][row] == BLACK || getColoursS(placement.charAt(i+2))[2] == BLACK || colourTable[col+1][row] == getColoursS(placement.charAt(i+2))[2]) {
                    colourTable[col+1][row] = getColoursS(placement.charAt(i+2))[2];
                }
                else return false;
            }


        }

        return false;
    }

    private static boolean isOnTop(String piece, String placement){
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


    // To Joseph: Treat the next function like a black box. I hope there are no bugs.
    // It checks if tiles are adjacent to one another or if they are stacked there must be no tile dangling
    // It does NOT check if all stacked tiles straddle between two -- (*)
    // It also has nothing to do with colours -- Manal

    // Also checking * shouldn't be difficult, it suffices to show that none of the tiles have same origin and orientation
    // It's just a simple for loop. One of us could implement it -- Manal

    private static boolean isPlacementAdjacent(String placement){
        /*The next array is used to identify if a position on the board has been covered*/
        /*I'll have the two middle tiles as 1 since they're covered since the beginning*/
        int[][] coverage = new int[26][26];
        coverage[12][12] = 1;
        coverage[12][13] = 1;

        if (!isPlacementWellFormed(placement))
            return false;
        for (int i = 0; i < placement.length(); i += 4){
            /*The first four characters must be MMUA .. skipping them*/
            if (i < 4)
                continue;
            int col = placement.charAt(i) - 'A';
            int row = placement.charAt(i + 1) - 'A';
            /*check if the tile placed is adjacent some other*/
            /*Fortunately, the rest of the tiles are all L-shaped*/
            /*To identify the squares covered by the tiles, we must have some sort of a representation of the tiles*/
            /*For every tile, with the given orientation, identify the squares it covers*/
            /*__At least__ one of them must be neighbours with a tile which is already placed*/

            /*Recursive Algo: loop through and for each of the tiles placed identify their positions. For the next tile
            * identify positions and check adjacency
            * */

            if (coverage[col][row] != 0){
                if (!isOnTop(placement.substring(i, i + 3), placement.substring(0, i - 1)))
                    return false;
                continue;
            }

            if (placement.charAt(i+3) == 'A'){
                if (coverage[1 + col][row] == 1 ||
                    coverage[col][1 + row] == 1)
                    return false;
                if (coverage[2 + col][row] +
                        coverage[1 + col][-1 + row] +
                        coverage[1 + col][1 + row] +
                        coverage[col][-1 + row] +
                        coverage[col][2 + row] +
                        coverage[-1 + col][row] +
                        coverage[-1 + col][-1 + row] == 0)
                    return false;
                coverage[col][row] = 1;
                coverage[1 + col][row] = 1;
                coverage[col][1 + row] = 1;

            }

            else if (placement.charAt(i+3) == 'B'){
                if (coverage[-1 + col][row] == 1 ||
                        coverage[col][1 + row] == 1)
                    return false;
                if (coverage[1 + col][row] +
                        coverage[1 + col][1 + row] +
                        coverage[col][-1 + row] +
                        coverage[col][2 + row] +
                        coverage[-1 + col][-1 + row] +
                        coverage[-1 + col][1 + row] +
                        coverage[-2 + col][row] == 0)
                    return false;
                coverage[col][row] = 1;
                coverage[-1 + col][row] = 1;
                coverage[col][1 + row] = 1;
            }

            else if (placement.charAt(i+3) == 'C'){
                if (coverage[-1 + col][row] == 1 ||
                        coverage[col][-1 + row] == 1)
                    return false;
                if (coverage[1 + col][row] +
                        coverage[1 + col][1 + row] +
                        coverage[col][-2 + row] +
                        coverage[col][1 + row] +
                        coverage[-1 + col][-1 + row] +
                        coverage[-1 + col][1 + row] +
                        coverage[-2 + col][row] == 0)
                    return false;
                coverage[col][row] = 1;
                coverage[-1 + col][row] = 1;
                coverage[col][-1 + row] = 1;
            }

            else if (placement.charAt(i+3) == 'D'){
                if (coverage[1 + col][row] == 1 ||
                        coverage[col][-1 + row] == 1)
                    return false;
                if (coverage[2 + col][row] +
                        coverage[1 + col][-1 + row] +
                        coverage[1 + col][1 + row] +
                        coverage[col][-2 + row] +
                        coverage[col][1 + row] +
                        coverage[-1 + col][row] +
                        coverage[-1 + col][-1 + row] == 0)
                    return false;
                coverage[col][row] = 1;
                coverage[1 + col][row] = 1;
                coverage[col][-1 + row] = 1;
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
        return 0;
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
        return null;
    }
}
