package com.hym.appstore.presenter;

import com.hym.appstore.R;
import com.hym.appstore.bean.RecommendBean2;
import com.hym.appstore.common.rx.RxHttpResponseCompat;
import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.contract.RecommendContract;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

//public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> implements OnResponseListener<String> {

public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {


    @Inject
    public RecommendPresenter(RecommendContract.View mView,RecommendModel model) {
        super(model,mView);
    }

   /* public void requestRecommendData(String URL) {
        mView.showLoading();
        mModel.getRecommendRequest(0,this, URL);
    }

    public void requestRecommendMoreData(String URL) {
        mModel.getRecommendRequest(1,this, URL);
    }*/

    public void requestRecommendData() {
        mModel.getRecommendRequest()
//                .subscribeOn(Schedulers.io())//把請求放到子綫程中去做(被观察者设置为子线程(发消息))
//                .observeOn(AndroidSchedulers.mainThread())//观察者设置为主线程(接收消息）
                .compose(RxHttpResponseCompat.<RecommendBean2>compatResult())
                .subscribeWith(new Subscriber<RecommendBean2>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(RecommendBean2 recommendBean2) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
               /* .subscribeWith(new DisposableObserver<RecommendBean2>() {
                    @Override
                    public void onStart() {
                        mView.showLoading();
                    }

                    @Override
                    public void onComplete() {
                        mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoading();
                        //處理錯誤
                    }

                    @Override
                    public void onNext(RecommendBean2 recommendBean2) {
                        if (recommendBean2.getDatas() != null){
                            mView.showResult(recommendBean2);
                        }else {
                            mView.showNoData();
                        }

                    }
                });*/
    }

    public void requestRecommendMoreData() {
    }








}
