package co.infinum.academy.danijel_sokac.boatit.dialogs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.raizlabs.android.dbflow.annotation.PrimaryKey;

import co.infinum.academy.danijel_sokac.boatit.Activities.BoatsActivity;
import co.infinum.academy.danijel_sokac.boatit.Activities.LoginActivity;
import co.infinum.academy.danijel_sokac.boatit.Enum.InternetConnectionStatus;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Factories.MvpFactory;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.LoginPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.NewCommentActionsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.LoginView;

/**
 * Created by Danijel on 24.7.2015..
 */
public class LoginDialog extends Dialog implements LoginView {
    private Context context;
    private ProgressDialog progressDialog;

    private Button registerBtn;

    private Button loginBtn;

    private EditText username;

    private EditText password;

    LoginPresenter presenter;

    public LoginDialog(Context context) {
        super(context);
        this.context = context;
        presenter = MvpFactory.getPresenter(context, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBtn = (Button)findViewById(R.id.login_dialog_btn);
        registerBtn = (Button)findViewById(R.id.register_dialog_btn);
        username = (EditText)findViewById(R.id.dialog_username);
        password = (EditText)findViewById(R.id.dialog_password);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(username.getText().toString(),
                        password.getText().toString());
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(context, BoatsActivity.class);
        context.startActivity(intent);
        dismiss();
    }

    @Override
    public void onLoginFailed(ErrorsEnum error) {
        onError(error);
    }

    @Override
    public void showProgress() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog =
                    ProgressDialog.
                            show(context, context.getString(R.string.app_name),
                                    context.getString(R.string.progress_wait), true, false);
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showError(@StringRes int error) {
        Toast.makeText(context, context.getString(error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(ErrorsEnum error) {
        showError(error.getId());
    }

    @Override
    public void onTokenExpired() {

    }

    @Override
    public void tokenExpired() {

    }
}
