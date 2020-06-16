package com.hym.appstore.dagger2.module;

import com.hym.appstore.data.AppInfoModel;
import com.hym.appstore.data.okhttp.ApiService;
import com.hym.appstore.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

@Module
public class RankingModule {

    private AppInfoContract.RankingView mView;

    public RankingModule(AppInfoContract.RankingView mView) {
        this.mView = mView;
    }


    @Provides
    public AppInfoContract.RankingView provideView(){
        return mView;
    }


    @Provides
    public AppInfoModel provideHomeModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }
}
