package com.hym.appstore.presenter;

import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.requestbean.AppsUpdateBean;
import com.hym.appstore.common.Constant;
import com.hym.appstore.common.apkparset.AndroidApk;
import com.hym.appstore.common.rx.RxHttpResponseCompat;
import com.hym.appstore.common.rx.subscriber.ProgressDialogDisposableObserver;
import com.hym.appstore.common.rx.subscriber.ProgressDisposableObserver;
import com.hym.appstore.common.utils.ACache;
import com.hym.appstore.common.utils.AppUtils;
import com.hym.appstore.common.utils.JsonUtils;
import com.hym.appstore.common.utils.PermissionUtil;
import com.hym.appstore.presenter.contract.MainContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static android.Manifest.permission.READ_PHONE_STATE;


public class MainPresenter extends BasePresenter<MainContract.IMainModel,MainContract.MainView> {


    @Inject
    public MainPresenter(MainContract.IMainModel model, MainContract.MainView mView) {
        super(model, mView);
    }




    public  void requestPermisson(){


        PermissionUtil.requestPermisson(mContext,READ_PHONE_STATE).doOnNext(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {

                if(!aBoolean){
                    mView.requestPermissionFail();
                }

            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                mView.requestPermissionSuccess();
            }
        });

    }

    public void getAppUpdateInfo(){


        getIntalledApps()
                .flatMap(new Function<AppsUpdateBean, ObservableSource<List<AppInfoBean>>>() {
                    @Override
                    public ObservableSource<List<AppInfoBean>> apply(@NonNull AppsUpdateBean params) throws Exception {

                        return  mModel.getUpdateApps(params).compose(RxHttpResponseCompat.<List<AppInfoBean>>compatResult());
                    }
                }).subscribe(new ProgressDisposableObserver<List<AppInfoBean>>(mContext,mView) {
            @Override
            public void onNext(List<AppInfoBean> appInfos) {

                if(appInfos !=null){

                    ACache.get(mContext).put(Constant.APP_UPDATE_LIST, JsonUtils.toJson(appInfos));
                }

                mView.changeAppNeedUpdateCount(appInfos==null?0:appInfos.size());


            }
        });

    }

    private Observable<AppsUpdateBean> getIntalledApps(){


        return  Observable.create(new ObservableOnSubscribe<AppsUpdateBean>() {


            @Override
            public void subscribe(ObservableEmitter<AppsUpdateBean> e) throws Exception {

                e.onNext(buildParams(AppUtils.getInstalledApps(mContext)));
                e.onComplete();
            }
        });

    }

    private AppsUpdateBean buildParams(List<AndroidApk> apks){


        StringBuilder packageNameBuilder = new StringBuilder();
        StringBuilder versionCodeBuilder = new StringBuilder();

        for(AndroidApk apk :apks){

            if(!apk.isSystem()){

                packageNameBuilder.append(apk.getPackageName()).append(",");
                versionCodeBuilder.append(apk.getAppVersionCode()).append(",");
            }
        }

        AppsUpdateBean param = new AppsUpdateBean();
        param.setPackageName(packageNameBuilder.toString());
        param.setVersionCode(versionCodeBuilder.toString());

        return param;

    }


}
