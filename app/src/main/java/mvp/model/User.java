package mvp.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class User {
    private String username;

    @SerializedName("permalink_url")
    private String profileLinkUrl;

    @SerializedName("avatar_url")
    private String avatarImageUrl;

    public String getUsername() {
        return username;
    }

    public String getAvatarImageUrl() {
        return avatarImageUrl;
    }

    public String getProfileLinkUrl() {
        return profileLinkUrl;
    }
}
