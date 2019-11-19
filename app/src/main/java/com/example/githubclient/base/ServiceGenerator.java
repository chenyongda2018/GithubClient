package com.example.githubclient.base;

import com.example.githubclient.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit的封装类
 * @author cyd
 * @date 2019/11/19 15:13
 */
public class ServiceGenerator {


    public static final String BASE_URL = "https://api.github.com/";

    private static Retrofit.Builder sBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    private static Retrofit sRetrofit = sBuilder.build();


    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    public static <S> S createService(Class<S> serviceClass) {
        if(!httpClient.interceptors().contains(logging)) {
            //在开发阶段输出日志
            if(BuildConfig.DEBUG) {
                httpClient.addInterceptor(logging);
                httpClient.addInterceptor(new StethoInterceptor());
            }
            sBuilder.client(httpClient.build());
            sRetrofit = sBuilder.build();
        }
        return sRetrofit.create(serviceClass);
    }
}
