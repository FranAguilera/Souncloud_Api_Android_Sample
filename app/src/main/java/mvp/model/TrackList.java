package mvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class TrackList {
    @SerializedName("collection")
    private List<FavoriteTrack> favoriteTracks;

    public List<FavoriteTrack> getFavoriteTracks() {
        return favoriteTracks;
    }

    public void setFavoriteTracks(List<FavoriteTrack> list){
        this.favoriteTracks = list;
    }
}
