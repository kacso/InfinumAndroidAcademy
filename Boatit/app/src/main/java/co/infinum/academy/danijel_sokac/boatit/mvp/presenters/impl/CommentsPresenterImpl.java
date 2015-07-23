package co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatRatingInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.CommentsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.CommentsListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.CommentsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BaseView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.CommentsView;

/**
 * Created by Danijel on 23.7.2015..
 */
public class CommentsPresenterImpl implements CommentsPresenter {
    private CommentsView view;
    private CommentsInteractor interactor;

    public CommentsPresenterImpl (CommentsView view, CommentsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getComments() {
        view.showProgress();
        interactor.getComments(listener);
    }

    private CommentsListener listener = new CommentsListener() {
        @Override
        public void onCommentsReceived(List<Comment> commentList) {
            view.hideProgress();
            if (commentList.isEmpty()) {
                view.onCommentListEmpty();
            } else {
                view.onCommentListReceived(commentList);
            }
        }

        @Override
        public void onError(ErrorsEnum error) {
            view.hideProgress();
            view.onError(error);
        }
    };
}
