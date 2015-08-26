package co.infinum.academy.danijel_sokac.boatit.mvp.interactors;

import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.RegisterListener;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface RegisterInteractor {
    public void register(RegisterListener listener, String email, String password,
                         String confirmPassword, String firstName, String lastName);
}
