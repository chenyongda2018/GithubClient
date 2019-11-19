package com.example.githubclient;

import com.google.gson.Gson;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    public void test_create_account () {
        User user = new User("chenyongda2019",
                "1243724041@qq.com",
                21,
                new String[]{"Android","Java"});
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        UserClient userClient = retrofit.create(UserClient.class);

        Call<User> userCall = userClient.createAccount(user);

        try {
            Integer id = userCall.execute().body().getId();
            System.out.println("创建成功 , 用户id为" + id);
        } catch (IOException e) {
            System.out.println("创建失败");
            e.printStackTrace();
        }
        //userCall.enqueue(new Callback<User>() {
        //    @Override
        //    public void onResponse(Call<User> call, Response<User> response) {
        //        Integer id =response.body().getId();
        //        System.out.println("创建成功 , 用户id为" + id);
        //    }
        //
        //    @Override
        //    public void onFailure(Call<User> call, Throwable t) {
        //        System.out.println("创建失败");
        //    }
        //});
    }
}