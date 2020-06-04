package com.hym.appstore.ui.activity;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.LayoutInflaterCompat;

import com.hym.appstore.R;
import com.hym.appstore.app.MyApplication;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.presenter.BasePresenter;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.utils.StatusBarUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private Unbinder mUnbinder;
    private MyApplication mMyApplication;

    @Inject
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(),new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceID());
        mUnbinder = ButterKnife.bind(this);
        XUI.initTheme(this);
        StatusBarUtils.initStatusBarStyle(this,false, ActivityCompat.getColor(this, R.color.theme_blue));
        mMyApplication = (MyApplication) getApplication();
        setupActivityComponent(mMyApplication.getAppComponent());
        init();
        initView();
        initEvent();
    }

    protected abstract int setLayoutResourceID();

    protected abstract void setupActivityComponent(AppComponent appComponent);



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }

    public abstract void init();
    public abstract void initView();
    public abstract void initEvent();
}
