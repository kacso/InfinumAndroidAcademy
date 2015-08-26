package co.infinum.academy.danijel_sokac.boatit.mvp.interactors;

import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatsListener;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface BoatsInteractor {
    public void getBoats(BoatsListener listener, int page, int perPage);

    public void onBoatClicked(BoatsListener listener, Boat boat);
}
