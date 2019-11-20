package com.example.githubclient;

import com.example.githubclient.api.service.GithubClient;
import com.example.githubclient.api.model.GithubRepo;
import com.example.githubclient.api.model.User;
import com.example.githubclient.base.ServiceGenerator;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void github_test() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        GithubClient githubClient = retrofit.create(GithubClient.class);

        Call<List<GithubRepo>> call = githubClient.reposForUser("chenyongda2018");

        try {
            List<GithubRepo> repoList = call.execute().body();
            for(GithubRepo repo : repoList) {
                System.out.println(repo.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //call.execute(new Callback<List<GithubRepo>>() {
        //    @Override
        //    public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
        //        List<GithubRepo> repoList = response.body();
        //        System.out.println(repoList.size());
        //    }
        //
        //    @Override
        //    public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
        //        t.printStackTrace();
        //    }
        //});

    }


    @Test
    public void testGetAllUser () {
        GithubClient client = ServiceGenerator.createService(GithubClient.class);

        Call<List<User>> call = client.getAllUsers();

        try {
            List<User> users = call.execute().body();
            for(User user : users) {
                System.out.println(user.getUsername() + " - " + user.getType() );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}