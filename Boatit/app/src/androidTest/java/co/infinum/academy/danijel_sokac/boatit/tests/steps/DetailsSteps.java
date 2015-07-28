package co.infinum.academy.danijel_sokac.boatit.tests.steps;

import android.app.Activity;

import co.infinum.academy.danijel_sokac.boatit.Activities.DetailsActivity;
import co.infinum.academy.danijel_sokac.boatit.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Danijel on 28.7.2015..
 */
public class DetailsSteps {
    private Activity activity;

    public DetailsSteps(Activity activity) {
        this.activity = activity;
    }

    public void checkIfInDetails() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.upboat)).check(matches(isDisplayed()));
    }
}
