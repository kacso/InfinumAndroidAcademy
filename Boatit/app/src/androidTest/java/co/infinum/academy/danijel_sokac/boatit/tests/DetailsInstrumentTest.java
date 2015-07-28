package co.infinum.academy.danijel_sokac.boatit.tests;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import co.infinum.academy.danijel_sokac.boatit.Activities.DetailsActivity;
import co.infinum.academy.danijel_sokac.boatit.R;

/**
 * Created by Danijel on 27.7.2015..
 */
public class DetailsInstrumentTest extends ActivityInstrumentationTestCase2<DetailsActivity> {

    private Activity activity;

    public DetailsInstrumentTest() {
        super(DetailsActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activity = getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testUpboat() {

    }
}
