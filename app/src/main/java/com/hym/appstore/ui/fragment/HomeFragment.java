package com.hym.appstore.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.hym.appstore.R;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.RecommendBean;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.dagger2.component.DaggerRecommendComponent;
import com.hym.appstore.dagger2.module.HomeModule;
import com.hym.appstore.dagger2.module.RecommendModule;
import com.hym.appstore.presenter.HomePresenter;
import com.hym.appstore.presenter.RecommendPresenter;
import com.hym.appstore.presenter.contract.HomeContract;
import com.hym.appstore.presenter.contract.RecommendContract;
import com.hym.appstore.ui.adapter.RecommendRVAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class HomeFragment extends ProgressFragment<HomePresenter> implements HomeContract.View {


    /**
     * 自定义的容器
     **/
    private List<AppInfoBean> mGameList;

    private RecommendRVAdapter mRecommendRVAdapter;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder().appComponent(appComponent).HomeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }


    @Override
    protected void init() {
        mGameList = new ArrayList<>();
        mPresenter.requestHomeData(true);
    }

    @Override
    protected void initEvent() {

    }





    @Override
    public void showResult(List<AppInfoBean> recommendBean) {

    }


    @Override
    public void showMoreResult(RecommendBean recommendBean) {

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
}
