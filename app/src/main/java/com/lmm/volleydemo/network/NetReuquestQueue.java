package com.lmm.volleydemo.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class NetReuquestQueue {

    private static RequestQueue mApplicationQueue;

    public  static RequestQueue getApplicationQueue(Context aContext)
    {

        if(mApplicationQueue == null)
        {

            if (aContext.getApplicationContext()!=null)
            {
                aContext = aContext.getApplicationContext();
            }

            mApplicationQueue = Volley.newRequestQueue(aContext);
        }

        return mApplicationQueue;
    }
}
