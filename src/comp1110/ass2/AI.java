package comp1110.ass2;

import static comp1110.ass2.StratoGame.getScoreForPlacement;

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

    /**
     * Use alpha-beta pruning to find the best score of all possible moves.
     * @param placement The board state with which to test each move
     * @param piece     The piece available to the current player
     * @param opiece    The piece available to the other player
     * @param depth     The depth of nested moves to search
     * @param a         Alpha value: minimum obtainable score
     * @param b         Beta value: maximum obtainable score
     * @param green     Whether the current player is green or not (red)
     * @return          a moveScore object containing the best score,
     *                  and the four letter move that corresponds to it
     */

    public static moveScore alphabeta(String placement, char piece, char opiece, int depth, int a, int b, boolean green) {
        if (depth==0) return new moveScore("", getScoreForPlacement(placement, green));
        if (green) {
            int bestScore = 0;
            String bestMove = "";
            for (char x='A'; x<='Z'; x++) {
                for (char y='A'; y<='Z'; y++) {
                    for (char o='A'; o<='D'; o++) {
                        moveScore ab = new moveScore (""+x+y+piece+o,alphabeta(placement+x+y+piece+o, opiece, piece, depth-1, a, b, false).score);
                        if (ab.score>bestScore) {
                            bestScore = ab.score;
                            bestMove = ab.move;
                        }
                        a = Math.max(a, bestScore);
                        if (b<=a) break;
                    }
                }
            }
            return new moveScore(bestMove, bestScore);
        }
        else {
            int bestScore = 0;
            String bestMove = "";
            for (char x='A'; x<='Z'; x++) {
                for (char y='A'; y<='Z'; y++) {
                    for (char o='A'; o<='D'; o++) {
                        moveScore ab = new moveScore (""+x+y+piece+o,alphabeta(placement+x+y+piece+o, opiece, piece, depth-1, a, b, true).score);
                        if (ab.score<bestScore) {
                            bestScore = ab.score;
                            bestMove = ab.move;
                        }
                        b = Math.min(b, bestScore);
                        if (b<=a) break;
                    }
                }
            }
            return new moveScore(bestMove, bestScore);
        }
    }
}
