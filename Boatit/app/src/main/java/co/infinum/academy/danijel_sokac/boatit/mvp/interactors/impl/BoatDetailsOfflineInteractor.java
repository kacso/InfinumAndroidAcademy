package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.NewComment;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatDetailsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatDetailsListener;

/**
 * Created by Danijel on 22.7.2015..
 */
public class BoatDetailsOfflineInteractor implements BoatDetailsInteractor {
    Context context;
    Boat boat;

    public BoatDetailsOfflineInteractor(Context context, Boat boat) {
        this.context = context;
        this.boat = boat;
    }
    @Override
    public void getBoatTitle(BoatDetailsListener listener) {
        listener.onBoatTitleReceived(boat.getTitle());
    }
}
