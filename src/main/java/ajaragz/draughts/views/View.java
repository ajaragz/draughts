package ajaragz.draughts.views;

import ajaragz.draughts.controllers.PlayController;
import ajaragz.draughts.controllers.ResumeController;
import ajaragz.draughts.controllers.StartController;
import ajaragz.draughts.controllers.InteractorController;
import ajaragz.draughts.controllers.InteractorControllersVisitor;
import ajaragz.draughts.models.Color;
import ajaragz.draughts.models.Coordinate;
import ajaragz.draughts.models.Error;
import ajaragz.draughts.utils.YesNoDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class View extends SubView implements InteractorControllersVisitor {

    private static final String TITTLE = "Draughts";
    private static final String RESUME_MESSAGE = "¿Queréis jugar otra";
    private static final String COLOR_PARAM = "#color";
    private static final String[] COLOR_VALUES = { "blancas", "negras" };
    private static final String PROMPT = "Mueven las " + View.COLOR_PARAM + ": ";
    private static final String CANCEL_FORMAT = "-1";
    private static final String MOVEMENT_FORMAT = "[1-8]{2}(\\.[1-8]{2}){1,2}";
    private static final String ERROR_MESSAGE = "Error!!! Formato incorrecto";
    private static final String LOST_MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";

    private YesNoDialog yesNoDialog;
    public String string;

    public View(){
        super();
        this.yesNoDialog = new YesNoDialog();
    }

    public void interact(InteractorController controller) {
        assert controller != null;
        controller.accept(this);
    }

    @Override
    public void visit(StartController startController) {
        assert startController != null;
        this.console.writeln(View.TITTLE);
        new GameView().write(startController);
        startController.start();
    }

    @Override
    public void visit(PlayController playController) {
        assert playController != null;
        Error error;
        do {
            error = null;
            this.string = this.read(playController.getColor());
            if (this.isCanceledFormat())
                playController.cancel();
            else if (!this.isMoveFormat()) {
                error = Error.BAD_FORMAT;
                this.writeError();
            } else {
                error = playController.move(this.getCoordinates());
                new GameView().write(playController);
                if (error == null && playController.isBlocked())
                    this.writeLost();
            }
        } while (error != null);
    }

    @Override
    public void visit(ResumeController resumeController) {
        assert resumeController != null;
        if (this.yesNoDialog.read(View.RESUME_MESSAGE))
            resumeController.reset();
        else
            resumeController.next();
    }

    private String read(Color color) {
        final String titleColor = View.PROMPT.replace(View.COLOR_PARAM ,View.COLOR_VALUES[color.ordinal()]);
        return this.console.readString(titleColor);
    }

    private boolean isCanceledFormat() {
        return string.equals(View.CANCEL_FORMAT);
    }

    private boolean isMoveFormat() {
        return Pattern.compile(View.MOVEMENT_FORMAT).matcher(string).find();
    }

    private void writeError(){
        this.console.writeln(View.ERROR_MESSAGE);
    }

    private Coordinate[] getCoordinates() {
        assert this.isMoveFormat();
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        while (string.length() > 0){
            coordinateList.add(Coordinate.getInstance(string.substring(0, 2)));
            string = string.substring(2, string.length());
            if (string.length() > 0 && string.charAt(0) == '.')
                string = string.substring(1, string.length());
        }
        Coordinate[] coordinates = new Coordinate[coordinateList.size()];
        for(int i=0; i< coordinates.length; i++){
            coordinates[i] = coordinateList.get(i);
        }
        return coordinates;
    }

    private void writeLost() {
        this.console.writeln(LOST_MESSAGE);
    }

}
