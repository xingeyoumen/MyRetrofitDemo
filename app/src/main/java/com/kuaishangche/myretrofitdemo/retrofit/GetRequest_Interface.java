package com.kuaishangche.myretrofitdemo.retrofit;

import com.kuaishangche.myretrofitdemo.retrofit.base.BaseCallModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Sugar on 2018/3/14/0014.
 */

public interface GetRequest_Interface {

    @GET("ajax.php?w=hello%20world")
    Call<Translation> getCall();

    @GET("ajax.php?w=hello%20world")
    Call<BaseCallModel<Translation>> getCallModel();
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<ResponseBody> getCalls();
    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // getCall()是接受网络请求数据的方法

}
