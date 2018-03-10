package com.example.wayne.base.basebean;

import java.io.Serializable;

/**
 * Author:Wayne
 * Time:2018/3/8 17:37
 * Description: This is BaseBean
 */

public class BaseBean<T> implements Serializable {
    int state;
    String msg;
    T datas;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}
