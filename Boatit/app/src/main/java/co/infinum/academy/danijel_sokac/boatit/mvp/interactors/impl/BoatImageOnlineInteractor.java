package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatImageInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatImageListener;

/**
 * Created by Danijel on 23.7.2015..
 */
public class BoatImageOnlineInteractor implements BoatImageInteractor{
    private Boat boat;
    private Context context;

    private BoatImageListener listener;

    public BoatImageOnlineInteractor(Context context, Boat boat) {
        this.context = context;
        this.boat = boat;
    }


    @Override
    public void getBoatImage(BoatImageListener listener) {
        this.listener = listener;
        BoatImageListener[] param = {listener};

        new AsyncTask<BoatImageListener, Void, Void>() {
            Bitmap image;
            BoatImageListener listener;

            @Override
            protected Void doInBackground(BoatImageListener... params) {
                listener = params[0];
                try {
                    image = Glide.with(context).load(boat
                            .getImageURL()).asBitmap().into(-1, -1).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (image != null) {
                    listener.onBoatImageReceived(image);
                } else {
                    listener.onError(InternetConnectionErrorsEnum.IMAGE_DOWNLOAD_ERROR);
                }
            }
        }.execute(param);
    }
}
