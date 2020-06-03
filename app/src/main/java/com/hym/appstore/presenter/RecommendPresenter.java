package com.hym.appstore.presenter;

import android.app.Activity;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.hjq.toast.ToastUtils;
import com.hym.appstore.bean.RecommendBean;
import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.contract.RecommendContract;
import com.hym.appstore.ui.adapter.RecommendRVAdapter;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

public class RecommendPresenter implements RecommendContract.Presenter {

    private RecommendContract.View mView;
    private RecommendModel mModel;
    private Activity mActivity;

    public RecommendPresenter(RecommendContract.View mView ,Activity activity) {
        this.mView = mView;
        this.mActivity = activity;
        mModel = new RecommendModel();
    }

    @Override
    public void requestRecommendData() {
        mView.showLoading();
        mModel.getRecommendRequest(mActivity, this, true, true);
    }



    @Override
    public void onSucceed(int what, Response response) {
        Gson gson = new Gson();
        RecommendBean recommendBean = gson.fromJson((JsonElement) response.get(), RecommendBean.class);
        List<RecommendBean.DataBean.ItemsBean> items = recommendBean.getData().getItems();
        switch (what) {
            case 0:
                mView.showResult(items);
                break;
        }
        mView.dismissLoading();
    }

    @Override
    public void onFailed(int what, Response response) {
        mView.showError(response.toString());
        mView.dismissLoading();
    }
}
