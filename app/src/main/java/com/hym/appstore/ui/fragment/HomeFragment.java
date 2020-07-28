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
import com.hym.appstore.presenter.contract.AppInfoContract;
import com.hym.appstore.ui.adapter.HomeAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import zlc.season.rxdownload2.RxDownload;

public class HomeFragment extends ProgressFragment<HomePresenter> implements AppInfoContract.View {


    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;
    private HomeAdapter adapter;

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
        mHomeRv.setAdapter(adapter);
        Log.d("showResult", String.valueOf(mHomeRv.getChildCount()));


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
