package ajaragz.draughts;

import ajaragz.draughts.controllers.AllControllerTest;
import ajaragz.draughts.models.AllModelTest;
import ajaragz.draughts.views.AllViewsTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllModelTest.class,
        AllControllerTest.class,
        AllViewsTest.class
})
public class AllTests {}
