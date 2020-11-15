package ajaragz.draughts.models;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CoordinateTest.class,
        DirectionTest.class,
        PieceTest.class,
        GameBuilderTest.class,
        GamePawnMoveTest.class
})
public class AllModelTest {}
