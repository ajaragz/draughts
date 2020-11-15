package ajaragz.draughts.controllers;

import ajaragz.draughts.models.Game;
import ajaragz.draughts.models.GameBuilder;
import ajaragz.draughts.models.State;
import ajaragz.draughts.models.StateValue;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ResumeControllerTest {

    private State state;
    private ResumeController resumeController;

    @Before
    public void before() {
        Game game = new GameBuilder().build();
        this.state = new State();
        this.resumeController = new ResumeController(game, this.state);
    }

    @Test
    public void testStateChangesFromInitialToFinalAndResetsAfterwards() {
        assertThat(this.state.getValueState(),is(StateValue.INITIAL));
        resumeController.next();
        assertThat(this.state.getValueState(),is(StateValue.IN_GAME));
        resumeController.next();
        assertThat(this.state.getValueState(),is(StateValue.FINAL));
        resumeController.reset();
        assertThat(this.state.getValueState(),is(StateValue.INITIAL));
    }

    @Test(expected = AssertionError.class)
    public void testStateResetsFromFinalToInitial() {
        assertThat(this.state.getValueState(),is(StateValue.INITIAL));
        resumeController.next();
        assertThat(this.state.getValueState(),is(StateValue.IN_GAME));
        resumeController.next();
        assertThat(this.state.getValueState(),is(StateValue.FINAL));
        resumeController.next();
        assertThat(this.state.getValueState(),is(StateValue.EXIT));
        resumeController.next();
    }

}
