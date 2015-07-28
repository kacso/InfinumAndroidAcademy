package co.infinum.academy.danijel_sokac.boatit.tests.steps;

import android.app.Activity;

import co.infinum.academy.danijel_sokac.boatit.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Created by Danijel on 28.7.2015..
 */
public class NewCommentSteps {
    Activity activity;

    public NewCommentSteps(Activity activity) {
        this.activity = activity;
    }

    public void enterComment(String comment) {
        onView(withId(R.id.new_comment_text)).perform(typeText(comment), closeSoftKeyboard());
    }

    public void clickSendComment() {
        onView(withId(R.id.send_new_comment)).perform(click());
    }

    public void checkNewCommentSent(String comment) {
        onView(withText(comment)).check(matches(isDisplayed()));
    }
}
