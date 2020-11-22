package ajaragz.draughts.views;

import ajaragz.draughts.controllers.StartController;
import ajaragz.draughts.models.Game;
import ajaragz.draughts.models.State;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GameViewTest {

    private StartController startController;
    private ByteArrayOutputStream outputStream;

    @InjectMocks
    GameView gameView;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.outputStream = new ByteArrayOutputStream();
        this.startController = new StartController(new Game(),new State());
    }

    @After
    public void after() {
        System.setOut(System.out);
    }

    @Test
    public void testInitialBoardOutput() throws IOException {

        String board =
        " 12345678\n" +
        "1 n n n n1\n" +
        "2n n n n 2\n" +
        "3 n n n n3\n" +
        "4        4\n" +
        "5        5\n" +
        "6b b b b 6\n" +
        "7 b b b b7\n" +
        "8b b b b 8\n" +
        " 12345678\n";

        System.setOut(new PrintStream(this.outputStream));
        gameView.write(startController);
        outputStream.flush();

        assertEquals(board, outputStream.toString());
    }

}
