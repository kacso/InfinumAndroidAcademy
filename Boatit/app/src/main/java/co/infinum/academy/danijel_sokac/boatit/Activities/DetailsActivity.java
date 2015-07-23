package co.infinum.academy.danijel_sokac.boatit.Activities;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.academy.danijel_sokac.boatit.Adapters.CommentAdapter;
import co.infinum.academy.danijel_sokac.boatit.Enum.InternetConnectionStatus;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorTypeEnum;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Factories.MvpFactory;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.Models.NewComment;
import co.infinum.academy.danijel_sokac.boatit.Models.NewCommentContent;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.BoatSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatDetailsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatImagePresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.BoatRatingPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.CommentsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.NewCommentActionsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.NewCommentPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatDetailsView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatImageView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.BoatRatingView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.CommentsView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.NewCommentActionsView;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.NewCommentView;

public class DetailsActivity extends BaseActivity implements BoatDetailsView, BoatImageView,
        BoatRatingView, CommentsView, NewCommentView, NewCommentActionsView, SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.upboat)
    Button upboat;

    @Bind(R.id.downboat)
    Button downboat;

    @Bind(R.id.details_image)
    ImageView boatImage;

    @Bind(R.id.comment_list_view)
    ListView commentList;

    @Bind(R.id.comment_swipe)
    SwipeRefreshLayout commentSwipe;

    private CommentAdapter adapter;

    private BoatImagePresenter boatImagePresenter;
    private BoatDetailsPresenter boatDetailsPresenter;
    private BoatRatingPresenter boatRatingPresenter;
    private CommentsPresenter commentsPresenter;
    private NewCommentActionsPresenter newCommentActionsPresenter;
    private NewCommentPresenter newCommentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        commentSwipe.setOnRefreshListener(this);

        initPresenters(BoatSingleton.InstanceOfSessionSingleton().getBoat());


        boatImagePresenter.getBoatImage();
        boatDetailsPresenter.getBoatTitle();
        commentsPresenter.getComments();
    }

    private void initPresenters(Boat boat) {
        boatImagePresenter = MvpFactory.getBoatImagePresenter(
                this, this,
                boat,
                InternetConnectionStatus.CONNECTED);

        boatDetailsPresenter = MvpFactory.getBoatDetailsPresenter(
                this, this,
                boat,
                InternetConnectionStatus.CONNECTED);

        boatRatingPresenter = MvpFactory.getBoatRatingPresenter(
                this, this,
                boat,
                InternetConnectionStatus.CONNECTED);

        commentsPresenter = MvpFactory.getCommentsPresenter(
                this, this,
                boat,
                InternetConnectionStatus.CONNECTED);

        newCommentPresenter = MvpFactory.getNewCommentPresenter(
                this, this,
                boat,
                InternetConnectionStatus.CONNECTED);

        newCommentActionsPresenter = MvpFactory.getNewCommentActionsPresenter(
                this, this,
                boat,
                InternetConnectionStatus.CONNECTED);
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
        if (id == R.id.new_comment) {
            onNewCommentClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.upboat)
    public void onUpboatClicked(View v) {
        boatRatingPresenter.onUpboatClicked();
    }

    @OnClick(R.id.downboat)
    public void onDownboatClicked(View v) {
        boatRatingPresenter.onDownboatClicked();
    }

    public void onNewCommentClicked() {
        newCommentPresenter.onNewCommentClicked();
    }

    @Override
    public void onCommentListReceived(List<Comment> comments) {
//        commentSwipe.setRefreshing(false);
        adapter = new CommentAdapter(this, comments);
        commentList.setAdapter(adapter);
    }

    @Override
    public void onCommentListEmpty() {

    }

    @Override
    public void onBoatImageReceived(Bitmap image) {
        boatImage.setImageBitmap(image);
    }

    @Override
    public void onTokenExpired() {

    }


    @Override
    public void onBoatTitleReceived(String title) {
        getActionBar().setTitle(title);
    }

    @Override
    public void onRatingFinished(Boat boat) {
        Toast.makeText(this, "New score: " + boat.getScore(), Toast.LENGTH_SHORT).show();
    }

    Dialog newCommentDialog;

    @Nullable @Bind(R.id.send_new_comment)
    Button sendNewComment;

    @Nullable @Bind(R.id.cancel_new_comment)
    Button cancelNewComment;

    @Nullable @Bind(R.id.new_comment_text)
    EditText newCommentContent;

    @Override
    public void displayNewCommentView() {
        newCommentDialog = new Dialog(this);
        newCommentDialog.setTitle(R.string.new_comment_dialog_title);
        newCommentDialog.setContentView(R.layout.new_comment_dialog);
        newCommentDialog.show();

        cancelNewComment = (Button)newCommentDialog.findViewById(R.id.cancel_new_comment);
        sendNewComment = (Button)newCommentDialog.findViewById(R.id.send_new_comment);
        newCommentContent = (EditText)newCommentDialog.findViewById(R.id.new_comment_text);

//        ButterKnife.bind(this);
        sendNewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendNewCommentClicked(v);
            }
        });

        cancelNewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelNewCommentClicked(v);
            }
        });
    }

    @Nullable @OnClick(R.id.send_new_comment)
    public void onSendNewCommentClicked(View v) {
        NewCommentContent content = new NewCommentContent();
        content.setContent(newCommentContent.getText().toString());
        NewComment newComment = new NewComment();
        newComment.setComment(content);

        newCommentActionsPresenter.onSendNewCommentClicked(newComment);
    }

    @Nullable @OnClick(R.id.cancel_new_comment)
    public void onCancelNewCommentClicked(View v) {
        newCommentActionsPresenter.onNewCommentCanceled();
    }

    @Override
    public void onNewCommentSent() {
        newCommentDialog.dismiss();
        commentsPresenter.getComments();
    }

    @Override
    public void onNewCommentCanceled() {
        newCommentDialog.dismiss();
    }


    @Override
    public void onBoatDetailsError(ErrorsEnum error) {
        onError(error);
        if (error.getType() == ErrorTypeEnum.INTERNET_ERROR) {
            BoatDetailsPresenter presenter = MvpFactory.getBoatDetailsPresenter(
                    this, this,
                    BoatSingleton.InstanceOfSessionSingleton().getBoat(),
                    InternetConnectionStatus.DISCONNECTED);
            presenter.getBoatTitle();
        }
    }

    @Override
    public void onBoatImageError(ErrorsEnum error) {
        onError(error);
        if (error.getType() == ErrorTypeEnum.INTERNET_ERROR) {
            BoatImagePresenter presenter = MvpFactory.getBoatImagePresenter(
                    this, this,
                    BoatSingleton.InstanceOfSessionSingleton().getBoat(),
                    InternetConnectionStatus.DISCONNECTED);
            presenter.getBoatImage();
        }
    }

    @Override
    public void onRatingError(ErrorsEnum error) {
        onError(error);
    }

    @Override
    public void onCommentsError(ErrorsEnum error) {
        onError(error);
        if (error.getType() == ErrorTypeEnum.INTERNET_ERROR) {
            CommentsPresenter presenter = MvpFactory.getCommentsPresenter(
                    this, this,
                    BoatSingleton.InstanceOfSessionSingleton().getBoat(),
                    InternetConnectionStatus.DISCONNECTED);
            presenter.getComments();
        }
    }

    @Override
    public void onNewCommentActionsError(ErrorsEnum error) {
        onError(error);
    }

    @Override
    public void onNewCommentError(ErrorsEnum error) {
        onError(error);
    }

    @Override
    public void onRefresh() {
        commentsPresenter.getComments();
    }


    @Override
    public void showProgress() {
        commentSwipe.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        commentSwipe.setRefreshing(false);
    }
}
