package com.example.wayne.base.baseui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.wayne.base.basehttp.RequstInterface;
import com.example.wayne.baseproject.R;
import com.example.wayne.baseproject.system.global.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

/**
 * Author:Wayne
 * Time:2018/3/9 11:49
 * Description: This is BaseActivity
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Retrofit mRetrofit;
    protected RequstInterface mRequstInterface;
    protected App mApp;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.pb_progress)
    ProgressBar pbProgress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_activity);
        ButterKnife.bind(this);
        mRetrofit = App.getRetrofitInstance();
        mRequstInterface = App.getRequstInterface();
        mApp = App.getInstance();
        initView();
        initTitle();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initTitle();

    @Override
    public void setContentView(View view) {
        llContent.addView(view);
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
