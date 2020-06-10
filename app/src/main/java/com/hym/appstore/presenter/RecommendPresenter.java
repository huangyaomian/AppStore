package com.hym.appstore.presenter;

import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.bean.RecommendBean2;
import com.hym.appstore.common.rx.RxErrorhandler;
import com.hym.appstore.common.rx.RxHttpResponseCompat;
import com.hym.appstore.common.rx.subscriber.ErrorHandlerSubscriber;
import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.contract.RecommendContract;

import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

//public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> implements OnResponseListener<String> {

public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {


    private RxErrorhandler errorhandler;

    @Inject
    public RecommendPresenter(RecommendContract.View mView, RecommendModel model, RxErrorhandler errorhandler) {
        super(model,mView);
        this.errorhandler = errorhandler;
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
                .compose(RxHttpResponseCompat.<BaseBean<RecommendBean2<List>>>compatResult())
                .subscribeWith(new ErrorHandlerSubscriber<BaseBean<RecommendBean2.DatasBean>>(errorhandler) {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(BaseBean recommendBean2) {

                    }


                    @Override
                    public void onComplete() {

                    }
                });

       /* mModel.getRecommendRequest()
//                .subscribeOn(Schedulers.io())//把請求放到子綫程中去做(被观察者设置为子线程(发消息))
//                .observeOn(AndroidSchedulers.mainThread())//观察者设置为主线程(接收消息）
                .compose(RxHttpResponseCompat.<RecommendBean2.DatasBean>compatResult())
                .subscribeWith(new ErrorHandlerSubscriber<RecommendBean2.DatasBean>(errorhandler) {

                    @Override
                    public void onSubscribe(Subscription s) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(RecommendBean2.DatasBean datasBean) {
                        if (datasBean != null){
                            mView.showResult(datasBean);
                        }else {
                            mView.showNoData();
                        }
                    }

                    @Override
                    public void onComplete() {
                        mView.dismissLoading();
                    }
                });*/
               /* .subscribeWith(new Subscriber<RecommendBean2.DatasBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(RecommendBean2.DatasBean recommendBean2) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
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
