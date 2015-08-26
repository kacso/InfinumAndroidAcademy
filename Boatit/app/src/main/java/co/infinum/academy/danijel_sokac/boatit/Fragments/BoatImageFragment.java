package co.infinum.academy.danijel_sokac.boatit.Fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.academy.danijel_sokac.boatit.Enum.InternetConnectionStatus;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorTypeEnum;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Factories.MvpFactory;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.BoatSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatImagePresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatImageView;

/**
 * Created by Danijel on 23.7.2015..
 */
public class BoatImageFragment extends BaseFragment implements BoatImageView {
    @Bind(R.id.details_image)
    ImageView boatImage;


    @Bind(R.id.image_swipe)
    SwipeRefreshLayout imageSwipe;

    BoatImagePresenter presenter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.boat_image_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter = MvpFactory.getPresenter(getActivity(), this,
                BoatSingleton.InstanceOfSessionSingleton().getBoat(), InternetConnectionStatus.CONNECTED);
        presenter.getBoatImage();
    }

    @Override
    public void onBoatImageReceived(Bitmap image) {
        boatImage.setImageBitmap(image);
    }

    @Override
    public void showProgress() {
        imageSwipe.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        imageSwipe.setRefreshing(false);
    }

    @Override
    public void onError(ErrorsEnum error) {
        showError(error.getId());
        if (error.getType() == ErrorTypeEnum.INTERNET_ERROR) {
            BoatImagePresenter presenter = MvpFactory.getPresenter(
                    getActivity(), this,
                    BoatSingleton.InstanceOfSessionSingleton().getBoat(),
                    InternetConnectionStatus.DISCONNECTED);
            presenter.getBoatImage();
        }
    }

}
