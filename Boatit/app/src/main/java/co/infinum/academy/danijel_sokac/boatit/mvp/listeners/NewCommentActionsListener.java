package co.infinum.academy.danijel_sokac.boatit.mvp.listeners;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface NewCommentActionsListener {
    public void newCommentSent();

    public void newCommentCanceled();

    public void onError(ErrorsEnum error);

    public void onTokenExpired();
}
