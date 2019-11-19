package com.example.githubclient;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class GithubClientApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
