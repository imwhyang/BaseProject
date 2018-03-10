package com.example.wayne.baseproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.wayne.base.basebean.BaseBean;
import com.example.wayne.base.basehttp.BaseCallback;
import com.example.wayne.base.baseui.BaseActivity;
import com.example.wayne.baseproject.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private Call<BaseBean<String>> call;

    @Override
    protected void initData() {

        call = mRequstInterface.getHomeMsg2("0");
        call.enqueue(new BaseCallback<BaseBean<String>>() {
            @Override
            public void onSuccessful(Call<BaseBean<String>> call, Response<BaseBean<String>> response) {
                Log.i(TAG, "onSuccessful: ");
            }

            @Override
            protected void onFail(Call<BaseBean<String>> call, Response<BaseBean<String>> response, String errorMsg) {
//                BaseBean<String> body = response.body();
//                int state = body.getState();
                Log.i(TAG, "onFail: " + errorMsg);
            }
        });
//        try {
//            Response<BaseBean<String>> execute = call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        call.cancel();
    }
}
