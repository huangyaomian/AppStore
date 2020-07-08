package com.hym.appstore.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hym.appstore.R;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.common.imageloader.ImageLoader;
import com.hym.appstore.ui.widget.DownloadButtonController;
import com.hym.appstore.ui.widget.DownloadProgressButton;

import org.jetbrains.annotations.NotNull;

import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class DownloadingAdapter extends BaseQuickAdapter<DownloadRecord, BaseViewHolder> implements LoadMoreModule {

    String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
    private DownloadButtonController mDownloadButtonController;

    private DownloadingAdapter() {
        super(R.layout.template_app_downloading);
//        mDownloadButtonController = new DownloadButtonController(builder.mRxDownload);

    }



    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, DownloadRecord downloadRecord) {



        ImageLoader.load(baseImgUrl+downloadRecord.getExtra2(),baseViewHolder.getView(R.id.img_app_icon));

        baseViewHolder.setText(R.id.home_recyclerview_name,downloadRecord.getExtra3());

        DownloadProgressButton btn = baseViewHolder.getView(R.id.btn_download);
        mDownloadButtonController.handClick(btn,downloadRecord);


    }




}
