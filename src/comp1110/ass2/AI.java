package comp1110.ass2;

import static comp1110.ass2.Scoring.getScore;
import static comp1110.ass2.StratoGame.getScoreForPlacement;
import static comp1110.ass2.StratoGame.isPlacementValid;

/**
 * Created by josephmeltzer on 11/08/16.
 */
public class AI {
    /**
     * The moveScore (nested) class is an object which combines a move and its score.
     */

    public static class moveScore {
        public String move;
        private int score;

        moveScore(String move, int score) {
            this.move = move;
            this.score = score;
        }
    }

    static char[] checkOrder = {'M','L','N','K','O','J','P','I','Q','H','R','G','S','F','T','E','U','D','V','C','W','B','X','A','Y','Z'};

    /**
     * Use alpha-beta pruning to find the best score of all possible moves.
     * @param placement  The board state with which to test each move
     * @param piece      The piece available to the current player
     * @param opiece     The piece available to the other player
     * @param depth      The depth of nested moves to search
     * @param a          Alpha value: minimum obtainable score
     * @param b          Beta value: maximum obtainable score
     * @param maximising Whether the current player is green or not (red)
     * @return           a moveScore object containing the best score,
     *                   and the four letter move that corresponds to it
     */

    public static moveScore alphabeta(String placement, char piece, char opiece, int depth, int a, int b, boolean maximising, boolean initialGreen) {
        if (depth==0) return new moveScore("###########", getScore(placement, initialGreen));
        if (maximising) {
            int bestScore = -100;
            String bestMove = "$$$$$$$$$$$$$$$";
            for (char x : checkOrder) {
                for (char y : checkOrder) {
                    for (char o='A'; o<='D'; o++) {
                        if (isPlacementValid(placement+x+y+piece+o)) {
                            moveScore ab = new moveScore("" + x + y + piece + o, alphabeta(placement + x + y + piece + o, opiece, piece, depth - 1, a, b, false, initialGreen).score);
                            if (ab.score > bestScore) {
                                bestScore = ab.score;
                                bestMove = ab.move;
                            }
                            a = Math.max(a, bestScore);
                            if (b <= a) break;
                        }
                    }
                }
            }
            return new moveScore(bestMove, bestScore);
        }
        else {
            int bestScore = 1000;
            String bestMove = "@@@@@@@@@@@@@@@@";
            for (char x : checkOrder) {
                for (char y : checkOrder) {
                    for (char o='A'; o<='D'; o++) {
                        if (isPlacementValid(placement+x+y+piece+o)) {
                            moveScore ab = new moveScore("" + x + y + piece + o, alphabeta(placement + x + y + piece + o, opiece, piece, depth - 1, a, b, true, initialGreen).score);
                            if (ab.score < bestScore) {
                                bestScore = ab.score;
                                bestMove = ab.move;
                            }
                            b = Math.min(b, bestScore);
                            if (b <= a) break;
                        }
                    }
                }
            }
            return new moveScore(bestMove, bestScore);
        }
    }
}
