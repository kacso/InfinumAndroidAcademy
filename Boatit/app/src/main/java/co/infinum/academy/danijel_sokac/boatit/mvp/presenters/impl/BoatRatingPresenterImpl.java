package co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatImageInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatRatingInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatRatingListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatRatingPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BaseView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatRatingView;

/**
 * Created by Danijel on 23.7.2015..
 */
public class BoatRatingPresenterImpl implements BoatRatingPresenter {
    private BoatRatingView view;
    private BoatRatingInteractor interactor;

    public BoatRatingPresenterImpl (BoatRatingView view, BoatRatingInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onUpboatClicked() {
        interactor.upboat(listener);
    }

    @Override
    public void onDownboatClicked() {
        interactor.downboat(listener);
    }

    private BoatRatingListener listener = new BoatRatingListener() {
        @Override
        public void onRateBoatFinished(Boat boat) {
            view.hideProgress();
            view.onRatingFinished(boat);
        }

        @Override
        public void onError(ErrorsEnum error) {
            view.hideProgress();
            view.onRatingError(error);
        }
    };
}
