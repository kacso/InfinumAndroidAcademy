package co.infinum.academy.danijel_sokac.boatit.mvp.views;

import android.graphics.Bitmap;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface BoatImageView extends BaseView {
    public void onBoatImageReceived(Bitmap boatImage);

    public void onBoatImageError(ErrorsEnum error);
}
