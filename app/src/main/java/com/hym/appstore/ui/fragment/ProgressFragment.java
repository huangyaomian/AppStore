package com.hym.appstore.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.fragment.app.Fragment;

import android.os.strictmode.Violation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hym.appstore.R;
import com.hym.appstore.app.MyApplication;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.presenter.BasePresenter;
import com.hym.appstore.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView {

    private FrameLayout mRootView;
    private View mViewProgress;
    private View mViewEmpty;
    private FrameLayout mViewContent;
    private TextView mTextError;

    private MyApplication mMyApplication;

    @Inject
    public T mPresenter;

    private Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress,container,false);
        mViewProgress = mRootView.findViewById(R.id.view_progress);
        mViewEmpty = mRootView.findViewById(R.id.view_empty);
        mViewContent = mRootView.findViewById(R.id.view_contern);
        mTextError = mRootView.findViewById(R.id.text_tip);
        mTextError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMyApplication = (MyApplication) getActivity().getApplication();
        setupActivityComponent(mMyApplication.getAppComponent());
        setRealContentView();
        init();
        initView();
        initEvent();
    }

    //子类实现此方法使其点击重新刷新页面
    public void onEmptyViewClick(){

    }

    protected  void setRealContentView(){
        View realContentView = LayoutInflater.from(getActivity()).inflate(setLayoutResourceID(), mViewContent, true);
        mUnbinder = ButterKnife.bind(this, realContentView);
    }

    public void showProgressView(){
        showView(R.id.view_progress);
    }

    public void showContentView(){
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
    public void showError(String msg) {
        showEmptyView(msg);
    }


    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID * * @return 布局文件资源ID
     */
    protected abstract int setLayoutResourceID();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    /**
     * 做一些初始化的操作
     */
    protected abstract void init();

    /**
     * 一些View的相关操作
     */
    protected abstract void initView();

    /**
     * 一些事件相關的監聽
     */
    protected abstract void initEvent();




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }
}
