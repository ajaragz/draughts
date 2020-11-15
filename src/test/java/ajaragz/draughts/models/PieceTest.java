package ajaragz.draughts.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PieceTest {

    @Test
    public void testMovementIsAdvancedGivenCoordinates() {
        assertTrue(new Pawn(Color.BLACK).isAdvanced(new Coordinate(2,3), new Coordinate(3, 2)));
        assertTrue(new Pawn(Color.WHITE).isAdvanced(new Coordinate(3,2),new Coordinate(2,3)));
    }

    @Test
    public void testMovementIsNotAdvancedGivenCoordinates() {
        assertFalse(new Pawn(Color.BLACK).isAdvanced(new Coordinate(2,3), new Coordinate(2, 4)));
        assertFalse(new Pawn(Color.BLACK).isAdvanced(new Coordinate(2,3), new Coordinate(1, 2)));
        assertFalse(new Pawn(Color.WHITE).isAdvanced(new Coordinate(3,2), new Coordinate(3,3)));
        assertFalse(new Pawn(Color.WHITE).isAdvanced(new Coordinate(3,2), new Coordinate(4,3)));
    }
}
