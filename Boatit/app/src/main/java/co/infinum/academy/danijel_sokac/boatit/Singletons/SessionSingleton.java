package co.infinum.academy.danijel_sokac.boatit.Singletons;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Danijel on 16.7.2015..
 */
public class SessionSingleton {

    private static final String SHARED_PREFS_ID = "co.infinum.academy.danijel_sokac.boatit.";

    private static final String TOKEN = "token";


    private String token;
    private static SessionSingleton sessionSingleton = null;

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFS_ID, Context.MODE_PRIVATE);
    }


    private SessionSingleton() {
    }

    public static SessionSingleton InstanceOfSessionSingleton() {
        if (sessionSingleton == null) {
            sessionSingleton = new SessionSingleton();
        }
        return sessionSingleton;
    }

    public String getToken(Context context) {
        if (token == null) {
            SharedPreferences prefs = getPreferences(context);
            token = prefs.getString(TOKEN, null);
        }
        return token;
    }

    public void setToken(Context context, String token) {
//        if (this.token != null && this.token.equals(token))
//            return;
        this.token = token;
        SharedPreferences prefs = getPreferences(context);
        prefs.edit().putString(TOKEN, token).apply();
    }
}
