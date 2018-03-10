package com.example.wayne.base.basehttp;


import android.util.Log;

import com.example.wayne.base.basebean.BaseBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Author:Wayne
 * Time:2018/3/9 16:48
 * Description: This is GsonResponseBodyConverter
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            Log.d("Network", "response>>" + response);
            //ResultResponse 只解析result字段
            BaseBean resultResponse = gson.fromJson(response, BaseBean.class);
            if (resultResponse.getState() == 0) {
                //result==0表示成功返回，继续用本来的Model类解析
                return gson.fromJson(response, type);
            } else {//这里处理与服务器约定好的规则
                throw new ResultException(resultResponse.getState(), resultResponse.getMsg());
            }
        } finally {
        }
    }
}
