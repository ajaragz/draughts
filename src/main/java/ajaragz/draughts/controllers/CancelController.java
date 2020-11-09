package ajaragz.draughts.controllers;

import ajaragz.draughts.models.Game;
import ajaragz.draughts.models.State;

class CancelController extends Controller {

    protected CancelController(Game game, State state) {
        super(game, state);
    }

    public void cancel() {
		this.game.cancel();
		this.state.next();
	}

}