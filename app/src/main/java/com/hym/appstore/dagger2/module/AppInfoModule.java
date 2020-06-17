package com.hym.appstore.dagger2.module;

import com.hym.appstore.data.AppInfoModel;
import com.hym.appstore.data.okhttp.ApiService;
import com.hym.appstore.presenter.contract.AppInfoContract;
import com.hym.appstore.ui.adapter.AppInfoAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class AppInfoModule {

    private AppInfoContract.AppInfoView mView;

    public AppInfoModule(AppInfoContract.AppInfoView mView) {
        this.mView = mView;
    }


    @Provides
    public AppInfoContract.AppInfoView provideView(){
        return mView;
    }


    @Provides
    public AppInfoModel provideHomeModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }


}
