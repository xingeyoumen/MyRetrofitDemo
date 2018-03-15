package com.kuaishangche.myretrofitdemo.retrofit.base;

import android.support.v4.BuildConfig;

import com.kuaishangche.myretrofitdemo.retrofit.Constants;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sugar on 2018/3/15/0015.
 */

public class MyBaseRetrofit {

    public static Retrofit getBaseRetrofit() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //公共参数
        //a=fy&f=auto&t=auto&
        Interceptor addQueryParameterInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request;
                String method = originalRequest.method();
                Headers headers = originalRequest.headers();
                HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                        // Provide your custom parameter here
                        .addQueryParameter("a", "fy")
                        .addQueryParameter("f", "auto")
                        .addQueryParameter("t", "auto")
                        .build();
                request = originalRequest.newBuilder().url(modifiedUrl).build();
                return chain.proceed(request);
            }
        };
        //公共参数
        builder.addInterceptor(addQueryParameterInterceptor);

        OkHttpClient okHttpClient = builder.build();

        //步骤4:创建Retrofit对象
        //a=fy&f=auto&t=auto&w
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_ICIBA) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
