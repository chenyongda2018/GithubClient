package com.example.githubclient.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.Toast;

import com.example.githubclient.R;
import com.example.githubclient.api.model.User;
import com.example.githubclient.api.service.GithubClient;
import com.example.githubclient.base.ServiceGenerator;
import com.example.githubclient.ui.adapter.UserListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示Github用户列表
 * @author cyd
 * @date 2019/11/20 10:29
 */
public class UserListActivity extends AppCompatActivity {

    RecyclerView mUserListRecyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        mUserListRecyclerView = (RecyclerView) findViewById(R.id.activity_user_list_recyclerview);
        mUserListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserListRecyclerView.setHasFixedSize(true);

        displayUserList();
    }


    private void displayUserList() {

        GithubClient client = ServiceGenerator.createService(GithubClient.class);

        Call<List<User>> call = client.getAllUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()) {
                    UserListAdapter adapter =
                            new UserListAdapter(UserListActivity.this, response.body());
                    mUserListRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(UserListActivity.this, "Request failed :(", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
