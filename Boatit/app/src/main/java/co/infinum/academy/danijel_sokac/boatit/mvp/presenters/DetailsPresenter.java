package co.infinum.academy.danijel_sokac.boatit.mvp.presenters;

import co.infinum.academy.danijel_sokac.boatit.Models.NewComment;

/**
 * Created by Danijel on 22.7.2015..
 */
public interface DetailsPresenter {
    public void getComments();

    public void getBoatTitle();

    public void getBoatImage();

    public void onNewCommentClicked();

    public void onSendNewCommentClicked(NewComment comment);

    public void onNewCommentCanceled();

    public void onUpboatClicked();

    public void onDownboatClicked();

}
