package com.hym.appstore.presenter.contract;

import com.hym.appstore.bean.RecommendBean;
import com.hym.appstore.bean.RecommendBean2;
import com.hym.appstore.presenter.BasePresenter;
import com.hym.appstore.ui.BaseView;

import java.util.List;

public interface RecommendContract {

    interface View extends BaseView{


//        void showResult(RecommendBean datas);
        void showResult(RecommendBean2.DatasBean datas);
        void showMoreResult(RecommendBean datas);
        void showNoData();
        void showError(String msg);



    }

/*    interface  Presenter extends BasePresenter{

        public void requestRecommendData(String URL);

    }*/
}
