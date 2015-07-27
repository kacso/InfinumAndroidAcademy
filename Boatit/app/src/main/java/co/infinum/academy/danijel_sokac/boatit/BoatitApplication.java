package co.infinum.academy.danijel_sokac.boatit;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;
import co.infinum.academy.danijel_sokac.boatit.Network.BoatitService;
import co.infinum.academy.danijel_sokac.boatit.Network.IApiManager;

/**
 * Created by Danijel on 19.7.2015..
 */
public class BoatitApplication extends Application {
    private static BoatitApplication instance;

    protected IApiManager apiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FlowManager.init(this);

        apiManager = ApiManager.getInstance();
    }

    public static BoatitApplication getInstance() {
        return instance;
    }

    public static BoatitService getApiService() {
        return getInstance().apiManager.getSERVICE();
    }
}
