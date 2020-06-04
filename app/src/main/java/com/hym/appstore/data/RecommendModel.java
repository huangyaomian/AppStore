package com.hym.appstore.data;

import android.app.Activity;

import com.hym.appstore.nohttp.CallServer;
import com.hym.appstore.nohttp.HttpListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;


public class RecommendModel {

    public  void getRecommendRequest(Activity activity, HttpListener httpListener, boolean canCancel, boolean isLoading,String URL){
        /**推薦遊戲的请求**/
        Request<String> recommendRequest = NoHttp.createStringRequest(URL, RequestMethod.GET);
        CallServer.getInstance().add(activity, 0, recommendRequest, httpListener, canCancel, isLoading);
    }
}
