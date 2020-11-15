package ajaragz.draughts;

import ajaragz.draughts.controllers.AllControllerTest;
import ajaragz.draughts.models.AllModelTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllModelTest.class,
        AllControllerTest.class
})
public class AllTests {}
