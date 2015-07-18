package co.infinum.academy.danijel_sokac.boatit.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.academy.danijel_sokac.boatit.Activities.BoatsActivity;
import co.infinum.academy.danijel_sokac.boatit.Models.LoginData;
import co.infinum.academy.danijel_sokac.boatit.Models.LoginResponse;
import co.infinum.academy.danijel_sokac.boatit.Models.RegisterData;
import co.infinum.academy.danijel_sokac.boatit.Models.User;
import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedString;

/**
 * Created by Danijel on 18.7.2015..
 */
public class RegisterFragment extends Fragment{
    @Bind(R.id.register_email)
    EditText email;

    @Bind(R.id.register_password)
    EditText password;

    @Bind(R.id.register_confirm_password)
    EditText confirmPassword;

    @Bind(R.id.register_first_name)
    EditText firstName;

    @Bind(R.id.register_last_name)
    EditText lastName;

    @Bind(R.id.register)
    Button register;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    @OnClick(R.id.register)
    public void onRegisterClicked(View v) {
        String pass = password.getText().toString();
        String confirmPass = confirmPassword.getText().toString();
        if (pass.equals(confirmPass)) {
            User user = new User();
            user.setLastName(lastName.getText().toString());
            user.setEmail(email.getText().toString());
            user.setFirstName(firstName.getText().toString());
            user.setPassword(pass);
            user.setPasswordConfirmation(confirmPass);

            String registerData = new Gson().toJson(new RegisterData(user));
//            JSONObject registerData = new JSONObject();
//            try {
//                registerData.put("user", user);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }


            ApiManager.postSERVICE().register(new TypedString(registerData), new retrofit.Callback<LoginResponse>(){

                @Override
                public void success(LoginResponse loginResponse, Response response) {
                    String token = loginResponse.getResponse().getToken();
                    SessionSingleton.InstanceOfSessionSingleton().setToken(token);
                    Intent intent = new Intent(getActivity(), BoatsActivity.class);
                    startActivity(intent);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getActivity(), "Registration failure: " + error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
