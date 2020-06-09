package com.hym.appstore.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.hym.appstore.bean.RecommendBean;
import com.hym.appstore.bean.RecommendBean2;
import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.contract.RecommendContract;
import com.yanzhenjie.nohttp.rest.OnResponseListener;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rx.Subscriber;
import rx.schedulers.Schedulers;

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
        mView.showLoading();
        mModel.getRecommendRequest()
                .subscribeOn(Schedulers.io())//把請求放到子綫程中去做(被观察者设置为子线程(发消息))
                .observeOn(AndroidSchedulers.mainThread())//观察者设置为主线程(接收消息）
                .subscribe(new Subscriber<RecommendBean2>() {

            @Override
            public void onStart() {
                mView.showLoading();
            }

            @Override
            public void onCompleted() {
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
        });
        /*mModel.getRecommendRequest(new Callback<RecommendBean2>() {
            @Override
            public void onResponse(Call<RecommendBean2> call, Response<RecommendBean2> response) {
                Log.d("requestRecommend",response.body().toString());
                mView.showResult(response.body());
            }

            @Override
            public void onFailure(Call<RecommendBean2> call, Throwable t) {
                Log.d("requestRecommend","請求失敗" + t.getMessage());
            }
        });*/
    }

    public void requestRecommendMoreData() {
/*        mModel.getRecommendRequest(new Callback<RecommendBean2>() {
            @Override
            public void onResponse(Call<RecommendBean2> call, Response<RecommendBean2> response) {

            }

            @Override
            public void onFailure(Call<RecommendBean2> call, Throwable t) {

            }
        });*/
    }




/*    @Override
    public void onSucceed(int what, Response<String> response) {
        Gson gson = new Gson();
        RecommendBean recommendBean = gson.fromJson(response.get(), RecommendBean.class);
        switch (what) {
            case 0:
                mView.showResult(recommendBean);
                break;
            case 1:
                mView.showMoreResult(recommendBean);
                break;
        }
        mView.dismissLoading();

    }

    @Override
    public void onFailed(int what, Response<String> response) {
        mView.showError(response.toString());
        mView.dismissLoading();
    }



    @Override
    public void onStart(int what) {
//        mView.showLoading();
    }

    @Override
    public void onFinish(int what) {

    }*/


}
