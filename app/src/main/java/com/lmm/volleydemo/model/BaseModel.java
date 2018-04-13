package com.lmm.volleydemo.model;

import android.text.TextUtils;

import java.io.Serializable;

public class BaseModel<T> implements Serializable {

    public T weatherinfo;

    public String error_msg;

    public String message;

    public int status;

    public void error()
    {
        if (TextUtils.isEmpty(error_msg) && !TextUtils.isEmpty(message))
        {
            error_msg = message;
        }
    }
}
