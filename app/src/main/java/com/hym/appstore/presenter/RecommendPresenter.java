package com.hym.appstore.presenter;

import android.app.Activity;
import android.util.Log;

import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.common.rx.RxErrorHandler;
import com.hym.appstore.common.rx.RxHttpResponseCompat;
import com.hym.appstore.common.rx.subscriber.ProgressDialogDisposableObserver;
import com.hym.appstore.common.rx.subscriber.ProgressDisposableObserver;
import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.contract.RecommendContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;


public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {



    @Inject
    public RecommendPresenter(RecommendContract.View mView, RecommendModel model) {
        super(model,mView);
    }


    public void requestRecommendData(boolean isShowProgress) {

        mModel.getRecommendRequest()
                .compose(RxHttpResponseCompat.<List<AppInfoBean>>compatResult())
//                .subscribe(new ProgressDialogDisposableObserver<List<AppInfoBean>>(mContext) {
                .subscribe(new ProgressDisposableObserver<List<AppInfoBean>>(mContext,mView) {

                    @Override
                    public void onNext(@NonNull List<AppInfoBean> appiInfoBeanPageBean) {

                        Log.d("requestRecommendData","请求成功");
                        if (appiInfoBeanPageBean != null){
                            mView.showResult(appiInfoBeanPageBean);
                        }else {
                            mView.showNoData();
                        }
                    }

                    @Override
                    protected boolean isShowProgress() {
                        return isShowProgress;
                    }
                });

    }









}
