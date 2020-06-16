package com.hym.appstore.ui.fragment;


import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.appstore.R;
import com.hym.appstore.bean.HomeBean;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.dagger2.component.DaggerHomeComponent;
import com.hym.appstore.dagger2.module.HomeModule;
import com.hym.appstore.presenter.HomePresenter;
import com.hym.appstore.presenter.contract.HomeContract;
import com.hym.appstore.ui.adapter.HomeAdapter;

import butterknife.BindView;

public class HomeFragment extends ProgressFragment<HomePresenter> implements HomeContract.View {


    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;
    private HomeAdapter adapter;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerHomeComponent.builder().appComponent(appComponent).homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
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
        adapter = new HomeAdapter(getActivity(), homeBean);
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
}
