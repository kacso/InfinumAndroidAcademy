package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import co.infinum.academy.danijel_sokac.boatit.BoatitApplication;
import co.infinum.academy.danijel_sokac.boatit.Database.Boatit;
import co.infinum.academy.danijel_sokac.boatit.Database.DBFlowBoatit;
import co.infinum.academy.danijel_sokac.boatit.Database.DatabaseExecutor;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.TokenExpiredInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.TokenExpiredListener;

/**
 * Created by Danijel on 24.7.2015..
 */
public class TokenExpiredInteractorImpl implements TokenExpiredInteractor {

    @Override
    public void tokenExpired(TokenExpiredListener listener) {
        SessionSingleton.InstanceOfSessionSingleton().setToken(BoatitApplication.getInstance(), null);
        Boatit db = new DBFlowBoatit(BoatitApplication.getInstance());
        new DatabaseExecutor(BoatitApplication.getInstance(), db).clearDatabase();
        listener.tokenExpired();
    }
}
