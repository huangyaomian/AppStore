package com.hym.appstore.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.common.Constant;
import com.hym.appstore.common.apkparset.AndroidApk;
import com.hym.appstore.common.rx.RxSchedulers;
import com.hym.appstore.common.rx.subscriber.ProgressDisposableObserver;
import com.hym.appstore.common.utils.ACache;
import com.hym.appstore.presenter.contract.AppManagerContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class AppManagerPresent extends BasePresenter<AppManagerContract.IAppManagerModel, AppManagerContract.AppManagerView> {


    @Inject
    public AppManagerPresent(AppManagerContract.IAppManagerModel mModel, AppManagerContract.AppManagerView mView) {
        super(mModel, mView);
    }

    public void getDownloadingApps(){
        mModel.getDownloadRecord().compose(RxSchedulers.io_main())
                .subscribe(new ProgressDisposableObserver<List<DownloadRecord>>(mContext,mView) {
                    @Override
                    public void onNext(List<DownloadRecord> downloadRecords) {
                        mView.showDownloading(downloadRecordFilter(downloadRecords));
                    }
                });
    }

    public void getDownloadedApps(){
        Observable<List<DownloadRecord>> downloadRecord = mModel.getDownloadRecord();
        Observable<List<AndroidApk>> localApks = mModel.getLocalApks();
        Observable<List<AndroidApk>> downloaded = Observable.zip(localApks, downloadRecord, new BiFunction<List<AndroidApk>, List<DownloadRecord>>() {
            @Override
            public Object apply(Object o, Object o2) throws Exception {
                return null;
            }

            @Override
            public List<DownloadRecord> apply(List<AndroidApk> androidApks) throws Exception {
                return null;
            }
        });

    }

    public Observable<Boolean> DelDownloadingApp(String url,boolean deleteFile){
        return mModel.DelDownloadRecord(url,deleteFile);
    }

    //过滤下载中的
    private List<DownloadRecord> downloadRecordFilter(List<DownloadRecord> downloadRecords){
        List<DownloadRecord> newList = new ArrayList<>();
        for (DownloadRecord r : downloadRecords){
            if (r.getFlag() != DownloadFlag.COMPLETED) {
                newList.add(r);
            }
        }
        return newList;
    }

    //獲取下載完成的記錄
    private List<DownloadRecord> downloadedFilter(List<DownloadRecord> downloadRecords){
        List<DownloadRecord> newList = new ArrayList<>();
        for (DownloadRecord r : downloadRecords){
            if (r.getFlag() == DownloadFlag.COMPLETED) {
                newList.add(r);
            }
        }
        return newList;
    }

    public void getUpdateApps(){
        String json= ACache.get(mContext).getAsString(Constant.APP_UPDATE_LIST);
        if(!TextUtils.isEmpty(json)){

            Gson gson = new Gson();
            List<AppInfoBean> apps = gson.fromJson(json,new TypeToken<List<AppInfoBean>>(){}.getType());
            Observable.just(apps)
                    .compose(RxSchedulers.<List<AppInfoBean>>io_main())

                    .subscribe(new ProgressDisposableObserver<List<AppInfoBean>>(mContext,mView) {
                        @Override
                        public void onNext(List<AppInfoBean> appInfos) {

                            mView.showUpdateApps(appInfos);
                        }
                    });
        }

    }

    public RxDownload getRxDownload(){
        return mModel.getRxDownload();
    }

    //獲取所有已下載的apk
    public void getLocalApks(){
        mModel.getLocalApks().compose(RxSchedulers.io_main())
                .subscribe(new ProgressDisposableObserver<List<AndroidApk>>(mContext,mView) {
                    @Override
                    public void onNext(List<AndroidApk> androidApks) {
                        mView.showApps(androidApks);
                    }
                });
    }

    //获取所有已安装的app
    public void getInstalledApps(){
        mModel.getInstalledApps().compose(RxSchedulers.io_main())
                .subscribe(new ProgressDisposableObserver<List<AndroidApk>>(mContext,mView) {
                    @Override
                    public void onNext(List<AndroidApk> androidApks) {
                        mView.showApps(androidApks);
                    }
                });
    }
}
