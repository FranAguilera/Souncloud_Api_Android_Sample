package mvp.presenter;

import android.content.res.Resources;
import android.support.annotation.VisibleForTesting;

import java.util.List;

import franjam.soundclouddemo.R;
import mvp.model.FavoriteTrack;
import mvp.model.TrackList;
import mvp.view.RankingView;
import network.callback.WeakRefRetrofitCallback;
import network.request.TracksRequest;

public class RankingTracksPresenter implements MainPresenter {
    private static final int SONGS_PER_REQUEST = 10;

    @VisibleForTesting
    boolean isInitialCall;
    private RankingView view;
    private TracksRequest trackRequest;
    private int currentIndex;
    private String queryText;

    private String title;
    private String titmessagele;
    private String okText;
    private String cancelText;

    public RankingTracksPresenter(RankingView view) {
        this.view = view;
        RankingCallback rankingCallback = new RankingCallback(this);
        trackRequest = new TracksRequest(rankingCallback);
        retrieveDialogText();
    }

    @Override
    public void initialize() {
        view.hideLoadingError();
        view.showLoadingProgress();
        executeRankingRequest(queryText, currentIndex);
        isInitialCall = true;
        currentIndex = 0;
    }

    @Override
    public void handleOnScrolled(int yPosition, int visibleItems, int totalItems, int pastVisibleItems) {
        boolean isNewPosition = (visibleItems + pastVisibleItems) >= totalItems;
        if (yPosition > 0 && isNewPosition) {
            appendMoreData();
        }
    }

    @Override
    public void clickFloatingActionButton() {
        view.launchSearchDialog(title, titmessagele,okText,cancelText);
    }

    @Override
    public void submitNewSearch(String query) {
        this.queryText = query;
        initialize();
    }

    @VisibleForTesting
    void executeRankingRequest(String request, int currentIndex) {
        trackRequest.execute(request, SONGS_PER_REQUEST, 1, currentIndex);
    }

    private void appendMoreData() {
        view.hideLoadingError();
        view.showLoadingProgress();
        currentIndex += SONGS_PER_REQUEST;
        executeRankingRequest(queryText, currentIndex);
    }

    private void retrieveDialogText() {
        Resources resources = view.getFragmentContext().getResources();
        if (resources == null) return;

        this.title = resources.getString(R.string.search_dialog_title);
        titmessagele = resources.getString(R.string.search_dialog_hint);
        okText = resources.getString(R.string.search_dialog_ok);
        cancelText = resources.getString(R.string.search_dialog_cancel);
    }

    @VisibleForTesting
    static class RankingCallback extends WeakRefRetrofitCallback<TrackList, RankingTracksPresenter> {

        public RankingCallback(RankingTracksPresenter presenter) {
            super(presenter);
        }

        @Override
        protected void onSuccessfulResponse(TrackList trackList, RankingTracksPresenter presenter) {
            if (trackList == null) return;
            List<FavoriteTrack> list = trackList.getFavoriteTracks();

            presenter.view.hideLoadingProgress();

            if (presenter.isInitialCall) {
                presenter.view.setInitialRecyclerView(list);
                presenter.isInitialCall = false;
            } else {
                presenter.view.updateRecyclerView(list);
            }
        }

        @Override
        protected void onFailedResponse(Throwable throwable, RankingTracksPresenter presenter) {
            presenter.view.displayErrorText(throwable.getMessage());
            presenter.view.hideLoadingProgress();
            presenter.view.showLoadingError();
        }
    }
}