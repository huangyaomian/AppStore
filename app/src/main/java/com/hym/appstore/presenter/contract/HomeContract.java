package com.hym.appstore.presenter.contract;

import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.RecommendBean;
import com.hym.appstore.ui.BaseView;

import java.util.List;

public interface HomeContract {

    interface View extends BaseView {

        void showResult(List<AppInfoBean> datas);
        void showMoreResult(RecommendBean datas);
        void showNoData();
        void showError(String msg);

        void onRequestPermissionSuccess();
        void onRequestPermissionError();

    }
}
