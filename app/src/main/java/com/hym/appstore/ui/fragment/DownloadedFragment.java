package com.hym.appstore.ui.fragment;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.hym.appstore.common.Constant;
import com.hym.appstore.common.apkparset.AndroidApk;
import com.hym.appstore.common.rx.RxSchedulers;
import com.hym.appstore.ui.adapter.AndroidApkAdapter;

import java.util.List;

import io.reactivex.functions.Consumer;


public class DownloadedFragment extends AppManagerFragment {

    private AndroidApkAdapter mAdapter;

    private PopupMenu mPopupMenu;



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
        mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                mPopupMenu = myPopupMenu(view);
                //设置PopupMenu的点击事件
                mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Log.d("hymmm", "onMenuItemClick: " + Constant.BASE_DOWNLOAD_URL + mAdapter.getItem(position).getDownloadUrl());
                        mPresenter.DelDownloadingApp(mAdapter.getItem(position).getDownloadUrl(),true)
                                .compose(RxSchedulers.io_main()).subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                Log.d("hymmm", "accept: ");
                            }
                        });
                        mAdapter.removeAt(position);
                        return true;
                    }

                });

                return true;
            }
        });
    }


    @Override
    public void showApps(List<AndroidApk> apps) {
        mAdapter.addData(apps);
    }


}
