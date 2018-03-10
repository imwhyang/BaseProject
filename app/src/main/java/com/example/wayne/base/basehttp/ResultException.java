package com.example.wayne.base.basehttp;

/**
 * Author:Wayne
 * Time:2018/3/9 16:54
 * Description: This is ResultException
 */

public class ResultException extends RuntimeException {
    private int errCode = 0;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}
