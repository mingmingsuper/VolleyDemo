package com.lmm.volleydemo;

import android.content.Context;
import android.util.Log;


import com.lmm.volleydemo.network.GsonRequest;
import com.lmm.volleydemo.network.NetReuquestQueue;
import com.lmm.volleydemo.network.RequestConfig.WeatherRequest;
import com.lmm.volleydemo.network.ResponseListener;

import java.io.Serializable;

public class WeatherDC<WeatherDetailModel> implements Serializable{

    public Context mContext;
    public WeatherDetailModel mData;
    ResponseListener mListener;

    public void requestWeather(ResponseListener listener) {
        mListener = listener;
        GsonRequest<WeatherDetailModel> request = new GsonRequest<>(new WeatherRequest(), response -> {
            mData = response;
            mListener.onResult();
        },error-> {
            Log.e("WeatherDC",error.getMessage().toString());
        });
        NetReuquestQueue.getApplicationQueue(mContext.getApplicationContext()).add(request);
    }
}
