package com.hym.appstore.presenter;

import com.hym.appstore.bean.LoginBean;
import com.hym.appstore.common.Constant;
import com.hym.appstore.common.utils.ACache;
import com.hym.appstore.presenter.contract.LoginContract;

import javax.inject.Inject;

public class SortPresenter extends BasePresenter<> {


    @Inject
    public SortPresenter(LoginContract.ILoginModel mModel, LoginContract.LoginView mView) {
        super(mModel, mView);
    }




    private void saveUser(LoginBean bean){
        ACache aCache = ACache.get(mContext);
        aCache.put(Constant.TOKEN,bean.getToken());
        aCache.put(Constant.USER,bean.getUser());
    }
}
