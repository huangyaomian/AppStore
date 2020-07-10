package com.hym.appstore.presenter;

import com.hym.appstore.common.apkparset.AndroidApk;
import com.hym.appstore.common.rx.RxSchedulers;
import com.hym.appstore.common.rx.subscriber.ProgressDisposableObserver;
import com.hym.appstore.presenter.contract.AppManagerContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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

    public RxDownload getRxDownload(){
        return mModel.getRxDownload();
    }

    public void getLocalApks(){
        mModel.getLocalApks().compose(RxSchedulers.io_main())
                .subscribe(new ProgressDisposableObserver<List<AndroidApk>>(mContext,mView) {
                    @Override
                    public void onNext(List<AndroidApk> androidApks) {
                        mView.showApps(androidApks);
                    }
                });
    }
}
