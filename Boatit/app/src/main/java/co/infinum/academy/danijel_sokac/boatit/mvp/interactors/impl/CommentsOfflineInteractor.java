package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;

import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.CommentsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.CommentsListener;

/**
 * Created by Danijel on 23.7.2015..
 */
public class CommentsOfflineInteractor implements CommentsInteractor {
    private Boat boat;
    private Context context;

    private CommentsListener listener;

    public CommentsOfflineInteractor(Context context, Boat boat) {
        this.context = context;
        this.boat = boat;
    }

    @Override
    public void getComments(CommentsListener listener) {
        listener.onCommentsReceived(boat.getComments());
    }
}
