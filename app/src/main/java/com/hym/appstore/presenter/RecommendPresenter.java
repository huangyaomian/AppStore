package com.hym.appstore.presenter;

import android.content.Context;

import com.hym.appstore.bean.AppiInfoBean;
import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.bean.PageBean;
import com.hym.appstore.bean.RecommendBean2;
import com.hym.appstore.common.rx.RxErrorhandler;
import com.hym.appstore.common.rx.RxHttpResponseCompat;
import com.hym.appstore.common.rx.subscriber.ErrorHandlerSubscriber;
import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.contract.RecommendContract;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

//public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> implements OnResponseListener<String> {

public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {


    private RxErrorhandler errorhandler;

    @Inject
    public RecommendPresenter(RecommendContract.View mView, RecommendModel model, RxErrorhandler errorhandler) {
        super(model,mView);
        this.errorhandler = errorhandler;
    }

   public void requestRecommendData() {
       mView.showLoading();
       mModel.getRecommendRequest()
               .subscribeOn(Schedulers.io())//把請求放到子綫程中去做(被观察者设置为子线程(发消息))
               .observeOn(AndroidSchedulers.mainThread(), false, 100)//观察者设置为主线程(接收消息）
//               .compose(RxHttpResponseCompat.<RecommendBean2.DatasBean>compatResult())
               .subscribe(new DisposableObserver<PageBean<AppiInfoBean>>() {

                   @Override
                   public void onStart() {
                       mView.showLoading();
                   }


                   @Override
                   public void onNext(@NonNull PageBean<AppiInfoBean> appiInfoBeanPageBean) {
                       if (appiInfoBeanPageBean.getDatas() != null){
                           mView.showResult(appiInfoBeanPageBean);
                       }else {
                           mView.showNoData();
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       mView.dismissLoading();
                       //處理錯誤
                   }

                   @Override
                   public void onComplete() {
                       mView.dismissLoading();
                   }

               });



/*
    public void requestRecommendMoreData() {

       }*/
    }








}
