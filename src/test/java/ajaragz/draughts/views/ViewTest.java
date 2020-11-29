package ajaragz.draughts.views;

import ajaragz.draughts.controllers.StartController;
import ajaragz.draughts.utils.Console;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

public class ViewTest {

    @Mock
    StartController startController;

    @Mock
    Console console;

    @InjectMocks
    View view;

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
    public void testViewStartsController() {
        view.visit(startController);
        verify(console).writeln(anyString());
        verify(startController).start();
    }
}
