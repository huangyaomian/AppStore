package com.hym.appstore.data;


import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.bean.HomeBean;
import com.hym.appstore.bean.PageBean;
import com.hym.appstore.data.okhttp.ApiService;

import io.reactivex.rxjava3.core.Observable;

public class AppInfoModel {
    private ApiService mApiService;

    public AppInfoModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }


    public Observable<BaseBean<HomeBean>> getHomeRequest(){
        return  mApiService.getHome();
    }

    public Observable<BaseBean<PageBean<AppInfoBean>>> getRankingRequest(int page){
        return  mApiService.getTopList(page);
    }

    public Observable<BaseBean<PageBean<AppInfoBean>>> getGameRequest(int page){
        return  mApiService.getGames(page);
    }
}
