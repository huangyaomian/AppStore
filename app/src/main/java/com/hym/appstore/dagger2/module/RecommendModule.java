package com.hym.appstore.dagger2.module;

import android.app.Activity;

import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.RecommendPresenter;
import com.hym.appstore.presenter.contract.RecommendContract;
import com.hym.appstore.ui.adapter.RecommendRVAdapter;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;

@Module
public class RecommendModule {

    private RecommendContract.View mView;
    private Activity mActivity;

    public RecommendModule(RecommendContract.View mView) {
        this.mView = mView;
//        this.mActivity = mActivity;
    }

    @Provides
    public RecommendContract.Presenter providePresenter(RecommendContract.View view, RecommendModel model){
        return  new RecommendPresenter(view,model);
    }

    @Provides
    public RecommendContract.View provideView(){
        return mView;
    }

    @Provides
    public Activity provideActivity(){
        return mActivity;
    }

    @Provides
    public RecommendModel provideRecommendModel(RequestQueue mRequestQueue){
        return new RecommendModel(mRequestQueue);
    }

    public RecommendRVAdapter provideAdapter(){
        return  null;
    }
}
