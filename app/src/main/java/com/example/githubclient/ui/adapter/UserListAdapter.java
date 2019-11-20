package com.example.githubclient.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.githubclient.R;
import com.example.githubclient.api.model.User;
import com.example.githubclient.ui.ReposListActivity;
import com.example.githubclient.ui.UserListActivity;
import com.example.githubclient.ui.event.UserEvent;
import com.example.githubclient.ui.listener.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 展示所有github用户列表的adapter
 * @author cyd
 * @date 2019/11/20 14:16
 */
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
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        User user = mUserList.get(position);
        if( null != user) {
            Glide.with(mContext)
                    .load(user.getAvatarUrl())
                    .into(holder.avatarIv);
            holder.username.setText(user.getUsername());
        }

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new UserEvent(mUserList.get(position)));
                mContext.startActivity(new Intent(mContext, ReposListActivity.class));
            }
        });

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
