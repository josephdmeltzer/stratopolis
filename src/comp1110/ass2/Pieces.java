package comp1110.ass2;

import static comp1110.ass2.Colour.*;

/**
 * Created by manalmohania on 11/08/2016.
 */

/**
 * This class contains the internal representations of the pieces and any associated functions
 * Developed by Manal Mohania
 * getColours function by Joseph Meltzer
 */

public enum Pieces {
    A (RED, BLACK, BLACK),
    B (BLACK, BLACK, RED),
    C (BLACK, RED, BLACK),
    D (RED, BLACK, RED),
    E (BLACK, RED, RED),
    F (RED, RED, BLACK),
    G (RED, GREEN, RED),
    H (GREEN, RED, RED),
    I (RED, RED, GREEN),
    J (RED, RED, RED),
    K (GREEN, BLACK, BLACK),
    L (BLACK, BLACK, GREEN),
    M (BLACK, GREEN, BLACK),
    N (GREEN, BLACK, GREEN),
    O (BLACK, GREEN, GREEN),
    P (GREEN, GREEN, BLACK),
    Q (GREEN, RED, GREEN),
    R (RED, GREEN, GREEN),
    S (GREEN, GREEN, RED),
    T (GREEN, GREEN, GREEN);

    final Colour[] colours = new Colour[3];

    Pieces(Colour a, Colour b, Colour c){
       // colours = new Colour[3];
        colours[0] = a;
        colours[1] = b;
        colours[2] = c;
    }

    public static Colour[] getColours(char piece) { return Pieces.valueOf(""+piece).colours; }
}
