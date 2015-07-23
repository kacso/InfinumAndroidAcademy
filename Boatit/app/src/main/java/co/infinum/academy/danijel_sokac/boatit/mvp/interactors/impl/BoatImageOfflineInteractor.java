package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatImageInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatImageListener;

/**
 * Created by Danijel on 23.7.2015..
 */
public class BoatImageOfflineInteractor implements BoatImageInteractor {
    private Boat boat;
    private Context context;

    private BoatImageListener listener;

    public BoatImageOfflineInteractor(Context context, Boat boat) {
        this.context = context;
        this.boat = boat;
    }

    @Override
    public void getBoatImage(BoatImageListener listener) {
        listener.onBoatImageReceived(null);
    }
}
