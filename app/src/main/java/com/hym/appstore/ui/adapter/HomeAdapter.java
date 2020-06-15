package com.hym.appstore.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.appstore.R;
import com.hym.appstore.bean.BannerBean;
import com.hym.appstore.bean.HomeBean;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleImageBanner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_BANNER = 1;
    private static final int TYPE_ICON = 2;
    private static final int TYPE_APP = 3;
    private static final int TYPE_GAME = 4;


    private LayoutInflater mLayoutInflater;
    private HomeBean mHomeBean;

    public HomeAdapter(Context context, HomeBean homeBean) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mHomeBean = homeBean;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 1) {
            return TYPE_BANNER;
        } else if (position == 2) {
            return TYPE_ICON;
        } else if (position == 3) {
            return TYPE_APP;
        } else if (position == 4) {
            return TYPE_GAME;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.home_banner, parent, false));
        } else if (viewType == TYPE_ICON) {
            return new IconViewHolder(mLayoutInflater.inflate(R.layout.home_nav_icon, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (position == 0){
            BannerViewHolder bannerViewHolder = (BannerViewHolder)holder;
            List<BannerBean> banners = mHomeBean.getBanners();
            bannerViewHolder.banner.set
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        SimpleImageBanner banner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

    class IconViewHolder extends RecyclerView.ViewHolder {

        public IconViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
