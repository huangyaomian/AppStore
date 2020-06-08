package com.hym.appstore.presenter;

import com.google.gson.Gson;
import com.hym.appstore.bean.RecommendBean;
import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.contract.RecommendContract;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

import javax.inject.Inject;

public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> implements OnResponseListener<String> {


    @Inject
    public RecommendPresenter(RecommendContract.View mView,RecommendModel model) {
        super(model,mView);
    }

    public void requestRecommendData(String URL) {
        mView.showLoading();
        mModel.getRecommendRequest(0,this, URL);
    }

    public void requestRecommendMoreData(String URL) {
        mModel.getRecommendRequest(1,this, URL);
    }




    @Override
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

    }


}
