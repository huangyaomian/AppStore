package com.hym.appstore.ui.fragment;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.hym.appstore.common.apkparset.AndroidApk;
import com.hym.appstore.ui.adapter.AndroidApkAdapter;

import java.util.ArrayList;
import java.util.List;

import zlc.season.rxdownload2.entity.DownloadRecord;


public class InstalledAppAppFragment extends AppManagerFragment {

    private AndroidApkAdapter mAdapter;

    @Override
    protected void init() {
        super.init();
        mPresenter.getInstalledApps();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        mAdapter = new AndroidApkAdapter(getContext(), AndroidApkAdapter.FLAG_APP);
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

    }

    @Override
    public void showApps(List<AndroidApk> apps) {
       List<AndroidApk> androidApks = new ArrayList<>();
        for (int i = 0; i < apps.size(); i++) {
            if (!apps.get(i).isSystem()) {
                androidApks.add(apps.get(i));
            }
        }
        mAdapter.addData(androidApks);
    }

    @Override
    public void PackageRemoved(String packageName) {
//        super.PackageRemoved(packageName);
        Log.d("hymmm", "PackageRemoved1: " + "卸载了应用："+packageName);
        for (int i = 0; i < mAdapter.getData().size(); i++) {
            Log.d("hymmm", "PackageRemoved: " + mAdapter.getData().get(i).getPackageName());
            if (mAdapter.getData().get(i).getPackageName().equals(packageName)){
                Log.d("hymmm", "PackageRemoved2: " + "卸载了应用："+packageName);
                mAdapter.getData().remove(i);
                mAdapter.notifyDataSetChanged();
            }
        }

    }

}
