package co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl;

import android.graphics.Bitmap;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.Models.NewComment;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatDetailsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatDetailsListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatDetailsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatDetailsView;

/**
 * Created by Danijel on 22.7.2015..
 */
public class BoatDetailsPresenterImpl implements BoatDetailsPresenter {
    private BoatDetailsView view;
    private BoatDetailsInteractor interactor;

    public BoatDetailsPresenterImpl(BoatDetailsView view, BoatDetailsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getBoatTitle() {
        interactor.getBoatTitle(listener);
    }

    private BoatDetailsListener listener = new BoatDetailsListener() {
        @Override
        public void onBoatTitleReceived(String title) {
            view.onBoatTitleReceived(title);
        }

        @Override
        public void onError(ErrorsEnum error) {
            view.hideProgress();
            view.onBoatDetailsError(error);
        }
    };
}
