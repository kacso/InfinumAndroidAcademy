package co.infinum.academy.danijel_sokac.boatit.tests;

import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.util.ActivityController;

import java.io.IOException;

import co.infinum.academy.danijel_sokac.boatit.Activities.BoatsActivity;
import co.infinum.academy.danijel_sokac.boatit.Activities.DetailsActivity;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.TestBoatitApplication;
import co.infinum.academy.danijel_sokac.boatit.network.TestApiManager2;
import co.infinum.academy.danijel_sokac.boatit.utils.ResourceUtils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Danijel on 28.7.2015..
 */
@Config(sdk = 21, application = TestBoatitApplication.class)
@RunWith(RobolectricTestRunner.class)
public class BoatsListTest {
    private MockWebServer mockWebServer;

    private final String token = "1234567890";

    @Before
    public void setUp() {
        TestApiManager2 apiManager = TestApiManager2.getInstance();
        mockWebServer = apiManager.getMockWebServer();

        SessionSingleton.InstanceOfSessionSingleton().setToken(TestBoatitApplication.getInstance(), token);

        ShadowLog.stream = System.out;
    }

    @After
    public void tearDown() {
        try {
            mockWebServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBoatsDownload() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json; charset=UTF-8")
                .setBody(ResourceUtils.readFromFile("boats.json")));

        ActivityController<BoatsActivity> boatsActivityController=
                Robolectric.buildActivity(BoatsActivity.class);


        BoatsActivity boatsActivity = boatsActivityController.create()
                .start()
                .resume()
                .visible()
                .get();

        try {
            RecordedRequest recordedRequest = mockWebServer.takeRequest();

            assertThat(recordedRequest.getMethod(), equalTo("GET"));

            assertThat(recordedRequest.getPath(), equalTo("/api/v1/posts?token=" + token + "&page=1&per_page=100"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
