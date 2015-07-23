package co.infinum.academy.danijel_sokac.boatit.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.academy.danijel_sokac.boatit.Adapters.CommentAdapter;
import co.infinum.academy.danijel_sokac.boatit.Enum.InternetConnectionStatus;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorTypeEnum;
import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Factories.MvpFactory;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.R;
import co.infinum.academy.danijel_sokac.boatit.Singletons.BoatSingleton;
import co.infinum.academy.danijel_sokac.boatit.mvp.listeners.CommentListChangeListener;
import co.infinum.academy.danijel_sokac.boatit.mvp.presenters.CommentsPresenter;
import co.infinum.academy.danijel_sokac.boatit.mvp.views.CommentsView;

/**
 * Created by Danijel on 23.7.2015..
 */
public class CommentListFragment extends Fragment  implements CommentsView, SwipeRefreshLayout.OnRefreshListener,
        CommentListChangeListener {
    @Bind(R.id.comment_list_view)
    ListView commentList;

    @Bind(R.id.comment_swipe)
    SwipeRefreshLayout commentSwipe;

    private CommentAdapter adapter;

    private CommentsPresenter presenter;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.comment_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        commentSwipe.setOnRefreshListener(this);
        presenter = MvpFactory.getPresenter(getActivity(), this,
                BoatSingleton.InstanceOfSessionSingleton().getBoat(), InternetConnectionStatus.CONNECTED);
        presenter.getComments();
    }

    @Override
    public void onCommentListReceived(List<Comment> comments) {
        adapter = new CommentAdapter(getActivity(), comments);
        commentList.setAdapter(adapter);
    }

    @Override
    public void onCommentListEmpty() {

    }

    @Override
    public void showProgress() {
        commentSwipe.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        commentSwipe.setRefreshing(false);
    }

    @Override
    public void showError(@StringRes int error) {
        Toast.makeText(getActivity(), getString(error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(ErrorsEnum error) {
        showError(error.getId());
        if (error.getType() == ErrorTypeEnum.INTERNET_ERROR) {
            CommentsPresenter presenter = MvpFactory.getPresenter(
                    getActivity(), this,
                    BoatSingleton.InstanceOfSessionSingleton().getBoat(),
                    InternetConnectionStatus.DISCONNECTED);
            presenter.getComments();
        }
    }

    @Override
    public void onTokenExpired() {

    }

    @Override
    public void onRefresh() {
        presenter.getComments();
    }

    @Override
    public void commentListChanged() {
        onRefresh();
    }
}
