package com.kuaishangche.myretrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kuaishangche.myretrofitdemo.retrofit.base.BaseCallModel;
import com.kuaishangche.myretrofitdemo.retrofit.Constants;
import com.kuaishangche.myretrofitdemo.retrofit.GetRequest_Interface;
import com.kuaishangche.myretrofitdemo.retrofit.base.MyBaseRetrofit;
import com.kuaishangche.myretrofitdemo.retrofit.base.MyCallback;
import com.kuaishangche.myretrofitdemo.retrofit.PostRequest_Interface;
import com.kuaishangche.myretrofitdemo.retrofit.Translation;
import com.kuaishangche.myretrofitdemo.retrofit.Translation1;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.user)
    EditText username;
    @BindView(R.id.pass)
    EditText password;

    @BindView(R.id.textshow)
    TextView textshow;

    @OnClick(R.id.submit) void submit() {
        // TODO call server...
//        textshow.setText("今天是个好天气！");
//        getBaseModelRequest();
        getRequest();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 使用Retrofit封装的方法
////                getRequest();
////                getBaseModelRequest();
////                postRequest();
////                getRequestStream();
//
//                textshow.setText("今天是个好天气！");
//            }
//        });




    }

    private void postRequest() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_YOUDAO) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        PostRequest_Interface request = retrofit.create(PostRequest_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<Translation1> call = request.getCall("I miss you");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation1>() {

            //请求成功时回调
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                System.out.println(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation1> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }
        });
    }

    private void getRequest() {

//        //步骤4:创建Retrofit对象
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL_ICIBA) // 设置 网络请求 Url
//                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
//                .build();

        Retrofit retrofit = MyBaseRetrofit.getBaseRetrofit();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<Translation> call = request.getCall();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                // 步骤7：处理返回的数据结果
//                response.body().show();
                int code = response.raw().code();
                System.out.println("code:" + code);

                Translation translation = response.body();
                String string = translation.toString();
                System.out.println("string:" + string);
//                Toast.makeText(MainActivity.this,string,Toast.LENGTH_SHORT ).show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }

    private void getRequestStream() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_ICIBA) // 设置 网络请求 Url
//                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<ResponseBody> call = request.getCalls();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<ResponseBody>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // 步骤7：处理返回的数据结果
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("string:" + string);
//                Toast.makeText(MainActivity.this,string,Toast.LENGTH_SHORT ).show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }

    private void getBaseModelRequest() {

        Retrofit retrofit = MyBaseRetrofit.getBaseRetrofit();
        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<BaseCallModel<Translation>> call = request.getCallModel();

        //步骤6:发送网络请求(异步)
        call.enqueue(new MyCallback<BaseCallModel<Translation>>() {

            @Override
            public void onSuccess(Response<BaseCallModel<Translation>> response) {
                int code = response.code();
//                System.out.println("code:" + code);
                BaseCallModel<Translation> body = response.body();
                String s = body.data.toString();
                System.out.println("s:"+s);
            }

            @Override
            public void onFail(String message) {

            }


        });
    }


}
