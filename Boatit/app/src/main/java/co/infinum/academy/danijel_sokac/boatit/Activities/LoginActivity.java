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
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Factories.MvpFactory;
import co.infinum.academy.danijel_sokac.boatit.Fragments.RegisterFragment;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.LoginData;
import co.infinum.academy.danijel_sokac.boatit.Models.LoginResponse;
import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.LoginPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.LoginView;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedString;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class LoginActivity extends BaseActivity implements LoginView {
    @Bind(R.id.username)
    EditText username;

    @Bind(R.id.password)
    EditText password;

    @Bind(R.id.login_btn)
    Button loginBtn;

    @Bind(R.id.register_btn)
    Button registerBtn;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = MvpFactory.getPresenter(this, this);
    }

    @OnClick(R.id.login_btn)
    public void login(View v) {
        presenter.login(username.getText().toString(), password.getText().toString());
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

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(this, BoatsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailed(ErrorsEnum error) {
        onError(error);
    }
}
