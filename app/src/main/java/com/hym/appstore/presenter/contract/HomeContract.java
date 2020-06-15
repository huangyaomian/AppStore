package com.hym.appstore.presenter.contract;

import com.hym.appstore.bean.HomeBean;
import com.hym.appstore.ui.BaseView;

public interface HomeContract {

    interface View extends BaseView {

        void showResult(HomeBean datas);
        void showMoreResult(HomeBean datas);
        void showNoData();
        void showError(String msg);

        void onRequestPermissionSuccess();
        void onRequestPermissionError();

    }
}
