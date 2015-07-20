package co.infinum.academy.danijel_sokac.boatit.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.academy.danijel_sokac.boatit.Adapters.CommentAdapter;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.RateBoat;
import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.BoatSingleton;
import co.infinum.academy.danijel_sokac.boatit.Singletons.SessionSingleton;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetailsActivity extends Activity {
    @Bind(R.id.upboat)
    Button upboat;

    @Bind(R.id.downboat)
    Button downboat;

    @Bind(R.id.details_image)
    ImageView boatImage;

    @Bind(R.id.comment_list_view)
    ListView commentList;

    CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        displayBoat(BoatSingleton.InstanceOfSessionSingleton().getBoat());
//        Glide.with(DetailsActivity.this).load(
//                BoatSingleton.InstanceOfSessionSingleton().getBoat()
//                        .getImageURL()).into(boatImage);
//
//        adapter = new CommentAdapter(this,
//                BoatSingleton.InstanceOfSessionSingleton().getBoat().getComments());
//        commentList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
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

    @OnClick(R.id.upboat)
    public void onUpboatClicked(View v) {
        ApiManager.getSERVICE().getUpboat(BoatSingleton.InstanceOfSessionSingleton().getBoat().getId(),
                SessionSingleton.InstanceOfSessionSingleton().getToken(),
                new retrofit.Callback<RateBoat>() {

                    @Override
                    public void success(RateBoat rateBoat, Response response) {
//                            displayBoat(rateBoat.getBoat());
                        Toast.makeText(DetailsActivity.this,
                                "New score: " + rateBoat.getBoat().getScore(),
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(DetailsActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick(R.id.downboat)
    public void onDownboatClicked(View v) {
        ApiManager.getSERVICE().getDownboat(BoatSingleton.InstanceOfSessionSingleton().getBoat().getId(),
                SessionSingleton.InstanceOfSessionSingleton().getToken(),
                new retrofit.Callback<RateBoat>() {

                    @Override
                    public void success(RateBoat rateBoat, Response response) {
                        Toast.makeText(DetailsActivity.this,
                                "New score: " + rateBoat.getBoat().getScore(),
                                Toast.LENGTH_SHORT).show();
//                        displayBoat(rateBoat.getBoat());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(DetailsActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void displayBoat(Boat boat) {
        Glide.with(DetailsActivity.this).load(boat
                .getImageURL()).into(boatImage);

        adapter = new CommentAdapter(DetailsActivity.this,
                boat.getComments());
        commentList.setAdapter(adapter);
        getActionBar().setTitle(boat.getTitle());
    }
}
