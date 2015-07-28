package co.infinum.academy.danijel_sokac.boatit.tests;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import co.infinum.academy.danijel_sokac.boatit.Activities.LoginActivity;
import co.infinum.academy.danijel_sokac.boatit.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Danijel on 27.7.2015..
 */
public class LoginInstrumentTest extends ActivityInstrumentationTestCase2<LoginActivity> {

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
        onView(withId(R.id.username)).perform(ViewActions.typeText("admin@infinum.co"));
        onView(withId(R.id.password)).perform(ViewActions.typeText("infinum1"), ViewActions.closeSoftKeyboard());
        onView(isRoot()).perform(ViewActions.swipeDown());
        onView(withId(R.id.login_btn)).perform(ViewActions.click());

        //New activity is shown
        onView(withText(activity.getString(R.string.progress_wait))).check((matches(isDisplayed())));
        onView(withText(activity.getString(R.string.title_activity_boats))).check(matches(isDisplayed()));

    }
}
