package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.NewComment;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.NewCommentActionsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.NewCommentActionsListener;

/**
 * Created by Danijel on 23.7.2015..
 */
public class NewCommentActionsOfflineInteractor implements NewCommentActionsInteractor {
    private Boat boat;
    private Context context;

    private NewCommentActionsListener listener;

    public NewCommentActionsOfflineInteractor(Context context, Boat boat) {
        this.context = context;
        this.boat = boat;
    }

    @Override
    public void sendNewComment(NewCommentActionsListener listener, NewComment comment) {
        listener.newCommentSent();
    }

    @Override
    public void cancelNewComment(NewCommentActionsListener listener) {
        listener.newCommentCanceled();
    }
}
