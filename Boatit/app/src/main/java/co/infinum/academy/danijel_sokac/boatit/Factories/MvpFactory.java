package co.infinum.academy.danijel_sokac.boatit.Factories;

import android.content.Context;

import co.infinum.academy.danijel_sokac.boatit.Enum.InternetConnectionStatus;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatDetailsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatImageInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatRatingInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.CommentsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.NewCommentActionsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatDetailsOfflineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatDetailsOnlineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatImageOfflineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatImageOnlineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatRatingOfflineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatRatingOnlineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatsOfflineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.BoatsOnlineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.CommentsOfflineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.CommentsOnlineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.LoginInteractorImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.NewCommentActionsOfflineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.NewCommentActionsOnlineInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.RegisterInteractorImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.TokenExpiredInteractorImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl.TokenInteractorImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatDetailsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatImagePresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatRatingPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.CommentsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.LoginPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.NewCommentActionsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.RegisterPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.SplashPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.TokenExpiredPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.BoatDetailsPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.BoatImagePresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.BoatRatingPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.BoatsPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.CommentsPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.LoginPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.NewCommentActionsPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.RegisterPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.SplashPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.impl.TokenExpiredPresenterImpl;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BaseView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatDetailsView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatImageView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatRatingView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatsView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.CommentsView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.LoginView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.NewCommentActionsView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.RegisterView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.SplashView;

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

    public static BoatsPresenter getPresenter
            (Context context, BoatsView view, InternetConnectionStatus status) {
        BoatsInteractor interactor;
        if (status == InternetConnectionStatus.CONNECTED) {
            interactor = new BoatsOnlineInteractor(context);
        } else {
            interactor = new BoatsOfflineInteractor(context);
        }
        return new BoatsPresenterImpl(view, interactor);
    }

    public static SplashPresenter getPresenter(Context context, SplashView view) {
        return new SplashPresenterImpl(view, new TokenInteractorImpl(context));
    }

    public static LoginPresenter getPresenter(Context context, LoginView view) {
        return new LoginPresenterImpl(view, new LoginInteractorImpl());
    }

    public static RegisterPresenter getPresenter(Context context, RegisterView view) {
        return new RegisterPresenterImpl(view, new RegisterInteractorImpl());
    }

    public static TokenExpiredPresenter getTokenExpiredPresenter(Context context, BaseView view) {
        return new TokenExpiredPresenterImpl(view, new TokenExpiredInteractorImpl());
    }
}
