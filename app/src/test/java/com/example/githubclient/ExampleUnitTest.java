package com.example.githubclient;

import com.example.githubclient.api.model.GithubRepo;
import com.example.githubclient.api.model.Repo;
import com.example.githubclient.api.service.GithubRequest;
import com.example.githubclient.api.model.User;
import com.example.githubclient.base.ServiceGenerator;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //创建请求接口实例
        GithubRequest githubRequest = retrofit.create(GithubRequest.class);
        //对发送请求进行封装
        Call<List<GithubRepo>> call = githubRequest.getReposByUser("JakeWharton");


        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                // 配置的数据转换器自动将 json -> 实体类
                List<GithubRepo> repoList = response.body();
                for(GithubRepo repo : repoList) {
                    System.out.println(repo.getName());
                }
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                t.printStackTrace();
            }
        });


        try {
            //同步请求
            List<GithubRepo> repoList = call.execute().body();

            for(GithubRepo repo : repoList) {
                System.out.println(repo.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}