package co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatRatingInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.NewCommentInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.NewCommentListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.NewCommentPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BaseView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.NewCommentView;

/**
 * Created by Danijel on 23.7.2015..
 */
public class NewCommentPresenterImpl implements NewCommentPresenter {
    private NewCommentView view;
    private NewCommentInteractor interactor;

    public NewCommentPresenterImpl (NewCommentView view, NewCommentInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onNewCommentClicked() {
        interactor.newComment(listener);
    }

    private NewCommentListener listener = new NewCommentListener() {
        @Override
        public void newCommentActionFinished() {
            view.displayNewCommentView();
        }

        @Override
        public void onError(ErrorsEnum error) {
            view.hideProgress();
            view.onError(error);
        }
    };
}
