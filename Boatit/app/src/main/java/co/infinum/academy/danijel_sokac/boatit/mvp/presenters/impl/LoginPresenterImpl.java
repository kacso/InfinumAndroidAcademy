package co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl;

import android.text.TextUtils;

import co.infinum.academy.danijel_sokac.boatit.BoatitApplication;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.OtherErrorEnum;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.LoginInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.LoginListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.LoginPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.LoginView;

/**
 * Created by Danijel on 23.7.2015..
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;

    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view, LoginInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void login(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            view.onLoginFailed(OtherErrorEnum.LOGIN_DATA_EMPTY);
        } else {
            view.showProgress();
            interactor.login(listener, username, password);
        }
    }

    private LoginListener listener = new LoginListener() {
        @Override
        public void onLoginSuccess(String token) {
            view.hideProgress();
            SessionSingleton.InstanceOfSessionSingleton()
                    .setToken(BoatitApplication.getInstance(), token);
            view.onLoginSuccess();
        }

        @Override
        public void onLoginFailed(ErrorsEnum error) {
            view.hideProgress();
            view.onLoginFailed(error);
        }
    };
}
