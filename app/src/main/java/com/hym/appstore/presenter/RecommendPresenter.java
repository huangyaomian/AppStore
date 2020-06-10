package com.hym.appstore.presenter;

import android.app.Activity;
import android.util.Log;

import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.common.rx.RxErrorHandler;
import com.hym.appstore.common.rx.RxHttpResponseCompat;
import com.hym.appstore.common.rx.subscriber.ProgressDialogDisposableObserver;
import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.contract.RecommendContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {


    private Activity mActivity;

    @Inject
    public RecommendPresenter(RecommendContract.View mView, RecommendModel model) {
        super(model,mView);
       /* if (mView instanceof Fragment){
            mActivity = ((Fragment)mView).getActivity();
        }else {
            mActivity = (Activity)mView;
        }*/
    }


    public void requestRecommendData() {

        mModel.getRecommendRequest()
                .subscribeOn(Schedulers.io())//把請求放到子綫程中去做(被观察者设置为子线程(发消息))
                .observeOn(AndroidSchedulers.mainThread(), false, 100)//观察者设置为主线程(接收消息）
                .compose(RxHttpResponseCompat.<List<AppInfoBean>>compatResult())
                .subscribe(new ProgressDialogDisposableObserver<List<AppInfoBean>>(mContext) {

                    @Override
                    public void onNext(@NonNull List<AppInfoBean> appiInfoBeanPageBean) {

                        Log.d("requestRecommendData","请求成功");
                        if (appiInfoBeanPageBean != null){
                            mView.showResult(appiInfoBeanPageBean);
                        }else {
                            mView.showNoData();
                        }
                    }


                });

    }









}
