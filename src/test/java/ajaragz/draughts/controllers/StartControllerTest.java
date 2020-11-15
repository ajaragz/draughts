package ajaragz.draughts.controllers;

import ajaragz.draughts.models.Game;
import ajaragz.draughts.models.GameBuilder;
import ajaragz.draughts.models.State;
import ajaragz.draughts.models.StateValue;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StartControllerTest {

    private State state;
    private StartController startController;

    @Before
    public void before() {
        Game game = new GameBuilder().build();
        this.state = new State();
        this.startController = new StartController(game, this.state);
    }

    @Test
    public void testStateChangesFromInitialToInGame() {
        assertThat(this.state.getValueState(),is(StateValue.INITIAL));
        startController.start();
        assertThat(this.state.getValueState(),is(StateValue.IN_GAME));
    }

}
