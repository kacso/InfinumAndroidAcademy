package co.infinum.academy.danijel_sokac.boatit.mvp.listeners;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface BoatsListener {
    public void onBoatsListReceived(List<Boat> boats);

    public void boatClicked(Boat boat);

    public void getAllBoatsError(ErrorsEnum error);

    public void boatClickedError(ErrorsEnum error);

    public void onTokenExpired();
}
