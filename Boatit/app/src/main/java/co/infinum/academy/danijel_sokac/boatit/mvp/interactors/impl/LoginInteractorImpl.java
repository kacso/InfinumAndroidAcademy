package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import co.infinum.academy.danijel_sokac.boatit.Activities.BoatsActivity;
import co.infinum.academy.danijel_sokac.boatit.BoatitApplication;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.OtherErrorEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.LoginResponse;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.LoginInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.LoginListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedString;

/**
 * Created by Danijel on 23.7.2015..
 */
public class LoginInteractorImpl implements LoginInteractor {
    LoginListener listener;

    @Override
    public void login(LoginListener listener, String username, String password) {
        this.listener = listener;
        JSONObject loginData = new JSONObject();
        try {
            loginData.put("email", username);
            loginData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        BoatitApplication.getApiService().login(new TypedString(loginData.toString()), loginCallback);
    }

    private Callback<LoginResponse> loginCallback = new Callback<LoginResponse>() {
        @Override
        public void success(LoginResponse loginResponse, Response response) {
            String token = loginResponse.getResponse().getToken();
            SessionSingleton.InstanceOfSessionSingleton()
                    .setToken(BoatitApplication.getInstance(), token);
            listener.onLoginSuccess(token);
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onLoginFailed(OtherErrorEnum.LOGIN_FAILURE);
        }
    };
}
