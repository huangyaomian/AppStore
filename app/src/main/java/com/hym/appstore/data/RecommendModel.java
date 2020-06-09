package com.hym.appstore.data;

import android.app.Activity;

import com.hym.appstore.bean.RecommendBean2;
import com.hym.appstore.data.nohttp.CallServer;
import com.hym.appstore.data.nohttp.HttpListener;
import com.hym.appstore.data.okhttp.ApiService;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Callback;


public class RecommendModel {


   /* public RecommendModel(RequestQueue mQueue,ApiService mApiService) {
        this.mQueue = mQueue;
        this.mApiService = mApiService;
    }*/

    public RecommendModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

//    private RequestQueue mQueue;
    private ApiService mApiService;

    /* public  void getRecommendRequest(Activity activity, OnResponseListener httpListener, boolean canCancel, boolean isLoading, String URL){
     *//**推薦遊戲的请求**//*
        Request<String> recommendRequest = NoHttp.createStringRequest(URL, RequestMethod.GET);
        mQueue.add(0, recommendRequest, httpListener);
//        mQueue.add(activity, 0, recommendRequest, httpListener, canCancel, isLoading);
    }*/


/*    public  void getRecommendRequest( int what,OnResponseListener responseListener, String URL){
        Request<String> recommendRequest = NoHttp.createStringRequest(URL, RequestMethod.GET);
        mQueue.add(what, recommendRequest, responseListener);
    }*/






    public Observable<RecommendBean2> getRecommendRequest(){
//        mApiService.getApps("{'page':0}").enqueue(callback);
        return  mApiService.getApps("{'page':0}");
    }
}
