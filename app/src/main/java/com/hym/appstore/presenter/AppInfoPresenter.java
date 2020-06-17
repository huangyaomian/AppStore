package com.hym.appstore.presenter;

import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.bean.PageBean;
import com.hym.appstore.common.rx.Optional;
import com.hym.appstore.common.rx.RxHttpResponseCompat;
import com.hym.appstore.common.rx.subscriber.ErrorHandlerDisposableObserver;
import com.hym.appstore.common.rx.subscriber.ProgressDisposableObserver;
import com.hym.appstore.data.AppInfoModel;
import com.hym.appstore.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class AppInfoPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppInfoView> {

    public final static int RANKING_LIST = 1;
    public final static int GAMES_LIST = 2;




    @Inject
    public AppInfoPresenter(AppInfoContract.AppInfoView mView, AppInfoModel model) {
        super(model, mView);
    }




    public void requestData(int type,int page) {
        DisposableObserver disposableObserver = null;
        if (page == 0) {
            disposableObserver = new ProgressDisposableObserver<Optional<PageBean<AppInfoBean>>>(mContext, mView) {
                @Override
                public void onNext(@NonNull Optional<PageBean<AppInfoBean>> pageBeanOptional) {
                    mView.showResult(pageBeanOptional.getIncludeNull());
                }
            };
        }else {
            disposableObserver = new ErrorHandlerDisposableObserver<Optional<PageBean<AppInfoBean>>>(mContext) {

                @Override
                public void onNext(@NonNull Optional<PageBean<AppInfoBean>> pageBeanOptional) {
                    mView.showResult(pageBeanOptional.getIncludeNull());
                }

                @Override
                public void onComplete() {
                    mView.onLoadMoreComplete();
                }
            };
        }

        Observable observable = getObservable(type,page);

        observable
                .compose(RxHttpResponseCompat.handle_result())
                .subscribe(disposableObserver);

      /*  mModel.getHomeRequest()
               .subscribeOn(Schedulers.io())//把請求放到子綫程中去做(被观察者设置为子线程(发消息))
               .observeOn(AndroidSchedulers.mainThread())//观观察者设置为主线程(接收消息）
                .subscribeWith(new ProgressDisposableObserver<BaseBean<HomeBean>>(mContext, mView) {
                    @Override
                    public void onNext(@NonNull BaseBean baseBean) {
//                        Log.d("requestHomeData", String.valueOf(appInfoBeans);
                        mView.showResult((HomeBean) baseBean.getData());
                    }

                    @Override
                    protected boolean isShowProgress() {
                        return isShowProgress;
                    }
                });*/


    }


    private Observable<BaseBean<PageBean<AppInfoBean>>> getObservable(int type, int page){
        switch (type){
            case RANKING_LIST:
                return mModel.getRankingRequest(page);
            case GAMES_LIST:
                return mModel.getGameRequest(page);
        }
        return Observable.empty();
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
