package com.hym.appstore.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hym.appstore.common.apkparset.AndroidApk;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.dagger2.component.DaggerAppManagerComponent;
import com.hym.appstore.dagger2.module.AppManagerModule;
import com.hym.appstore.ui.adapter.AndroidApkAdapter;
import com.hym.appstore.ui.adapter.DownloadingAdapter;

import java.util.List;

import zlc.season.rxdownload2.entity.DownloadRecord;


public class DownloadedFragment extends AppManagerFragment {

    private AndroidApkAdapter mAdapter;



    @Override
    protected void init() {
        super.init();
        mPresenter.getLocalApks();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        mAdapter = new AndroidApkAdapter(getContext(), AndroidApkAdapter.FLAG_APK);
        return mAdapter;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }


    @Override
    public void showApps(List<AndroidApk> apps) {
        mAdapter.addData(apps);
    }


}
