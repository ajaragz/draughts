package ajaragz.draughts.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GameBuilderTest {

    private GameBuilder gameBuilder;

    @Before
    public void before(){
        this.gameBuilder = new GameBuilder();
    }

    @Test(expected = AssertionError.class)
    public void testColorIsInvalid() {
        this.gameBuilder.color(null);
    }

    @Test
    public void testColorIsValid() {
        this.gameBuilder.color(Color.WHITE);
        this.gameBuilder.color(Color.BLACK);
    }

    @Test
    public void testBoardSetupIsValid() {
        Game game = this.gameBuilder.boardSetup(
                "        ",
                "        ",
                " n      ",
                "  b     ",
                "        ",
                "        ",
                "        ",
                "        ").build();
        assertNull(game.getColor(new Coordinate(1, 2)));
        assertEquals(Color.BLACK, game.getColor(new Coordinate(2, 1)));
        assertEquals(Color.WHITE, game.getColor(new Coordinate(3, 2)));
        assertNull(game.getColor(new Coordinate(7, 6)));
    }

    @Test(expected = AssertionError.class)
    public void testBoardSetupIncorrectNumberOfRows() {
        this.gameBuilder.boardSetup(
                "        ",
                "        ",
                "        ",
                "        ",
                "        ",
                "        ",
                "        ").build();
    }

    @Test(expected = AssertionError.class)
    public void testBoardSetupIncorrectNumberOfColumn() {
        this.gameBuilder.boardSetup(
                "        ",
                "       ",
                "        ",
                "     ",
                "        ",
                "           ",
                "        ",
                "        ").build();
    }

    @Test(expected = AssertionError.class)
    public void testBoardSetupWithIncorrectCharacter() {
        this.gameBuilder.boardSetup(
                "        ",
                "        ",
                "   Y    ",
                "        ",
                "        ",
                "        ",
                "        ",
                "        ").build();
    }
}
