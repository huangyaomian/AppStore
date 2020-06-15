package com.hym.appstore.dagger2.module;

import com.hym.appstore.data.HomeModel;
import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.data.okhttp.ApiService;
import com.hym.appstore.presenter.contract.HomeContract;
import com.hym.appstore.presenter.contract.RecommendContract;

import dagger.Provides;

public class HomeModule {
    private HomeContract.View mView;

    public HomeModule(HomeContract.View mView) {
        this.mView = mView;
    }


    @Provides
    public HomeContract.View provideView(){
        return mView;
    }

 /*   @Provides
    public RecommendModel provideRecommendModel(RequestQueue mRequestQueue){
        return new RecommendModel(mRequestQueue);
    }*/

    @Provides
    public HomeModel provideHomeModel(ApiService apiService){
        return new HomeModel(apiService);
    }
}
