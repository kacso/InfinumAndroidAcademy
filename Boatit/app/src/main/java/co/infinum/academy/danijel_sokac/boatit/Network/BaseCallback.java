package co.infinum.academy.danijel_sokac.boatit.Network;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Danijel on 24.7.2015..
 */
public abstract class BaseCallback<T> implements Callback<T> {
    private static final int STATUS_UNAUTHORIZED = 401;

    @Override
    public void failure(RetrofitError error) {
        if (error.getResponse().getStatus() == STATUS_UNAUTHORIZED) {
            onTokenExpired();
        } else {
            error(error);
        }
    }

    public abstract void onTokenExpired();

    public abstract void error(RetrofitError error);
}
