package com.example.githubclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.githubclient.R;
import com.example.githubclient.api.model.Repo;
import com.example.githubclient.api.model.User;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 仓库列表的adapter
 * @author cyd
 * @date 2019/11/20 16:17
 */
public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoListViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Repo> mRepoList;
    private User mUser;

    public RepoListAdapter(Context context, List<Repo> repos) {
        mContext = context;
        mRepoList = repos;
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public RepoListAdapter.RepoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater
                .inflate(R.layout.repo_list_item_layout, parent, false);
        return new RepoListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoListViewHolder holder, int position) {
        Repo repo = mRepoList.get(position);

        Glide.with(mContext)
                .asBitmap()
                .load(mUser.getAvatarUrl())
                .apply(
                        RequestOptions.bitmapTransform(new CircleCrop())
                )
                .into(holder.userAvatarIv);


        holder.repoNameTv.setText(repo.getName());
        if(repo.getDescription() != null) {
            holder.repoDescription.setText(repo.getDescription().toString());
        }
        holder.repoStarCount.setText(repo.getStargazersCount().toString());
        holder.repoForkCount.setText(repo.getForksCount().toString());
        holder.repoOwnerName.setText(mUser.getUsername());
    }


    @Override
    public int getItemCount() {
        return mRepoList.size();
    }

    public class RepoListViewHolder extends RecyclerView.ViewHolder {

        ImageView userAvatarIv;
        TextView repoNameTv;
        TextView repoDescription;
        TextView repoStarCount;
        TextView repoForkCount;
        TextView repoOwnerName;

        CardView mCardView;
        public RepoListViewHolder(@NonNull View itemView) {
            super(itemView);
            userAvatarIv = (ImageView)itemView.findViewById(R.id.repo_list_item_user_avatar_iv);
            repoNameTv = (TextView)itemView.findViewById(R.id.repo_list_item_repo_name_tv);
            repoDescription = (TextView)itemView.findViewById(R.id.repo_list_item_repo_description_tv);
            repoStarCount = (TextView) itemView.findViewById(R.id.repo_list_item_repo_star_count_tv);
            repoForkCount = (TextView)itemView.findViewById(R.id.repo_list_item_repo_fork_count_tv);
            repoOwnerName = (TextView)itemView.findViewById(R.id.repo_list_item_owner_name_tv);

            mCardView = (CardView) itemView.findViewById(R.id.repo_list_item_card_view);
            mCardView.setCardElevation(5.0f);
        }
    }

    public void setUser(User user) {
        mUser = user;
    }
}
