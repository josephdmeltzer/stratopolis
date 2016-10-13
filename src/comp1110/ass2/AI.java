package comp1110.ass2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import static comp1110.ass2.Scoring.getScore;
import static comp1110.ass2.StratoGame.isPlacementValid;

/**
 * Created by josephmeltzer on 11/08/16.
 */

/*Implemented by Joseph Meltzer*/
class AI {
    /* The moveScore (nested) class is an object which combines a move and its score. */
    static class moveScore {
        String move;
        float score;

        moveScore(String move, float score) {
            this.move = move;
            this.score = score;
        }
    }

    /* The checking order for tiles: Begin in the middle and move out, since valid and good moves are most likely to be in the middle */
//    static char[] checkOrder = {'M','L','N','K','O','J','P','I','Q','H','R','G','S','F','T','E','U','D','V','C','W','B','X','A','Y','Z'};

    /**
     * Use alpha-beta pruning to find the best score of all possible moves.
     * @param placement    The board state with which to test each move
     * @param piece        The piece available to the current player
     * @param opiece       The piece available to the other player
     * @param depth        The depth of nested moves to search
     * @param prob         How many layers of probabilistic moves to search
     * @param a            Alpha value: minimum obtainable score
     * @param b            Beta value: maximum obtainable score
     * @param maximising   Whether the current player is green or not (red)
     * @param initialGreen Whether the player executing this function is green or not (red)
     * @return             A moveScore object containing the best score,
     *                     and the four letter move that corresponds to it
     */
    static moveScore alphabeta(String placement, char piece, char opiece, int depth, int prob, float a, float b, boolean maximising, boolean initialGreen) {
        if (depth==0 || placement.length()==164) return new moveScore("", average(placement,piecesLeft(placement,maximising==initialGreen),prob,a,b,maximising,initialGreen));
        if (maximising) {
            float bestScore = -100;
            String bestMove = "x";
            for (String move : validTiles(placement)) {
                char x = move.charAt(0);
                char y = move.charAt(1);
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
            return new moveScore(bestMove, bestScore);
        }
        else {
            float bestScore = 1000;
            String bestMove = "";
            for (String move : validTiles(placement)) {
                char x = move.charAt(0);
                char y = move.charAt(1);
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
            return new moveScore(bestMove, bestScore);
        }
    }

    /**
     * Use alpha-beta pruning to search for the best move, taking expected values (averages) when the piece is unknown.
     * @param placement     The board state with which to test each move
     * @param piece         The piece available to the current player
     * @param depth         The depth of nested moves to search
     * @param a             Alpha value: minimum obtainable score
     * @param b             Beta value: maximum obtainable score
     * @param maximising    Whether the current player is green or not (red)
     * @param initialGreen  Whether the player executing this function is green or not (red)
     * @return              A moveScore object that contains the best score,
     *                      and the four letter move that corresponds to it.
     */
    private static moveScore probAB(String placement, char piece, int depth, float a, float b, boolean maximising, boolean initialGreen) {
        if (depth==0 || placement.length()==164) return new moveScore ("", average(placement,piecesLeft(placement, maximising==initialGreen), 0, a, b, maximising, initialGreen));
        if (maximising) {
            float bestScore = -100.0f;
            String bestMove = "";
            for (String move : validTiles(placement)) {
                char x = move.charAt(0);
                char y = move.charAt(1);
                for (char o='A'; o<='D'; o++) {
                    if (isPlacementValid(placement+x+y+piece+o)) {
                        /* Instead of a recursive call to probAB, instead take the average of probABs with all possible pieces */
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
            return new moveScore(bestMove, bestScore);
        }
        else {
            float bestScore = 1000.0f;
            String bestMove = "";
            for (String move : validTiles(placement)) {
                char x = move.charAt(0);
                char y = move.charAt(1);
                for (char o='A'; o<='D'; o++) {
                    if (isPlacementValid(placement+x+y+piece+o)) {
                        /* Instead of a recursive call to probAB, instead take the average of probABs with all possible pieces */
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
            return new moveScore(bestMove, bestScore);
        }
    }

    /**
     * Calculate an average of scores given a placement and possible available tiles for that placement
     * @param placement     The placement to test scores on
     * @param pieceArray    A list of pieces whose appearance is still possible
     * @param depth         Depth: pass-through from above
     * @param a             Alpha: pass-through from above
     * @param b             Beta:  pass-through from above
     * @param maximising    Whether the current player is green or not (red)
     * @param initialGreen  Whether the player executing this function is green or not (red)
     * @return              The expected value of the score given the board state and available moves.
     */
    private static float average(String placement, ArrayList<Character> pieceArray, int depth, float a, float b, boolean maximising, boolean initialGreen) {
        if (depth==0 || pieceArray.size()==0) {
            return getScore(placement, initialGreen)-getScore(placement, !initialGreen);
        }
        float counter = 0.0f;
        ArrayList<Character> noDupsPieces = new ArrayList<>(new HashSet<>(pieceArray));
        for (Character piece : noDupsPieces) {
            if(Collections.frequency(pieceArray, piece)>1) counter = counter + 2 * probAB(placement, piece, depth, a, b, maximising, initialGreen).score;
            else counter = counter + 2 * probAB(placement, piece, depth, a, b, maximising, initialGreen).score;
        }
        return counter/pieceArray.size();
    }

    /**
     * Create a list of available pieces for a particular player from a board state.
     * @param placement     The board state to evaluate
     * @param green         Whether or not the desired pieces belong to the green player
     * @return              A list of pieces (with duplicates when necessary) that can be played by the chosen player
     */
    private static ArrayList<Character> piecesLeft(String placement, boolean green) {
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

    /**
     * Find the valid places for the next placement given a certain placement.
     * @param placement     The placement to check
     * @return              A set of all and only tiles covered or adjacent to a piece
     */
    static HashSet<String> validTiles(String placement) {
        HashSet<String> tiles = new HashSet<>();
        char tilex;
        char tiley;
        char o;
        tiles.add("ML");
        tiles.add("NL");
        tiles.add("LM");
        tiles.add("MM");
        tiles.add("NM");
        tiles.add("OM");
        tiles.add("LN");
        tiles.add("MN");
        tiles.add("NN");
        tiles.add("MO");
        for (int i=4; i<placement.length(); i+=4) {
            tilex = placement.charAt(i);
            tiley = placement.charAt(i+1);
            o = placement.charAt(i+3);
            if (o=='A') {
                for (int j= 0;j<=1;j++) tiles.add(""+(char)(Math.min(90,Math.max(65,tilex+j)))+(char)(Math.max(65,tiley-1)));
                for (int j=-1;j<=2;j+=3) tiles.add(""+(char)(Math.min(90,Math.max(65,tilex+j)))+tiley);
                for (int j=-1;j<=1;j+=2) tiles.add(""+(char)(Math.min(90,Math.max(65,tilex+j)))+(char)(tiley+1));
                tiles.add(""+tilex+(char)(Math.min(90,tiley+2)));
            }
            if (placement.charAt(i+3)=='B') {
                for (int j=-1;j<=0;j++) tiles.add(""+(char)(Math.min(90,Math.max(65,tilex+j)))+(char)(Math.max(65,tiley-1)));
                for (int j=-2;j<=1;j+=3) tiles.add(""+(char)(Math.min(90,Math.max(65,tilex+j)))+tiley);
                for (int j=-1;j<=1;j+=2) tiles.add(""+(char)(Math.min(90,Math.max(65,tilex+j)))+(char)(tiley+1));
                tiles.add(""+tilex+(char)(Math.min(90,tiley+2)));
            }
            if (placement.charAt(i+3)=='C') {
                for (int j=-1;j<=1;j+=2) tiles.add(""+(char)(Math.min(90,Math.max(65,tilex+j)))+(char)(tiley-1));
                for (int j=-2;j<=1;j+=3) tiles.add(""+(char)(Math.min(90,Math.max(65,tilex+j)))+tiley);
                for (int j=-1;j<=0;j++) tiles.add(""+(char)(Math.min(90,Math.max(65,tilex+j)))+(char)(Math.min(90,tiley+1)));
                tiles.add(""+tilex+(char)(Math.max(65,tiley-2)));
            }
            if (placement.charAt(i+3)=='D') {
                for (int j=-1;j<=1;j+=2) tiles.add(""+(char)(Math.min(90,Math.max(65,tilex+j)))+(char)(tiley-1));
                for (int j=-1;j<=2;j+=3) tiles.add(""+(char)(Math.min(90,Math.max(65,tilex+j)))+tiley);
                for (int j=0;j<=1;j++) tiles.add(""+(char)(Math.min(90,Math.max(65,tilex+j)))+(char)(Math.min(90,tiley+1)));
                tiles.add(""+tilex+(char)(Math.max(65,tiley-2)));
            }
        }
        return tiles;
    }


    /*Function by Zhixian Wu, based very heavily off the implementation of alpha-beta pruning by Joseph Meltzer*/
    /**
     * Use alpha-beta pruning to find the best score of all possible moves.
     * @param placement    The board state with which to test each move
     * @param us           The current player
     * @param opponent     The other player
     * @param depth        The depth of nested moves to search
     * @param maxDepth     The number of depths from the original call to the alpha-beta function
     * @param a            Alpha value: minimum obtainable score
     * @param b            Beta value: maximum obtainable score
     * @param maximising   Whether the current player is green or not (red)
     * @param initialGreen Whether the player executing this function is green or not (red)
     * @return             A moveScore object containing the best score,
     *                     and the four letter move that corresponds to it
     */
    static moveScore alphabetaCheat(String placement, Player us, Player opponent, int depth, int maxDepth, float a, float b, boolean maximising, boolean initialGreen) {
        if (depth==0) return new moveScore("", getScore(placement, initialGreen)-getScore(placement, !initialGreen));
        if (maximising) {
            float bestScore = -1000;
            String bestMove = "x";
            char piece = (char) (us.available_tiles).get(us.used_tiles+maxDepth-depth);

            for (String move : validTiles(placement)) {
                char x = move.charAt(0);
                char y = move.charAt(1);
                for (char o='A'; o<='D'; o++) {
                    if (isPlacementValid(placement+x+y+piece+o)) {
                        moveScore ab = new moveScore("" + x + y + piece + o, alphabetaCheat(placement + x + y + piece + o, opponent, us, depth - 1, maxDepth, a, b, false, initialGreen).score);
                        if (ab.score > bestScore) {
                            bestScore = ab.score;
                            bestMove = ab.move;
                        }
                        a = Math.max(a, bestScore);
                        if (b <= a) break;
                    }
                }
            }
            return new moveScore(bestMove, bestScore);
        }
        else {
            float bestScore = 1000;
            String bestMove = "";
            char piece = (char) (us.available_tiles).get(us.used_tiles+maxDepth-depth);
            for (String move : validTiles(placement)) {
                char x = move.charAt(0);
                char y = move.charAt(1);
                for (char o='A'; o<='D'; o++) {
                    if (isPlacementValid(placement+x+y+piece+o)) {
                        moveScore ab = new moveScore("" + x + y + piece + o, alphabetaCheat(placement + x + y + piece + o, opponent, us, depth - 1, maxDepth, a, b, true, initialGreen).score);
                        if (ab.score < bestScore) {
                            bestScore = ab.score;
                            bestMove = ab.move;
                        }
                        b = Math.min(b, bestScore);
                        if (b <= a) break;
                    }
                }
            }
            return new moveScore(bestMove, bestScore);
        }
    }
}
