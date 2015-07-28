package co.infinum.academy.danijel_sokac.boatit.tests;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import co.infinum.academy.danijel_sokac.boatit.Activities.DetailsActivity;
import co.infinum.academy.danijel_sokac.boatit.Activities.LoginActivity;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.tests.Constants.Constants;
import co.infinum.academy.danijel_sokac.boatit.tests.steps.BoatsSteps;
import co.infinum.academy.danijel_sokac.boatit.tests.steps.DetailsSteps;
import co.infinum.academy.danijel_sokac.boatit.tests.steps.LoginSteps;

/**
 * Created by Danijel on 27.7.2015..
 */
public class DetailsInstrumentTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Activity activity;

    LoginSteps loginSteps;
    BoatsSteps boatsSteps;
    DetailsSteps detailsSteps;

    public DetailsInstrumentTest() {
        super(LoginActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activity = getActivity();
        loginSteps = new LoginSteps(activity);
        boatsSteps = new BoatsSteps(activity);
        detailsSteps = new DetailsSteps(activity);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testUpboat() {
        loginSteps.login(Constants.username, Constants.password);
        loginSteps.checkIfLogedIn();
        boatsSteps.clickDetails(0);
        detailsSteps.checkIfInDetails();

    }
}
