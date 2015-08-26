package co.infinum.academy.danijel_sokac.boatit.mvp.interactors.impl;

import android.content.Context;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Database.Boatit;
import co.infinum.academy.danijel_sokac.boatit.Database.DBFlowBoatit;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.interactors.BoatsInteractor;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.BoatsListener;

/**
 * Created by Danijel on 23.7.2015..
 */
public class BoatsOfflineInteractor implements BoatsInteractor {
    private Boat boat;
    private Context context;

    private BoatsListener listener;

    private List<Boat> boats;

    private Boatit db;

    public BoatsOfflineInteractor(Context context) {
        this.context = context;
        db = new DBFlowBoatit(context);
    }


    @Override
    public void getBoats(BoatsListener listener, int page, int perPage) {
        boats = db.getBoats(SessionSingleton.InstanceOfSessionSingleton().getToken(context)).getBoatList();
        listener.onBoatsListReceived(boats);
    }

    @Override
    public void onBoatClicked(BoatsListener listener, Boat boat) {
        listener.boatClicked(boat);
    }
}
