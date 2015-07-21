package co.infinum.academy.danijel_sokac.boatit.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
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
import co.infinum.academy.danijel_sokac.boatit.Models.AllBoats;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.BoatSingleton;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class BoatsActivity extends Activity implements AdapterView.OnItemClickListener {
    @Bind(R.id.boats_list)
    ListView boatsList;

    BoatsAdapter boatsAdapter;

    AllBoats boats;
    Boatit db = new DBFlowBoatit(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boats);
        ButterKnife.bind(this);
        getBoats();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_boats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void getBoats() {
        ApiManager.getSERVICE().getBoats(SessionSingleton.InstanceOfSessionSingleton().getToken(),
                1, 100, new retrofit.Callback<AllBoats>() {
                    @Override
                    public void success(AllBoats allBoats, Response response) {
//                        Toast.makeText(BoatsActivity.this, "Got responses: " + response, Toast.LENGTH_SHORT).show();
                        boats = allBoats;
                        boatsAdapter = new BoatsAdapter(BoatsActivity.this, allBoats.getBoatList());
                        boatsList.setAdapter(boatsAdapter);
                        boatsList.setOnItemClickListener(BoatsActivity.this);

                        storeToDatabase(allBoats, SessionSingleton.InstanceOfSessionSingleton().getToken());

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(BoatsActivity.this, "Get all boats error: " + error, Toast.LENGTH_LONG).show();

                        boats = db.getBoats(SessionSingleton.InstanceOfSessionSingleton().getToken());
                        boatsAdapter = new BoatsAdapter(BoatsActivity.this, boats.getBoatList());
                        boatsList.setAdapter(boatsAdapter);
                        boatsList.setOnItemClickListener(BoatsActivity.this);
                    }
                });
    }

//    private void storeToDatabase(AllBoats allBoats) {
//        BoatDatabaseElement dbElement = new BoatDatabaseElement();
//
//        try {
//            String dbBoats = ApiManager.GSON.toJson(allBoats);
//
//            dbElement.setToken(SessionSingleton.InstanceOfSessionSingleton().getToken());
//            dbElement.setBoats(dbBoats);
//            db.addBoat(dbElement);
//        } catch (SQLiteConstraintException e) {
//            e.printStackTrace();
//            try {
//                db.updateBoat(dbElement);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void storeToDatabase(AllBoats allBoats, String token) {
        db.deleteBoats();

        for (Boat boat : allBoats.getBoatList()) {
            try {
                boat.setToken(token);
                db.addBoat(boat);

                for (Comment comment : boat.getComments()) {
                    comment.setBoatId(boat.getId());
                    try {
                        db.addComment(comment);
                    } catch (SQLiteConstraintException e) {
                        e.printStackTrace();
                        db.updateComment(comment);
                    }
                }

            } catch (SQLiteConstraintException ex) {
                ex.printStackTrace();
                db.updateBoat(boat);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error while saving to db", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BoatSingleton.InstanceOfSessionSingleton().setBoat(boats.getBoatList().get(position));
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}
