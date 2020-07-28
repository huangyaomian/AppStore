package com.hym.appstore.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.LayoutInflaterCompat;

import com.hym.appstore.R;
import com.hym.appstore.app.MyApplication;
import com.hym.appstore.common.exception.BaseException;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.presenter.BasePresenter;
import com.hym.appstore.ui.BaseView;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.utils.StatusBarUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class ProgressActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    private Unbinder mUnbinder;
    protected MyApplication mMyApplication;

    private FrameLayout mRootView;
    private View mViewProgress;
    private View mViewEmpty;
    private FrameLayout mViewContent;
    private TextView mTextError;
    private Button mLoginButton;


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

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        mRootView = (FrameLayout) getLayoutInflater().from(this).inflate(R.layout.fragment_progress,context);
        mViewProgress = mRootView.findViewById(R.id.view_progress);
        mViewEmpty = mRootView.findViewById(R.id.view_empty);
        mViewContent = mRootView.findViewById(R.id.view_contern);
        mTextError = mRootView.findViewById(R.id.text_tip);
        mLoginButton = mRootView.findViewById(R.id.login_btn);
        mTextError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });

        return mRootView;
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





    protected void startActivity(Class c) {
        this.startActivity(new Intent(this, c));
    }

    public abstract void init();
    public abstract void initView();
    public abstract void initEvent();


    //子类实现此方法使其点击重新刷新页面
    public void onEmptyViewClick(){

    }

    protected  void setRealContentView(){
        View realContentView = LayoutInflater.from(this).inflate(setLayoutResourceID(), mViewContent, true);
        mUnbinder = ButterKnife.bind(this, realContentView);
    }

    public void showProgressView(){
        Log.d("ProgressFragment","showProgressView");
        showView(R.id.view_progress);
    }

    public void showContentView(){
        Log.d("ProgressFragment","showContentView");
        showView(R.id.view_contern);
    }

    public void showEmptyView(){
        showView(R.id.view_empty);
    }

    public void showEmptyView(int resId){
        showView(R.id.view_empty);
        mTextError.setText(resId);
    }

    public void showEmptyView(String msg){
        showView(R.id.view_empty);
        mTextError.setText(msg);
    }

    public void showEmptyView(String msg, int errorCode){
        showEmptyView(msg);
        if (errorCode == BaseException.ERROR_TOKEN || errorCode == BaseException.INVALID_TOKEN) {
            mLoginButton.setVisibility(View.VISIBLE);
            mLoginButton.setText("登录");
            mLoginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplication(),LoginActivity.class));
                }
            });
        }
    }

    public void showView(int viewId){
        for (int i = 0; i < mRootView.getChildCount(); i++) {
            if (mRootView.getChildAt(i).getId() == viewId) {
                mRootView.getChildAt(i).setVisibility(View.VISIBLE);
            }else {
                mRootView.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showLoading() {
        showProgressView();
    }

    @Override
    public void dismissLoading() {
        showContentView();
    }

    @Override
    public void showError(String msg,int errorCode) {
        Log.d("ProgressFragment","showError");
        showEmptyView(msg,errorCode);
    }
}
