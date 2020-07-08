package com.hym.appstore.presenter.contract;

import com.hym.appstore.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class AppManagerContract {

    public interface AppManagerView extends BaseView{
        void showDownloading(List<DownloadRecord> downloadRecords);
    }

    public interface IAppManagerModel{
        Observable<List<DownloadRecord>> getDownloadRecord();
    }
}
