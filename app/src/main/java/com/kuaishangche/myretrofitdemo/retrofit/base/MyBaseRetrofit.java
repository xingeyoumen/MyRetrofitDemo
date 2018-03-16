package com.kuaishangche.myretrofitdemo.retrofit.base;

import android.support.v4.BuildConfig;

import com.kuaishangche.myretrofitdemo.retrofit.Constants;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sugar on 2018/3/15/0015.
 *
 * Retrofit+okhttp封装
 */

public class MyBaseRetrofit {
    public static Retrofit retrofit = null;

    public static Retrofit getBaseRetrofit() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //日志
            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }
            //公共参数a=fy&f=auto&t=auto&
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
            builder.addInterceptor(addQueryParameterInterceptor);


            //设置头信息
            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    Request.Builder requestBuilder = originalRequest.newBuilder()
                            .header("AppType", "TPOS")
                            .header("Content-Type", "application/json")
                            .header("Accept", "application/json")
                            .method(originalRequest.method(), originalRequest.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            };
            builder.addInterceptor(headerInterceptor);

            //cookie信息
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            builder.cookieJar(new JavaNetCookieJar(cookieManager));


            //设置超时
            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);

            OkHttpClient okHttpClient = builder.build();

            //步骤4:创建Retrofit对象
            //a=fy&f=auto&t=auto&w
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL_ICIBA) // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .client(okHttpClient)
                    .build();

        }
        return retrofit;
    }
}
