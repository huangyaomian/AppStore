package com.hym.appstore.presenter;

import com.hym.appstore.bean.HomeBean;
import com.hym.appstore.common.rx.RxHttpResponseCompat;
import com.hym.appstore.common.rx.subscriber.ProgressDisposableObserver;
import com.hym.appstore.data.HomeModel;
import com.hym.appstore.presenter.contract.HomeContract;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;

public class HomePresenter extends BasePresenter<HomeModel, HomeContract.View> {

    @Inject
    public HomePresenter(HomeContract.View mView, HomeModel model) {
        super(model, mView);
    }




    public void requestHomeData(boolean isShowProgress) {

        mModel.getHomeRequest()
                .compose(RxHttpResponseCompat.<HomeBean>compatResult())
                .subscribe(new ProgressDisposableObserver<HomeBean>(mContext, mView) {
                    @Override
                    public void onNext(@NonNull HomeBean homeBean) {
                        mView.showResult(homeBean);
                    }

                    @Override
                    protected boolean isShowProgress() {
                        return isShowProgress;
                    }
                });

    }
}


    /*RxPermissions rxPermissions = new RxPermissions((Fragment) mView);
        rxPermissions.requestSimple(Manifest.permission.READ_PHONE_STATE).flatMap(new Function<Boolean[], ObservableSource<HomeBean>>() {
            @Override
            public ObservableSource<HomeBean> apply(Boolean[] booleans) throws Throwable {
                for (int i = 0; i < booleans.length; i++) {
                    if (booleans[i] && i == booleans.length - 1) {
                        return mModel.getHomeRequest().compose(RxHttpResponseCompat.<HomeBean>compatResult());
                    } else {
                        return Observable.empty();
                    }
                }
                return Observable.empty();
            }
        }).subscribe(new ProgressDisposableObserver<HomeBean>(mContext, mView) {
            @Override
            public void onNext(@NonNull HomeBean homeBean) {
                Log.d("requestRecommendData", "onNext");
                if (homeBean != null) {
                    mView.showResult(homeBean);
                } else {
                    mView.showNoData();
                }
            }

            @Override
            protected boolean isShowProgress() {
                return isShowProgress;
            }
        });
    }*/