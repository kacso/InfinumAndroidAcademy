package co.infinum.academy.danijel_sokac.boatit.Activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import butterknife.ButterKnife;
import co.infinum.academy.danijel_sokac.boatit.Enum.InternetConnectionStatus;
import co.infinum.academy.danijel_sokac.boatit.Factories.MvpFactory;
import co.infinum.academy.danijel_sokac.boatit.Fragments.BoatImageFragment;
import co.infinum.academy.danijel_sokac.boatit.Fragments.BoatRatingFragment;
import co.infinum.academy.danijel_sokac.boatit.Fragments.CommentListFragment;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.BoatSingleton;
import co.infinum.academy.danijel_sokac.boatit.dialogs.NewCommentDialog;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatDetailsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatDetailsView;

public class DetailsActivity extends BaseActivity implements BoatDetailsView {
    private BoatDetailsPresenter boatDetailsPresenter;

    private CommentListFragment commentListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        initPresenters(BoatSingleton.InstanceOfSessionSingleton().getBoat());

        initFragments();

        boatDetailsPresenter.getBoatTitle();
    }

    private void initFragments() {
        initImageFragment();
        initRatingFragment();
        initCommentsFragment();
    }

    private void initImageFragment() {
        BoatImageFragment f = new BoatImageFragment();
        Bundle args = new Bundle();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.details_image_fragment, f);
        ft.attach(f);
        ft.commit();
    }

    private void initRatingFragment() {
        BoatRatingFragment f = new BoatRatingFragment();
        Bundle args = new Bundle();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.details_rating_fragment, f);
        ft.commit();
    }

    private void initCommentsFragment() {
        commentListFragment = new CommentListFragment();
        Bundle args = new Bundle();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.comment_list_fragment, commentListFragment);
        ft.commit();
    }

    private void initPresenters(Boat boat) {
        boatDetailsPresenter = MvpFactory.getPresenter(this, this,
                BoatSingleton.InstanceOfSessionSingleton().getBoat(), InternetConnectionStatus.CONNECTED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_details, menu);
//        return true;

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_comment) {
            onNewCommentClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onNewCommentClicked() {
        Dialog newCommentDialog = new NewCommentDialog(this, commentListFragment);
        newCommentDialog.setTitle(R.string.new_comment_dialog_title);
        newCommentDialog.setContentView(R.layout.new_comment_dialog);
        newCommentDialog.show();
    }


    @Override
    public void onBoatTitleReceived(String title) {
        getActionBar().setTitle(title);
    }

}
