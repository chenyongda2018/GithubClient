package com.example.githubclient.ui;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.githubclient.api.service.GithubClient;
import com.example.githubclient.api.model.GithubRepo;
import com.example.githubclient.R;
import com.example.githubclient.base.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * 显示Github仓库列表
 * @author cyd
 * @date 2019/11/19 14:35
 */
public class GithubReposListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.repos_list_view);

        GithubClient githubClient = ServiceGenerator.createService(GithubClient.class);

        Call<List<GithubRepo>> call = githubClient.reposForUser("chenyongda2018");

        //异步加载
        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                List<GithubRepo> repos = response.body();
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GithubReposListActivity.this,
                        android.R.layout.simple_expandable_list_item_1,
                        extractReposName(repos));
                listView.setAdapter(arrayAdapter);
                Toast.makeText(GithubReposListActivity.this, "Request success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Toast.makeText(GithubReposListActivity.this, "Request failure ~", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private ArrayList<String> extractReposName(List<GithubRepo> repos) {
        ArrayList<String> reposName = new ArrayList<>();
        for (GithubRepo repo : repos) {
            reposName.add(repo.getName());
        }
        return reposName;
    }
}
