package co.infinum.academy.danijel_sokac.boatit.mvp.presenters;

/**
 * Created by Danijel on 23.7.2015..
 */
public interface RegisterPresenter {
    public void register(String username, String password, String confirmPassword,
                         String firstName, String lastName);
}
