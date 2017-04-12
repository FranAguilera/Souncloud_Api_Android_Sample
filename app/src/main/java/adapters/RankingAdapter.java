package adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import franjam.soundclouddemo.R;
import mvp.model.FavoriteTrack;
import utils.FontUtils;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {
    private List<FavoriteTrack> favoriteTrackList;
    private Context context;

    public RankingAdapter(List<FavoriteTrack> androidList, Context context) {
        favoriteTrackList = androidList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent
                .getContext())
                .inflate(R.layout.recycler_row_ranking, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (favoriteTrackList == null) return;
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return favoriteTrackList.size();
    }

    public void updateFavoriteTrackList(List<FavoriteTrack> list) {
        this.favoriteTrackList.addAll(list);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_background) ImageView avatarImage;
        @BindView(R.id.user_name_text_view_ranking) TextView userNameTextView;
        @BindView(R.id.additional_info_text_view_ranking) TextView additionalInfoTextView;
        @BindView(R.id.user_url) TextView playerCount;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

            Typeface boldTypeFace = FontUtils.getBoldTypeFace(context);
            Typeface defaultTypeFace = FontUtils.getDefaultTypeFace(context);

            userNameTextView.setTypeface(boldTypeFace);
            additionalInfoTextView.setTypeface(defaultTypeFace);
            playerCount.setTypeface(defaultTypeFace);
        }

        private void bindView(int position) {
            final String userName = favoriteTrackList.get(position).getUserName();
            final String details = favoriteTrackList.get(position).getTitle();
            final String userLinkUrl = favoriteTrackList.get(position).getUserLink();
            final String avatarUrl = favoriteTrackList.get(position).getAvatarImageUrl();

            setGlideImage(avatarUrl);

            userNameTextView.setText(userName.toUpperCase());
            additionalInfoTextView.setText(details);
            playerCount.setText(userLinkUrl);
        }

        private void setGlideImage(String url) {
            Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .placeholder(android.R.drawable.stat_sys_download)
                    .crossFade()
                    .into(avatarImage);
        }
    }
}