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

public class StartViewTest {

    @Mock
    StartController startController;

    @Mock
    Console console;

    @InjectMocks
    StartView startView;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testViewStartsController() {
        startView.interact(startController);
        verify(console).writeln(anyString());
        verify(startController).start();
    }
}
