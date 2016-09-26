package comp1110.ass2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static comp1110.ass2.Scoring.getScore;
import static comp1110.ass2.StratoGame.isPlacementValid;

/**
 * Created by josephmeltzer on 11/08/16.
 */

/*Implementated by Joseph Meltzer*/
public class AI {
    /**
     * The moveScore (nested) class is an object which combines a move and its score.
     */

//    public static class moveScore {
//        public String move;
//        private int score;
//
//        moveScore(String move, int score) {
//            this.move = move;
//            this.score = score;
//        }
//    }

    public static class moveScore {
        public String move;
        public float score;

        moveScore(String move, float score) {
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
     * @param prob       How many layers of probabilistic moves to search
     * @param a          Alpha value: minimum obtainable score
     * @param b          Beta value: maximum obtainable score
     * @param maximising Whether the current player is green or not (red)
     * @return           a moveScore object containing the best score,
     *                   and the four letter move that corresponds to it
     */

    public static moveScore alphabeta(String placement, char piece, char opiece, int depth, int prob, float a, float b, boolean maximising, boolean initialGreen) {
//        if (depth==0) return new moveScore("", getScore(placement, initialGreen)-getScore(placement, !initialGreen));
        if (depth==0) return new moveScore("", average(placement,piecesLeft(placement,maximising==initialGreen),prob,a,b,maximising,initialGreen));
        if (maximising) {
            float bestScore = -100;
            String bestMove = "x";
            for (char x : checkOrder) {
                for (char y : checkOrder) {
                    for (char o='A'; o<='D'; o++) {
                        if (isPlacementValid(placement+x+y+piece+o)) {
                            moveScore ab = new moveScore("" + x + y + piece + o, alphabeta(placement + x + y + piece + o, opiece, piece, depth - 1, prob, a, b, false, initialGreen).score);
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
            float bestScore = 1000;
            String bestMove = "";
            for (char x : checkOrder) {
                for (char y : checkOrder) {
                    for (char o='A'; o<='D'; o++) {
                        if (isPlacementValid(placement+x+y+piece+o)) {
                            moveScore ab = new moveScore("" + x + y + piece + o, alphabeta(placement + x + y + piece + o, opiece, piece, depth - 1, prob, a, b, true, initialGreen).score);
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

    public static moveScore probMM(String placement, char piece, int depth, float a, float b, boolean maximising, boolean initialGreen) {
        if (depth==0) return new moveScore ("", average(placement,piecesLeft(placement, maximising==initialGreen), 0, a, b, maximising, initialGreen));
        if (maximising) {
            float bestScore = -100.0f;
            String bestMove = "";
            for (char x : checkOrder) {
                for (char y : checkOrder) {
                    for (char o='A'; o<='D'; o++) {
                        if (isPlacementValid(placement+x+y+piece+o)) {
                            moveScore mS = new moveScore(""+x+y+piece+o, average(placement+x+y+piece+o, piecesLeft(placement+x+y+piece+o, false), depth-1, a, b, false, initialGreen));
                            if (mS.score > bestScore) {
                                bestScore = mS.score;
                                bestMove = mS.move;
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
            float bestScore = 1000.0f;
            String bestMove = "";
            for (char x : checkOrder) {
                for (char y : checkOrder) {
                    for (char o='A'; o<='D'; o++) {
                        if (isPlacementValid(placement+x+y+piece+o)) {
                            moveScore mS = new moveScore(""+x+y+piece+o, average(placement+x+y+piece+o, piecesLeft(placement+x+y+piece+o, true), depth-1, a, b, true, initialGreen));
                            if (mS.score < bestScore) {
                                bestScore = mS.score;
                                bestMove = mS.move;
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


    public static float average(String placement, ArrayList<Character> pieceArray, int depth, float a, float b, boolean maximising, boolean initialGreen) {
        if (depth==0 || pieceArray.size()==0) return getScore(placement, initialGreen)-getScore(placement, !initialGreen);
        float counter = 0.0f;
        ArrayList<Character> noDupsPieces = new ArrayList<>(new HashSet<>(pieceArray));
        for (Character piece : pieceArray) {
            counter = counter + probMM(placement, piece, depth, a, b, maximising, initialGreen).score;
        }
        return counter/pieceArray.size();
    }

    public static ArrayList<Character> piecesLeft(String placement, boolean green) {
        if (green) {
            ArrayList<Character> piecesG = new ArrayList<>();
            for (char p = 'K'; p <= 'T'; p++) {
                piecesG.add(p);
                piecesG.add(p);
            }
            for (char i = 6; i <= placement.length(); i += 8) {
                if (piecesG.contains(placement.charAt(i))) {
                    piecesG.remove((Character) placement.charAt(i));
                }
            }
            return piecesG;
        }
        else {
            ArrayList<Character> piecesR = new ArrayList<>();
            for (char p = 'A'; p <= 'J'; p++) {
                piecesR.add(p);
                piecesR.add(p);
            }
            for (char i = 10; i <= placement.length(); i += 8) {
                if (piecesR.contains(placement.charAt(i))) {
                    piecesR.remove((Character) placement.charAt(i));
                }
            }
            return piecesR;
        }
    }

}
