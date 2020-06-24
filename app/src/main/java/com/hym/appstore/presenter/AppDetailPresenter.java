package com.hym.appstore.presenter;

import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.bean.PageBean;
import com.hym.appstore.common.rx.RxHttpResponseCompat;
import com.hym.appstore.common.rx.subscriber.ErrorHandlerDisposableObserver;
import com.hym.appstore.common.rx.subscriber.ProgressDisposableObserver;
import com.hym.appstore.data.AppInfoModel;
import com.hym.appstore.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class AppDetailPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppDetailView> {


    @Inject
    public AppDetailPresenter(AppInfoContract.AppDetailView mView, AppInfoModel model) {
        super(model, mView);
    }


    public void getAppDetail(int id){
        mModel.getAppDetail(id)
                .compose(RxHttpResponseCompat.compatResult())
                .subscribe(new ProgressDisposableObserver<AppInfoBean>(mContext,mView) {
                    @Override
                    public void onNext(@NonNull AppInfoBean appInfoBean) {
                        mView.showAppDetail(appInfoBean);
                    }
                });
    }
}
