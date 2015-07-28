package co.infinum.academy.danijel_sokac.boatit.tests;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import co.infinum.academy.danijel_sokac.boatit.Activities.LoginActivity;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.tests.steps.LoginSteps;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Danijel on 27.7.2015..
 */
public class LoginInstrumentTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private static final String username = "admin@infinum.co";
    private static final String password = "infinum1";

    private Activity activity;

    public LoginInstrumentTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testSuccessfullLogin() {
        LoginSteps loginSteps = new LoginSteps(activity);
        loginSteps.login(username, password);
        loginSteps.checkIfLogedIn();
    }

    @Test
    public void testLoginFail() {
        LoginSteps loginSteps = new LoginSteps(activity);
        loginSteps.login("x@x", "x");
        loginSteps.checkIfLoginFailed();
    }
}
