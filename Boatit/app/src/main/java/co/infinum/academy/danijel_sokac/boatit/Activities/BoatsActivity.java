package co.infinum.academy.danijel_sokac.boatit.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.academy.danijel_sokac.boatit.Adapters.BoatsAdapter;
import co.infinum.academy.danijel_sokac.boatit.Models.AllBoats;
import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.BoatSingleton;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BoatsActivity extends Activity implements AdapterView.OnItemClickListener {
    @Bind(R.id.boats_list)
    ListView boatsList;

    BoatsAdapter boatsAdapter;

    AllBoats boats;

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getBoats() {
        ApiManager.getSERVICE().getBoats(SessionSingleton.InstanceOfSessionSingleton().getToken(),
                1, 100, new retrofit.Callback<AllBoats>() {
                    @Override
                    public void success(AllBoats allBoats, Response response) {
                        Toast.makeText(BoatsActivity.this, "Got responses: " + response, Toast.LENGTH_SHORT).show();
                        boats = allBoats;
                        boatsAdapter = new BoatsAdapter(BoatsActivity.this, allBoats.getBoatList());
                        boatsList.setAdapter(boatsAdapter);
                        boatsList.setOnItemClickListener(BoatsActivity.this);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(BoatsActivity.this, "Get all boats error: " + error, Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BoatSingleton.InstanceOfSessionSingleton().setBoat(boats.getBoatList().get(position));
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}
