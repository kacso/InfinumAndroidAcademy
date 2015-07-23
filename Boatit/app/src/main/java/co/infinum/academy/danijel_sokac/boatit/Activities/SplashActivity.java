package co.infinum.academy.danijel_sokac.boatit.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import co.infinum.academy.danijel_sokac.boatit.Factories.MvpFactory;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.SplashPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.SplashView;

/**
 * Created by kjurkovic on 17/07/15.
 */
public class SplashActivity extends BaseActivity implements SplashView {

    private static final int SPLASH_DURATION = 1500; // millis

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupPresenter();
    }

    private void setupPresenter() {
        presenter = MvpFactory.getPresenter(this, this);
        presenter.checkUserExists();
    }

    @Override
    public void showBoatList() {
        startScreen(BoatsActivity.class);
    }

    @Override
    public void showLogin() {
        startScreen(LoginActivity.class);
    }

    private void startScreen(Class<? extends Activity> c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
        finish();
    }
}
