package comp1110.ass2;
import java.util.Arrays;

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

    /**
     * Determine whether a placement is valid.  To be valid, the placement must be well-formed
     * and each tile placement must follow the game's placement rules.
     *
     * @param placement A placement string
     * @return True if the placement is valid
     */
    static boolean isPlacementValid(String placement, BoardState board) {
        // FIXME Task 6: determine whether a placement is valid
        return false;
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
