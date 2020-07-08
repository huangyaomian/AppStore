package com.hym.appstore.data;

import com.hym.appstore.presenter.contract.AppManagerContract;

import java.util.List;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class AppManagerModel implements AppManagerContract.IAppManagerModel {

    RxDownload mRxDownload;

    public AppManagerModel(RxDownload mRxDownload) {
        this.mRxDownload = mRxDownload;
    }

    @Override
    public Observable<List<DownloadRecord>> getDownloadRecord() {
        return mRxDownload.getTotalDownloadRecords();
    }
}
