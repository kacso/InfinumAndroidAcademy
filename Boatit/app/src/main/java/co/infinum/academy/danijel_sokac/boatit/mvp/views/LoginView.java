package co.infinum.academy.danijel_sokac.boatit.mvp.views;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface LoginView extends BaseView {
    public void onLoginSuccess();

    public void onLoginFailed(ErrorsEnum error);
}
