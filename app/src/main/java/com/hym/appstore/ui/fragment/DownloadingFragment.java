package com.hym.appstore.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hym.appstore.common.apkparset.AndroidApk;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.dagger2.component.DaggerAppManagerComponent;
import com.hym.appstore.dagger2.module.AppManagerModule;
import com.hym.appstore.ui.adapter.DownloadingAdapter;

import java.util.List;

import zlc.season.rxdownload2.entity.DownloadRecord;


public class DownloadingFragment extends AppManagerFragment {

    private DownloadingAdapter mAdapter;


    @Override
    protected void init() {
        super.init();
        mPresenter.getDownloadingApps();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {

        mAdapter = new DownloadingAdapter(mPresenter.getRxDownload());
        mAdapter.setAnimationEnable(true);
        mAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn);
        return mAdapter;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {
        mAdapter.addData(downloadRecords);
    }



}
