package co.infinum.academy.danijel_sokac.boatit.mvp.listeners;

import android.graphics.Bitmap;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface BoatImageListener {
    public void onBoatImageReceived(Bitmap image);

    public void onError(ErrorsEnum error);
}
