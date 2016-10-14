package comp1110.ass2;

import org.junit.Test;

import static comp1110.ass2.Player.MAX_TILES;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*By Zhixian Wu*/
public class PlayerTestByZhixian {

    /*Checks if the player is created with the correct number of tiles*/
    @Test
    public void testPlayerInitialCreationNumberOfTiles() {
        PlayerG playerG = new PlayerG();
        PlayerR playerR = new PlayerR();
        assertTrue("Green Player does not have " + MAX_TILES +
                " tiles", playerG.available_tiles.size() == MAX_TILES);
        assertTrue("Red Player does not have " + MAX_TILES +
                " tiles", playerR.available_tiles.size() == MAX_TILES);
    }

    /*Checks if Green has tiles from K-U and Red from A-J*/
    @Test
    public void testPlayerInitialCreationTilesOfPlayers() {
        PlayerG playerG = new PlayerG();
        PlayerR playerR = new PlayerR();
        for (int i = 0; i < playerG.available_tiles.size(); i++) {
            if (((playerG.available_tiles).get(i)) instanceof Character) {
                char tile = (char) (playerG.available_tiles).get(i);
                assertTrue("Green Player has tiles that are not from 'K' to 'T'.",
                        'K' <= tile && tile <= 'T');
            } else {
                assertTrue("Green Player has tiles that are not of the Character type.", false);
            }
        }

        for (int i = 0; i < playerR.available_tiles.size(); i++) {
            if (((playerR.available_tiles).get(i)) instanceof Character) {
                char tile = (char) (playerR.available_tiles).get(i);
                assertTrue("Red Player has tiles that are not from 'A' to 'J'.",
                        'A' <= tile && tile <= 'J');
            } else {
                assertTrue("Red Player has tiles that are not of the Character type.", false);
            }
        }
    }

    /*Checks if the player at creation has rotation from A to D*/
    @Test
    public void testPlayerInitialCreationRotation() {
        PlayerG playerG = new PlayerG();
        PlayerR playerR = new PlayerR();
        assertFalse("Green Player's rotation out of bounds when it was first created",
                playerG.rotation>'D' || playerG.rotation<'A');
        assertFalse("Red Player's rotation out of bounds when it was first created",
                playerR.rotation>'D' || playerR.rotation<'A');

    }

    /*Rotates the tiles 5 times, checking the rotation is from a to D each time*/
    @Test
    public void testRotateTileRange() {
        Player playerG = new PlayerG();
        Player playerR = new PlayerR();
        for (int i=1; i<6; i++){
            playerG.rotateTile();
            assertFalse("Green Player's rotation out of bounds after " +i+ "rotations",
                    playerG.rotation>'D' || playerG.rotation<'A');
            playerR.rotateTile();
            assertFalse("Red Player's rotation out of bounds after " +i+ "rotations",
                    playerR.rotation>'D' || playerR.rotation<'A');
        }
    }

    /*Rotates the tiles 5 times, checking the next rotation is the right one each time*/
    @Test
    public void testRotateTileOrder() {
        Player playerG = new PlayerG();
        Player playerR = new PlayerR();
        for (int i=1; i<6; i++){
            switch ((playerG.rotation-65) % 4) {
                case 0: playerG.rotateTile();
                    assertTrue("Green Player's rotation out of order", playerG.rotation == 'B' );
                    break;
                case 1: playerG.rotateTile();
                    assertTrue("Green Player's rotation out of order", playerG.rotation == 'C' );
                    break;
                case 2: playerG.rotateTile();
                    assertTrue("Green Player's rotation out of order", playerG.rotation == 'D' );
                    break;
                case 3: playerG.rotateTile();
                    assertTrue("Green Player's rotation out of order", playerG.rotation == 'A' );
                    break;
                }
            switch ((playerR.rotation-65) % 4) {
                case 0: playerR.rotateTile();
                    assertTrue("Red Player's rotation out of order", playerG.rotation == 'B' );
                    break;
                case 1: playerR.rotateTile();
                    assertTrue("Red Player's rotation out of order", playerG.rotation == 'C' );
                    break;
                case 2: playerR.rotateTile();
                    assertTrue("Red Player's rotation out of order", playerG.rotation == 'D' );
                    break;
                case 3: playerR.rotateTile();
                    assertTrue("Red Player's rotation out of order", playerG.rotation == 'A' );
                    break;
            }
        }
    }

    /*Calls getNextTile MAX_TILES+3 times, checking that the used_tiles
    * counter doesn't go out of bounds each time*/
    @Test
    public void testGetTileRange() {
        Player playerG = new PlayerG();
        Player playerR = new PlayerR();

        assertFalse("Green Player's tile counter is not supposed to be negative",
                playerG.used_tiles<0);
        assertFalse("Red Player's tile counter is not supposed to be negative",
                playerR.used_tiles<0);

        for (int i=1; i<MAX_TILES+4; i++){
            playerG.getNextTile();
            assertTrue("Green Player's tile counter eventually goes out of bounds",
                    playerG.used_tiles<MAX_TILES);
            assertFalse("Green Player's tile counter is not supposed to be negative",
                    playerG.used_tiles<0);
            playerR.getNextTile();
            assertTrue("Red Player's tile counter eventually goes out of bounds",
                    playerR.used_tiles<MAX_TILES);
            assertFalse("Red Player's tile counter is not supposed to be negative",
                    playerR.used_tiles<0);
        }

    }
}