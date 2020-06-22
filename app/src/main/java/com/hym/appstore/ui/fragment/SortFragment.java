package com.hym.appstore.ui.fragment;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.appstore.R;
import com.hym.appstore.bean.SortBean;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.dagger2.component.DaggerSortComponent;
import com.hym.appstore.dagger2.module.SortModule;
import com.hym.appstore.presenter.SortPresenter;
import com.hym.appstore.presenter.contract.SortContract;
import com.hym.appstore.ui.adapter.AppInfoAdapter;
import com.hym.appstore.ui.adapter.SortAdapter;

import java.util.List;

import butterknife.BindView;


public class SortFragment extends ProgressFragment<SortPresenter> implements SortContract.SortView {


    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;

    protected SortAdapter mSortAdapter;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerSortComponent.builder().appComponent(appComponent).sortModule(new SortModule(this)).build().inject(this);
    }

    @Override
    protected void init() {
        mPresenter.getAllSort();
    }

    @Override
    protected void initView() {
        mHomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mHomeRv.addItemDecoration(dividerItemDecoration);
        mSortAdapter = new SortAdapter();

        mHomeRv.setAdapter(mSortAdapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void showResult(List<SortBean> datas) {
        mSortAdapter.addData(datas);
    }
}
