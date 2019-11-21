package com.example.githubclient.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.githubclient.api.model.Repo;
import com.example.githubclient.api.model.User;
import com.example.githubclient.api.service.GithubRequest;
import com.example.githubclient.R;
import com.example.githubclient.base.ServiceGenerator;
import com.example.githubclient.ui.adapter.RepoListAdapter;
import com.example.githubclient.ui.event.UserEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 显示Github仓库列表
 * @author cyd
 * @date 2019/11/19 14:35
 */
public class ReposListActivity extends AppCompatActivity {

    public static final String TAG = ReposListActivity.class.getSimpleName();

    GithubRequest mGithubRequest;
    RecyclerView mRecyclerView;
    RepoListAdapter mRepoListAdapter;
    User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repo_list_activity);

        mRecyclerView = (RecyclerView) findViewById(R.id.repo_list_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        mGithubRequest = ServiceGenerator.createService(GithubRequest.class);

    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    //@Subscribe(sticky = true , threadMode = ThreadMode.MAIN)
    //public void onUserEvent(UserEvent event) {
    //    Log.d(TAG, "username" + event.getUser().getUsername());
    //    mUser = event.getUser();
    //    Call<List<Repo>> call = mGithubRequest.getReposByUser(event.getUser().getUsername());
    //    //异步加载
    //    call.enqueue(new Callback<List<Repo>>() {
    //        @Override
    //        public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
    //            mRepoListAdapter = new RepoListAdapter(ReposListActivity.this,  response.body());
    //            mRepoListAdapter.setUser(mUser);
    //            mRecyclerView.setAdapter(mRepoListAdapter);
    //        }
    //
    //        @Override
    //        public void onFailure(Call<List<Repo>> call, Throwable t) {
    //            Toast.makeText(ReposListActivity.this, "Request failure ~", Toast.LENGTH_SHORT).show();
    //        }
    //    });
    //}
    //

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
