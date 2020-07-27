package com.hym.appstore.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.hym.appstore.common.apkparset.AndroidApk;
import com.hym.appstore.ui.adapter.AndroidApkAdapter;

import java.util.List;


public class DownloadedFragment extends AppManagerFragment {

    private AndroidApkAdapter mAdapter;



    @Override
    protected void init() {
//        RxBus.getDefault().toObservable(User.class).subscribe(new Consumer<User>() {
//            @Override
//            public void accept(User user) {
//                mPresenter.getLocalApks();
//            }
//        });
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
