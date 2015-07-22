package co.infinum.academy.danijel_sokac.boatit.Factories;

import android.content.Context;

import co.infinum.academy.danijel_sokac.boatit.Enum.InternetConnectionStatus;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.DetailsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.DetailsOfflineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.DetailsOnlineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.DetailsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.DetailsPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.DetailsView;

/**
 * Created by Danijel on 22.7.2015..
 */
public class MvpFactory {
    public static DetailsPresenter getPresenter(Context context, DetailsView view, Boat boat, InternetConnectionStatus status) {
        DetailsInteractor interactor;
        if (status == InternetConnectionStatus.CONNECTED) {
            interactor = new DetailsOnlineInteractor(context, boat);
        } else {
            interactor = new DetailsOfflineInteractor(context, boat);
        }
        return new DetailsPresenterImpl(view, interactor);
    }
}
