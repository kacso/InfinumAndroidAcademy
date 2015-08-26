package co.infinum.academy.danijel_sokac.boatit.tests.steps;

import android.app.Activity;
import android.support.test.espresso.ViewAssertion;
import android.view.View;

import org.hamcrest.Matcher;

import co.infinum.academy.danijel_sokac.boatit.R;

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
 * Created by Danijel on 28.7.2015..
 */
public class LoginSteps {
    Activity activity;

    public LoginSteps(Activity activity) {
        this.activity = activity;
    }


    public void enterUsername(String username) {
        onView(withId(R.id.username)).perform(typeText(username));
    }

    public void enterPassword(String password) {
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
    }

    public void clickLogin() {
        onView(isRoot()).perform(swipeDown());
        onView(withId(R.id.login_btn)).perform(click());
    }

    public void login(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickLogin();

    }

    public void checkIfLogedIn() {
        //New activity is shown
//        onView(withText(activity.getString(R.string.progress_wait))).check((matches(isDisplayed())));
//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //Problematic part. Doesn't wait for progress dialog to dismiss
        onView(withText(activity.getString(R.string.title_activity_boats))).check(matches(isDisplayed()));
    }

    public void checkIfLoginFailed() {
//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        onView(withId(R.id.login_btn)).check(matches(isDisplayed()));
    }

}
