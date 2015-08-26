package co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl;

import android.graphics.Bitmap;
import android.view.View;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatImageInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatImageListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatImagePresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BaseView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatImageView;

/**
 * Created by Danijel on 23.7.2015..
 */
public class BoatImagePresenterImpl implements BoatImagePresenter {
    private BoatImageView view;
    private BoatImageInteractor interactor;

    public BoatImagePresenterImpl (BoatImageView view, BoatImageInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getBoatImage() {
        view.showProgress();
        interactor.getBoatImage(listener);
    }

    private BoatImageListener listener = new BoatImageListener() {
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
    };
}
