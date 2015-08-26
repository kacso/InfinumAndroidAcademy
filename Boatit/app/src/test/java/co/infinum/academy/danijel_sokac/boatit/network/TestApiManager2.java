package co.infinum.academy.danijel_sokac.boatit.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import java.io.IOException;
import java.net.CookieManager;
import java.util.concurrent.Executor;

import co.infinum.academy.danijel_sokac.boatit.Network.BoatitService;
import co.infinum.academy.danijel_sokac.boatit.Network.IApiManager;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Danijel on 27.7.2015..
 */
public class TestApiManager2 implements IApiManager {
    private static final String TAG = "Network";

    public static final String API_ENDPOINT = "https://boatit.infinum.co";

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    private static final RestAdapter.Log LOG = new RestAdapter.Log() {
        @Override
        public void log(String message) {
            Log.d(TAG, message);
        }
    };

    private static TestApiManager2 instance;

    private MockWebServer mockWebServer;

    private BoatitService service;


    public synchronized static TestApiManager2 getInstance() {
        if (instance == null) {
            instance = new TestApiManager2();
        }
        return instance;
    }

    private TestApiManager2() {
    }

    public void init() {
        OkHttpClient okHttpClient = new OkHttpClient().setCookieHandler(new CookieManager());
        setup(new SingleThreadExecutor(), new SingleThreadExecutor(), new OkClient(okHttpClient));
    }

    public void init(Executor httpExecutor, Executor callbackExecutor, OkClient okClient) {
        setup(httpExecutor, callbackExecutor, okClient);
    }

    private void setup(Executor httpExecutor, Executor callbackExecutor, OkClient okClient) {
        mockWebServer = new MockWebServer();
        try {
            mockWebServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setClient(okClient)
                .setEndpoint(mockWebServer.getUrl("/").toString())
                .setConverter(new GsonConverter(gson))
                .setLog(LOG)
                .setLogLevel(RestAdapter.LogLevel.FULL);

        if (httpExecutor != null && callbackExecutor != null) {
            builder.setExecutors(httpExecutor, callbackExecutor);
        }

        service = builder.build().create(BoatitService.class);
    }

    public BoatitService getSERVICE() {
        return service;
    }

    public MockWebServer getMockWebServer() {
        return mockWebServer;
    }
}
