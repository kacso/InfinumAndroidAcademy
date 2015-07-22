package co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl;

import android.graphics.Bitmap;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.DetailsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.DetailsListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.DetailsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.DetailsView;

/**
 * Created by Danijel on 22.7.2015..
 */
public class DetailsPresenterImpl implements DetailsPresenter {
    private DetailsView view;
    private DetailsInteractor interactor;

    public DetailsPresenterImpl (DetailsView view, DetailsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getComments() {
        view.showProgress();
        interactor.getComments(listener);
    }

    @Override
    public void getBoatTitle() {
        interactor.getBoatTitle(listener);
    }

    @Override
    public void getBoatImage() {
        view.showProgress();
        interactor.getBoatImage(listener);
    }

    @Override
    public void onNewCommentClicked() {

    }

    @Override
    public void onUpboatClicked() {
        interactor.upboat(listener);
    }

    @Override
    public void onDownboatClicked() {
        interactor.downboat(listener);
    }

    private DetailsListener listener = new DetailsListener() {
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
        public void onBoatTitleReceived(String title) {
            view.onBoatTitleReceived(title);
        }

        @Override
        public void onBoatImageReceived(Bitmap image) {
            view.hideProgress();
            view.onBoatImageReceived(image);
        }

        @Override
        public void onError(ErrorsEnum error) {
            view.hideProgress();
            view.onError(error);
        }


        @Override
        public void onRateBoatFinished(Boat boat) {
            view.hideProgress();
            view.onRatingFinished(boat);
        }
    };
}
