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
        return tilePlacement.length() == 4 && tilePlacement.charAt(0) >= 'A' &&
                tilePlacement.charAt(0) <= 'Z' && tilePlacement.charAt(1) >= 'A' &&
                tilePlacement.charAt(1) <= 'Z' && tilePlacement.charAt(2) >= 'A' &&
                tilePlacement.charAt(2) <= 'U' && tilePlacement.charAt(3) >= 'A' &&
                tilePlacement.charAt(3) <= 'D';
    }

    /**
     * Determine whether a placement string is well-formed:
     *  - it consists of exactly N four-character tile placements (where N = 1 .. 41)
     *  - each tile placement is well-formed
     *  - the first tile placement is 'MMUA'
     *  - the second tile placement (if any) is for a green tile
     *  - remaining tile placements alternate between red and green
     *  - no tile appears more than twice in the placement
     *  Method by Manal Mohania and Joseph Meltzer
     *
     * @param placement     A string describing a placement of one or more tiles
     * @return              True if the placement is well-formed
     */
    static boolean isPlacementWellFormed(String placement) {
        if (placement == null) return false;
        int len = placement.length();
        int numPieces = len/4;
        int[] counter = new int[20];
        Boolean c1 = (len % 4 == 0) && (numPieces >=1) && (numPieces <= 41);
        if (!c1) return false;
        if (! placement.substring(0,4).equals("MMUA")) return false;
        for (int i=2; i<len; i+=4) {
            if (i%4==2 && !(isTilePlacementWellFormed(placement.substring(i-2,i+2)))) return false;
            if (i%8==6 && !(placement.charAt(i) >= 'K' && placement.charAt(i) <= 'T')) return false;
            if (i%8==2 && i>9 && !(placement.charAt(i) >= 'A' && placement.charAt(i) <= 'J')) return false;
            if (i%4==2 && i>5) {
                int idx = placement.charAt(i) - 'A';
                counter[idx]++;
                if (counter[idx]>2) return false;
            }
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
        return isPlacementWellFormed(placement) && isPlacementAdjacent(placement) && straddleAndColours(placement);
    }

    /**
     * Convert a placement string to a 26x26 array of colours
     * Function written by Manal Mohania
     * @param placement: A _valid_ placement string
     * @return a 26 x 26 array of colours
     */
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
     * Function written by Manal Mohania
     * @param placement: A _valid_ placement string
     * @return a 26 x 26 array of heights
     */
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

    /**
     * This method checks
     *
     * 1. if each of the tiles in the placement string share at least one side in common with another tile.
     * 2. if the tile lies on top of another, it ensures that the tile os not dangling, and the squares it rests upon have the same heights.
     * 3. that no part of any tile falls of the board
     *
     * @param placement: the placement string
     * @return true iff the above three conditions are satisfied
     *
     * Method by Manal Mohania
     * */
    private static boolean isPlacementAdjacent(String placement){

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
        }
        return true;
    }

    /**
     * Method by Joseph Meltzekr
     * Combines the old tileStraddle and areColoursAlright methods into a single method.
     * The placement string is therefore only looped through once for these methods, saving time.
     * @param placement     The placement string to inspect.
     * @return              Whether or not the placement is valid with respect to tile straddling and colour overlaps.
     */
    private static boolean straddleAndColours(String placement) {
        int[][] tileTable = new int[26][26];
        Colour[][] colourTable = new Colour[26][26];
        colourTable[12][12] = RED;
        colourTable[12][13] = GREEN;

        for (int i=4; i < placement.length(); i+=4) {
            int col = placement.charAt(i) - 'A';
            int row = placement.charAt(i + 1) - 'A';

            if ((colourTable[col][row] != RED || getColours(placement.charAt(i+2))[0] != GREEN) && (colourTable[col][row] != GREEN || getColours(placement.charAt(i+2))[0] != RED)) {
                colourTable[col][row] = getColours(placement.charAt(i+2))[0];
            }
            else return false;

            if (placement.charAt(i+3) == 'A'){
                if (tileTable[col][row] == tileTable[col+1][row] && tileTable[col][row] == tileTable[col][row+1] && tileTable[col][row] != 0) return false;
                tileTable[col][row] = i;
                tileTable[col+1][row] = i;
                tileTable[col][row+1] = i;

                if ((colourTable[col+1][row] != RED || getColours(placement.charAt(i+2))[1] != GREEN) && (colourTable[col+1][row] != GREEN || getColours(placement.charAt(i+2))[1] != RED)) {
                    colourTable[col+1][row] = getColours(placement.charAt(i+2))[1];
                }
                else return false;
                if ((colourTable[col][row+1] != RED || getColours(placement.charAt(i+2))[2] != GREEN) && (colourTable[col][row+1] != GREEN || getColours(placement.charAt(i+2))[2] != RED)) {
                    colourTable[col][row+1] = getColours(placement.charAt(i+2))[2];
                }
                else return false;
            }
            else if (placement.charAt(i + 3) == 'B'){
                if (tileTable[col][row] == tileTable[col-1][row] && tileTable[col][row] == tileTable[col][row+1] && tileTable[col][row] != 0) return false;
                tileTable[col][row] = i;
                tileTable[col-1][row] = i;
                tileTable[col][row+1] = i;

                if ((colourTable[col][row+1] != RED || getColours(placement.charAt(i+2))[1] != GREEN) && (colourTable[col][row+1] != GREEN || getColours(placement.charAt(i+2))[1] != RED)) {
                    colourTable[col][row+1] = getColours(placement.charAt(i+2))[1];
                }
                else return false;
                if ((colourTable[col-1][row] != RED || getColours(placement.charAt(i+2))[2] != GREEN) && (colourTable[col-1][row] != GREEN || getColours(placement.charAt(i+2))[2] != RED)) {
                    colourTable[col-1][row] = getColours(placement.charAt(i+2))[2];
                }
                else return false;
            }
            else if (placement.charAt(i + 3) == 'C'){
                if (tileTable[col][row] == tileTable[col-1][row] && tileTable[col][row] == tileTable[col][row-1] && tileTable[col][row] != 0) return false;
                tileTable[col][row] = i;
                tileTable[col-1][row] = i;
                tileTable[col][row-1] = i;

                if ((colourTable[col-1][row] != RED || getColours(placement.charAt(i+2))[1] != GREEN) && (colourTable[col-1][row] != GREEN || getColours(placement.charAt(i+2))[1] != RED)) {
                    colourTable[col-1][row] = getColours(placement.charAt(i+2))[1];
                }
                else return false;
                if ((colourTable[col][row-1] != RED || getColours(placement.charAt(i+2))[2] != GREEN) && (colourTable[col][row-1] != GREEN || getColours(placement.charAt(i+2))[2] != RED)) {
                    colourTable[col][row-1] = getColours(placement.charAt(i+2))[2];
                }
                else return false;
            }
            else if (placement.charAt(i + 3) == 'D'){
                if (tileTable[col][row] == tileTable[col+1][row] && tileTable[col][row] == tileTable[col][row-1] && tileTable[col][row] != 0) return false;
                tileTable[col][row] = i;
                tileTable[col+1][row] = i;
                tileTable[col][row-1] = i;

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
     * This method returns true if green has won the game given a valid placement string.
     * Function written by Manal Mohania
     * @param placement   The string of all the placements so far
     */
    static boolean greenHasWon(String placement){
        return getWinner(placement);
    }

    /**
     * Determine the score for a player given a placement, following the
     * scoring rules for the game.
     * Function written by Manal Mohania
     * @param placement A placement string
     * @param green     True if the score for the green player is requested,
     *                  otherwise the score for the red player should be returned
     * @return          The score for the requested player, given the placement
     */
    public static int getScoreForPlacement(String placement, boolean green) {
        /*I have this here for the moment but will remove it once main gets implemented*/
        if (!isPlacementValid(placement))
            return -1;

        return getScore(placement, green);
    }

    /**
     * Generate a valid move that follows from: the given placement, a piece to
     * play, and the piece the opponent will play next.
     * Search two deterministic levels and one probabilistic level into the game tree.
     * Used when the AI difficulty setting is set to 'Hard'.
     * Method by Joseph Meltzer
     *
     * @param placement  A valid placement string indicating a game state
     * @param piece  The piece you are to play ('A' to 'T')
     * @param opponentsPiece The piece your opponent will be asked to play next ('A' to 'T' or 0 if last move).
     * @return A string indicating a valid tile placement that represents your move.
     */
    public static String generateMove(String placement, char piece, char opponentsPiece) {
        boolean green = (piece>='K' && piece<='T');
        return alphabeta(placement, piece, opponentsPiece, 2, 1, -100, 1000, true, green).move;
    }

    /* Previous version of the generateMove function: used as a faster, but less powerful generator.
    *  Its computation time is around 1 second, or less, per move.
    *  Used exclusively when the AI difficulty setting is set to 'Medium'.*/
    public static String genMoveMedium(String placement, char piece, char opponentsPiece) {
        boolean green = (piece>='K' && piece<='T');
        return comp1110.ass2.AI.alphabeta(placement, piece, opponentsPiece, 2, 0, -100, 1000, true, green).move;
    }
    /* Even older version of the generateMove function. Only looks at the immediately available moves.
       Seemingly instant computation time.
       Used exclusively when the AI difficulty setting is set to 'Easy'.*/

    public static String genMoveEasy(String placement, char piece) {
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

    /**
     * Function by Zhixian Wu, based off generateMove() by Joseph Meltzer
     * @param placement   A valid placement string indicating the game state
     * @param us          The player the AI is playing as
     * @param opponent    The player the AI is opposing
     * @return            placeholder
     */
    public static String genMoveCheating(String placement, Player us, Player opponent){
        int depth = min(MAX_TILES-us.used_tiles-1, 3);
        char piece = (char) (us.available_tiles).get(us.used_tiles);
        boolean green = (piece>='K' && piece<='T');
        if (depth>0) return alphabetaCheat(placement, us, opponent, depth, depth, -1000, 1000, true, green).move;
        else {
            return genMoveNotEasy(placement,piece);
        }
    }

    /**
     * Method by Joseph Meltzer
     * @param placement  A valid placement string indicating the game state
     * @param piece      The piece you are to play ('A' to 'T')
     * @return           placeholder
     */
    private static String genMoveNotEasy(String placement, char piece) {
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
