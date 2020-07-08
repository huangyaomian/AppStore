package com.hym.appstore.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.hym.appstore.R;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.FragmentInfo;
import com.hym.appstore.common.Constant;
import com.hym.appstore.common.imageloader.ImageLoader;
import com.hym.appstore.common.utils.DensityUtil;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.ui.adapter.MyViewPagerAdapter;
import com.hym.appstore.ui.fragment.AppDetailFragment;
import com.hym.appstore.ui.fragment.DownloadingFragment;
import com.hym.appstore.ui.fragment.GameFragment;
import com.hym.appstore.ui.fragment.HomeFragment;
import com.hym.appstore.ui.fragment.RankingFragment;
import com.hym.appstore.ui.fragment.SortFragment;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppManagerActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_tab_layout)
    TabLayout mainTabLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;


    private List<FragmentInfo> fragmentInfos;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_app_manager;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        toolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.theme_black)
                        )
        );

    }


    @Override
    public void initView() {
        //fragmentinfo 数据集合
        fragmentInfos =  new ArrayList<>(4);
        fragmentInfos.add(new FragmentInfo("下载", DownloadingFragment.class));
//        fragmentInfos.add(new FragmentInfo(getString(R.string.home_tab_ranking), RankingFragment.class));
//        fragmentInfos.add(new FragmentInfo(getString(R.string.home_tab_game), GameFragment.class));
//        fragmentInfos.add(new FragmentInfo(getString(R.string.home_tab_sort), SortFragment.class));
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),fragmentInfos);
        mainViewpager.setAdapter(myViewPagerAdapter);
        mainTabLayout.setupWithViewPager(mainViewpager);
    }

    @Override
    public void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
