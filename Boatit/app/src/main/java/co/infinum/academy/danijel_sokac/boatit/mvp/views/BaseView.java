package co.infinum.academy.danijel_sokac.boatit.mvp.views;

import android.support.annotation.StringRes;

/**
 * Created by Danijel on 22.7.2015..
 */
public interface BaseView {
    public void showProgress();

    public void hideProgress();

    public void showError(@StringRes int error);
}
