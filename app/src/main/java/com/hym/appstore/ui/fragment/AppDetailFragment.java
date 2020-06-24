package com.hym.appstore.ui.fragment;

import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hym.appstore.R;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.PageBean;
import com.hym.appstore.common.Constant;
import com.hym.appstore.common.imageloader.ImageLoader;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.dagger2.component.DaggerAppDetailComponent;
import com.hym.appstore.dagger2.module.AppDetailModule;
import com.hym.appstore.presenter.AppDetailPresenter;
import com.hym.appstore.presenter.contract.AppInfoContract;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


public class AppDetailFragment extends ProgressFragment<AppDetailPresenter> implements AppInfoContract.AppDetailView {

    @BindView(R.id.view_gallery)
    LinearLayout viewGallery;


    private int mAppId;
    private LayoutInflater mLayoutInflater;

    public AppDetailFragment(int appId) {
        this.mAppId = appId;
    }

    @Override
    protected void init() {
        mLayoutInflater = LayoutInflater.from(getActivity());
        mPresenter.getAppDetail(mAppId);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_app_detail;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAppDetailComponent.builder().appComponent(appComponent).appDetailModule(new AppDetailModule(this))
                .build().inject(this);
    }

    @Override
    public void showAppDetail(AppInfoBean appInfoBean) {
        showScreenshot(appInfoBean.getScreenshot());
    }

    private void showScreenshot(String screenshot) {
        List<String> urls = Arrays.asList(screenshot.split(","));
        for (String url : urls) {
            ImageView imageView = (ImageView) mLayoutInflater.inflate(R.layout.template_imageview,viewGallery,false);
            ImageLoader.load(Constant.BASE_IMG_URL + url, imageView);
            viewGallery.addView(imageView);
        }
    }

}
