package com.hym.appstore.data;

import android.app.Activity;

import com.hym.appstore.data.nohttp.CallServer;
import com.hym.appstore.data.nohttp.HttpListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;


public class RecommendModel {


    public RecommendModel(RequestQueue mQueue) {
        this.mQueue = mQueue;
    }

    private RequestQueue mQueue;

   /* public  void getRecommendRequest(Activity activity, OnResponseListener httpListener, boolean canCancel, boolean isLoading, String URL){
        *//**推薦遊戲的请求**//*
        Request<String> recommendRequest = NoHttp.createStringRequest(URL, RequestMethod.GET);
        mQueue.add(0, recommendRequest, httpListener);
//        mQueue.add(activity, 0, recommendRequest, httpListener, canCancel, isLoading);
    }*/


     public  void getRecommendRequest( OnResponseListener responseListener, String URL){
        Request<String> recommendRequest = NoHttp.createStringRequest(URL, RequestMethod.GET);
        mQueue.add(0, recommendRequest, responseListener);
    }
}
