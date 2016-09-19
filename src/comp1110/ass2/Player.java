package comp1110.ass2;

/**
 * Created by manalmohania on 11/08/2016.
 */

/*The player class. Should contain anything that's common to both players
* Each of the players are subclasses of it
* */
public class Player {
    public int used_tiles = 0;
    public static final int MAX_TILES = 20; /*The number of tiles each player is supposed to have*/
    public char rotation = 'A';

    public void rotateTile() {
        switch (this.rotation) {
            case 'A':
                this.rotation = 'B';
                break;
            case 'B':
                this.rotation = 'C';
                break;
            case 'C':
                this.rotation = 'D';
                break;
            case 'D':
                this.rotation = 'A';
        }
    }
    public void getNextTile(){
        if (this.used_tiles < MAX_TILES-1){
            used_tiles++;
        }
    }
}
/*a function that rotates the tile should be here instead*/
/*a fuction that gets the next tile should be here instead*/
