package co.infinum.academy.danijel_sokac.boatit.mvp.listeners;

import android.graphics.Bitmap;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorTypeEnum;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;

/**
 * Created by Danijel on 22.7.2015..
 */
public interface DetailsListener {
    public void onCommentsReceived(List<Comment> commentList);

    public void onBoatTitleReceived(String title);

    public void onBoatImageReceived(Bitmap image);

    public void onError(ErrorsEnum error);

    public void onRateBoatFinished(Boat boat);

    public void newCommentActionFinished();

    public void newCommentSent();

    public void newCommentCanceled();
}
