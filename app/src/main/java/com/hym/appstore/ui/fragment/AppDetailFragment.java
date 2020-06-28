package com.hym.appstore.ui.fragment;

import android.text.format.DateUtils;
import android.text.style.UpdateAppearance;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.appstore.R;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.common.Constant;
import com.hym.appstore.common.imageloader.ImageLoader;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.dagger2.component.DaggerAppDetailComponent;
import com.hym.appstore.dagger2.module.AppDetailModule;
import com.hym.appstore.presenter.AppDetailPresenter;
import com.hym.appstore.presenter.contract.AppInfoContract;
import com.hym.appstore.ui.adapter.AppInfoAdapter;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


public class AppDetailFragment extends ProgressFragment<AppDetailPresenter> implements AppInfoContract.AppDetailView {

    @BindView(R.id.view_gallery)
    LinearLayout viewGallery;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.expandable_text)
    TextView expandableText;
    @BindView(R.id.expand_collapse)
    ImageButton expandCollapse;
    @BindView(R.id.view_introduction)
    ExpandableTextView viewIntroduction;
    @BindView(R.id.txt_update_time)
    TextView txtUpdateTime;
    @BindView(R.id.txt_version)
    TextView txtVersion;
    @BindView(R.id.txt_apk_size)
    TextView txtApkSize;
    @BindView(R.id.txt_publisher)
    TextView txtPublisher;
    @BindView(R.id.txt_publisher2)
    TextView txtPublisher2;
    @BindView(R.id.recycler_view_same_dev)
    RecyclerView recyclerViewSameDev;
    @BindView(R.id.recycler_view_relate)
    RecyclerView recyclerViewRelate;


    private int mAppId;
    private LayoutInflater mLayoutInflater;
    private AppInfoAdapter mAppInfoAdapter;

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

        viewIntroduction.setText(appInfoBean.getIntroduction());

        txtUpdateTime.setText(String.valueOf(appInfoBean.getUpdateTime()));
        txtApkSize.setText(appInfoBean.getApkSize());
        txtVersion.setText(appInfoBean.getVersionCode());
        txtPublisher.setText(appInfoBean.getPublisherName());
        txtPublisher2.setText(appInfoBean.getPublisherName());

        mAppInfoAdapter = AppInfoAdapter.builder().layout(R.layout.template_appinfo2_item).build();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewSameDev.setLayoutManager(linearLayoutManager);

        mAppInfoAdapter.addData(appInfoBean.getSameDevAppInfoList());
        recyclerViewSameDev.setAdapter(mAppInfoAdapter);

        recyclerViewRelate.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mAppInfoAdapter.addData(appInfoBean.getRelateAppInfoList());
        recyclerViewRelate.setAdapter(mAppInfoAdapter);

    }

    private void showScreenshot(String screenshot) {
        List<String> urls = Arrays.asList(screenshot.split(","));
        for (String url : urls) {
            ImageView imageView = (ImageView) mLayoutInflater.inflate(R.layout.template_imageview, viewGallery, false);
            ImageLoader.load(Constant.BASE_IMG_URL + url, imageView);
            viewGallery.addView(imageView);
        }
    }


}
