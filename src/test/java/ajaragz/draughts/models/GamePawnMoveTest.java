package ajaragz.draughts.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GamePawnMoveTest {

    private Game game;
    private Game resultGame;

    @Test
    public void testWhitePawnJumpsTwoBlacksThenBecomesDraught() {
        this.game = new GameBuilder().color(Color.WHITE).boardSetup(
                "        ",
                " n      ",
                "    n   ",
                " n n    ",
                "  b     ",
                "        ",
                "        ",
                "        ").build();
        Coordinate origin = new Coordinate(4,2);
        Coordinate firstJump = new Coordinate(2, 0);
        Coordinate destination = new Coordinate(0,2);
        assertNull(this.game.move(origin, firstJump, destination));
        this.resultGame = new GameBuilder().color(Color.BLACK).boardSetup(
                "  B     ",
                "        ",
                "    n   ",
                "   n    ",
                "        ",
                "        ",
                "        ",
                "        ").build();
        assertEquals(this.game, this.resultGame);
    }
}
