package co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl;

import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.TokenInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.SplashPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.SplashView;

/**
 * Created by kjurkovic on 17/07/15.
 */
public class SplashPresenterImpl implements SplashPresenter {

    private SplashView view;

    private TokenInteractor tokenInteractor;

    public SplashPresenterImpl(SplashView splashView, TokenInteractor tokenInteractor) {
        this.view = splashView;
        this.tokenInteractor = tokenInteractor;
    }

    @Override
    public void checkUserExists() {
        if (tokenInteractor.isUserExists()) {
            view.showBoatList();
        } else {
            view.showLogin();
        }
    }
}
