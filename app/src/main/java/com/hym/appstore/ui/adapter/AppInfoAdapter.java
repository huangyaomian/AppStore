package com.hym.appstore.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hym.appstore.R;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.common.imageloader.ImageLoader;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AppInfoAdapter extends BaseQuickAdapter<AppInfoBean, BaseViewHolder> {

    String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    public AppInfoAdapter() {
        super(R.layout.home_recyclerview_item);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AppInfoBean appInfoBean) {
        ImageLoader.load(baseImgUrl+appInfoBean.getIcon(),baseViewHolder.getView(R.id.home_item_iv));
        baseViewHolder.setText(R.id.home_recyclerview_name,appInfoBean.getDisplayName())
                .setText(R.id.home_recyclerview_brief,appInfoBean.getBriefShow());
    }
}
