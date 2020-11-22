package ajaragz.draughts.views;

import ajaragz.draughts.controllers.PlayController;
import ajaragz.draughts.models.Color;
import ajaragz.draughts.models.Coordinate;
import ajaragz.draughts.utils.Console;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PlayViewTest {

    @Captor
    ArgumentCaptor<Coordinate[]> coordinateArgumentCaptor;

    @Mock
    PlayController playController;

    @Mock
    Console console;

    @InjectMocks
    PlayView playView;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWhenCancelOperationThenCancel() {
        when(playController.getColor()).thenReturn(Color.WHITE);
        when(console.readString(anyString())).thenReturn("-1");
        playView.interact(playController);
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
        playView.interact(playController);
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
        playView.interact(playController);
        verify(console).writeln("Derrota!!! No puedes mover tus fichas!!!");
    }

}
