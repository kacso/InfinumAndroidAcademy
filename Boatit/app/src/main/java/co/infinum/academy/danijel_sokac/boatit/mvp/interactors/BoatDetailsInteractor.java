package co.infinum.academy.danijel_sokac.boatit.mvp.interactors;

import co.infinum.academy.danijel_sokac.boatit.Models.NewComment;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatDetailsListener;

/**
 * Created by Danijel on 22.7.2015..
 */
public interface BoatDetailsInteractor {
    public void getBoatTitle(BoatDetailsListener listener);
}
