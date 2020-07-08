package com.hym.appstore.ui.fragment;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hym.appstore.R;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.presenter.AppManagerPresent;
import com.hym.appstore.presenter.contract.AppManagerContract;

import java.util.List;

import butterknife.BindView;
import zlc.season.rxdownload2.entity.DownloadRecord;


public class DownloadingFragment extends ProgressFragment<AppManagerPresent> implements AppManagerContract.AppManagerView {


    @BindView(R.id.home_rv)
    RecyclerView mRecyclerView;

    private BaseQuickAdapter mAdapter;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void init() {
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.shape_question_diveder));
        mRecyclerView.addItemDecoration(dividerItemDecoration);
//        mAdapter = buildAdapter();
        mAdapter.setAnimationEnable(true);
        mAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {
        mAdapter.addData(downloadRecords);
    }


}
