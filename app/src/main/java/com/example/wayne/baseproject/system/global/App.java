package com.example.wayne.baseproject.system.global;

import android.app.Application;

import com.example.wayne.base.basehttp.RequstInterface;
import com.example.wayne.base.basehttp.ResponseConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.wayne.baseproject.BuildConfig.BASE_URL;

/**
 * Author:Wayne
 * Time:2018/3/9 10:40
 * Description: This is App
 */

public class App extends Application {
    // Application 实例
    private static App mInstance;
    // Retrofit 实例
    private static Retrofit retrofit;
    private static RequstInterface mRequstInterface;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // 第2部分：在创建Retrofit实例时通过.baseUrl()设置
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) //设置网络请求的Url地址
                .addConverterFactory(ResponseConverterFactory.create()) //设置数据解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//
                .build();
        mRequstInterface = retrofit.create(RequstInterface.class);
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public static synchronized Retrofit getRetrofitInstance() {
        return retrofit;
    }

    public static synchronized RequstInterface getRequstInterface() {
        return mRequstInterface;
    }

}
