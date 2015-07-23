package co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.NewComment;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.NewCommentActionsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.NewCommentActionsListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.NewCommentActionsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.NewCommentActionsView;

/**
 * Created by Danijel on 23.7.2015..
 */
public class NewCommentActionsPresenterImpl implements NewCommentActionsPresenter {
    private NewCommentActionsView view;
    private NewCommentActionsInteractor interactor;

    public NewCommentActionsPresenterImpl (NewCommentActionsView view, NewCommentActionsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onSendNewCommentClicked(NewComment comment) {
        view.showProgress();
        interactor.sendNewComment(listener, comment);
    }

    @Override
    public void onNewCommentCanceled() {
        interactor.cancelNewComment(listener);
    }

    private NewCommentActionsListener listener = new NewCommentActionsListener() {
        @Override
        public void newCommentSent() {
            view.hideProgress();
            view.onNewCommentSent();
        }

        @Override
        public void newCommentCanceled() {
            view.onNewCommentCanceled();
        }

        @Override
        public void onError(ErrorsEnum error) {
            view.hideProgress();
            view.onError(error);
        }
    };
}
