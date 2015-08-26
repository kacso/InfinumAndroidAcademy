package co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.LoginInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.RegisterInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.RegisterListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.RegisterPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.LoginView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.RegisterView;

/**
 * Created by Danijel on 24.7.2015..
 */
public class RegisterPresenterImpl implements RegisterPresenter {

    private RegisterView view;

    private RegisterInteractor interactor;

    public RegisterPresenterImpl(RegisterView view, RegisterInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void register(String username, String password, String confirmPassword, String firstName, String lastName) {
        view.showProgress();
        interactor.register(listener, username, password, confirmPassword, firstName, lastName);
    }

    private RegisterListener listener = new RegisterListener() {
        @Override
        public void onRegisterSuccess(String token) {
            view.hideProgress();
            view.onRegisterSuccess();
        }

        @Override
        public void onRegisterFailed(ErrorsEnum error) {
            view.hideProgress();
            view.onRegisterFailed(error);
        }
    };
}
