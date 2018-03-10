package com.example.wayne.base.basehttp;

import android.widget.Toast;

import com.example.wayne.baseproject.system.global.App;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.text.ParseException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;


/**
 * Author:Wayne
 * Time:2018/3/9 12:05
 * Description: This is BaseCallback
 */

public abstract class BaseCallback<T> implements Callback<T> {
    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() == 200) {
            onSuccessful(call, response);
        } else {
            onFail(call, response, "访问失败_Callback code is not 200");
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable e) {
        Throwable throwable = e;
        //获取最根源的异常
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }
//        ApiException ex;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
//            ex = new ApiException(e, httpException.code());
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                    //权限错误，需要实现
                    break;
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:


                default:
                    onFail(call, null, "访问失败_Callback Throwable 网络错误");
                    break;
            }
        } else if (e instanceof ResultException) {    //服务器返回的错误
            ResultException resultException = (ResultException) e;
//            ex = new ApiException(resultException, resultException.getErrCode());
//            onResultError(ex);
            onFail(call, null, "访问失败_Callback Throwable " + resultException.getMessage() + " " + resultException.getErrCode());
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
//            ex = new ApiException(e, ApiException.PARSE_ERROR);
//            ex.setDisplayMessage(parseMsg);            //均视为解析错误
//            onError(ex);
            onFail(call, null, "访问失败_Callback Throwable 解析错误 "+e.getMessage());
        } else {
//            ex = new ApiException(e, ApiException.UNKNOWN);
//            ex.setDisplayMessage(unknownMsg);          //未知错误
//            onError(ex);
            onFail(call, null, "访问失败_Callback Throwable " + "未知错误"+e.getMessage());
        }

    }

    public abstract void onSuccessful(Call<T> call, Response<T> response);

    protected void onFail(Call<T> call, Response<T> response, String errorMsg) {
        if (response == null) {
            Toast.makeText(App.getInstance(), errorMsg, Toast.LENGTH_SHORT).show();
            return;
        }
        if (response.errorBody() != null) {
            Toast.makeText(App.getInstance(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
        }

    }

}
