package com.hym.appstore.presenter;

import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.data.nohttp.HttpListener;
import com.hym.appstore.presenter.contract.RecommendContract;
import com.hym.appstore.ui.BaseView;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

public class BasePresenter<M,V extends BaseView>  {

    protected M mModel;
    protected V mView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
    }

}
