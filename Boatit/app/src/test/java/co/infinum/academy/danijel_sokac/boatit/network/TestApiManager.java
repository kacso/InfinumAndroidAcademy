package co.infinum.academy.danijel_sokac.boatit.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import java.io.IOException;
import java.util.concurrent.Executor;

import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;
import co.infinum.academy.danijel_sokac.boatit.Network.BoatitService;
import co.infinum.academy.danijel_sokac.boatit.Network.IApiManager;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Danijel on 25.7.2015..
 */
public class TestApiManager implements IApiManager {
    private static TestApiManager instance;
    private TestApiManager(){}

    public static TestApiManager getInstance() {
        if (instance == null) {
            instance = new TestApiManager();
        }
        return instance;
    }


    public static final String ENDPOINT = "https://boatit.infinum.co";
    private static OkClient CLIENT = new OkClient(new OkHttpClient());
    private static RestAdapter.Log LOG = new RestAdapter.Log() {
        @Override
        public void log(String message) {
            Log.d("NETWORK", message);
        }
    };

    public static Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

//    private static RestAdapter ADAPTER =
//            new RestAdapter.Builder()
//                    .setConverter(new GsonConverter(GSON))
//                    .setEndpoint(ENDPOINT)
//                    .setClient(CLIENT)
//                    .setLog(LOG)
//                    .build();

    private  BoatitService SERVICE;// = ADAPTER.create(BoatitService.class);

    public  BoatitService getSERVICE() { return SERVICE; }


    private static MockWebServer mockWebServer;

    public void setup() {
        mockWebServer = new MockWebServer();
        try {
            mockWebServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setClient(CLIENT)
                .setEndpoint(mockWebServer.getUrl("/").toString())
                .setConverter(new GsonConverter(GSON))
                .setLog(LOG)
                .setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setExecutors(new SingleThreadExecutor(), new SingleThreadExecutor());

        SERVICE = builder.build().create(BoatitService.class);
    }

    public MockWebServer getMockWebServer() {
        return mockWebServer;
    }

}
