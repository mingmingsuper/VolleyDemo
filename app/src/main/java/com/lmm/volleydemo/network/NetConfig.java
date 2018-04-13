package com.lmm.volleydemo.network;

import android.util.Log;

import com.android.volley.Request;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class NetConfig {

    protected HashMap<String,Object> mParams;

    protected HashMap<String,Object> mBody;

    public byte[] mBodyByte;

    public NetConfig(HashMap<String, Object> mParams, HashMap<String, Object> mBody) {
        this.mParams = mParams;
        this.mBody = mBody;
    }

    public NetConfig(HashMap<String, Object> mParams) {
        this.mParams = mParams;
    }

    public NetConfig() {
    }

    public int requestMethod()
    {
        return Request.Method.POST;
    }

    public abstract String serverApiPath();

    public abstract Type parserJsonType();

    public String getRequestUrl()
    {
        String baseUrl = NetConstans.BaseURL + serverApiPath();

        String params = createLinkString(mParams,true);

        if(baseUrl.contains("?"))
        {
            return baseUrl + "&" + params;
        }
        Log.e("NetConfig",baseUrl);
        return  baseUrl + "?" + params;

    }

    public static String createLinkString(Map<String, Object> params, boolean urlEncode)
    {
        if (params == null) {
            return "";
        }
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++)
        {
            String key = keys.get(i);
            if (params.get(key)==null)
            {
                continue;
            }
            String value = params.get(key).toString();

            if(urlEncode)
            {
                value = urlEncode(value);
            }

            if (i == keys.size() - 1)
            {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            }
            else
            {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }


    private static String urlEncode(String value)
    {
        try
        {
            value = URLEncoder.encode(value,"UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return value;
    }
}
