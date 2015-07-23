package co.infinum.academy.danijel_sokac.boatit.mvp.views;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface CommentsView extends BaseView {
    public void onCommentListReceived(List<Comment> comments);

    public void onCommentListEmpty();

    public void onCommentsError(ErrorsEnum error);
}
