package co.infinum.academy.danijel_sokac.boatit.mvp.presenters;

import co.infinum.academy.danijel_sokac.boatit.Models.NewComment;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface NewCommentActionsPresenter {
    public void onSendNewCommentClicked(NewComment comment);

    public void onNewCommentCanceled();
}
