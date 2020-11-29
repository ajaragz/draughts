package ajaragz.draughts.views;

import ajaragz.draughts.controllers.PlayController;
import ajaragz.draughts.controllers.ResumeController;
import ajaragz.draughts.controllers.StartController;
import ajaragz.draughts.models.Color;
import ajaragz.draughts.models.Coordinate;
import ajaragz.draughts.utils.Console;
import ajaragz.draughts.utils.YesNoDialog;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

    @Mock
    PlayController playController;

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
    public void testInteractWithPlayControllerThenAcceptsView() {
        view.interact(playController);
        verify(playController).accept(view);
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

    @Test
    public void testWhenCancelOperationThenCancel() {
        when(playController.getColor()).thenReturn(Color.WHITE);
        when(console.readString(anyString())).thenReturn("-1");
        view.visit(playController);
        verify(playController).cancel();
    }

    @Test
    public void testWhenFormatIncorrectThenWriteBadFormat3TimesAndCancel() {
        when(playController.getColor()).thenReturn(Color.WHITE);
        when(console.readString(anyString()))
                .thenReturn("abcd")
                .thenReturn("1234")
                .thenReturn("11.a")
                .thenReturn("-1");
        view.visit(playController);
        verify(console, times(3)).writeln("Error!!! Formato incorrecto");
        verify(playController).cancel();
    }


    @Test
    public void testGameBlockedThenLost() {
        when(playController.getColor()).thenReturn(Color.WHITE);
        when(console.readString(anyString()))
                .thenReturn("11.22");
        when(playController.move(any(Coordinate.class),any(Coordinate.class))).thenReturn(null);
        when(playController.isBlocked()).thenReturn(true);
        view.visit(playController);
        verify(console).writeln("Derrota!!! No puedes mover tus fichas!!!");
    }
}
