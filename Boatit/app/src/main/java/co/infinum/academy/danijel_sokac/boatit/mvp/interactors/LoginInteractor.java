package co.infinum.academy.danijel_sokac.boatit.mvp.interactors;

import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.LoginListener;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface LoginInteractor {
    public void login(LoginListener listener, String username, String password);
}
