package co.infinum.academy.danijel_sokac.boatit.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Factories.MvpFactory;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.dialogs.LoginDialog;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BaseView;

/**
 * Created by Danijel on 24.7.2015..
 */
public class BaseFragment extends Fragment implements BaseView {
    private ProgressDialog progressDialog;

    @Override
    public void showProgress() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog =
                    ProgressDialog.
                            show(getActivity(), getString(R.string.app_name),
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
        Toast.makeText(getActivity(), getString(error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(ErrorsEnum error) {
        showError(error.getId());
    }

    @Override
    public void onTokenExpired() {
        MvpFactory.getTokenExpiredPresenter(getActivity(), this).tokenExpired();
    }

    @Override
    public void tokenExpired() {
        Dialog loginDialog = new LoginDialog(getActivity());
        loginDialog.setTitle(R.string.login_btn);
        loginDialog.setContentView(R.layout.login_dialog);
        loginDialog.show();
    }
}
