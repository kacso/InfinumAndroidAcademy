package co.infinum.academy.danijel_sokac.boatit.mvp.listeners;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface BoatRatingListener {
    public void onRateBoatFinished(Boat boat);

    public void onError(ErrorsEnum error);
}
