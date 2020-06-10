package com.hym.appstore.data;

import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.bean.PageBean;
import com.hym.appstore.data.okhttp.ApiService;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;


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






    public Observable<BaseBean<List<AppInfoBean>>> getRecommendRequest(){
//        mApiService.getApps("{'page':0}").enqueue(callback);
        return  mApiService.getApps("{'page':0}");
    }
}
