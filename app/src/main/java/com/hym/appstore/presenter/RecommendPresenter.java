package com.hym.appstore.presenter;

import android.app.Activity;

import com.google.gson.Gson;
import com.hym.appstore.bean.RecommendBean;
import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.contract.RecommendContract;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import javax.inject.Inject;

public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {


    @Inject
    public RecommendPresenter(RecommendContract.View mView,RecommendModel model) {
        super(model,mView);
    }

    public void requestRecommendData(String URL) {
        mModel.getRecommendRequest(this, URL);
    }


    @Override
    public void onStart(int what) {
        mView.showLoading();
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

    @Override
    public void onFinish(int what) {

    }


}
