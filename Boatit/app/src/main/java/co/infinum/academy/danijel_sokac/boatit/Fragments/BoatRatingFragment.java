package co.infinum.academy.danijel_sokac.boatit.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.academy.danijel_sokac.boatit.Activities.BaseActivity;
import co.infinum.academy.danijel_sokac.boatit.Enum.InternetConnectionStatus;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Factories.MvpFactory;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.BoatSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatRatingPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatRatingView;

/**
 * Created by Danijel on 23.7.2015..
 */
public class BoatRatingFragment extends Fragment implements BoatRatingView{
    @Bind(R.id.upboat)
    Button upboat;

    @Bind(R.id.downboat)
    Button downboat;

    BoatRatingPresenter presenter;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rating_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter = MvpFactory.getPresenter(getActivity(), this,
                BoatSingleton.InstanceOfSessionSingleton().getBoat(), InternetConnectionStatus.CONNECTED);
    }

    @OnClick(R.id.upboat)
    public void onUpboatClicked(View v) {
        presenter.onUpboatClicked();
    }

    @OnClick(R.id.downboat)
    public void onDownboatClicked(View v) {
        presenter.onDownboatClicked();
    }

    @Override
    public void onRatingFinished(Boat boat) {
        Toast.makeText(getActivity(), "New score: " + boat.getScore(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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

    }
}
