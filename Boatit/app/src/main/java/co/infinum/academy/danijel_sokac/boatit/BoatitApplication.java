package co.infinum.academy.danijel_sokac.boatit;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Danijel on 19.7.2015..
 */
public class BoatitApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
