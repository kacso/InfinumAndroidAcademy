package co.infinum.academy.danijel_sokac.boatit.Network;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.squareup.okhttp.OkHttpClient;

import co.infinum.academy.danijel_sokac.boatit.Activities.LoginActivity;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Danijel on 13.7.2015..
 */
public class ApiManager {
    private static ApiManager instance;
    private ApiManager(){}

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
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

    private static RestAdapter ADAPTER =
            new RestAdapter.Builder()
                .setConverter(new GsonConverter(GSON))
                .setEndpoint(ENDPOINT)
                .setClient(CLIENT)
                .setLog(LOG)
                .build();

    private static BoatitService SERVICE = ADAPTER.create(BoatitService.class);

    public static BoatitService postSERVICE() {
        return SERVICE;
    }

    public static BoatitService getSERVICE() { return SERVICE; }

}
