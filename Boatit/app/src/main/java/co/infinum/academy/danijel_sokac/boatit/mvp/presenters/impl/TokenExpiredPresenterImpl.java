package co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl;

import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.TokenExpiredInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.TokenInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.TokenExpiredListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.TokenExpiredPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BaseView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.SplashView;

/**
 * Created by Danijel on 24.7.2015..
 */
public class TokenExpiredPresenterImpl implements TokenExpiredPresenter {

    private BaseView view;

    private TokenExpiredInteractor interactor;

    public TokenExpiredPresenterImpl(BaseView view, TokenExpiredInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void tokenExpired() {
        interactor.tokenExpired(listener);
    }

    TokenExpiredListener listener = new TokenExpiredListener() {
        @Override
        public void tokenExpired() {
            view.tokenExpired();
        }
    };
}
