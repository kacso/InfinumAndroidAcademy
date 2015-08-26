package co.infinum.academy.danijel_sokac.boatit.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import co.infinum.academy.danijel_sokac.boatit.Database.Boatit;
import co.infinum.academy.danijel_sokac.boatit.Database.BoatitDatabase;
import co.infinum.academy.danijel_sokac.boatit.Database.DBFlowBoatit;
import co.infinum.academy.danijel_sokac.boatit.Database.DatabaseExecutor;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Factories.MvpFactory;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.dialogs.LoginDialog;
import co.infinum.academy.danijel_sokac.boatit.dialogs.NewCommentDialog;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BaseView;

/**
 * Created by Danijel on 22.7.2015..
 */
public class BaseActivity extends FragmentActivity implements BaseView {
    private ProgressDialog progressDialog;

    @Override
    public void showProgress() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog =
                    ProgressDialog.
                            show(this, getString(R.string.app_name),
                                    getString(R.string.progress_wait), true, false);
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
//        Snackbar.make(findViewById(android.R.id.content),
//                getString(error), Snackbar.LENGTH_LONG).show();
        Toast.makeText(this, getString(error), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgress();
    }


    @Override
    public void onError(ErrorsEnum error) {
        showError(error.getId());
    }

    @Override
    public void onTokenExpired() {
        MvpFactory.getTokenExpiredPresenter(this, this).tokenExpired();
    }

    @Override
    public void tokenExpired() {
        Dialog loginDialog = new LoginDialog(this);
        loginDialog.setTitle(R.string.login_btn);
        loginDialog.setContentView(R.layout.login_dialog);
        loginDialog.show();
    }
}
