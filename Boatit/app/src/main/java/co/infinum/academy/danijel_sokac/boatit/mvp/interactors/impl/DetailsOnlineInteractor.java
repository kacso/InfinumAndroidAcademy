package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Looper;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import co.infinum.academy.danijel_sokac.boatit.BoatitApplication;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.Models.CommentList;
import co.infinum.academy.danijel_sokac.boatit.Models.NewComment;
import co.infinum.academy.danijel_sokac.boatit.Models.RateBoat;
import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;
import co.infinum.academy.danijel_sokac.boatit.Singletons.BoatSingleton;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.DetailsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.DetailsListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedString;

/**
 * Created by Danijel on 22.7.2015..
 */
public class DetailsOnlineInteractor implements DetailsInteractor {
    private Boat boat;
    private Context context;

    private DetailsListener listener;

    public DetailsOnlineInteractor(Context context, Boat boat) {
        this.context = context;
        this.boat = boat;
    }

    @Override
    public void getComments(DetailsListener listener) {
        this.listener = listener;
        BoatitApplication.getApiService().getComments(boat.getId(),
                SessionSingleton.InstanceOfSessionSingleton().getToken(),
                1, 100, commentsResponseCallback);
    }

    private Callback<CommentList> commentsResponseCallback = new Callback<CommentList>() {
        @Override
        public void success(CommentList commentList, Response response) {
            listener.onCommentsReceived(commentList.getComments());
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onError(InternetConnectionErrorsEnum.COMMENT_DOWNLOAD_ERROR);
        }
    };

    @Override
    public void getBoatImage(DetailsListener listener) {
        DetailsListener[] param = {listener};

        new AsyncTask<DetailsListener, Void, Void>() {
            Bitmap image;
            DetailsListener listener;
            @Override
            protected Void doInBackground(DetailsListener... params) {
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

    @Override
    public void getBoatTitle(DetailsListener listener) {
        listener.onBoatTitleReceived(boat.getTitle());
    }

    @Override
    public void upboat(DetailsListener listener) {
        this.listener = listener;
        BoatitApplication.getApiService().getUpboat(boat.getId(),
                SessionSingleton.InstanceOfSessionSingleton().getToken(),
                rateBoatCallback);
    }

    @Override
    public void downboat(DetailsListener listener) {
        BoatitApplication.getApiService().getDownboat(boat.getId(),
                SessionSingleton.InstanceOfSessionSingleton().getToken(),
                rateBoatCallback);
    }

    private Callback<RateBoat> rateBoatCallback = new Callback<RateBoat>() {
        @Override
        public void success(RateBoat rateBoat, Response response) {
            listener.onRateBoatFinished(rateBoat.getBoat());
            boat = rateBoat.getBoat();
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onError(InternetConnectionErrorsEnum.RATING_ERROR);
        }
    };

    @Override
    public void newComment(DetailsListener listener) {
        listener.newCommentActionFinished();
    }

    @Override
    public void sendNewComment(DetailsListener listener, NewComment comment) {
        //TODO
        Gson gson = new Gson();
        String s = gson.toJson(comment);
        BoatitApplication.getApiService().sendComment(boat.getId(),
                SessionSingleton.InstanceOfSessionSingleton().getToken(),
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

        }
    };

    @Override
    public void cancelNewComment(DetailsListener listener) {
        listener.newCommentCanceled();
    }
}
