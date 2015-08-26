package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;

import co.infinum.academy.danijel_sokac.boatit.Activities.BoatsActivity;
import co.infinum.academy.danijel_sokac.boatit.BoatitApplication;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.OtherErrorEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.LoginResponse;
import co.infinum.academy.danijel_sokac.boatit.Models.RegisterData;
import co.infinum.academy.danijel_sokac.boatit.Models.User;
import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.RegisterInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.RegisterListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedString;

/**
 * Created by Danijel on 24.7.2015..
 */
public class RegisterInteractorImpl implements RegisterInteractor {
    RegisterListener listener;

    @Override
    public void register(RegisterListener listener, String email,String password,
                         String confirmPassword, String firstName, String lastName) {
        this.listener = listener;

        if (password.equals(confirmPassword)) {
            User user = new User();
            user.setLastName(lastName);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setPassword(password);
            user.setPasswordConfirmation(confirmPassword);

            String registerData = new Gson().toJson(new RegisterData(user));

            BoatitApplication.getApiService().register(new TypedString(registerData), registerCallback);
        } else {
            listener.onRegisterFailed(OtherErrorEnum.REGISTER_PASSWORD_NOT_MATCHED);
        }
    }

    private Callback<LoginResponse> registerCallback = new Callback<LoginResponse>() {
        @Override
        public void success(LoginResponse loginResponse, Response response) {
            String token = loginResponse.getResponse().getToken();
            SessionSingleton.InstanceOfSessionSingleton().setToken(BoatitApplication.getInstance(), token);
            listener.onRegisterSuccess(token);
//            Intent intent = new Intent(getActivity(), BoatsActivity.class);
//            startActivity(intent);
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onRegisterFailed(OtherErrorEnum.REGISTER_FAILURE);
        }
    };
}
