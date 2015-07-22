package co.infinum.academy.danijel_sokac.boatit;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;
import co.infinum.academy.danijel_sokac.boatit.Network.BoatitService;

/**
 * Created by Danijel on 19.7.2015..
 */
public class BoatitApplication extends Application {
    private static BoatitApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FlowManager.init(this);
    }

    public static BoatitApplication getInstance() {
        return instance;
    }

    public static BoatitService getApiService() {
        return ApiManager.getInstance().getSERVICE();
    }
}
