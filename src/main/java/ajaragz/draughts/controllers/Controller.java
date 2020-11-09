package ajaragz.draughts.controllers;

import ajaragz.draughts.models.Color;
import ajaragz.draughts.models.Coordinate;
import ajaragz.draughts.models.Game;
import ajaragz.draughts.models.State;

class Controller {

    protected Game game;
    protected State state;

    protected Controller(Game game, State state) {
        assert game != null;
        assert state != null;
        this.game = game;
        this.state = state;
    }

    public Color getColor(Coordinate coordinate) {
        assert coordinate != null;
        return this.game.getColor(coordinate);
    }

    public int getDimension() {
        return this.game.getDimension();
    }

}
