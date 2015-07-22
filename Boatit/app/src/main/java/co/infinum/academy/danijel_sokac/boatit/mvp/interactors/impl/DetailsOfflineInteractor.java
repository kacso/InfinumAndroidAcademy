package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.DetailsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.DetailsListener;

/**
 * Created by Danijel on 22.7.2015..
 */
public class DetailsOfflineInteractor implements DetailsInteractor {
    Context context;
    Boat boat;

    public DetailsOfflineInteractor(Context context, Boat boat) {
        this.context = context;
        this.boat = boat;
    }


    @Override
    public void getComments(DetailsListener listener) {
        listener.onCommentsReceived(boat.getComments());
    }

    @Override
    public void getBoatImage(DetailsListener listener) {

    }

    @Override
    public void getBoatTitle(DetailsListener listener) {
        listener.onBoatTitleReceived(boat.getTitle());
    }

    @Override
    public void upboat(DetailsListener listener) {
        listener.onError(InternetConnectionErrorsEnum.OFFLINE_MODE);
    }

    @Override
    public void downboat(DetailsListener listener) {
        listener.onError(InternetConnectionErrorsEnum.OFFLINE_MODE);
    }
}
