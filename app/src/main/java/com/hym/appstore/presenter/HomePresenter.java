package com.hym.appstore.presenter;

import android.Manifest;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.abarajithan.rxpermissions.RxPermissions;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.bean.HomeBean;
import com.hym.appstore.common.rx.RxHttpResponseCompat;
import com.hym.appstore.common.rx.subscriber.ProgressDisposableObserver;
import com.hym.appstore.data.HomeModel;
import com.hym.appstore.data.RecommendModel;
import com.hym.appstore.presenter.contract.HomeContract;
import com.hym.appstore.presenter.contract.RecommendContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;

public class HomePresenter extends BasePresenter<HomeModel, HomeContract.View> {

    @Inject
    public HomePresenter(HomeContract.View mView, HomeModel model) {
        super(model,mView);
    }



    public void requestHomeData(boolean isShowProgress) {

        RxPermissions rxPermissions = new RxPermissions((Fragment) mView);
        rxPermissions.requestSimple(Manifest.permission.READ_PHONE_STATE).flatMap(new Function<Boolean[], ObservableSource<List<AppInfoBean>>>() {
            @Override
            public ObservableSource<BaseBean<HomeBean>> apply(Boolean[] booleans) throws Throwable {
                for (int i = 0; i < booleans.length; i++) {
                    if (booleans[i] && i == booleans.length -1) {
                        return mModel.getHomeRequest().compose(RxHttpResponseCompat.<BaseBean<HomeBean>>compatResult());
                    }else {
//                        mView.onRequestPermissionError();
                        return Observable.empty();
                    }
                }
                return Observable.empty();
            }
        }).subscribe(new ProgressDisposableObserver<List<AppInfoBean>>(mContext,mView) {
            @Override
            public void onNext(@NonNull HomeBean appiInfoBeanPageBean) {
                Log.d("requestRecommendData","onNext");
                if (appiInfoBeanPageBean != null){
                    mView.showResult(appiInfoBeanPageBean);
                }else {
                    mView.showNoData();
                }
            }

            @Override
            protected boolean isShowProgress() {
                return isShowProgress;
            }
        });
}
