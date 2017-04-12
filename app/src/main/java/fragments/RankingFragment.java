package fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import adapters.RankingAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import franjam.soundclouddemo.R;
import mvp.model.FavoriteTrack;
import mvp.presenter.MainPresenter;
import mvp.presenter.RankingTracksPresenter;
import mvp.view.LoadingView;
import mvp.view.RankingView;
import utils.FontUtils;

public class RankingFragment extends Fragment implements RankingView, LoadingView {
    @BindView(R.id.floating_action_bar)
    FloatingActionButton fab;
    @BindView(R.id.loading_error_image_view)
    ImageView imageView;
    @BindView(R.id.loading_progress_bar)
    ProgressBar bottomProgressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private RankingAdapter rankingAdapter;
    private LinearLayoutManager layoutManager;
    private View rootView;
    private FloatingActionBarListener floatingActionBarListener;
    private MainPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.tracks_fragment, container, false);
        ButterKnife.bind(this, rootView);

        floatingActionBarListener = new FloatingActionBarListener(this);
        fab.setOnClickListener(floatingActionBarListener);

        initRecyclerView();
        initializePresenter();
        presenter.initialize();

        return rootView;
    }

    @Override
    public void setInitialRecyclerView(List<FavoriteTrack> userList) {
        rankingAdapter = new RankingAdapter(userList, getActivity());
        recyclerView.setAdapter(rankingAdapter);
    }

    @Override
    public void showLoadingProgress() {
        bottomProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgress() {
        bottomProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateRecyclerView(List<FavoriteTrack> userList) {
        rankingAdapter.updateFavoriteTrackList(userList);
        int position = rankingAdapter.getItemCount();
        rankingAdapter.notifyItemChanged(position - 1, userList);
        layoutManager.scrollToPosition(position);
    }

    @Override
    public Context getFragmentContext() {
        return this.getContext();
    }

    @Override
    public void launchSearchDialog(String title, String message, String okText, String cancelText) {
        createSearchDialog(title, message, okText, cancelText);
    }

    @Override
    public void showLoadingError() {
        setLoadingErrorState(true);
    }

    @Override
    public void hideLoadingError() {
        setLoadingErrorState(false);
    }

    @Override
    public void displayErrorText(String errorMessage) {
        Toast toast = Toast.makeText(this.getContext(), errorMessage, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void initializePresenter() {
        presenter = new RankingTracksPresenter(this);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        setScrollListener();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int xPosition, int yPosition) {
                int visibleItems = layoutManager.getChildCount();
                int totalItems = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                presenter.handleOnScrolled(yPosition, visibleItems, totalItems, pastVisibleItems);
            }
        });
    }

    private void setErrorStateVisibility(boolean isVisible) {
        //TODO: This should belong to the presenter. No logic should be placed in the view
        int recyclerVisibility;
        int errorImageVisibility;

        if (isVisible) {
            recyclerVisibility = View.GONE;
            errorImageVisibility = View.VISIBLE;
        } else {
            recyclerVisibility = View.VISIBLE;
            errorImageVisibility = View.GONE;
        }

        recyclerView.setVisibility(recyclerVisibility);
        imageView.setVisibility(errorImageVisibility);
    }

    private void setLoadingErrorState(final boolean isVisible) {
        setErrorStateVisibility(isVisible);
    }

    private void createSearchDialog(String title, String message, String okText, String cancelText) {
        Context context = getContext();

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);

        final EditText input = new EditText(context);
        alert.setView(input);
        Typeface defaultTypeFace = FontUtils.getDefaultTypeFace(context);
        input.setTypeface(defaultTypeFace);

        alert.setPositiveButton(okText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                final String query = input.getText().toString();
                presenter.submitNewSearch(query);
            }
        });
        alert.setNegativeButton(cancelText, null);
        alert.show();
    }

    private static class FloatingActionBarListener implements View.OnClickListener {

        private WeakReference<RankingFragment> weakReference;

        public FloatingActionBarListener(RankingFragment fragment) {
            this.weakReference = new WeakReference<>(fragment);
        }

        @Override
        public void onClick(View view) {
            RankingFragment fragment = this.weakReference.get();
            if (fragment == null) return;

            fragment.presenter.clickFloatingActionButton();
        }
    }
}
