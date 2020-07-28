package com.hym.appstore.ui.fragment;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.appstore.R;
import com.hym.appstore.bean.BannerBean;
import com.hym.appstore.bean.HomeBean;
import com.hym.appstore.common.imageloader.ImageLoadConfig;
import com.hym.appstore.common.imageloader.ImageLoader;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.dagger2.component.DaggerHomeComponent;
import com.hym.appstore.dagger2.module.HomeModule;
import com.hym.appstore.presenter.HomePresenter;
import com.hym.appstore.presenter.contract.AppInfoContract;
import com.hym.appstore.ui.adapter.HomeAdapter;
import com.hym.appstore.ui.adapter.HomeAdapter2;
import com.hym.appstore.ui.widget.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import zlc.season.rxdownload2.RxDownload;

public class HomeFragment extends ProgressFragment<HomePresenter> implements AppInfoContract.View {


    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;
    private HomeAdapter adapter;
//    private HomeAdapter2 adapter;

    @Inject
    RxDownload mRxDownload;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerHomeComponent.builder().appComponent(appComponent).homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void initView() {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }


    @Override
    protected void init() {
        mHomeRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mHomeRv.setItemAnimator(new DefaultItemAnimator());
        mPresenter.requestHomeData(true);
    }

    @Override
    protected void initEvent() {

    }





    @Override
    public void showResult(HomeBean homeBean) {
        adapter = new HomeAdapter(getActivity(), homeBean, mRxDownload);
//        adapter = new HomeAdapter(getActivity(), homeBean,mRxDownload);
//        return new HomeAdapter2.BannerViewHolder(mLayoutInflater.inflate(R.layout.home_banner, parent, false));
       /* LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.home_banner,null);
        BannerLayout bannerView = view.findViewById(R.id.banner);
        bannerView.setImageLoader(new ImgLoader());
        List<BannerBean> banners = homeBean.getBanners();
        List<String> urls = new ArrayList<>(banners.size());
        for (BannerBean banner : banners) {
            urls.add(banner.getThumbnail());
        }
        bannerView.setViewUrls(urls);
        adapter.setHeaderView(view);*/
//        https://github.com/CymChad/BaseRecyclerViewAdapterHelper/blob/master/readme/4-%E5%A4%9A%E5%B8%83%E5%B1%80.md


//        List<HomeBean> list = new ArrayList<>();
//        list.add(homeBean);
//        list.add(homeBean);
//        list.add(homeBean);
//        list.add(homeBean);
//        adapter.addData(list);
        mHomeRv.setAdapter(adapter);
        Log.d("showResult", String.valueOf(mHomeRv.getChildCount()));


    }


    @Override
    public void showMoreResult(HomeBean homeBean) {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void onRequestPermissionSuccess() {

    }

    @Override
    public void onRequestPermissionError() {
        Toast.makeText(getActivity(),"您已拒絕授權!",Toast.LENGTH_SHORT).show();
    }

    class ImgLoader implements BannerLayout.ImageLoader {

        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoadConfig defConfig = new ImageLoadConfig.Builder()
                    .setCropType(ImageLoadConfig.CENTER_CROP)
                    .setAsBitmap(true)
                    .setPlaceHolderResId(R.drawable.vector_drawable_init_pic)
                    .setDiskCacheStrategy(ImageLoadConfig.DiskCache.ALL)
                    .setPrioriy(ImageLoadConfig.LoadPriority.HIGH)
                    .setCrossFade(true)
                    .build();
            ImageLoader.load(path, imageView,defConfig);
        }
    }
}
