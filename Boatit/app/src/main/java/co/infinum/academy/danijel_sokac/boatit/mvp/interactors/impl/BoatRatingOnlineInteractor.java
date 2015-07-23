package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;

import co.infinum.academy.danijel_sokac.boatit.BoatitApplication;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.RateBoat;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatRatingInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatRatingListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Danijel on 23.7.2015..
 */
public class BoatRatingOnlineInteractor implements BoatRatingInteractor {
    private Boat boat;
    private Context context;

    private BoatRatingListener listener;

    public BoatRatingOnlineInteractor(Context context, Boat boat) {
        this.context = context;
        this.boat = boat;
    }

    @Override
    public void upboat(BoatRatingListener listener) {
        this.listener = listener;
        BoatitApplication.getApiService().getUpboat(boat.getId(),
                SessionSingleton.InstanceOfSessionSingleton().getToken(),
                rateBoatCallback);
    }

    @Override
    public void downboat(BoatRatingListener listener) {
        this.listener = listener;
        BoatitApplication.getApiService().getDownboat(boat.getId(),
                SessionSingleton.InstanceOfSessionSingleton().getToken(),
                rateBoatCallback);
    }

    private Callback<RateBoat> rateBoatCallback = new Callback<RateBoat>() {
        @Override
        public void success(RateBoat rateBoat, Response response) {
            listener.onRateBoatFinished(rateBoat.getBoat());
            boat = rateBoat.getBoat();
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onError(InternetConnectionErrorsEnum.RATING_ERROR);
        }
    };
}
