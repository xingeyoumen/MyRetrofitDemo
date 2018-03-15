package com.kuaishangche.myretrofitdemo.retrofit.base;

import com.kuaishangche.myretrofitdemo.retrofit.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sugar on 2018/3/15/0015.
 */

public class MyBaseRetrofit {

    public static Retrofit getBaseRetrofit(){
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_ICIBA) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        return retrofit;
    }
}
