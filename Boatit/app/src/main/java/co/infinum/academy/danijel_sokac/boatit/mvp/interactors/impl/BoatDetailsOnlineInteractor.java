package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import co.infinum.academy.danijel_sokac.boatit.BoatitApplication;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.Models.CommentList;
import co.infinum.academy.danijel_sokac.boatit.Models.NewComment;
import co.infinum.academy.danijel_sokac.boatit.Models.RateBoat;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatDetailsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatDetailsListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedString;

/**
 * Created by Danijel on 22.7.2015..
 */
public class BoatDetailsOnlineInteractor implements BoatDetailsInteractor {
    private Boat boat;
    private Context context;

    private BoatDetailsListener listener;

    public BoatDetailsOnlineInteractor(Context context, Boat boat) {
        this.context = context;
        this.boat = boat;
    }

    @Override
    public void getBoatTitle(BoatDetailsListener listener) {
        listener.onBoatTitleReceived(boat.getTitle());
    }
}
