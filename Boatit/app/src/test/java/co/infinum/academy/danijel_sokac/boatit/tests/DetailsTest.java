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

import co.infinum.academy.danijel_sokac.boatit.Activities.DetailsActivity;
import co.infinum.academy.danijel_sokac.boatit.Activities.LoginActivity;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.BoatSingleton;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.TestBoatitApplication;
import co.infinum.academy.danijel_sokac.boatit.network.TestApiManager;
import co.infinum.academy.danijel_sokac.boatit.network.TestApiManager2;
import co.infinum.academy.danijel_sokac.boatit.utils.ResourceUtils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Danijel on 25.7.2015..
 */
@Config(sdk = 21, application = TestBoatitApplication.class)
@RunWith(RobolectricTestRunner.class)
public class DetailsTest {
    private MockWebServer mockWebServer;

    private final String token = "1234567890";

    @Before
    public void setUp() {
        TestApiManager2 apiManager = TestApiManager2.getInstance();
        mockWebServer = apiManager.getMockWebServer();

        SessionSingleton.InstanceOfSessionSingleton().setToken(TestBoatitApplication.getInstance(), token);



        Boat boat = new Boat();
        boat.setComments(null);
        boat.setTitle("Test");
        boat.setId(1);
        boat.setImageURL("test.com");
        boat.setToken(token);
        boat.setScore(3);

        BoatSingleton.InstanceOfSessionSingleton().setBoat(boat);

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
    public void testUpboat() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json; charset=UTF-8")
                .setBody(ResourceUtils.readFromFile("comments.json")));
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json; charset=UTF-8")
                .setBody(ResourceUtils.readFromFile("upboat.json")));


        ActivityController<DetailsActivity> detailsActivityController=
                Robolectric.buildActivity(DetailsActivity.class);


        DetailsActivity detailsActivity = detailsActivityController.create()
                .start()
                .resume()
                .visible()
                .get();


        Button upboat = (Button) detailsActivity.findViewById(R.id.upboat);

        assertThat(upboat, notNullValue());


        upboat.performClick();

        try {
            RecordedRequest recordedRequest = mockWebServer.takeRequest();
            assertThat(recordedRequest.getHeader("Content-Type"), equalTo("application/json; charset=UTF-8"));
//            assertThat(ResourceUtils.convertStreamToString(recordedRequest.getBody().inputStream())
//                          .replaceAll("\\s+",""),
//                    equalTo(ResourceUtils.readFromFile("upboat.json"))).replaceAll("\\s+","");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
