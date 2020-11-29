package ajaragz.draughts.views;

import ajaragz.draughts.controllers.PlayController;
import ajaragz.draughts.controllers.ResumeController;
import ajaragz.draughts.controllers.StartController;
import ajaragz.draughts.controllers.InteractorController;
import ajaragz.draughts.controllers.InteractorControllersVisitor;

public class View extends SubView implements InteractorControllersVisitor {

    private PlayView playView;
    private ResumeView resumeView;

    public View(){
        super();
        this.playView = new PlayView();
        this.resumeView = new ResumeView();
    }

    public void interact(InteractorController controller) {
        assert controller != null;
        controller.accept(this);
    }

    @Override
    public void visit(StartController startController) {
        assert startController != null;
        this.console.writeln(StartView.TITTLE);
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
        this.resumeView.interact(resumeController);
    }

}
