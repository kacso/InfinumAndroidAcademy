package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.NewCommentInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.CommentsListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.NewCommentListener;

/**
 * Created by Danijel on 23.7.2015..
 */
public class NewCommentOfflineInteractor implements NewCommentInteractor {
    private Context context;

    private CommentsListener listener;

    public NewCommentOfflineInteractor(Context context) {
        this.context = context;
    }

    @Override
    public void newComment(NewCommentListener listener) {
        listener.newCommentActionFinished();
    }
}
