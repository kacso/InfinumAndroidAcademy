package co.infinum.academy.danijel_sokac.boatit.mvp.views;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface RegisterView extends BaseView {
    public void onRegisterSuccess();

    public void onRegisterFailed(ErrorsEnum error);
}
