package ajaragz.draughts.controllers;

import java.util.HashMap;
import java.util.Map;

import ajaragz.draughts.models.Game;
import ajaragz.draughts.models.State;
import ajaragz.draughts.models.StateValue;

public class Logic {

	private Game game;
	private State state;
	private Map<StateValue, InteractorController> controllers;

	public Logic() {
		this.game = new Game();
		this.state = new State();
        this.controllers = new HashMap<StateValue, InteractorController>();
		this.controllers.put(StateValue.INITIAL, new StartController(this.game, this.state));
		this.controllers.put(StateValue.IN_GAME, new PlayController(this.game, this.state));
		this.controllers.put(StateValue.FINAL, new ResumeController(this.game, this.state));
		this.controllers.put(StateValue.EXIT, null);
	}

	public InteractorController getController() {
		return this.controllers.get(this.state.getValueState());
    }

}
