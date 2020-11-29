package ajaragz.draughts.views;

import ajaragz.draughts.controllers.PlayController;
import ajaragz.draughts.controllers.ResumeController;
import ajaragz.draughts.controllers.StartController;
import ajaragz.draughts.controllers.InteractorController;
import ajaragz.draughts.controllers.InteractorControllersVisitor;
import ajaragz.draughts.utils.YesNoDialog;

public class View extends SubView implements InteractorControllersVisitor {

    private static final String TITTLE = "Draughts";
    private static final String RESUME_MESSAGE = "¿Queréis jugar otra";

    private YesNoDialog yesNoDialog;

    private PlayView playView;

    public View(){
        super();
        this.yesNoDialog = new YesNoDialog();
        this.playView = new PlayView();
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
        this.playView.interact(playController);
    }

    @Override
    public void visit(ResumeController resumeController) {
        assert resumeController != null;
        if (this.yesNoDialog.read(View.RESUME_MESSAGE))
            resumeController.reset();
        else
            resumeController.next();
    }

}
