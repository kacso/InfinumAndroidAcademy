package co.infinum.academy.danijel_sokac.boatit.Singletons;

/**
 * Created by Danijel on 16.7.2015..
 */
public class SessionSingleton {

    private String token;
    private static SessionSingleton sessionSingleton = null;

    private SessionSingleton() {
    }

    public static SessionSingleton InstanceOfSessionSingleton() {
        if (sessionSingleton == null) {
            sessionSingleton = new SessionSingleton();
        }
        return sessionSingleton;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
