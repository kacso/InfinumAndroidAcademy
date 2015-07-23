package co.infinum.academy.danijel_sokac.boatit.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.academy.danijel_sokac.boatit.Adapters.BoatsAdapter;
import co.infinum.academy.danijel_sokac.boatit.Database.BoatDatabaseElement;
import co.infinum.academy.danijel_sokac.boatit.Database.Boatit;
import co.infinum.academy.danijel_sokac.boatit.Database.BoatitDatabase;
import co.infinum.academy.danijel_sokac.boatit.Database.DBFlowBoatit;
import co.infinum.academy.danijel_sokac.boatit.Database.DatabaseExecutor;
import co.infinum.academy.danijel_sokac.boatit.Enum.InternetConnectionStatus;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorTypeEnum;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Factories.MvpFactory;
import co.infinum.academy.danijel_sokac.boatit.Models.AllBoats;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.BoatSingleton;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatsView;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class BoatsActivity extends BaseActivity implements AdapterView.OnItemClickListener, BoatsView,
        SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.boats_list)
    ListView boatsList;

    @Bind(R.id.boats_list_swipe)
    SwipeRefreshLayout boatsSwipe;

    BoatsAdapter boatsAdapter;

    List<Boat> boats;

    BoatsPresenter presenter;

    int page = 1, perPage = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boats);
        ButterKnife.bind(this);
        boatsSwipe.setOnRefreshListener(this);
        presenter = MvpFactory.getPresenter(this, this, InternetConnectionStatus.CONNECTED);
        presenter.getBoats(page, perPage);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onBoatClicked(boats.get(position));
    }

    @Override
    public void onBoatsListReceived(List<Boat> boats) {
        this.boats = boats;
        boatsAdapter = new BoatsAdapter(BoatsActivity.this, boats);
        boatsList.setAdapter(boatsAdapter);
        boatsList.setOnItemClickListener(BoatsActivity.this);
    }

    @Override
    public void onEmptyBoatsListReceived() {

    }

    @Override
    public void boatClicked(Boat boat) {
        BoatSingleton.InstanceOfSessionSingleton().setBoat(boat);
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void getAllBoatsError(ErrorsEnum error) {
        onError(error);
        if (error.getType() == ErrorTypeEnum.INTERNET_ERROR) {
            BoatsPresenter presenter =
                    MvpFactory.getPresenter(this, this, InternetConnectionStatus.DISCONNECTED);
            presenter.getBoats(page, perPage);
        }
    }

    @Override
    public void boatClickedError(ErrorsEnum error) {
        onError(error);
    }

    @Override
    public void showProgress() {
        boatsSwipe.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        boatsSwipe.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        presenter.getBoats(page, perPage);
    }
}
