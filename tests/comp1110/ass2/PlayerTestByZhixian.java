package comp1110.ass2;

import org.junit.Test;

import static comp1110.ass2.Player.MAX_TILES;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aftran261 on 14/09/2016.
 */
public class PlayerTestByZhixian {

    @Test
    public void testPlayerInitialCreation() {
        PlayerG playerG = new PlayerG();
        PlayerR playerR = new PlayerR();
        assertTrue("Green Player does not have "+MAX_TILES+" tiles", playerG.available_tiles.size()==MAX_TILES);
        assertTrue("Red Player does not have "+MAX_TILES+" tiles", playerR.available_tiles.size()==MAX_TILES);

        for (int i=0; i<playerG.available_tiles.size(); i++) {
            if (((playerG.available_tiles).get(i)) instanceof Character) {
                char tile = (char) (playerG.available_tiles).get(i);
                assertTrue("Green Player has tiles that are not from 'K' to 'T'.", 'K'<= tile && tile <='T');
            } else{
                assertTrue("Green Player has tiles that are not of the Character type.", false);
            }
        }

        for (int i=0; i<playerR.available_tiles.size(); i++) {
            if (((playerR.available_tiles).get(i)) instanceof Character) {
                char tile = (char) (playerR.available_tiles).get(i);
                assertTrue("Red Player has tiles that are not from 'A' to 'J'.", 'A'<= tile && tile <='J');
            } else{
                assertTrue("Red Player has tiles that are not of the Character type.", false);
            }
        }

        assertFalse("Green Player's rotation out of bounds when it was first created", playerG.rotation>'D' || playerG.rotation<'A');
        assertFalse("Red Player's rotation out of bounds when it was first created", playerR.rotation>'D' || playerR.rotation<'A');

        assertFalse("Green Player's tile counter is not supposed to be negative", playerG.used_tiles<0);
        assertFalse("Red Player's tile counter is not supposed to be negative", playerR.used_tiles<0);
    }

    @Test
    public void testRotationRange() {
        Player playerG = new PlayerG();
        Player playerR = new PlayerR();
        for (int i=1; i<6; i++){
            playerG.rotateTile();
            assertFalse("Green Player's rotation out of bounds after " +i+ "rotations", playerG.rotation>'D' || playerG.rotation<'A');
            playerR.rotateTile();
            assertFalse("Red Player's rotation out of bounds after " +i+ "rotations", playerR.rotation>'D' || playerR.rotation<'A');
        }
    }

    @Test
    public void testTileRange() {
        Player playerG = new PlayerG();
        Player playerR = new PlayerR();
        for (int i=1; i<MAX_TILES+4; i++){
            playerG.getNextTile();
            assertTrue("Green Player's tile counter eventually goes out of bounds", playerG.used_tiles<MAX_TILES);
            assertFalse("Green Player's tile counter is not supposed to be negative", playerG.used_tiles<0);
            playerR.getNextTile();
            assertTrue("Red Player's tile counter eventually goes out of bounds", playerR.used_tiles<MAX_TILES);
            assertFalse("Red Player's tile counter is not supposed to be negative", playerR.used_tiles<0);
        }
    }
}