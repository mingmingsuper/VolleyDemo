package com.lmm.volleydemo.network.RequestConfig;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;
import com.lmm.volleydemo.model.BaseModel;
import com.lmm.volleydemo.model.WeatherDetailModel;
import com.lmm.volleydemo.network.NetConfig;

import java.lang.reflect.Type;

public class WeatherRequest extends NetConfig {

    @Override
    public String serverApiPath() {
        return "/101010100.html";
    }

    @Override
    public Type parserJsonType() {
        return new TypeToken<BaseModel<WeatherDetailModel>>(){}.getType();
    }

    @Override
    public int requestMethod() {
        return Request.Method.GET;
    }
}
