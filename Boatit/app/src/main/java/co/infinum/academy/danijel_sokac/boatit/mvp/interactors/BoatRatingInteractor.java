package co.infinum.academy.danijel_sokac.boatit.mvp.interactors;

import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatRatingListener;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface BoatRatingInteractor {
    public void upboat(BoatRatingListener listener);

    public void downboat(BoatRatingListener listener);
}
