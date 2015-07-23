package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatRatingInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatRatingListener;

/**
 * Created by Danijel on 23.7.2015..
 */
public class BoatRatingOfflineInteractor implements BoatRatingInteractor {
    private Boat boat;
    private Context context;

    private BoatRatingListener listener;

    public BoatRatingOfflineInteractor(Context context, Boat boat) {
        this.context = context;
        this.boat = boat;
    }

    @Override
    public void upboat(BoatRatingListener listener) {
        listener.onRateBoatFinished(boat);
    }

    @Override
    public void downboat(BoatRatingListener listener) {
        listener.onRateBoatFinished(boat);
    }
}
