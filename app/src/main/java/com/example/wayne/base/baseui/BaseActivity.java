package com.example.wayne.base.baseui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wayne.base.basehttp.RequstInterface;
import com.example.wayne.baseproject.system.global.App;

import retrofit2.Retrofit;

/**
 * Author:Wayne
 * Time:2018/3/9 11:49
 * Description: This is BaseActivity
 */

public class BaseActivity extends AppCompatActivity {

    protected Retrofit mRetrofit;
    protected RequstInterface mRequstInterface;
    protected App mApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRetrofit = App.getRetrofitInstance();
        mRequstInterface = App.getRequstInterface();
        mApp = App.getInstance();
        initData();
    }

    protected void initData() {

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
