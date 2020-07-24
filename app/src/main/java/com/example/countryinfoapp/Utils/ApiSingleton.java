package com.example.countryinfoapp.Utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ApiSingleton  {

    private static ApiSingleton mSingleton = null;
    private RequestQueue requestQueue;

    private ApiSingleton(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized ApiSingleton getInstance(Context context)
    {
        if(mSingleton == null)
        {
            mSingleton = new ApiSingleton(context);
        }
        return mSingleton;
    }

    public RequestQueue getRequestQueue()
    {
        return requestQueue;
    }
}
