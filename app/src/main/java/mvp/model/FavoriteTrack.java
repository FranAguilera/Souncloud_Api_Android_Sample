package mvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Sample payload for TRACKS from SoundCloudAPI
 * <p>
 * {
 * "kind": "track",
 * "id": 291965400,
 * "created_at": "2016/11/07 20:35:41 +0000",
 * "user_id": 266834715,
 * "duration": 193925,
 * "commentable": true,
 * "state": "finished",
 * "original_content_size": 7758284,
 * "last_modified": "2016/11/07 20:41:23 +0000",
 * "sharing": "public",
 * "tag_list": "",
 * "permalink": "lost-spaniards-your-name",
 * "streamable": true,
 * "embeddable_by": "all",
 * "downloadable": true,
 * "purchase_url": null,
 * "label_id": null,
 * "purchase_title": null,
 * "genre": "Latin",
 * "title": "Lost Spaniards - Your Name",
 * "description": "Dos Spaniards, José y Fran, lost in Los Angeles con only their guitarras to showcase what they have found in America combined with nostalgia of where they came from.",
 * "label_name": null,
 * "release": null,
 * "track_type": null,
 * "key_signature": null,
 * "isrc": null,
 * "video_url": null,
 * "bpm": null,
 * "release_year": 2016,
 * "release_month": 11,
 * "release_day": 7,
 * "original_format": "mp3",
 * "license": "all-rights-reserved",
 * "uri": "https://api.soundcloud.com/tracks/291965400",
 * "user": {
 * "id": 266834715,
 * "kind": "user",
 * "permalink": "lostspaniards",
 * "username": "Lost Spaniards",
 * "last_modified": "2017/01/30 20:12:19 +0000",
 * "uri": "https://api.soundcloud.com/users/266834715",
 * "permalink_url": "http://soundcloud.com/lostspaniards",
 * "avatar_url": "https://i1.sndcdn.com/avatars-000293187126-lnd3na-large.jpg"
 * },
 * "attachments_uri": "https://api.soundcloud.com/tracks/291965400/attachments",
 * "permalink_url": "http://soundcloud.com/lostspaniards/lost-spaniards-your-name",
 * "artwork_url": "https://i1.sndcdn.com/artworks-000192516435-hrwzot-large.jpg",
 * "waveform_url": "https://w1.sndcdn.com/YaTSKPudqeYg_m.png",
 * "stream_url": "https://api.soundcloud.com/tracks/291965400/stream",
 * "download_url": "https://api.soundcloud.com/tracks/291965400/download",
 * "playbackCount": 6,
 * "downloadCount": 0,
 * "favoritings_count": 2,
 * "comment_count": 0
 * }
 */

@SuppressWarnings("unused")
public class FavoriteTrack {
    private String title;
    private User user;

    @SerializedName("playback_count")
    private int playbackCount;

    @SerializedName("download_count")
    private int downloadCount;

    @SerializedName("favoritings_counts")
    private int favoritingCounts;

    public FavoriteTrack(String title, User user, int playbackCount) {
        this.title = title;
        this.user = user;
        this.playbackCount = playbackCount;
    }

    public String getTitle() {
        return title;
    }

    public int getPlaybackCount() {
        return playbackCount;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public int getFavoritingCounts() {
        return favoritingCounts;
    }

    public String getUserName() {
        if (user == null) return "";
        return user.getUsername();
    }

    public String getUserLink() {
        if (user == null) return "";
        return user.getProfileLinkUrl();
    }

    public String getAvatarImageUrl() {
        if (user == null) return "";
        return user.getAvatarImageUrl();
    }
}



