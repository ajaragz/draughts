package ajaragz.draughts.views;

import ajaragz.draughts.controllers.ResumeController;
import ajaragz.draughts.controllers.StartController;
import ajaragz.draughts.utils.Console;
import ajaragz.draughts.utils.YesNoDialog;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ViewTest {

    @Mock
    StartController startController;

    @Mock
    Console console;

    @InjectMocks
    View view;

    @Mock
    ResumeController resumeController;

    @Mock
    YesNoDialog yesNoDialog;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInteractWithStartControllerThenAcceptsView() {
        view.interact(startController);
        verify(startController).accept(view);
    }

    @Test
    public void testInteractWithResumeControllerThenAcceptsView() {
        view.interact(resumeController);
        verify(resumeController).accept(view);
    }

    @Test
    public void testViewStartsController() {
        view.visit(startController);
        verify(console).writeln(anyString());
        verify(startController).start();
    }

    @Test
    public void testWhenResumeThenControllerReset() {
        when(yesNoDialog.read(any())).thenReturn(true);
        view.visit(resumeController);
        verify(resumeController).reset();
    }

    @Test
    public void testWhenNotResumeThenControllerNext() {
        when(yesNoDialog.read(any())).thenReturn(false);
        view.visit(resumeController);
        verify(resumeController).next();
    }
}
