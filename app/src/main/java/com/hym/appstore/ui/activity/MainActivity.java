package com.hym.appstore.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.hjq.toast.ToastUtils;
import com.hym.appstore.R;
import com.hym.appstore.bean.FragmentInfo;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.ui.adapter.MyViewPagerAdapter;
import com.hym.appstore.ui.fragment.GameFragment;
import com.hym.appstore.ui.fragment.RankingFragment;
import com.hym.appstore.ui.fragment.RecommendFragment;
import com.hym.appstore.ui.fragment.SortFragment;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.main_tab_layout)
    TabLayout mainTabLayout;
    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private List<FragmentInfo> fragmentInfos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
    }

    @Override
    public void init() {
        //fragmentinfo 数据集合
        fragmentInfos =  new ArrayList<>();

        // NavigationView 可以将滑动菜单页面的实现变得非常简单
        ActionBar supportActionBar = getSupportActionBar();
        //Toolbar 的最左边加入一个导航按钮；引得用户滑动
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(new IconicsDrawable(this, Ionicons.Icon.ion_android_menu).color(getResources().getColor(R.color.TextColor)).actionBar());
        }

//        navigationView.setCheckedItem(R.id.nav_call);//设置默认选择
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                CharSequence title = item.getTitle();
                Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void initView() {
        toolbar.setOverflowIcon(new IconicsDrawable(this, Ionicons.Icon.ion_android_more_vertical).color(getResources().getColor(R.color.TextColor)).actionBar());
        fragmentInfos.add(new FragmentInfo("推荐", RecommendFragment.class));
        fragmentInfos.add(new FragmentInfo("排行", RankingFragment.class));
        fragmentInfos.add(new FragmentInfo("游戏", GameFragment.class));
        fragmentInfos.add(new FragmentInfo("分类", SortFragment.class));
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),fragmentInfos);
        mainViewpager.setAdapter(myViewPagerAdapter);
        mainTabLayout.setupWithViewPager(mainViewpager);
    }


    @Override
    public void initEvent() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar, menu);//加载toolbar.xml 菜单文件
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        menu.getItem(2).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline).color(getResources().getColor(R.color.TextColor)).actionBar());
        menu.findItem(R.id.delete).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline).color(getResources().getColor(R.color.TextColor)).actionBar());
        menu.findItem(R.id.search).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_search).color(getResources().getColor(R.color.TextColor)).actionBar());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://Toolbar 的最左边加入一个导航按钮；引得用户滑动
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                ToastUtils.show("you clicked backup");
//                Toast.makeText(this, "you clicked backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "you clicked delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "you clicked 11", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting2:
                Toast.makeText(this, "you clicked 22", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }



}
