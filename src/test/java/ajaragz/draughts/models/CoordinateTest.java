package ajaragz.draughts.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void testCoordinateIsInsideBoard() {
        assertNotNull(Coordinate.getInstance("45"));
        assertNotNull(Coordinate.getInstance("11"));
        assertNotNull(Coordinate.getInstance("88"));
    }

    @Test
    public void testCoordinateIsOutsideBoard() {
        assertNull(Coordinate.getInstance("01"));
        assertNull(Coordinate.getInstance("99"));
        assertNull(Coordinate.getInstance("-11"));
        assertNull(Coordinate.getInstance("Wrong Format"));
    }

    @Test
    public void testDirectionBetweenCoordinatesMatches() {
        assertEquals(Direction.NE, new Coordinate(4,4).getDirection(new Coordinate(5,5)));
        assertEquals(Direction.SE, new Coordinate(4, 4).getDirection(new Coordinate(3, 5)));
        assertEquals(Direction.SW, new Coordinate(4, 4).getDirection(new Coordinate(2, 2)));
        assertEquals(Direction.NW, new Coordinate(4, 4).getDirection(new Coordinate(5, 3)));
        assertNull(new Coordinate(4, 4).getDirection(new Coordinate(5, 6)));
    }

    @Test
    public void testDirectionBetweenCoordinatesIsDiagonal() {
        assertTrue(new Coordinate(0, 0).isOnDiagonal(new Coordinate(7, 7)));
        assertTrue(new Coordinate(7, 0).isOnDiagonal(new Coordinate(0, 7)));
        assertTrue(new Coordinate(4, 4).isOnDiagonal(new Coordinate(6, 6)));
        assertTrue(new Coordinate(4, 4).isOnDiagonal(new Coordinate(3, 3)));
        assertTrue(new Coordinate(4, 4).isOnDiagonal(new Coordinate(6, 2)));
        assertTrue(new Coordinate(4, 4).isOnDiagonal(new Coordinate(2, 6)));
    }

    @Test
    public void testDirectionBetweenCoordinatesIsNotDiagonal() {
        assertFalse(new Coordinate(2, 6).isOnDiagonal(new Coordinate(2, 6)));
        assertFalse(new Coordinate(2, 6).isOnDiagonal(new Coordinate(2, 7)));
        assertFalse(new Coordinate(2, 6).isOnDiagonal(new Coordinate(2, 5)));
        assertFalse(new Coordinate(2, 6).isOnDiagonal(new Coordinate(3, 6)));
        assertFalse(new Coordinate(2, 6).isOnDiagonal(new Coordinate(1, 6)));
    }

    @Test
    public void testDiagonalDistanceBetweenCoordinates() {
        assertEquals(5, new Coordinate(1,2).getDiagonalDistance(new Coordinate(6,7)));
        assertEquals(3, new Coordinate(4,5).getDiagonalDistance(new Coordinate(1,2)));
    }

    @Test(expected = AssertionError.class)
    public void testDiagonalDistanceBetweenCoordinatesNotOnDiagonal() {
        new Coordinate(2,3).getDiagonalDistance(new Coordinate(4,6));
    }

    @Test
    public void testDiagonalCoordinatesBetween2Coordinates() {
        final List<Coordinate> CoordinatesInBetween = Arrays.asList(
                new Coordinate(2, 3),
                new Coordinate(3, 2)
        );
        assertEquals(
                CoordinatesInBetween,
                new Coordinate(1,4).getBetweenDiagonalCoordinates(new Coordinate(4,1))
        );

    }

    @Test
    public void testDiagonalCoordinatesBetweenContiguousCoordinates() {
        final List<Coordinate> CoordinatesInBetween = new ArrayList<Coordinate>();
        assertEquals(
                CoordinatesInBetween,
                new Coordinate(3,2).getBetweenDiagonalCoordinates(new Coordinate(4,3))
        );

    }

}
