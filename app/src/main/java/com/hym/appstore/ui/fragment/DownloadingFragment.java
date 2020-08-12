package com.hym.appstore.ui.fragment;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.hym.appstore.common.apkparset.AndroidApk;
import com.hym.appstore.common.utils.FileUtils;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.dagger2.component.DaggerAppManagerComponent;
import com.hym.appstore.dagger2.module.AppManagerModule;
import com.hym.appstore.ui.adapter.DownloadingAdapter;

import java.io.File;
import java.util.List;

import zlc.season.rxdownload2.entity.DownloadRecord;


public class DownloadingFragment extends AppManagerFragment {

    private DownloadingAdapter mAdapter;

    private PopupMenu mPopupMenu;


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
        mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                mPopupMenu = myPopupMenu(view);
                //设置PopupMenu的点击事件
                mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        mAdapter.removeAt(position);
                        FileUtils.deleteFile(mAdapter.getItem(position).getSavePath() + File.separator + mAdapter.getItem(position).getSaveName());
                        Log.d("hymmm", "onMenuItemClick: " + mAdapter.getItem(position).getSavePath() + File.separator + mAdapter.getItem(position).getSaveName());
                        return true;
                    }

                });

                return true;
            }
        });


    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {
        mAdapter.addData(downloadRecords);
    }



}
