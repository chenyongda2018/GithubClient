package com.example.githubclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.githubclient.R;
import com.example.githubclient.api.model.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<User> mUserList;

    public UserListAdapter(Context context, List<User> userList) {
        mContext = context;
        mUserList = userList;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.user_list_item_layout, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mUserList.get(position);
        if( null != user) {
            Glide.with(mContext)
                    .load(user.getAvatarUrl())
                    .into(holder.avatarIv);
            holder.username.setText(user.getUsername());
        }
    }



    @Override
    public int getItemCount() {
        return mUserList.size();
    }


    protected class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView avatarIv;//头像
        TextView username;//用户名

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarIv = (ImageView) itemView.findViewById(R.id.avatar_user_list_item_iv);
            username = (TextView) itemView.findViewById(R.id.username_user_list_item_tv);
        }
    }
}
