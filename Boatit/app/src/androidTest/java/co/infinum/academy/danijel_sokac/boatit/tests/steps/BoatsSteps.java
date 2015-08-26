package co.infinum.academy.danijel_sokac.boatit.tests.steps;

import android.app.Activity;

import co.infinum.academy.danijel_sokac.boatit.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Created by Danijel on 28.7.2015..
 */
public class BoatsSteps {
    private Activity activity;

    public BoatsSteps(Activity activity) {
        this.activity = activity;
    }

    public void clickDetails(int index) {
        onData(anything())
                .inAdapterView(withId(R.id.boats_list))
                .atPosition(index)
                .perform(click());
    }
}
