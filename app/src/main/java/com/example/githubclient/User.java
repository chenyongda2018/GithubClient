package com.example.githubclient;

/**
 * Github 用户类
 */
public class User {

    private Integer id;
    private String name;
    private String email;
    private int age;
    private String[] topics;


    public User(String name, String email, int age, String[] topics) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.topics = topics;
    }

    public Integer getId() {
        return id;
    }
}
