package co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatsListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatsView;

/**
 * Created by Danijel on 23.7.2015..
 */
public class BoatsPresenterImpl implements BoatsPresenter {
    private BoatsView view;
    private BoatsInteractor interactor;

    public BoatsPresenterImpl (BoatsView view, BoatsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getBoats(int page, int perPage) {
        view.showProgress();
        interactor.getBoats(listener, page, perPage);
    }

    @Override
    public void onBoatClicked(Boat boat) {
        interactor.onBoatClicked(listener, boat);
    }

    private BoatsListener listener = new BoatsListener() {
        @Override
        public void onBoatsListReceived(List<Boat> boats) {
            view.hideProgress();
            view.onBoatsListReceived(boats);
        }

        @Override
        public void boatClicked(Boat boat) {
            view.boatClicked(boat);
        }

        @Override
        public void getAllBoatsError(ErrorsEnum error) {
            view.hideProgress();
            view.getAllBoatsError(error);
        }

        @Override
        public void boatClickedError(ErrorsEnum error) {
            view.hideProgress();
            view.boatClickedError(error);
        }

        @Override
        public void onTokenExpired() {
            view.hideProgress();
            view.onTokenExpired();
        }
    };
}
