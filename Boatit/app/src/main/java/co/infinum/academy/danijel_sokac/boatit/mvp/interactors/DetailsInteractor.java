package co.infinum.academy.danijel_sokac.boatit.mvp.interactors;

import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.DetailsListener;

/**
 * Created by Danijel on 22.7.2015..
 */
public interface DetailsInteractor {
    public void getComments(DetailsListener listener);

    public void getBoatImage(DetailsListener listener);

    public void getBoatTitle(DetailsListener listener);

    public void upboat(DetailsListener listener);

    public void downboat(DetailsListener listener);
}
