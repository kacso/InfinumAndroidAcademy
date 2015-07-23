package co.infinum.academy.danijel_sokac.boatit.mvp.views;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface BoatRatingView extends BaseView {
    public void onRatingFinished(Boat boat);

    public void onRatingError(ErrorsEnum error);
}
