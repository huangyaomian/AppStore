package com.hym.appstore.data;

import android.util.Log;

import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.bean.HomeBean;
import com.hym.appstore.data.okhttp.ApiService;

import io.reactivex.rxjava3.core.Observable;

public class HomeModel {
    private ApiService mApiService;

    public HomeModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }


    public Observable<BaseBean<HomeBean>> getHomeRequest(){
        return  mApiService.getHome();
    }
}
