package com.hym.appstore.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.appstore.R;
import com.hym.appstore.bean.BannerBean;
import com.hym.appstore.bean.HomeBean;
import com.hym.appstore.common.imageloader.ImageLoader;
import com.hym.appstore.ui.widget.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

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
        return position;
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

        if (position == 0) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<BannerBean> banners = mHomeBean.getBanners();
            List<String> urls = new ArrayList<>(banners.size());
            for (BannerBean banner : banners) {
                urls.add(banner.getThumbnail());
            }
            bannerViewHolder.banner.setViewUrls(urls);
            bannerViewHolder.banner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {
//                    banners.get(position)
                }
            });

        } else if (position == 1) {

            IconViewHolder iconViewHolder = (IconViewHolder) holder;

            iconViewHolder.mIconHotApp.setOnClickListener(this);
            iconViewHolder.mIconHotGame.setOnClickListener(this);
            iconViewHolder.mIconHotRecommend.setOnClickListener(this);

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public void onClick(View view) {

    }


    class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        BannerLayout banner;



        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
            banner.setImageLoader(new ImgLoader());
        }
    }

    class IconViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon_hot_app)
        LinearLayout mIconHotApp;
        @BindView(R.id.icon_hot_game)
        LinearLayout mIconHotGame;
        @BindView(R.id.icon_hot_recommend)
        LinearLayout mIconHotRecommend;

        public IconViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }






    class ImgLoader implements BannerLayout.ImageLoader {

        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoader.load(path, imageView);
        }
    }

}