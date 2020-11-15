package ajaragz.draughts.controllers;

import ajaragz.draughts.models.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayControllerTest {

    private PlayController playController;

    @Test
    public void testInitialValidMoveAndGameNotBlockedAfterwards() {
        this.playController = new PlayController(new GameBuilder().build(), new State());
        Coordinate origin = new Coordinate(5,4);
        Coordinate destination = new Coordinate(4,3);
        this.playController.move(origin, destination);
        assertThat(this.playController.getColor(destination),is(Color.WHITE));
        assertFalse(this.playController.isBlocked());
    }

    @Test
    public void testCancelGame() {
        State state = new State();
        state.next();
        assertThat(state.getValueState(),is(StateValue.IN_GAME));
        this.playController = new PlayController(new GameBuilder().build(), state);
        this.playController.cancel();
        assertThat(state.getValueState(),is(StateValue.FINAL));
    }

    @Test
    public void testGameBlockedBecauseNoMoreMovesForWhites() {
        Game game = new GameBuilder().boardSetup(
                "        ",
                " n      ",
                "    n   ",
                " n n    ",
                "  b     ",
                "        ",
                "        ",
                "        ").color(Color.BLACK).build();
        this.playController = new PlayController(game, new State());
        Coordinate origin = new Coordinate(1,1);
        Coordinate destination = new Coordinate(2,0);
        this.playController.move(origin,destination);
        assertTrue(playController.isBlocked());

    }


}
