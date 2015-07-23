package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Activities.BoatsActivity;
import co.infinum.academy.danijel_sokac.boatit.Adapters.BoatsAdapter;
import co.infinum.academy.danijel_sokac.boatit.BoatitApplication;
import co.infinum.academy.danijel_sokac.boatit.Database.Boatit;
import co.infinum.academy.danijel_sokac.boatit.Database.DBFlowBoatit;
import co.infinum.academy.danijel_sokac.boatit.Database.DatabaseExecutor;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.InternetConnectionErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.AllBoats;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatsListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.CommentsListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Danijel on 23.7.2015..
 */
public class BoatsOnlineInteractor implements BoatsInteractor {
    private Boat boat;
    private Context context;

    private BoatsListener listener;

    private List<Boat> boats;


    Boatit db;

    public BoatsOnlineInteractor(Context context) {
        this.context = context;
        db = new DBFlowBoatit(context);
    }


    @Override
    public void getBoats(BoatsListener listener, int page, int perPage) {
        this.listener = listener;
        BoatitApplication.getApiService()
                .getBoats(SessionSingleton.InstanceOfSessionSingleton().getToken(context),
                        page, perPage, allBoatsCallback);
    }

    Callback<AllBoats> allBoatsCallback = new Callback<AllBoats>() {
        @Override
        public void success(AllBoats allBoats, Response response) {
            boats = allBoats.getBoatList();
            listener.onBoatsListReceived(boats);

            DatabaseExecutor dbExecutor = new DatabaseExecutor(context, db);
            dbExecutor
                    .storeBoat(boats,
                            SessionSingleton.InstanceOfSessionSingleton().getToken(context));

        }

        @Override
        public void failure(RetrofitError error) {
            listener.getAllBoatsError(InternetConnectionErrorsEnum.BOAT_DOWNLOAD_ERROR);
        }
    };

    @Override
    public void onBoatClicked(BoatsListener listener, Boat boat) {
        listener.boatClicked(boat);
    }
}
