package co.infinum.academy.danijel_sokac.boatit.tests.steps;

import android.app.Activity;
import android.support.test.espresso.matcher.ViewMatchers;

import co.infinum.academy.danijel_sokac.boatit.Activities.DetailsActivity;
import co.infinum.academy.danijel_sokac.boatit.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

/**
 * Created by Danijel on 28.7.2015..
 */
public class DetailsSteps {
    private Activity activity;

    public DetailsSteps(Activity activity) {
        this.activity = activity;
    }

    public void checkIfInDetails() {
        onView(withId(R.id.upboat)).check(matches(isDisplayed()));
    }

    public void clickUpboat() {
        onView(withId(R.id.upboat)).check(matches(isClickable())).perform(click());
    }

    public void checkIfRated() {
//        onView(withText("New score")).check(matches(isDisplayed()));

        onView(withText("New score:"))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }


}
