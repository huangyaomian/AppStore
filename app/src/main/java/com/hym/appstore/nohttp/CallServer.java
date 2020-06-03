package com.hym.appstore.nohttp;

import android.app.Activity;
import android.content.Context;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

public class CallServer {

    private final RequestQueue mQueue;

    private CallServer(){
        mQueue = NoHttp.newRequestQueue();
    }

    private static CallServer callServer;

    //單例模式
    public synchronized static CallServer getInstance(){
        if (callServer == null){
            callServer = new CallServer();
        }
        return callServer;
    }

    /***
     * 添加一个请求到队列中的
     */
    public <T> void add(Context context, int what, Request<T> request
            , HttpListener<T> httpListener, boolean canCancel, boolean isLoading){
        mQueue.add(what,request,new HttpResponseListener<T>(context,httpListener,request,isLoading,canCancel));
    }

}
