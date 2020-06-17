package com.hym.appstore.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hym.appstore.R;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.common.imageloader.ImageLoader;
import com.hym.appstore.presenter.contract.RecommendContract;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AppInfoAdapter extends BaseQuickAdapter<AppInfoBean, BaseViewHolder> implements LoadMoreModule {

    String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
    private Builder mBuilder;

    private AppInfoAdapter(Builder builder) {
        super(R.layout.home_recyclerview_item);
        this.mBuilder = builder;


    }

    public static Builder builder(){
        return new Builder();
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AppInfoBean appInfoBean) {
        ImageLoader.load(baseImgUrl+appInfoBean.getIcon(),baseViewHolder.getView(R.id.home_item_iv));
        baseViewHolder.setText(R.id.home_recyclerview_name,appInfoBean.getDisplayName())
                .setText(R.id.home_recyclerview_brief,appInfoBean.getBriefShow());
        TextView positionView = baseViewHolder.getView(R.id.home_recyclerview_position);
        positionView.setVisibility(mBuilder.isShowPosition? View.VISIBLE:View.GONE);
        positionView.setText(appInfoBean.getPosition() + 1 + ".");

        TextView categoryView = baseViewHolder.getView(R.id.home_recyclerview_category);
        categoryView.setVisibility(mBuilder.isShowCategoryName? View.VISIBLE:View.GONE);
        categoryView.setText(appInfoBean.getLevel1CategoryName());

        TextView briefView = baseViewHolder.getView(R.id.home_recyclerview_brief);
        briefView.setVisibility(mBuilder.isShowBrief? View.VISIBLE:View.GONE);
        briefView.setText(appInfoBean.getBriefShow());

    }


    public static class Builder{
        private boolean isShowPosition;
        private boolean isShowCategoryName;
        private boolean isShowBrief;


        public Builder showPosition(boolean b){
            this.isShowPosition = b;
            return this;
        }

        public Builder showCategoryName(boolean b){
            this.isShowCategoryName = b;
            return this;
        }

        public Builder showBrief(boolean b){
            this.isShowBrief = b;
            return this;
        }

        public AppInfoAdapter build(){
            return new AppInfoAdapter(this);
        }


    }


}
