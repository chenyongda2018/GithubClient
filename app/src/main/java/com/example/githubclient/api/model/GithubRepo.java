package com.example.githubclient.api.model;

/**
 * Github仓库
 * @author cyd
 * @date 2019/11/18 22:44
 */
public class GithubRepo {

    private String name;

    public GithubRepo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
