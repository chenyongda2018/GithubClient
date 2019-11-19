package com.example.githubclient;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.repos_list_view);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        GithubClient githubClient = retrofit.create(GithubClient.class);

        Call<List<GithubRepo>> call = githubClient.reposForUser("chenyongda2018");

        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                List<GithubRepo> repos = response.body();
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_expandable_list_item_1,
                        extractReposName(repos));
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "request failure ~", Toast.LENGTH_SHORT).show();
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
