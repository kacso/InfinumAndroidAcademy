package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;

import com.google.gson.Gson;

import co.infinum.academy.danijel_sokac.boatit.BoatitApplication;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.Models.NewComment;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.NewCommentActionsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.NewCommentActionsListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedString;

/**
 * Created by Danijel on 23.7.2015..
 */
public class NewCommentActionsOnlineInteractor implements NewCommentActionsInteractor {
    private Boat boat;
    private Context context;

    private NewCommentActionsListener listener;

    public NewCommentActionsOnlineInteractor(Context context, Boat boat) {
        this.context = context;
        this.boat = boat;
    }

    @Override
    public void sendNewComment(NewCommentActionsListener listener, NewComment comment) {
        this.listener = listener;
        Gson gson = new Gson();
        String s = gson.toJson(comment);
        BoatitApplication.getApiService().sendComment(boat.getId(),
                SessionSingleton.InstanceOfSessionSingleton().getToken(context),
                new TypedString(s),
                newCommentCallback);
    }

    Callback<Comment> newCommentCallback = new Callback<Comment>() {
        @Override
        public void success(Comment comment, Response response) {
            listener.newCommentSent();
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onError(InternetConnectionErrorsEnum.COMMENT_POST_ERROR);
        }
    };

    @Override
    public void cancelNewComment(NewCommentActionsListener listener) {
        listener.newCommentCanceled();
    }
}
