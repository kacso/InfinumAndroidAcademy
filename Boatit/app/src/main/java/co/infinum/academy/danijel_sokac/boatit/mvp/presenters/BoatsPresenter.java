package co.infinum.academy.danijel_sokac.boatit.mvp.presenters;

import co.infinum.academy.danijel_sokac.boatit.Models.Boat;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface BoatsPresenter {
    public void getBoats(int page, int perPage);

    public void onBoatClicked(Boat boat);
}
