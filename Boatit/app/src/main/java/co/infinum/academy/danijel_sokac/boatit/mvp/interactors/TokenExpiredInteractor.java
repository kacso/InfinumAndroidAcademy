package co.infinum.academy.danijel_sokac.boatit.mvp.interactors;

import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.TokenExpiredListener;

/**
 * Created by Danijel on 24.7.2015..
 */
public interface TokenExpiredInteractor {
    public void tokenExpired(TokenExpiredListener listener);
}
