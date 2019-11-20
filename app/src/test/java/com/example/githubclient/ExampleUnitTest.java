package com.example.githubclient;

import com.example.githubclient.api.model.Repo;
import com.example.githubclient.api.service.GithubClient;
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

        GithubClient githubClient = ServiceGenerator.createService(GithubClient.class);

        Call<List<Repo>> call = githubClient.reposForUser("chenyongda2018");

        try {
            List<Repo> repoList = call.execute().body();
            for(Repo repo : repoList) {
                if(repo.getUser() == null) {
                    System.out.println("null");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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