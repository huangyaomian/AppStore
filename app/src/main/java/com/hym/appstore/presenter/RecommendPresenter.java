package com.hym.appstore.presenter;

import android.app.Activity;

import com.google.gson.Gson;
import com.hym.appstore.bean.RecommendBean;
import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.contract.RecommendContract;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

public class RecommendPresenter implements RecommendContract.Presenter {

    private RecommendContract.View mView;
    private RecommendModel mModel;
    private Activity mActivity;

    public RecommendPresenter(RecommendContract.View mView ,Activity activity,RecommendModel model) {
        this.mView = mView;
        this.mActivity = activity;
        mModel = model;
    }

    @Override
    public void requestRecommendData(boolean isLoading, String URL) {
        mView.showLoading();
        mModel.getRecommendRequest(mActivity, this, true, isLoading,URL);
    }



    @Override
    public void onSucceed(int what, Response<String> response) {
        Gson gson = new Gson();
        RecommendBean recommendBean = gson.fromJson(response.get(), RecommendBean.class);
//        List<RecommendBean.DataBean.ItemsBean> items = recommendBean.getData().getItems();
        switch (what) {
            case 0:
                mView.showResult(recommendBean);
                break;
        }
        mView.dismissLoading();
    }

    @Override
    public void onFailed(int what, Response<String> response) {
        mView.showError(response.toString());
        mView.dismissLoading();
    }



}
