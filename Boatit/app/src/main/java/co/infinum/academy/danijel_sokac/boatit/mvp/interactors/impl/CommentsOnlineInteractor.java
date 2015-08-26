package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;

import co.infinum.academy.danijel_sokac.boatit.BoatitApplication;
import co.infinum.academy.danijel_sokac.boatit.Database.DBFlowBoatit;
import co.infinum.academy.danijel_sokac.boatit.Database.DatabaseExecutor;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.CommentList;
import co.infinum.academy.danijel_sokac.boatit.Network.BaseCallback;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.CommentsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.CommentsListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Danijel on 23.7.2015..
 */
public class CommentsOnlineInteractor implements CommentsInteractor {
    private Boat boat;
    private Context context;

    private CommentsListener listener;

    public CommentsOnlineInteractor(Context context, Boat boat) {
        this.context = context;
        this.boat = boat;
    }

    @Override
    public void getComments(CommentsListener listener) {
        this.listener = listener;
        BoatitApplication.getApiService().getComments(boat.getId(),
                SessionSingleton.InstanceOfSessionSingleton().getToken(context),
                1, 100, commentsResponseCallback);
    }

    private Callback<CommentList> commentsResponseCallback = new BaseCallback<CommentList>() {
        @Override
        public void success(CommentList commentList, Response response) {
            DatabaseExecutor dbExecutor = new DatabaseExecutor(context, new DBFlowBoatit(context));
            boat.setComments(commentList.getComments());
            dbExecutor.storeCommentsToDatabase(boat);
            listener.onCommentsReceived(commentList.getComments());
        }

        @Override
        public void onTokenExpired() {
            listener.onTokenExpired();
        }

        @Override
        public void error(RetrofitError error) {
            listener.onError(InternetConnectionErrorsEnum.COMMENT_DOWNLOAD_ERROR);
        }
    };
}
