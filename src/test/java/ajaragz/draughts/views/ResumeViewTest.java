package ajaragz.draughts.views;

import ajaragz.draughts.controllers.ResumeController;
import ajaragz.draughts.utils.YesNoDialog;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResumeViewTest {

    @Mock
    YesNoDialog yesNoDialog;

    @Mock
    ResumeController resumeController;

    @InjectMocks
    ResumeView resumeView;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWhenResumeThenControllerReset() {
        when(yesNoDialog.read(any())).thenReturn(true);
        resumeView.interact(resumeController);
        verify(resumeController).reset();
    }

    @Test
    public void testWhenNotResumeThenControllerNext() {
        when(yesNoDialog.read(any())).thenReturn(false);
        resumeView.interact(resumeController);
        verify(resumeController).next();
    }
}
