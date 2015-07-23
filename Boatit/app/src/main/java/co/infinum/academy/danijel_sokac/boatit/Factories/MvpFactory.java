package co.infinum.academy.danijel_sokac.boatit.Factories;

import android.content.Context;

import co.infinum.academy.danijel_sokac.boatit.Enum.InternetConnectionStatus;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatDetailsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatImageInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatRatingInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.CommentsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.NewCommentActionsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.NewCommentInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatDetailsOfflineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatDetailsOnlineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatImageOfflineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatImageOnlineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatRatingOfflineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatRatingOnlineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.CommentsOfflineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.CommentsOnlineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.NewCommentActionsOfflineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.NewCommentActionsOnlineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.NewCommentOfflineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.NewCommentOnlineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatDetailsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatImagePresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatRatingPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.CommentsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.NewCommentActionsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.NewCommentPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.BoatDetailsPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.BoatImagePresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.BoatRatingPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.CommentsPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.NewCommentActionsPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.NewCommentPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatDetailsView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatImageView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatRatingView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.CommentsView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.NewCommentActionsView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.NewCommentView;

/**
 * Created by Danijel on 22.7.2015..
 */
public class MvpFactory {
    public static BoatDetailsPresenter getPresenter
            (Context context, BoatDetailsView view, Boat boat, InternetConnectionStatus status) {

        BoatDetailsInteractor interactor;
        if (status == InternetConnectionStatus.CONNECTED) {
            interactor = new BoatDetailsOnlineInteractor(context, boat);
        } else {
            interactor = new BoatDetailsOfflineInteractor(context, boat);
        }
        return new BoatDetailsPresenterImpl(view, interactor);
    }

    public static BoatImagePresenter getPresenter
            (Context context, BoatImageView view, Boat boat, InternetConnectionStatus status) {

        BoatImageInteractor interactor;
        if (status == InternetConnectionStatus.CONNECTED) {
            interactor = new BoatImageOnlineInteractor(context, boat);
        } else {
            interactor = new BoatImageOfflineInteractor(context, boat);
        }
        return new BoatImagePresenterImpl(view, interactor);
    }

    public static BoatRatingPresenter getPresenter
            (Context context, BoatRatingView view, Boat boat, InternetConnectionStatus status) {

        BoatRatingInteractor interactor;
        if (status == InternetConnectionStatus.CONNECTED) {
            interactor = new BoatRatingOnlineInteractor(context, boat);
        } else {
            interactor = new BoatRatingOfflineInteractor(context, boat);
        }
        return new BoatRatingPresenterImpl(view, interactor);
    }

    public static CommentsPresenter getPresenter
            (Context context, CommentsView view, Boat boat, InternetConnectionStatus status) {

        CommentsInteractor interactor;
        if (status == InternetConnectionStatus.CONNECTED) {
            interactor = new CommentsOnlineInteractor(context, boat);
        } else {
            interactor = new CommentsOfflineInteractor(context, boat);
        }
        return new CommentsPresenterImpl(view, interactor);
    }

    public static NewCommentActionsPresenter getPresenter
            (Context context, NewCommentActionsView view, Boat boat, InternetConnectionStatus status) {
        NewCommentActionsInteractor interactor;
        if (status == InternetConnectionStatus.CONNECTED) {
            interactor = new NewCommentActionsOnlineInteractor(context, boat);
        } else {
            interactor = new NewCommentActionsOfflineInteractor(context, boat);
        }
        return new NewCommentActionsPresenterImpl(view, interactor);
    }

    public static NewCommentPresenter getPresenter
            (Context context, NewCommentView view, Boat boat, InternetConnectionStatus status) {
        NewCommentInteractor interactor;
        if (status == InternetConnectionStatus.CONNECTED) {
            interactor = new NewCommentOnlineInteractor(context);
        } else {
            interactor = new NewCommentOfflineInteractor(context);
        }
        return new NewCommentPresenterImpl(view, interactor);
    }
}
