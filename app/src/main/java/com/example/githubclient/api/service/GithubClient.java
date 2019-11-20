package com.example.githubclient.api.service;

import com.example.githubclient.api.model.Repo;
import com.example.githubclient.api.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 */
public interface GithubClient {

    /**
     * 根据用户名获取此用户的仓库列表
     * @param user
     * @return
     */
    @GET("/users/{user}/repos")
    Call<List<Repo>> reposForUser(@Path("user") String user);

    /**
     * 获取github的所有用户
     * @return  用户列表
     */
    @GET("/users")
    Call<List<User>> getAllUsers();
}
