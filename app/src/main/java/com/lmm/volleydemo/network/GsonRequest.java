package com.lmm.volleydemo.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lmm.volleydemo.model.BaseModel;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GsonRequest<T> extends Request<T> {

    private final Gson gson = new Gson();
    private Response.Listener<T> mListener;
    private Response.ErrorListener mErrorListener;

    private NetConfig mConfig;

    public GsonRequest(NetConfig config, Response.Listener<T> listener ,Response.ErrorListener errorListener) {
        super(config.requestMethod(), config.getRequestUrl(), null);
        this.mListener = listener;
        this.mErrorListener = errorListener;
        //超时
        mConfig = config;
        this.setRetryPolicy(new DefaultRetryPolicy(30 * 1000, 1, 1.0f));
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return new HashMap<>();
    }


    @Override
    public byte[] getBody() throws AuthFailureError {
        return super.getBody();
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, "utf-8");
            Type type = mConfig.parserJsonType();
            BaseModel<T> model = gson.fromJson(json, type);
//            if (model.status == 200) {
                return Response.success(model.weatherinfo,HttpHeaderParser.parseCacheHeaders(response));
//            } else {
//                return Response.error(new VolleyError(model.error_msg,null));
//            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
        finally {
            mConfig = null;
        }
    }

    @Override
    protected void deliverResponse(T response) {
        Log.e("GsonRequest","deliverResponse");
        if (mListener != null) {
            mListener.onResponse(response);
            release();
        }
    }

    private void release()
    {
        mErrorListener = null;

        mListener = null;
    }
}
