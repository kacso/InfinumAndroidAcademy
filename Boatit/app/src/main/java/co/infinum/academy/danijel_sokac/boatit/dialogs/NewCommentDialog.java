package co.infinum.academy.danijel_sokac.boatit.dialogs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.infinum.academy.danijel_sokac.boatit.Enum.InternetConnectionStatus;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Factories.MvpFactory;
import co.infinum.academy.danijel_sokac.boatit.Models.NewComment;
import co.infinum.academy.danijel_sokac.boatit.Models.NewCommentContent;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.BoatSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.CommentListChangeListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.NewCommentActionsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.NewCommentActionsView;

/**
 * Created by Danijel on 23.7.2015..
 */
public class NewCommentDialog extends Dialog implements NewCommentActionsView {
    Button sendNewComment;

    Button cancelNewComment;

    EditText newCommentContent;

    Context context;

    NewCommentActionsPresenter presenter;

    CommentListChangeListener listener;

    public NewCommentDialog(Context context, CommentListChangeListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;

        presenter = MvpFactory.getPresenter(context, this,
                BoatSingleton.InstanceOfSessionSingleton().getBoat(), InternetConnectionStatus.CONNECTED);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cancelNewComment = (Button)findViewById(R.id.cancel_new_comment);
        sendNewComment = (Button)findViewById(R.id.send_new_comment);
        newCommentContent = (EditText)findViewById(R.id.new_comment_text);

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

    private void onCancelNewCommentClicked(View v) {
        presenter.onNewCommentCanceled();
    }

    private void onSendNewCommentClicked(View v) {
        NewCommentContent content = new NewCommentContent();
        content.setContent(newCommentContent.getText().toString());
        NewComment newComment = new NewComment();
        newComment.setComment(content);

        presenter.onSendNewCommentClicked(newComment);
    }

    @Override
    public void onNewCommentSent() {
        dismiss();
        listener.commentListChanged();
    }

    @Override
    public void onNewCommentCanceled() {
        dismiss();
    }

    private ProgressDialog progressDialog;

    @Override
    public void showProgress() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog =
                    ProgressDialog.
                            show(context, context.getString(R.string.app_name),
                                    context.getString(R.string.progress_wait), true, false);
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showError(@StringRes int error) {
        Toast.makeText(context, context.getString(error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(ErrorsEnum error) {
        showError(error.getId());
    }

    @Override
    public void onTokenExpired() {

    }

}
