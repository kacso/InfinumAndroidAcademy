package co.infinum.academy.danijel_sokac.boatit.mvp.views;

import android.graphics.Bitmap;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;

/**
 * Created by Danijel on 22.7.2015..
 */
public interface DetailsView extends BaseView {
    public void onCommentListReceived(List<Comment> comments);

    public void onCommentListEmpty();

    public void onBoatImageReceived(Bitmap boatImage);

    public void onTokenExpired();

    public void onError(ErrorsEnum error);

    public void onBoatTitleReceived(String title);

    public void onRatingFinished(Boat boat);

    public void displayNewCommentView();

    public void onNewCommentSent();

    public void onNewCommentCanceled();
}
