package com.example.githubclient.api.service;

import com.example.githubclient.api.model.GithubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubClient {

    @GET("/users/{user}/repos")
    Call<List<GithubRepo>> reposForUser(@Path("user") String user);
}
