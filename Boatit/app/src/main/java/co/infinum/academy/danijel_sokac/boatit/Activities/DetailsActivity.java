package co.infinum.academy.danijel_sokac.boatit.Activities;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.DetailsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.DetailsView;

public class DetailsActivity extends BaseActivity implements DetailsView {
    @Bind(R.id.upboat)
    Button upboat;

    @Bind(R.id.downboat)
    Button downboat;

    @Bind(R.id.details_image)
    ImageView boatImage;

    @Bind(R.id.comment_list_view)
    ListView commentList;

    private CommentAdapter adapter;

    private DetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

//        boatId = BoatSingleton.InstanceOfSessionSingleton().getBoat().getId();

//        displayBoat(BoatSingleton.InstanceOfSessionSingleton().getBoat());

        presenter = MvpFactory.getPresenter(
                this, this,
                BoatSingleton.InstanceOfSessionSingleton().getBoat(),
                InternetConnectionStatus.CONNECTED);
        presenter.getBoatImage();
        presenter.getBoatTitle();
        presenter.getComments();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
//        saveItem = menu.findItem(R.id.save);
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
        presenter.onUpboatClicked();
    }

    @OnClick(R.id.downboat)
    public void onDownboatClicked(View v) {
        presenter.onDownboatClicked();
    }

    public void onNewCommentClicked() {
        presenter.onNewCommentClicked();
    }

    @Override
    public void onCommentListReceived(List<Comment> comments) {
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
    public void onError(ErrorsEnum error) {
        showError(error.getId());
        if (error.getType() == ErrorTypeEnum.INTERNET_ERROR) {
            DetailsPresenter presenter = MvpFactory.getPresenter(
                    this, this,
                    BoatSingleton.InstanceOfSessionSingleton().getBoat(),
                    InternetConnectionStatus.DISCONNECTED);
            presenter.getBoatImage();
            presenter.getBoatTitle();
            presenter.getComments();
        }
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
//        sendNewComment = (Button) findViewById(R.id.send_new_comment);
        sendNewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendNewCommentClicked(v);
            }
        });
//        cancelNewComment = (Button) findViewById(R.id.cancel_new_comment);
        cancelNewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelNewCommentClicked(v);
            }
        });
//        newCommentContent = (EditText) findViewById(R.id.new_comment_text);
    }

    @Nullable @OnClick(R.id.send_new_comment)
    public void onSendNewCommentClicked(View v) {
        NewCommentContent content = new NewCommentContent();
        content.setContent(newCommentContent.getText().toString());
        NewComment newComment = new NewComment();
        newComment.setComment(content);

        presenter.onSendNewCommentClicked(newComment);
    }

    @Nullable @OnClick(R.id.cancel_new_comment)
    public void onCancelNewCommentClicked(View v) {
        presenter.onNewCommentCanceled();
    }

    @Override
    public void onNewCommentSent() {
        presenter.getComments();
    }

    @Override
    public void onNewCommentCanceled() {
        newCommentDialog.dismiss();
    }
}
