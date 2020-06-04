package com.hym.appstore.dagger2.module;

import android.app.Activity;

import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.RecommendPresenter;
import com.hym.appstore.presenter.contract.RecommendContract;
import com.hym.appstore.ui.adapter.RecommendRVAdapter;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;

@Module
public class RecommendModule {

    private RecommendContract.View mView;
    private Activity mActivity;

    public RecommendModule(RecommendContract.View mView, Activity mActivity) {
        this.mView = mView;
        this.mActivity = mActivity;
    }

    @Provides
    public RecommendContract.Presenter providePresenter(RecommendContract.View view, Activity activity, RecommendModel model){
        return  new RecommendPresenter(view,activity,model);
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
    public RecommendModel provideRecommendModel(){
        return new RecommendModel();
    }

    public RecommendRVAdapter provideAdapter(){
        return  null;
    }
}
