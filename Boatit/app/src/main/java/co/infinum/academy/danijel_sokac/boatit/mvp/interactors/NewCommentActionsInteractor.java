package co.infinum.academy.danijel_sokac.boatit.mvp.interactors;

import co.infinum.academy.danijel_sokac.boatit.Models.NewComment;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.NewCommentActionsListener;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface NewCommentActionsInteractor {
    public void sendNewComment(NewCommentActionsListener listener, NewComment comment);

    public void cancelNewComment(NewCommentActionsListener listener);
}
