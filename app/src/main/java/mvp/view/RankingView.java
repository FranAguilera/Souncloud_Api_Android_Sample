package mvp.view;

import android.content.Context;

import java.util.List;

import mvp.model.FavoriteTrack;

public interface RankingView extends LoadingView {
    void setInitialRecyclerView(List<FavoriteTrack> userList);
    void updateRecyclerView(List<FavoriteTrack> userList);
    Context getFragmentContext();
    void launchSearchDialog(String title, String message, String okText, String cancelText);
}
