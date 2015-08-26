package co.infinum.academy.danijel_sokac.boatit.mvp.listeners;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface CommentsListener {
    public void onCommentsReceived(List<Comment> commentList);

    public void onError(ErrorsEnum error);

    public void onTokenExpired();
}
