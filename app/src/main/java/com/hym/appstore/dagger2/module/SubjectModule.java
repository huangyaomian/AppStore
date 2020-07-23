package com.hym.appstore.dagger2.module;

import com.hym.appstore.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

@Module(includes = {AppModelModule.class})
public class SubjectModule {

    private AppInfoContract.AppInfoView mView;

    public SubjectModule(AppInfoContract.AppInfoView mView) {
        this.mView = mView;
    }


    @Provides
    public AppInfoContract.AppInfoView provideView(){
        return mView;
    }





}
