package co.infinum.academy.danijel_sokac.boatit.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.academy.danijel_sokac.boatit.Fragments.RegisterFragment;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.LoginData;
import co.infinum.academy.danijel_sokac.boatit.Models.LoginResponse;
import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedString;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class LoginActivity extends FragmentActivity{
    @Bind(R.id.username)
    EditText username;

    @Bind(R.id.password)
    EditText password;

    @Bind(R.id.login_btn)
    Button loginBtn;

    @Bind(R.id.register_btn)
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.login_btn)
    public void login(View v) {
        JSONObject loginData = new JSONObject();
        try {
            loginData.put("email", username.getText().toString());
            loginData.put("password", password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        final TypedString loginData = new TypedString("{\"email\":\"" + username.getText().toString()
//                + "\", \"password\": \"" + password.getText().toString() + "\"}");
//        final TypedString loginData = new TypedString("{\n\"email\": \"admin@infinum.co\",\n\"password\": \"infinum1\"\n}");
        ApiManager.postSERVICE().login(new TypedString(loginData.toString()), new retrofit.Callback<LoginResponse>() {
            @Override
            public void success(LoginResponse login, Response response) {
//                Toast.makeText(LoginActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                String token = login.getResponse().getToken();
                SessionSingleton.InstanceOfSessionSingleton().setToken(LoginActivity.this, token);
                Intent intent = new Intent(LoginActivity.this, BoatsActivity.class);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(LoginActivity.this, "LoginData failure: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.register_btn)
    public void onRegisterClicked(View v) {
        RegisterFragment registerFragment = new RegisterFragment();

        Bundle args = new Bundle();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.login_content, registerFragment);

        ft.addToBackStack(null);
        ft.commit();
    }
}
