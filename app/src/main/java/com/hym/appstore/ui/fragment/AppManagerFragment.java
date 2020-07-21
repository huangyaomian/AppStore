package com.hym.appstore.ui.fragment;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hym.appstore.R;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.common.apkparset.AndroidApk;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.dagger2.component.DaggerAppManagerComponent;
import com.hym.appstore.dagger2.module.AppManagerModule;
import com.hym.appstore.presenter.AppManagerPresent;
import com.hym.appstore.presenter.contract.AppManagerContract;
import com.hym.appstore.ui.adapter.DownloadingAdapter;

import java.util.List;

import butterknife.BindView;
import zlc.season.rxdownload2.entity.DownloadRecord;

public abstract class AppManagerFragment extends ProgressFragment<AppManagerPresent> implements AppManagerContract.AppManagerView {

    @BindView(R.id.home_rv)
    RecyclerView mRecyclerView;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAppManagerComponent.builder().appComponent(appComponent).appManagerModule(new AppManagerModule(this)).build().inject(this);
    }


    @Override
    protected void init() {
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_question_diveder));
        mRecyclerView.addItemDecoration(dividerItemDecoration);
//        mAdapter = new DownloadingAdapter(mPresenter.getRxDownload());
//        mAdapter.setAnimationEnable(true);
//        mAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn);
        mRecyclerView.setAdapter(setupAdapter());
    }

    protected abstract RecyclerView.Adapter setupAdapter();

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {

    }

    @Override
    public void showApps(List<AndroidApk> apps) {

    }

    @Override
    public void showUpdateApps(List<AppInfoBean> appInfoBeans) {

    }
}
