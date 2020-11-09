package ajaragz.draughts.controllers;

import ajaragz.draughts.models.Coordinate;
import ajaragz.draughts.models.Game;
import ajaragz.draughts.models.Piece;
import ajaragz.draughts.models.State;

public abstract class InteractorController extends Controller {

	protected InteractorController(Game game, State state) {
		super(game, state);
	}

	public Piece getPiece(Coordinate coordinate) {
		return this.game.getPiece(coordinate);
	}

	abstract public void accept(InteractorControllersVisitor controllersVisitor);

}
