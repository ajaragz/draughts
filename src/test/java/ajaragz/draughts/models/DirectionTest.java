package ajaragz.draughts.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    @Test
    public void testCoordinateMatchesDirection() {
        assertTrue(Direction.NE.isOnDirection(new Coordinate(7,7)));
        assertTrue(Direction.SE.isOnDirection(new Coordinate(-5,5)));
        assertTrue(Direction.SW.isOnDirection(new Coordinate(-1,-1)));
        assertTrue(Direction.NW.isOnDirection(new Coordinate(2,-2)));
    }

    @Test
    public void testCoordinateDoesntMatchDirection() {
        assertFalse(Direction.NE.isOnDirection(new Coordinate(-3,3)));
        assertFalse(Direction.SE.isOnDirection(new Coordinate(7,7)));
        assertFalse(Direction.SW.isOnDirection(new Coordinate(3, 3)));
        assertFalse(Direction.NW.isOnDirection(new Coordinate(5, 5)));
    }

    @Test
    public void testDirectionNotDiagonal() {
        assertFalse(Direction.NE.isOnDirection(new Coordinate(4, 5)));
        assertFalse(Direction.NE.isOnDirection(new Coordinate(0, 0)));
        assertFalse(Direction.NE.isOnDirection(new Coordinate(0, 2)));
    }
}
